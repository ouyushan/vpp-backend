package de.uol.vpp.action.infrastructure.rabbitmq;

import de.uol.vpp.action.domain.aggregates.ActionRequestAggregate;
import de.uol.vpp.action.domain.exceptions.ActionException;
import de.uol.vpp.action.domain.exceptions.ActionRepositoryException;
import de.uol.vpp.action.domain.repositories.IActionRequestRepository;
import de.uol.vpp.action.domain.valueobjects.ActionRequestIdVO;
import de.uol.vpp.action.infrastructure.algorithm.ActionCatalogInfrastructureService;
import de.uol.vpp.action.infrastructure.rabbitmq.messages.ActionRequestFailedMessage;
import de.uol.vpp.action.infrastructure.rabbitmq.messages.LoadMessage;
import de.uol.vpp.action.infrastructure.rabbitmq.messages.ProductionMessage;
import de.uol.vpp.action.infrastructure.rest.exceptions.LoadRestClientException;
import de.uol.vpp.action.infrastructure.rest.exceptions.MasterdataRestClientException;
import de.uol.vpp.action.infrastructure.rest.exceptions.ProductionRestClientException;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

/**
 * Listener für RabbitMQ Queues, empfängt die erfolgreiche Generierung der Last- und Erzeugungswerte
 */
@Component
@Log4j2
@RequiredArgsConstructor
public class RabbitMQConsumer {

    private final ActionCatalogInfrastructureService actionCatalogInfrastructureService;
    private final IActionRequestRepository actionRequestRepository;
    private final Map<String, Integer> actionRequestIdCounterMap = new HashMap<>();

    @RabbitListener(queues = "${vpp.rabbitmq.queue.load.to.action.failed}")
    public void receivedActionFailedMessage(ActionRequestFailedMessage actionRequestFailedMessage) {
        log.info("操作请求 {} 失败.", actionRequestFailedMessage.getActionRequestId());
        actionCatalogInfrastructureService.actionFailed(actionRequestFailedMessage.getActionRequestId());
    }

    @RabbitListener(queues = "${vpp.rabbitmq.queue.load.to.action}")
    public void receivedLoadMessage(LoadMessage loadMessage) {
        try {
            Optional<ActionRequestAggregate> actionRequest = actionRequestRepository.getActionRequest(new ActionRequestIdVO(loadMessage.getActionRequestId()));
            if (actionRequest.isPresent()) {
                this.incrementAndCheck(loadMessage.getActionRequestId());
            }
        } catch (ActionRepositoryException | ActionException e) {
            log.error(e);
        }
        log.info("收到的负荷预测：操作请求 {},  时间戳 {}", loadMessage.getActionRequestId(), loadMessage.getTimestamp());
    }

    private synchronized void incrementAndCheck(String actionRequestId) {
        if (actionRequestIdCounterMap.getOrDefault(actionRequestId, 0) == 0) {
            actionRequestIdCounterMap.put(actionRequestId, 0);
        }

        actionRequestIdCounterMap.put(actionRequestId, actionRequestIdCounterMap.get(actionRequestId) + 1);

        if (actionRequestIdCounterMap.get(actionRequestId) == 2) {
            actionRequestIdCounterMap.remove(actionRequestId);
            log.info("操作请求 {} 没有收到.", actionRequestId);
            try {
                actionCatalogInfrastructureService.createActionCatalogs(actionRequestId);
            } catch (ActionException | ActionRepositoryException | MasterdataRestClientException | LoadRestClientException | ProductionRestClientException e) {
                log.error("创建操作查询时出错.", e);
                actionCatalogInfrastructureService.actionFailed(actionRequestId);
            }
        } else if (actionRequestIdCounterMap.get(actionRequestId) == 1) {
            log.info("处理操作下一次预测 {} 被接收. 预计第二次预测...", actionRequestId);
            new Thread(() -> {
                log.info("等待操作请求的第二次预测 {} 已经开始.", actionRequestId);
                if (actionRequestIdCounterMap.get(actionRequestId) == 1) {
                    try {
                        TimeUnit.MINUTES.sleep(5);
                    } catch (InterruptedException e) {
                        log.info("等待预测时出错. (操作请求 {})", actionRequestId);
                        actionCatalogInfrastructureService.actionFailed(actionRequestId);
                    } finally {
                        if (actionRequestIdCounterMap.get(actionRequestId) != null) {
                            if (actionRequestIdCounterMap.get(actionRequestId) == 1) {
                                log.info("等待第二个预测时出错. (操作请求 {})", actionRequestId);
                                actionRequestIdCounterMap.remove(actionRequestId);
                            }
                        }
                        log.info("等待预测已结束. (操作请求 {})", actionRequestId);
                    }
                }
            }).start();
        } else {
            log.info("接收预测时出现故障. (操作请求 {})", actionRequestId);
            actionCatalogInfrastructureService.actionFailed(actionRequestId);
        }
    }

    @RabbitListener(queues = "${vpp.rabbitmq.queue.production.to.action}")
    public void receivedProductionMessage(ProductionMessage productionMessage) {
        log.info("收到的发电预测：操作请求 {}, 时间戳 {}", productionMessage.getActionRequestId(), productionMessage.getTimestamp());
        this.incrementAndCheck(productionMessage.getActionRequestId());
    }

}