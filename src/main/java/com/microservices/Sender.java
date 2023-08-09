package com.microservices;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class Sender {
    public static void main(String[] args) throws Exception {
        try(Connection connection = createConnection()) {
            Channel channel = connection.createChannel();
            channel.queueDeclare("My queue",false,false,false,null);

            String message = "Hello world";
            channel.basicPublish("", "My queue", false,null,message.getBytes());
            System.out.println("Message has been sent");
        }
    }
    public static Connection createConnection() throws Exception {
            ConnectionFactory factory = new ConnectionFactory();
            factory.setHost("localhost");
            factory.setPort(5672);
            factory.setUsername("rabbitmq");
            factory.setPassword("rabbitmq");
            factory.setVirtualHost("/");

            return factory.newConnection();
    }
}
