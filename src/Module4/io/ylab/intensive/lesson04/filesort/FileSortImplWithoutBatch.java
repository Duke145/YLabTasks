package Module4.io.ylab.intensive.lesson04.filesort;

import java.io.*;
import java.sql.*;
import javax.sql.DataSource;

public class FileSortImplWithoutBatch implements FileSorter {
    private DataSource dataSource;

    public FileSortImplWithoutBatch(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public File sort(File data) {
        String tempLine = null;
        String writeToDb = "insert into numbers (val) " +
                "values (?)";
        String getSort = "select val from numbers order by val desc";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(writeToDb);
             Statement statementGetResult = connection.createStatement();
             FileReader fr = new FileReader(data);
             BufferedReader br = new BufferedReader(fr);
        ) {
            //очистка базы перед повторным прогоном
            statementGetResult.executeUpdate("delete FROM numbers");
            while ((tempLine = br.readLine()) != null) {
                statement.setLong(1, Long.parseLong(tempLine));
                statement.executeUpdate();
            }
            ResultSet rs = statementGetResult.executeQuery(getSort);
            File sortedFile = new File(data.getName().replace(".txt", "_sorted.txt"));
            FileWriter fw = new FileWriter(sortedFile);
            PrintWriter pw = new PrintWriter(fw);
            while (rs.next()) {
                pw.println(rs.getString(1));
            }
            pw.close();
            fw.close();
            return sortedFile;
        } catch (SQLException | IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
