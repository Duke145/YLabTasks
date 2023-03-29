package Module4.io.ylab.intensive.lesson04.eventsourcing.api;

import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import Module4.io.ylab.intensive.lesson04.RabbitMQUtil;

public class ApiApp {
  public static void main(String[] args) throws Exception {
    //ConnectionFactory connectionFactory = initMQ();
    // Тут пишем создание PersonApi, запуск и демонстрацию работы
    PersonApiImpl personApi = new PersonApiImpl();
    for (int i=0;i<20;i++) {
      personApi.savePerson(145L, "aaa", "bbb", "ccc");
      personApi.savePerson(2L, "aaa2", "bbb2", "ccc2");
      personApi.savePerson(null, "", "", "");
      personApi.savePerson(1231L, "", null, "");
      personApi.savePerson(231L, "", "asd", "2");
      personApi.savePerson(123L, "22", "asd", null);
      personApi.deletePerson(123L);
      System.out.println(personApi.findAll());
      System.out.println(personApi.findPerson(2L));
      System.out.println(personApi.findPerson(1231L));
      System.out.println(personApi.findPerson(231L));
      System.out.println(personApi.findPerson(123L));
      personApi.savePerson(2L, "aaa2", null, "ccc2");
      System.out.println(personApi.findAll());
      personApi.deletePerson(145L);
      personApi.deletePerson(145L);
      personApi.savePerson((long) i,"aaa", "bbb", "ccc");
    }
    for (int i=0;i<20;i++) {
      personApi.deletePerson((long) i);
    }
    personApi.closeConnection();
  }

  private static ConnectionFactory initMQ() throws Exception {
    return RabbitMQUtil.buildConnectionFactory();
  }
}
