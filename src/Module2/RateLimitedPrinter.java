package Module2;

public class RateLimitedPrinter implements RatePrinter {
    private int interval = 0;
    private long lastTimeStart = System.currentTimeMillis();

    public RateLimitedPrinter(int interval) {
        this.interval = interval;
    }

    @Override
    public void print(String message) {
        long currentTimeMillis = System.currentTimeMillis();
        if (currentTimeMillis - this.lastTimeStart > this.interval) {
            System.out.println(message);
            //для отладки
            //System.out.println("Текущее время: " + currentTimeMillis + ", дельта: " + (currentTimeMillis - this.lastTimeStart));
            this.lastTimeStart = currentTimeMillis;
        }
    }
}
