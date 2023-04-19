package Module5.io.ylab.intensive.lesson05.messagefilter;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * Класс для работы с очередями в RabbitMQ
 */
@Component
public class Queue {
    private Channel channel;
    private Connection connection;
    private ConnectionFactory cf;

    @Autowired
    public Queue(ConnectionFactory cf) {
        try {
            this.cf = cf;
            this.connection = cf.newConnection();
            channel = connection.createChannel();
        } catch (Exception e) {
            System.err.println(e);
        }
    }

    public void sendMessage(String queue, String message){
        try {
            channel.basicPublish("", queue, null, message.getBytes(StandardCharsets.UTF_8));
        } catch (Exception e) {
            System.err.println(e);
        }
    }

    public void createExchangeQueue(String queueName, String exchangeName, String exchangeType) {
        try {
            channel.queueDeclare(queueName, false, false, false, null);
            channel.exchangeDeclare(exchangeName, exchangeType);
        } catch (Exception e) {
            System.err.println(e);
        }
    }


    public Connection getConnection() {
        return connection;
    }

    public Channel getChannel() {
        return channel;
    }
}
