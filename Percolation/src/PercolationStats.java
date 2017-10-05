
import edu.princeton.cs.algs4.StdDraw;
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
    private final int[] data;
    
    public PercolationStats(int n, int trials) {
        if (n <= 0 || trials <= 0) throw new IllegalArgumentException();
        this.n = n;
        this.trials = trials;
        this.data = new int[trials];
    }
    
    public double mean() {
        return StdStats.mean(data);
    }
    
    public double stddev() {
        return StdStats.stddev(data);
    }
    
    public double confidenceLo() {
        return mean()-2*stddev();
    }
    
    public double confidenceHi() {
        return mean()+2*stddev();
    }
    
    private void runTrials() {
        for (int i = 0; i < trials; i++) {
            Percolation p = new Percolation(n);
            int j;
            for (j = 0; !p.percolates(); j++) {
                int x = StdRandom.uniform(n) + 1;
                int y = StdRandom.uniform(n) + 1;
                p.open(x, y);
            }
            data[i] = p.numberOfOpenSites();
        }
    }
    
    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);
        int t = Integer.parseInt(args[1]);
        PercolationStats ps = new PercolationStats(n, t);
        ps.runTrials();
        StdOut.println(ps.mean());
        StdOut.println(ps.stddev());
        StdOut.println(ps.confidenceLo());
        StdOut.println(ps.confidenceHi());
    }
    
}
