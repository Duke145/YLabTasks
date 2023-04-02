package Module5.io.ylab.intensive.lesson05.messagefilter;

import com.rabbitmq.client.DeliverCallback;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class MessageFilterApp {
    public static void main(String[] args) throws IOException {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(Config.class);
        applicationContext.start();
        DBCheck dbCheck = applicationContext.getBean(DBCheck.class);
        dbCheck.initConnection();
        dbCheck.fillWordsTable();
        Queue queue = applicationContext.getBean(Queue.class);
        queue.createExchangeQueue("input", "GET", "direct");
        queue.createExchangeQueue("output", "SEND", "direct");
//        for (int i=0;i<10;i++) {
//          queue.sendMessage("input", "Hello " + i + "!");
//        }
        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
            String message = new String(delivery.getBody(), StandardCharsets.UTF_8);
            System.out.println(" [x] Received '" + message + "'");
            String newMessage = dbCheck.checkMessage(message);
            queue.sendMessage("output", newMessage);
            System.out.println(" [x] Sent '" + newMessage + "'");
        };
        queue.getChannel().basicConsume("input", true, deliverCallback, consumerTag -> {
        });

    }
}
