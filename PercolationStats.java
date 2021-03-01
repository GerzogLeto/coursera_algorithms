import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
    private final int n;
    private final int trials;
    private double[] values;

    public PercolationStats(int n, int trials) {
        if (n <= 0 || trials <= 0) throw new IllegalArgumentException("n and trials must be more than 0");
        this.n = n;
        this.trials = trials;
    }

    public double mean() {
        this.values = new double[this.trials];
        for (int i = 0; i < values.length; i++) {
            Percolation perc = new Percolation(this.n);
            while (!(perc.percolates())) {
                perc.open(StdRandom.uniform(1, n + 1), StdRandom.uniform(1, n + 1));
            }
            values[i] = (double) perc.numberOfOpenSites() / (n * n);
        }
        return StdStats.mean(values);
    }

    public double stddev() {
        if (values.length == 1) return Double.NaN;
        return StdStats.stddev(values);
    }

    public double confidenceLo() {
        return StdStats.mean(values) - 1.96 * StdStats.stddev(values) / Math.sqrt(this.trials);
    }

    public double confidenceHi() {
        return StdStats.mean(values) + 1.96 * StdStats.stddev(values) / Math.sqrt(this.trials);
    }

    public static void main(String[] args) {
        PercolationStats percStats = new PercolationStats(Integer.parseInt(args[0]),
                Integer.parseInt(args[1]));
        System.out.println("mean = " + percStats.mean());
        System.out.println("stddev = " + percStats.stddev());
        System.out.println("95% confidence interval = " + "[" +
                percStats.confidenceLo() + ", " + percStats.confidenceHi() + "]");
    }
}
