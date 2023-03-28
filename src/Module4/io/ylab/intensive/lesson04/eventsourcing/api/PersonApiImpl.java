package Module4.io.ylab.intensive.lesson04.eventsourcing.api;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeoutException;

import Module4.io.ylab.intensive.lesson04.DbUtil;
import Module4.io.ylab.intensive.lesson04.RabbitMQUtil;
import Module4.io.ylab.intensive.lesson04.eventsourcing.Person;
import Module4.io.ylab.intensive.lesson04.eventsourcing.Queue;
import com.rabbitmq.client.*;

import javax.sql.DataSource;

/**
 * Тут пишем реализацию
 */
public class PersonApiImpl implements PersonApi {

    private DataSource dataSource;
    private Queue queue;

    public PersonApiImpl() throws SQLException, IOException, TimeoutException {
        this.dataSource = DbUtil.buildDataSource();
        this.queue = new Queue();
        this.queue.createExchangeQueue("queue", "MyQueue", "direct");
    }

    public void closeConnection() throws IOException {
        this.queue.getConnection().close();
    }

    @Override
    public void deletePerson(Long personId) {
        String message = personId + "";
        queue.sendMessage("MyQueue", "delete", message);
        System.out.println(" [x] Sent delete '" + message + "'");
    }

    @Override
    public void savePerson(Long personId, String firstName, String lastName, String middleName) {
        Person person = new Person();
        person.setId(personId);
        person.setName(firstName);
        person.setLastName(lastName);
        person.setMiddleName(middleName);
        String message = person.toString();
        queue.sendMessage("MyQueue", "save", message);
        System.out.println(" [x] Sent save '" + message + "'");
    }

    @Override
    public Person findPerson(Long personId) {
        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement();
             ResultSet rs = statement.executeQuery("select * FROM person WHERE person_id="
                     + personId + "");) {
            while (rs.next()) {
                if (rs.getLong(1) == personId) {
                    Person person = new Person();
                    person.setId(rs.getLong(1));
                    person.setName(rs.getString(2));
                    person.setLastName(rs.getString(3));
                    person.setMiddleName(rs.getString(4));
                    return person;
                }
            }
            return null;
        } catch (SQLException e) {
            return null;
        }
    }

    @Override
    public List<Person> findAll() {
        List<Person> result = new ArrayList<>();
        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement();
             ResultSet rs = statement.executeQuery("select * FROM person");) {
            while (rs.next()) {
                Person person = new Person();
                person.setId(rs.getLong(1));
                person.setName(rs.getString(2));
                person.setLastName(rs.getString(3));
                person.setMiddleName(rs.getString(4));
                result.add(person);
            }
            return result;
        } catch (SQLException e) {
            return result;
        }
    }
}
