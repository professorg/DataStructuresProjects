
import java.util.ArrayList;
import java.util.List;

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

    private final int n;
    private final int[][] blocks;

    public Board(int[][] tiles) {
        this.n = tiles.length;
        this.blocks = deepCopy(tiles);
    }

    public int dimension() {
        return this.n;
    }

    public int hamming() {
        int sum = 0;
        for (int row = 0; row < n; row++) {
            for (int col = 0; col < n; col++) {
                if (blocks[row][col] != 0 && blocks[row][col] != row * n + col + 1) {
                    sum++;
                }
            }
        }
        return sum;
    }

    public int manhattan() {
        int sum = 0;
        for (int row = 0; row < n; row++) {
            for (int col = 0; col < n; col++) {
                if (blocks[row][col] != 0 && blocks[row][col] != row * n + col + 1) {
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
                if (blocks[row][col] != 0 && blocks[row][col] != row * n + col + 1) {
                    return false;
                }
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
        if (this == y) {
            return true;
        }
        if (y == null || y.getClass() != this.getClass()) {
            return false;
        }
        if (((Board) y).n != n) {
            return false;
        }
        Board other = (Board) y;
        for (int row = 0; row < n; row++) {
            for (int col = 0; col < n; col++) {
                if (blocks[row][col] != other.blocks[row][col]) {
                    return false;
                }
            }
        }
        return true;
    }

    private int[][] deepCopy(int[][] tiles) {
        int[][] copy = new int[n][n];
        for (int i = 0; i < n; i++) {
            System.arraycopy(tiles[i], 0, copy[i], 0, n);
        }
        return copy;
    }

    public Iterable<Board> neighbors() {
        List<Board> neighbors = new ArrayList<Board>();
        int[][] clone;
        for (int row = 0; row < n; row++) {
            for (int col = 0; col < n; col++) {
                if (blocks[row][col] == 0) {
                    if (row != 0) {
                        clone = deepCopy(blocks);
                        clone[row][col] = blocks[row - 1][col];
                        clone[row - 1][col] = 0;
                        neighbors.add(new Board(clone));
                    }
                    if (col != 0) {
                        clone = deepCopy(blocks);
                        clone[row][col] = blocks[row][col - 1];
                        clone[row][col - 1] = 0;
                        neighbors.add(new Board(clone));
                    }
                    if (row != n - 1) {
                        clone = deepCopy(blocks);
                        clone[row][col] = blocks[row + 1][col];
                        clone[row + 1][col] = 0;
                        neighbors.add(new Board(clone));
                    }
                    if (col != n - 1) {
                        clone = deepCopy(blocks);
                        clone[row][col] = blocks[row][col + 1];
                        clone[row][col + 1] = 0;
                        neighbors.add(new Board(clone));
                    }
                    return neighbors;
                }
            }
        }
        return neighbors;
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append(n + "\n");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                s.append(String.format("%2d ", blocks[i][j]));
            }
            s.append("\n");
        }
        return s.toString();
    }

}
