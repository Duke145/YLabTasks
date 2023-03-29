package Module4.io.ylab.intensive.lesson04.filesort;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;
import javax.sql.DataSource;
import javax.xml.crypto.Data;

import Module3.Task5.Generator;
import Module4.io.ylab.intensive.lesson04.DbUtil;

public class FileSorterTest {
    public static void main(String[] args) throws SQLException, IOException {
        DataSource dataSource = initDb();
        //File data = new File("data.txt");
        File data = new Generator().generate("data.txt", 500_000);
        FileSorter fileSorter = new FileSortImpl(dataSource);
        FileSorter fileSorterWithoutBatch = new FileSortImplWithoutBatch(dataSource);
        long endTime;
        long startTime = System.currentTimeMillis();
        File res = fileSorter.sort(data);
        endTime = System.currentTimeMillis();
        System.out.println("Длительность с batch-processing: " + new Date(endTime-startTime).getTime() + "ms");
        startTime = System.currentTimeMillis();
        File resWithoutBatch = fileSorterWithoutBatch.sort(data);
        endTime = System.currentTimeMillis();
        System.out.println("Длительность без batch-processing: " + new Date(endTime-startTime).getTime() + "ms");
    }

    public static DataSource initDb() throws SQLException {
        String createSortTable = ""
                + "drop table if exists numbers;"
                + "CREATE TABLE if not exists numbers (\n"
                + "\tval bigint\n"
                + ");";
        DataSource dataSource = DbUtil.buildDataSource();
        DbUtil.applyDdl(createSortTable, dataSource);
        return dataSource;
    }
}
