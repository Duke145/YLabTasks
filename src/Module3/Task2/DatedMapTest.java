package Module3.Task2;

public class DatedMapTest {
    public static void main(String[] args) {
        DatedMap map = new DatedMapImpl();
        map.put("first","asd");
        map.put("second","pasdasd");
        System.out.println(map.get("first"));
        System.out.println(map.get("second"));
        System.out.println(map.get("third"));
        System.out.println(map.containsKey("first"));
        System.out.println(map.containsKey("second"));
        System.out.println(map.containsKey("third"));
        System.out.println(map.keySet());
        System.out.println(map.getKeyLastInsertionDate("first").getTime());
        System.out.println(map.getKeyLastInsertionDate("second"));
        System.out.println(map.getKeyLastInsertionDate("third"));
        map.remove("first");
        map.put("third","111123123");
        System.out.println(map.getKeyLastInsertionDate("first"));
        System.out.println(map.getKeyLastInsertionDate("second"));
        System.out.println(map.getKeyLastInsertionDate("third"));
        map.put("first","asd");
        System.out.println(map.getKeyLastInsertionDate("first").getTime());
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        map.put("first","asdфвфыв");
        System.out.println(map.getKeyLastInsertionDate("first").getTime());

    }
}
