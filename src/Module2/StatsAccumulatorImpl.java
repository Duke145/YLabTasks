package Module2;

public class StatsAccumulatorImpl implements StatsAccumulator {
    private int count = 0;
    private int min = Integer.MAX_VALUE;
    private int max = Integer.MIN_VALUE;
    private double avg = 0;
    private int sum = 0;

    @Override
    public void add(int value) {
        this.count++;
        this.min = Integer.min(this.min, value);
        this.max = Integer.max(this.max, value);
        this.sum += value;
        this.avg = (double) this.sum / this.count;
    }

    @Override
    public int getMin() {
        return this.min;
    }

    @Override
    public int getMax() {
        return this.max;
    }

    @Override
    public int getCount() {
        return this.count;
    }

    @Override
    public Double getAvg() {
        return this.avg;
    }
}
