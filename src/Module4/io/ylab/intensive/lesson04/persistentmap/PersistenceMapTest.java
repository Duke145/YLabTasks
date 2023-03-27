package Module4.io.ylab.intensive.lesson04.persistentmap;

import java.sql.SQLException;
import javax.sql.DataSource;

import Module4.io.ylab.intensive.lesson04.DbUtil;

public class PersistenceMapTest {
  public static void main(String[] args) throws SQLException {
    DataSource dataSource = initDb();
    PersistentMap persistentMap = new PersistentMapImpl(dataSource);
    persistentMap.put("1","asdasd");
    persistentMap.init("first");
    persistentMap.put("2","asdasdasdasd");
    persistentMap.put("3","asdasdasdasd13123");
    persistentMap.put("2","new");
    persistentMap.put("2","new2323");
    System.out.println(persistentMap.containsKey("2")); //true
    System.out.println(persistentMap.containsKey("2asdasd")); //false
    persistentMap.put("1","new2323");
    PersistentMap persistentMap2 = new PersistentMapImpl(dataSource);
    System.out.println(persistentMap2.containsKey("2")); //false

    persistentMap.init("blabla");
    persistentMap.put("42easd","asdasdasdasd");
    persistentMap2.init("second");
    System.out.println(persistentMap2.containsKey("231")); //false
    persistentMap2.put("231","asdasda23123");
    System.out.println(persistentMap2.containsKey("231")); //true
    persistentMap2.put("231","newone");
    System.out.println(persistentMap2.containsKey("3")); //false
    persistentMap2.put("2321","newone2");
    persistentMap2.put("2asda31","newone333");

    System.out.println(persistentMap.get("2"));
    System.out.println(persistentMap.get("3"));
    System.out.println(persistentMap.get("4"));
    System.out.println(persistentMap2.get("231"));
    System.out.println(persistentMap2.get("some"));

    System.out.println(persistentMap.getKeys());
    System.out.println(persistentMap2.getKeys());


    persistentMap.remove("1");
    System.out.println(persistentMap.getKeys());

    persistentMap2.clear();
    System.out.println(persistentMap2.getKeys());
  }
  
  public static DataSource initDb() throws SQLException {
    String createMapTable = "" 
                                + "drop table if exists persistent_map; " 
                                + "CREATE TABLE if not exists persistent_map (\n"
                                + "   map_name varchar,\n"
                                + "   KEY varchar,\n"
                                + "   value varchar\n"
                                + ");";
    DataSource dataSource = DbUtil.buildDataSource();
    DbUtil.applyDdl(createMapTable, dataSource);
    return dataSource;
  }
}
