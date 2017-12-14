
import java.util.Arrays;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author gvandomelen19
 */
public class Board {
    
    private int n;
    private int[][] blocks;
    
    public Board(int[][] blocks) {
        this.n = blocks.length;
        this.blocks = deepCopy(blocks);
    }
    
    public int dimension() {
        return this.n;
    }
    
    public int hamming() {
        int sum = 0;
        for (int row = 0; row < n; row++) {
            for (int col = 0; col < n; col++) {
                if (blocks[row][col] != 0 && blocks[row][col] != row*n + col - 1)
                    sum++;
            }
        }
        return sum;
    }
    
    public int manhattan() {
        int sum = 0;
        for (int row = 0; row < n; row++) {
            for (int col = 0; col < n; col++) {
                if (blocks[row][col] != 0 && blocks[row][col] != row*n + col - 1) {
                    int real = blocks[row][col] - 1;
                    int rrow = real / n;
                    int rcol = real % n;
                    sum += Math.abs(rrow - row) + Math.abs(rcol - col);
                }
            }
        }
        return sum;
    }
    
    public boolean isGoal() {
        for (int row = 0; row < n; row++) {
            for (int col = 0; col < n; col++) {
                if (blocks[row][col] != 0 && blocks[row][col] != row*n + col - 1)
                    return false;
            }
        }
        return true;
    }
    
    public Board twin() {
        int[][] twin = deepCopy(blocks);
        if (twin[0][0] == 0) {
            int b = twin[0][1];
            twin[0][1] = twin[1][1];
            twin[1][1] = b;
        } else {
            int b = twin[0][0];
            if (twin[0][1] == 0) {
                twin[0][0] = twin[1][0];
                twin[1][0] = b;
            } else {
                twin[0][0] = twin[0][1];
                twin[0][1] = b;
            }
        }
        return new Board(twin);
    }
    
    @Override
    public boolean equals(Object y) {
        if (!(y instanceof Board))
            return false;
        Board other = (Board) y;
        for (int row = 0; row < n; row++) {
            for (int col = 0; col < n; col++) {
                if (blocks[row][col] != other.blocks[row][col])
                    return false;
            }
        }
        return true;
    }
   
    private int[][] deepCopy(int[][] blocks) {
        int[][] copy = new int[n][n];
        for (int i = 0; i < n; i++) {
            System.arraycopy(copy[i], 0, this.blocks[i], 0, n);
        }
        return copy;
    }
    
    public Iterable<Board> neighbors() {
        for (int row = 0; row < n; row++) {
            for (int col = 0; col < n; col++) {
                if (blocks[row][col] == 0) {
                    byte flags = (byte) 0b1111_0000;
                    if (row == 0) {
                        flags ^= 0b0001_0000;
                        flags++;
                    }
                    if (row == n-1) {
                        flags ^= 0b0010_0000;
                        flags++;
                    }
                    if (col == 0) {
                        flags ^= 0b0100_0000;
                        flags++;
                    }
                    if (col == n-1) {
                        flags ^= 0b1000_0000;
                        flags++;
                    }
                    Board[] neighbors = new Board[flags & 0b0000_1111];
                    flags &= 0b1111_0000;
                    if ((flags & 0b0001_0000) != 0) {
                        neighbors[flags++ & 0b0000_1111] = new Board();
                    }
                }
            }
        }
    }
    
    @Override
    public String toString() {
        
    }
    
}
