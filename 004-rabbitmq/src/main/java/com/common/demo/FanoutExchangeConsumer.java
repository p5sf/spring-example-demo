package com.common.demo;


import com.common.config.RabbitmqConfig;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author YanZhao
 * @description:direct 消息队列接受消息
 * @date 2022年05月31日 9:30
 */

@Component
public class FanoutExchangeConsumer {


    @RabbitListener(queuesToDeclare = @Queue(RabbitmqConfig.RABBITMQ_DEMO_TOPIC))
    @RabbitHandler
    public void receiveDirect(Map<String, Object> map) {
        System.out.println("RabbitMQConsumer1接收到消息" + map);
    }

    @RabbitListener(queuesToDeclare = @Queue(RabbitmqConfig.HEADERS_EXCHANGE_QUEUE_A))
    public void process(Message message) throws Exception {
        MessageProperties messageProperties = message.getMessageProperties();
        String contentType = messageProperties.getContentType();
        System.out.println("队列[" + RabbitmqConfig.HEADERS_EXCHANGE_QUEUE_A + "]收到消息：" + new String(message.getBody(), contentType));
    }
}
