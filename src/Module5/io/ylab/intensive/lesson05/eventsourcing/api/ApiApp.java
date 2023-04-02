package Module5.io.ylab.intensive.lesson05.eventsourcing.api;

import Module5.io.ylab.intensive.lesson05.eventsourcing.Queue;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ApiApp {
  public static void main(String[] args) throws Exception {
    // Тут пишем создание PersonApi, запуск и демонстрацию работы
    AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(Config.class);
    applicationContext.start();
    PersonApi personApi = applicationContext.getBean(PersonApiImpl.class);

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

    Queue queue = applicationContext.getBean(Queue.class);
    queue.getConnection().close();
  }
}
