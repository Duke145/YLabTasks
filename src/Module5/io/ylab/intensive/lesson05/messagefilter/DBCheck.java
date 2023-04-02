package Module5.io.ylab.intensive.lesson05.messagefilter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Pattern;

@Component
public class DBCheck {
    private DataSource dataSource;
    private File file;

    @Autowired
    public DBCheck(DataSource dataSource, File file) {
        this.dataSource = dataSource;
        this.file = file;
    }

    /**
     * Проверка на существование таблицы и её создание в противном случае
     */
    public void initConnection() {
        try (Connection connection = dataSource.getConnection();
        ) {
            DatabaseMetaData metaData = connection.getMetaData();
            String[] types = {"TABLE"};
            List<String> list = new LinkedList<>();
            ResultSet tables = metaData.getTables(null, null, "words", types);
            if (!tables.next()) {
                System.out.println("Создание таблицы words");
                Statement statement = connection.createStatement();
                String query = ""
                        + "create table words ("
                        + "var varchar"
                        + ")";
                statement.execute(query);
                statement.close();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Очистка и заполнение таблицы words словами из файла
     */
    public void fillWordsTable() {
        String tempLine = null;
        String writeToDb = "insert into words (var) " +
                "values (?)";
        String clearTable = "delete from words";
        try (FileReader fr = new FileReader(file);
             BufferedReader br = new BufferedReader(fr);
             Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement();
             PreparedStatement preparedStatement =
                     connection.prepareStatement(writeToDb);
        ) {
            statement.execute(clearTable);
            while ((tempLine = br.readLine()) != null) {
                preparedStatement.setString(1, tempLine);
                preparedStatement.addBatch();
            }
            preparedStatement.executeBatch();
        } catch (IOException | SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Проверка message на наличие слов из таблицы words
     *
     * @param message Сообщение из очереди
     * @return Отцензурированное сообщение
     */
    public String checkMessage(String message) {
        String getWordFromTable = "select var from words where var=?";
        StringBuilder newMessage = new StringBuilder(message);
        try (
                Connection connection = dataSource.getConnection();
                PreparedStatement preparedStatement =
                        connection.prepareStatement(getWordFromTable);
        ) {
            List<String> list = new ArrayList<>(List.of(message.split("(\s|\\.|,|;|\\?|!|\n|\t|\b|\r)")));
            for (String elem : list) {
                StringBuilder newElem;
                preparedStatement.setString(1, elem.toLowerCase());
                ResultSet rs = preparedStatement.executeQuery();
                while (rs.next()) {
                    newElem = new StringBuilder(elem);
                    for (int i = 1; i < elem.length() - 1; i++) {
                        newElem.replace(i, i + 1, "*");
                    }
                    newMessage = new StringBuilder(Pattern.compile(elem).matcher(newMessage).replaceFirst(newElem.toString()));
                }
            }
            return newMessage.toString();
        } catch (SQLException e) {
            e.printStackTrace();
            return message;
        }
    }
}
