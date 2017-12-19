package com.mq.test;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import org.springframework.amqp.core.ExchangeTypes;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * 生产者类
 * @author 贾新轩
 * @time 17-12-18
 */
public class Produce{

    private final static String QUEUE_NAME = "hello";

    public static void main(String[] argv) throws Exception {
//        new Produce().DirectExChange("direct");
        new Produce().FanoutExChange("fanout");
//        new Produce().TopicExChange("topic");
    }

    /**
     * 直连交换机发送消息
     * @param message
     * @throws IOException
     * @throws TimeoutException
     */
    public void DirectExChange(String message) throws IOException, TimeoutException {
        // 创建连接工厂
        ConnectionFactory factory = new ConnectionFactory();
        //设置RabbitMQ地址
        factory.setHost("localhost");
        //创建一个新的连接
        Connection connection = factory.newConnection();
        //创建一个信道
        Channel channel = connection.createChannel();
        //声明一个直连交换机
        channel.exchangeDeclare("DIRECT_EXCHANGE", ExchangeTypes.DIRECT,true);
        //声明一个队列 -- 在RabbitMQ中，队列声明是幂等性的（一个幂等操作的特点是其任意多次执行所产生的影响均与一次执行的影响相同），
        // 也就是说，如果不存在，就创建，如果存在，不会对已经存在的队列产生任何影响。
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
        //交换机和队列key通过路由规则绑定
        channel.queueBind(QUEUE_NAME,"DIRECT_EXCHANGE","test");
        //发送消息交换机中
        channel.basicPublish("DIRECT_EXCHANGE", "test", null, message.getBytes("UTF-8"));
        System.out.println("P [x] Sent '" + message + "'");
        //关闭频道和连接
        channel.close();
        connection.close();
    }

    /**
     * 扇形交换机
     * @param message
     * @throws IOException
     * @throws TimeoutException
     */
    public void FanoutExChange(String message) throws IOException, TimeoutException {
        // 创建连接工厂
        ConnectionFactory factory = new ConnectionFactory();
        //设置RabbitMQ地址
        factory.setHost("localhost");
        //创建一个新的连接
        Connection connection = factory.newConnection();
        //创建一个信道
        Channel channel = connection.createChannel();
        //声明一个扇形交换机
        channel.exchangeDeclare("FANOUT_EXCHANGE", ExchangeTypes.FANOUT,true);
        //发送消息交换机中
        channel.basicPublish("FANOUT_EXCHANGE", "", null, message.getBytes("UTF-8"));
        System.out.println("P [x] Sent '" + message + "'");
        //关闭频道和连接
        channel.close();
        connection.close();
    }

    /**
     * 话题交换机
     * @param message
     * @throws IOException
     * @throws TimeoutException
     */
    public void TopicExChange(String message) throws IOException, TimeoutException {
        // 创建连接工厂
        ConnectionFactory factory = new ConnectionFactory();
        //设置RabbitMQ地址
        factory.setHost("localhost");
        //创建一个新的连接
        Connection connection = factory.newConnection();
        //创建一个信道
        Channel channel = connection.createChannel();
        //声明一个直连交换机
        channel.exchangeDeclare("TOPIC_EXCHANGE", ExchangeTypes.TOPIC,true);
        //声明一个队列 -- 在RabbitMQ中，队列声明是幂等性的（一个幂等操作的特点是其任意多次执行所产生的影响均与一次执行的影响相同），
        // 也就是说，如果不存在，就创建，如果存在，不会对已经存在的队列产生任何影响。
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
        //交换机和队列key通过路由规则绑定
        channel.queueBind(QUEUE_NAME,"TOPIC_EXCHANGE","test");
        //发送消息交换机中
        channel.basicPublish("TOPIC_EXCHANGE", "test", null, message.getBytes("UTF-8"));
        System.out.println("P [x] Sent '" + message + "'");
        //关闭频道和连接
        channel.close();
        connection.close();
    }
}
