
import edu.princeton.cs.algs4.StdRandom;

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
    
    private int n;
    private int sum;
    private int trials;
    
    public PercolationStats(int n, int trials) {
        if (n <= 0 || trials <= 0) throw new IllegalArgumentException();
        this.n = n;
        this.sum = 0;
        this.trials = trials;
    }
    
    public double mean() {
        
    }
    
    public double stddev() {
        
    }
    
    public double confidenceLo() {
        
    }
    
    public double confidenceHi() {
        
    }
    
    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);
        int t = Integer.parseInt(args[1]);
        PercolationStats = new PercolationStats();
        for (int i = 0; i < t; i++) {
            Percolation p = new Percolation(n);
            int j;
            for (j = 0; !p.percolates(); j++) {
                int x = StdRandom.uniform(n) + 1;
                int y = StdRandom.uniform(n) + 1;
                while (!p.isOpen(x, y)) {
                    x = StdRandom.uniform(n) + 1;
                    y = StdRandom.uniform(n) + 1;
                }
                p.open(x, y);
            }
            sum += j;
        }
    }
    
}
