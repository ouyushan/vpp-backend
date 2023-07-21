package de.uol.vpp.production.infrastructure.rabbitmq;

import de.uol.vpp.production.infrastructure.rabbitmq.messages.ActionRequestMessage;
import de.uol.vpp.production.infrastructure.scheduler.ProductionScheduler;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * RabbitMQ Consumer
 * Hört die Queue zwischen Maßnahmen- und Erzeugungs-Service ab und nimmt Nachrichten entgegen,
 * wenn eine Maßnahmenabfrage gestartet ist
 */
@Component
@Log4j2
@RequiredArgsConstructor
public class RabbitMQConsumer {

    private final ProductionScheduler productionScheduler;

    @RabbitListener(queues = "${vpp.rabbitmq.queue.action.to.production}")
    public void receivedActionRequest(ActionRequestMessage message) {
        log.info("接收操作请求: 操作请求 {}, 虚拟电厂 {}", message.getActionRequestId(), message.getVppId());
        log.info("发电量预测的请求已开始...");
        productionScheduler.createProduction(message);
    }


}