/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import edu.princeton.cs.algs4.WeightedQuickUnionUF;

/**
 *
 * @author gvandomelen19
 */
public class Percolation {

    private final WeightedQuickUnionUF uf;
    private final WeightedQuickUnionUF ufFull;
    private final boolean[] open;
    private int openSites;
    private final int n;
    private final int virtualTop;
    private final int virtualBot;
    
    public Percolation(int n) {
        if (n <= 0) throw new IllegalArgumentException();
        uf = new WeightedQuickUnionUF(n * n + 2);   // 0 - n*n-1 -- sites
        virtualTop = n*n;                           // n*n       -- virtual top
        virtualBot = n*n + 1;                       // n*n + 1   -- virtual bottom
        ufFull = new WeightedQuickUnionUF(n * n + 1);
        open = new boolean[n*n+2];
        open[virtualTop] = true;
        open[virtualBot] = true;
        this.n = n;
        openSites = 0;
    }

    public void open(int row, int col) {
        row--;
        col--;
        if (row < 0 || col < 0 || row >= n || col >= n) throw new IllegalArgumentException();
        if (!open[to1D(row, col)]) {
            openSites++;
            open[to1D(row,col)] = true;
            unionNear(row, col);
        }
    }
    
    private void unionNear(int row, int col) {
        int id = to1D(row, col);
        int up = to1D(row-1, col);
        int dn = to1D(row+1, col);
        int lt = to1D(row, col-1);
        int rt = to1D(row, col+1);
        if (up >= 0 && open[up] && !uf.connected(id, up)) {
            uf.union(id, up);
            ufFull.union(id, up);
        }
        if (dn >= 0 && open[dn] && !uf.connected(id, dn)) {
            uf.union(id, dn);
            if (dn != virtualBot) ufFull.union(id, dn);
        }
        if (lt >= 0 && open[lt] && !uf.connected(id, lt)) {
            uf.union(id, lt);
            ufFull.union(id, lt);
        }
        if (rt >= 0 && open[rt] && !uf.connected(id, rt)) {
            uf.union(id, rt);
            ufFull.union(id, rt);
        }
    }
    
    private int to1D(int row, int col) {
        if (row == -1) return virtualTop;
        if (row == n)  return virtualBot;
        if (row < 0 || col < 0 || row >= n || col >= n) return -1;
        return col + row*n;
    }

    public boolean isOpen(int row, int col) {
        row--;
        col--;
        if (row < 0 || col < 0 || row >= n || col >= n) throw new IllegalArgumentException();
        return open[to1D(row, col)];
    }

    public boolean isFull(int row, int col) {
        row--;
        col--;
        if (row < 0 || col < 0 || row >= n || col >= n) throw new IllegalArgumentException();
        return open[to1D(row, col)] && ufFull.connected(to1D(row, col), virtualTop);
    }

    public int numberOfOpenSites() {
        return openSites;
    }

    public boolean percolates() {
        return uf.connected(virtualTop, virtualBot);
    }

    public static void main(String[] args) {

    }

}
