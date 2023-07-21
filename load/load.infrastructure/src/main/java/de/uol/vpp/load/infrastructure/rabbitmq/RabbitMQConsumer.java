package de.uol.vpp.load.infrastructure.rabbitmq;

import de.uol.vpp.load.infrastructure.rabbitmq.messages.ActionRequestMessage;
import de.uol.vpp.load.infrastructure.scheduler.LoadScheduler;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * Rabbit MQ Consumer
 * Hört die Queue vpp.rabbitmq.queue.action.to.load und erhält Nachrichten, wenn
 * eine neue Maßnahmenabfrage gestartet ist.
 */
@Component
@Log4j2
@RequiredArgsConstructor
public class RabbitMQConsumer {

    private final LoadScheduler loadScheduler;

    /**
     * Startet die Lastgenerierung, wenn neue Message erhalten ist
     *
     * @param message Benachrichtigung einer neuen Maßnahmenabfrage
     */
    @RabbitListener(queues = "${vpp.rabbitmq.queue.action.to.load}")
    public void receivedActionRequest(ActionRequestMessage message) {
        log.info("已接收操作请求：操作请求 {}, 虚拟电厂 {}", message.getActionRequestId(), message.getVppId());
        log.info("开始创建负荷.");
        loadScheduler.createLoad(message.getActionRequestId(), message.getVppId());
    }


}