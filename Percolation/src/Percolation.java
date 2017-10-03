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

    private WeightedQuickUnionUF uf;
    private boolean[] open;
    private int openSites;
    private final int n;
    private final int VIRTUAL_TOP;
    private final int VIRTUAL_BOT;
    
    public Percolation(int n) {
        if (n <= 0) throw new IllegalArgumentException();
        uf = new WeightedQuickUnionUF(n * n + 2);   // 0 - n*n-1 -- sites
        VIRTUAL_TOP = n*n;                          // n*n       -- virtual top
        VIRTUAL_BOT = n*n + 1;                      // n*n + 1   -- virtual bottom
        open = new boolean[n*n];
        this.n = n;
        openSites = 0;
        initUnions();
    }
    
    private void initUnions() {
        for (int i = 0; i < n; i++) {
            uf.union(i, VIRTUAL_TOP);
            uf.union(VIRTUAL_BOT-1-i, VIRTUAL_BOT);
        }
    }

    public void open(int row, int col) { row--;col--;
        if (row < 0 || col < 0) throw new IllegalArgumentException();
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
        if (up >= 0) uf.union(id, up);
        if (dn >= 0) uf.union(id, dn);
        if (lt >= 0) uf.union(id, lt);
        if (rt >= 0) uf.union(id, rt);
    }
    
    private int to1D(int row, int col) {
        if (row < 0 || col < 0) return -1;
        return col + row*n;
    }

    public boolean isOpen(int row, int col) { row--;col--;
        if (row < 0 || col < 0) throw new IllegalArgumentException();
        return open[to1D(row, col)];
    }

    public boolean isFull(int row, int col) { row--;col--;
        if (row < 0 || col < 0) throw new IllegalArgumentException();
        return uf.connected(to1D(row, col), VIRTUAL_TOP);
    }

    public int numberOfOpenSites() {
        return openSites;
    }

    public boolean percolates() {
        return uf.connected(VIRTUAL_TOP, VIRTUAL_BOT);
    }

    public static void main(String[] args) {

    }

}
