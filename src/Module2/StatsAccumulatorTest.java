package Module2;

public class StatsAccumulatorTest {
    public static void main(String[] args) {
        StatsAccumulator s = new StatsAccumulatorImpl();
        System.out.println(s.getCount());
        System.out.println(s.getMin());
        System.out.println(s.getMax());
        System.out.println(s.getAvg());
        s.add(1);
        s.add(2);
        System.out.println(s.getAvg());// 1.5
        s.add(0);
        System.out.println(s.getMin()); // 0
        s.add(3);
        s.add(8);
        System.out.println(s.getMax()); //8
        System.out.println(s.getCount()); //5
        s.add(-15);
        System.out.println(s.getCount()); //6
        System.out.println(s.getMin()); //-15
        System.out.println(s.getMax()); //8
        System.out.println(s.getAvg()); // -0.16(6)

    }
}
