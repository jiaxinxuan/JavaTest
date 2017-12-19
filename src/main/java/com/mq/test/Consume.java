package com.mq.test;

import com.rabbitmq.client.*;
import org.springframework.amqp.core.ExchangeTypes;

import java.io.IOException;
import java.util.concurrent.atomic.AtomicReference;

/**
 * 消费者类
 * @author 贾新轩
 * @time 17-12-18
 */
public class Consume{


    private final static String QUEUE_NAME = "hello";

    public static void main(String[] argv) throws Exception {
        // 创建连接工厂
        ConnectionFactory factory = new ConnectionFactory();
        //设置RabbitMQ地址
        factory.setHost("localhost");
        //创建一个新的连接
        Connection connection = factory.newConnection();
        //创建一个频道
        AtomicReference<Channel> channel = new AtomicReference<>(connection.createChannel());
        channel.get().exchangeDeclare("FANOUT_EXCHANGE", ExchangeTypes.FANOUT,true);
        //声明要关注的队列 -- 在RabbitMQ中，队列声明是幂等性的（一个幂等操作的特点是其任意多次执行所产生的影响均与一次执行的影响相同），
        // 也就是说，如果不存在，就创建，如果存在，不会对已经存在的队列产生任何影响。
        String queueName=channel.get().queueDeclare().getQueue();
        channel.get().queueBind(queueName,"FANOUT_EXCHANGE","");
        System.out.println("C [*] Waiting for messages. To exit press CTRL+C");
        //DefaultConsumer类实现了Consumer接口，通过传入一个信道，告诉服务器我们需要那个信道的消息，如果信道中有消息，就会执行回调函数handleDelivery
        Consumer consumer = new DefaultConsumer(channel.get()) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                String message = new String(body, "UTF-8");
                System.out.println("C [x] Received '" + message + "'");
            }
        };
        //自动回复队列应答 -- RabbitMQ中的消息确认机制，后面章节会详细讲解
        channel.get().basicConsume(queueName, true, consumer);
    }
}
