package Module5.io.ylab.intensive.lesson05.eventsourcing;

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

    public void sendMessage(String exchange, String key, String message){
        try {
            channel.basicPublish(exchange, key, null, message.getBytes(StandardCharsets.UTF_8));
        } catch (Exception e) {
            System.err.println(e);
        }
    }

    public void createExchangeQueue(String queueName, String exchangeName, String exchangeType) {
        try {
            channel.queueDeclare(queueName, false, false, false, null);
            channel.exchangeDeclare(exchangeName, exchangeType);
            channel.queueBind(queueName, exchangeName, "save");
            channel.queueBind(queueName, exchangeName, "delete");
        } catch (Exception e) {
            System.err.println(e);
        }
    }

    public void createSimpleExchangeQueue(String queueName) {
        try {
            channel.queueDeclare(queueName, false, false, false, null);
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
