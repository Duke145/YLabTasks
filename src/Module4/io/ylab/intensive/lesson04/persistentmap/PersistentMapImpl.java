package Module4.io.ylab.intensive.lesson04.persistentmap;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;

/**
 * Класс, методы которого надо реализовать
 */
public class PersistentMapImpl implements PersistentMap {

    private DataSource dataSource;

    public PersistentMapImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    private String name;

    @Override
    public void init(String name) {
        if (this.name == null) {
            this.name = name;
        }
    }

    @Override
    public boolean containsKey(String key) throws SQLException {
        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement();
             ResultSet rs = statement.executeQuery("select map_name,KEY FROM persistent_map WHERE map_name='"
                     + name + "' and key='" + key + "'");) {
            if (name != null) {
                while (rs.next()) {
                    if (rs.getString(2).equals(key)) {
                        return true;
                    }
                }
            }
            return false;
        } catch (SQLException e) {
            return false;
        }
    }

    @Override
    public List<String> getKeys() throws SQLException {
        List<String> result = new ArrayList<>();
        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement();
             ResultSet rs = statement.executeQuery("select key FROM persistent_map WHERE map_name='"
                     + name + "'");) {
            if (name != null) {
                while (rs.next()) {
                    result.add(rs.getString(1));
                }
            }
            return result;
        } catch (SQLException e) {
            return result;
        }
    }

    @Override
    public String get(String key) throws SQLException {
        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement();
             ResultSet rs = statement.executeQuery("select key, value FROM persistent_map WHERE map_name='"
                     + name + "' and key='" + key + "'");) {
            if (name != null) {
                while (rs.next()) {
                    if (rs.getString(1).equals(key)) {
                        return rs.getString(2);
                    }
                }
            }
            return null;
        } catch (SQLException e) {
            return null;
        }
    }

    @Override
    public void remove(String key) throws SQLException {
        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement();
            ) {
            statement.executeUpdate("delete FROM persistent_map WHERE map_name='"
                    + name + "' and key='" + key + "'");
        }
    }

    @Override
    public void put(String key, String value) throws SQLException {
        String sqlCheck = "select map_name,KEY FROM persistent_map WHERE map_name='"
                + name + "' and key='" + key + "'";
        String sqlInsert = "insert into persistent_map(map_name,key,value) values(?,?,?)";
        String sqlDelete = "delete FROM persistent_map WHERE map_name='"
                + name + "' and key='" + key + "'";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sqlInsert);
             Statement statementCheck = connection.createStatement();
             ResultSet rs = statementCheck.executeQuery(sqlCheck)
        ) {
            boolean condition = false;
            if (name != null) {
                while (rs.next()) {
                    if (rs.getString(2).equals(key)) {
                        statementCheck.executeUpdate(sqlDelete);
                        condition = true;
                        break;
                    }
                }
                if (!condition) {
                    statement.setString(1, name);
                    statement.setString(2, key);
                    statement.setString(3, value);
                    statement.executeUpdate();
                }
            }
        }
    }

    @Override
    public void clear() throws SQLException {
        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement();
        ) {
            statement.executeUpdate("delete FROM persistent_map WHERE map_name='"
                    + name + "'");
        }
    }
}
