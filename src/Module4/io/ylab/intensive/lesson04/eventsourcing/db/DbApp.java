package Module4.io.ylab.intensive.lesson04.eventsourcing.db;


import java.nio.charset.StandardCharsets;
import java.sql.*;
import java.util.List;

import Module4.io.ylab.intensive.lesson04.eventsourcing.Queue;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import Module4.io.ylab.intensive.lesson04.DbUtil;
import Module4.io.ylab.intensive.lesson04.RabbitMQUtil;
import com.rabbitmq.client.DeliverCallback;
import org.w3c.dom.ls.LSOutput;

import javax.sql.DataSource;

public class DbApp {
    public static void main(String[] args) throws Exception {
        DataSource dataSource = initDb();
        //ConnectionFactory connectionFactory = initMQ();

        // тут пишем создание и запуск приложения работы с БД
        Queue queue = new Queue();
        queue.createSimpleExchangeQueue("queue");
        System.out.println(" [*] Waiting for messages. To exit press CTRL+C");
        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
            String message = new String(delivery.getBody(), StandardCharsets.UTF_8);
            List<String> list = new java.util.ArrayList<>(List.of(message.split(";")));
            System.out.println(" [x] Received " + delivery.getEnvelope().getRoutingKey() + " '" + message + "'");
            if (delivery.getEnvelope().getRoutingKey().equals("save")) {
                if (list.size() < 4) {
                    while (list.size() < 4) {
                        list.add("");
                    }
                }
                save(dataSource, list, message);
            } else if (delivery.getEnvelope().getRoutingKey().equals("delete")) {
                delete(dataSource, list.get(0), message);
            }
        };
        queue.getChannel().basicConsume("queue", true, deliverCallback, consumerTag -> {
        });

    }

    /**
     * Метод реализует запрос save на добавление строки в БД
     */
    private static void save(DataSource dataSource, List<String> list, String message) {
        if (list.get(0).matches("\\d+")) {
            String sqlCheck = "select person_id FROM person WHERE person_id="
                    + list.get(0) + "";
            String sqlInsert = "insert into person(person_id,first_name,last_name,middle_name) values(?,?,?,?)";
            String sqlUpdate = "update person set first_name=?,last_name=?,middle_name=? WHERE person_id=?";
            try (java.sql.Connection SQLConnection = dataSource.getConnection();
                 PreparedStatement statement = SQLConnection.prepareStatement(sqlInsert);
                 PreparedStatement statementUpdate = SQLConnection.prepareStatement(sqlUpdate);
                 Statement statementCheck = SQLConnection.createStatement();
                 ResultSet rs = statementCheck.executeQuery(sqlCheck)
            ) {
                boolean condition = false;
                while (rs.next()) {
                    if (rs.getLong(1) == Long.parseLong(list.get(0))) {

                        statementUpdate.setLong(4, Long.parseLong(list.get(0)));

                        if (!list.get(1).isEmpty() && !list.get(1).equals("null")) {
                            statementUpdate.setString(1, list.get(1));
                        } else {
                            statementUpdate.setNull(1, Types.VARCHAR);
                        }
                        if (!list.get(2).isEmpty() && !list.get(2).equals("null")) {
                            statementUpdate.setString(2, list.get(2));
                        } else {
                            statementUpdate.setNull(2, Types.VARCHAR);
                        }
                        if (!list.get(3).isEmpty() && !list.get(3).equals("null")) {
                            statementUpdate.setString(3, list.get(3));
                        } else {
                            statementUpdate.setNull(3, Types.VARCHAR);
                        }
                        statementUpdate.executeUpdate();
                        System.out.println("Данные по запросу save " + message + " были обновлены");
                        condition = true;
                        break;
                    }
                }
                if (!condition) {

                    statement.setLong(1, Long.parseLong(list.get(0)));

                    if (!list.get(1).isEmpty() && !list.get(1).equals("null")) {
                        statement.setString(2, list.get(1));
                    } else {
                        statement.setNull(2, Types.VARCHAR);
                    }
                    if (!list.get(2).isEmpty() && !list.get(2).equals("null")) {
                        statement.setString(3, list.get(2));
                    } else {
                        statement.setNull(3, Types.VARCHAR);
                    }
                    if (!list.get(3).isEmpty() && !list.get(3).equals("null")) {
                        statement.setString(4, list.get(3));
                    } else {
                        statement.setNull(4, Types.VARCHAR);
                    }
                    statement.executeUpdate();
                    System.out.println("Данные по запросу save " + message + " были добавлены");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("id в запросе save " + message + " не числовой!");
        }
    }

    /**
     * Метод реализует запрос delete на удаление строки в БД
     */
    private static void delete(DataSource dataSource, String id, String message) {

        String sqlCheck = "select person_id FROM person WHERE person_id="
                + Long.parseLong(id);
        String sqlDelete = "delete FROM person WHERE person_id="
                + Long.parseLong(id);
        try (java.sql.Connection SQLConnection = dataSource.getConnection();
             Statement statementCheck = SQLConnection.createStatement();
             ResultSet rs = statementCheck.executeQuery(sqlCheck)
        ) {
            while (rs.next()) {
                if (rs.getLong(1) == Long.parseLong(id)) {
                    statementCheck.executeUpdate(sqlDelete);
                    System.out.println("данные по запросу delete " + message + " удалены");
                    return;
                }
            }
            System.out.println("была попытка удаления запросу delete " + message + " , но данные не найдены: id = " + id);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    private static ConnectionFactory initMQ() throws Exception {
        return RabbitMQUtil.buildConnectionFactory();
    }

    private static DataSource initDb() throws SQLException {
        String ddl = ""
                + "drop table if exists person;"
                + "create table if not exists person ("
                + "person_id bigint primary key,"
                + "first_name varchar,"
                + "last_name varchar,"
                + "middle_name varchar"
                + ")";
        DataSource dataSource = DbUtil.buildDataSource();
        DbUtil.applyDdl(ddl, dataSource);
        return dataSource;
    }
}
