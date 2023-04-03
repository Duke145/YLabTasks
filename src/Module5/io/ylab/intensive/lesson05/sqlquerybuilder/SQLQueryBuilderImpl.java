package Module5.io.ylab.intensive.lesson05.sqlquerybuilder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.*;
import java.util.LinkedList;
import java.util.List;

@Component
public class SQLQueryBuilderImpl implements SQLQueryBuilder {

    private DataSource dataSource;

    @Autowired
    public SQLQueryBuilderImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public String queryForTable(String tableName) throws SQLException {
        try (Connection connection = dataSource.getConnection();
        ) {
            DatabaseMetaData metaData = connection.getMetaData();
            List<String> list = new LinkedList<>();
            ResultSet columns = metaData.getColumns(null, null, tableName, null);
            while (columns.next()) {
                list.add(columns.getString("COLUMN_NAME"));
            }
            if (list.size() == 0) {
                return null;
            } else {
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("SELECT ");
                for (String elem : list) {
                    stringBuilder.append(elem).append(", ");
                }
                stringBuilder.setLength(stringBuilder.length() - 2);
                stringBuilder.append(" FROM ").append(tableName);
                return stringBuilder.toString();
            }
        } catch (SQLException e) {
            return null;
        }
    }

    @Override
    public List<String> getTables() throws SQLException {
        try (Connection connection = dataSource.getConnection();
        ) {
            DatabaseMetaData metaData = connection.getMetaData();
            String[] types = {"TABLE"};
            List<String> list = new LinkedList<>();
            //ResultSet tables = metaData.getTables(null, null, "%", types);
            ResultSet tables = metaData.getTables(null, null, null, null);
            while (tables.next()) {
                list.add(tables.getString("TABLE_NAME"));
            }
            return list;
        } catch (SQLException e) {
            return null;
        }
    }
}
