package Module5.io.ylab.intensive.lesson05.eventsourcing.api;

import Module5.io.ylab.intensive.lesson05.eventsourcing.Person;
import Module5.io.ylab.intensive.lesson05.eventsourcing.Queue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeoutException;

@Component
public class PersonApiImpl implements PersonApi {

    private DataSource dataSource;
    private Queue queue;

    @Autowired
    public PersonApiImpl(Queue queue, DataSource dataSource) throws SQLException, IOException, TimeoutException {
        this.dataSource = dataSource;
        this.queue = queue;
        this.queue.createExchangeQueue("queue", "MyQueue", "direct");
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
