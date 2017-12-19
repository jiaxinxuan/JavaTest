package com.mq.test;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author 贾新轩
 * @time 17-12-18
 */
public class MqTest {

    private final static String QUEUE_NAME = "hello";

    public static void main(String[] args) throws IOException, TimeoutException {
        sendMsg();
        receiveMsg();
    }

    public static void sendMsg() throws IOException, TimeoutException {
        //创建连接工厂
        ConnectionFactory connectionFactory=new ConnectionFactory();
        connectionFactory.setHost("127.0.0.1");
        //获取连接
        Connection connnection=connectionFactory.newConnection();
        //创建频道
        Channel channel=connnection.createChannel();
        //声明队列
        channel.queueDeclare(QUEUE_NAME,false,false,false,null);
        String message = "Hello World!";
        //发送消息到队列中
        channel.basicPublish("", QUEUE_NAME, null, message.getBytes("UTF-8"));
        System.out.println("P [x] Sent '" + message + "'");
        channel.close();
        connnection.close();
    }

    public static void receiveMsg() throws IOException, TimeoutException {
        //创建连接工厂
        ConnectionFactory connectionFactory=new ConnectionFactory();
        connectionFactory.setHost("localhost");
        //获取连接
        Connection connnection=connectionFactory.newConnection();
        //创建频道
        Channel channel=connnection.createChannel();
        //声明队列
        channel.queueDeclare(QUEUE_NAME,false,false,false,null);
        Consumer consumer=new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                String message = new String(body, "UTF-8");
                System.out.println("C [x] Received '" + message + "'");
            }
        };
        //自动回复队列应答 -- RabbitMQ中的消息确认机制，后面章节会详细讲解
        channel.basicConsume(QUEUE_NAME, true, consumer);
    }
}
