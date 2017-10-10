
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author gvandomelen19
 */
public class PercolationStats {
    
    private final int n;
    private final int trials;
    private final double[] data;
    private final double mean;
    private final double stddev;
    private final double confidenceLo;
    private final double confidenceHi;
    
    public PercolationStats(int n, int trials) {
        if (n <= 0 || trials <= 0) throw new IllegalArgumentException();
        this.n = n;
        this.trials = trials;
        this.data = new double[trials];
        runTrials();
        this.mean = StdStats.mean(data);
        this.stddev = StdStats.stddev(data);
        this.confidenceLo = mean - 1.96 * stddev / Math.sqrt(trials);
        this.confidenceHi = mean + 1.96 * stddev / Math.sqrt(trials);
    }
    
    public double mean() {
        return mean;
    }
    
    public double stddev() {
        return stddev;
    }
    
    public double confidenceLo() {
        return confidenceLo;
    }
    
    public double confidenceHi() {
        return confidenceHi;
    }
    
    private void runTrials() {
        for (int i = 0; i < trials; i++) {
            Percolation p = new Percolation(n);
            int j = 0;
            while (!p.percolates()) {
                int x = StdRandom.uniform(n) + 1;
                int y = StdRandom.uniform(n) + 1;
                if (!p.isOpen(x, y)) p.open(x, y);
                j++;
            }
            data[i] = p.numberOfOpenSites() / (double) (n*n);
        }
    }
    
    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);
        int trials = Integer.parseInt(args[1]);
        PercolationStats ps = new PercolationStats(n, trials);
        StdOut.println(ps.mean());
        StdOut.println(ps.stddev());
        StdOut.println(ps.confidenceLo());
        StdOut.println(ps.confidenceHi());
    }
    
}
