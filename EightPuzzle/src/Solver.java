
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.StdOut;
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
public class Solver {

    private final Board board;
    private boolean solvable;
    private final List<Board> solution;

    public Solver(Board initial) {
        if (initial == null) {
            throw new IllegalArgumentException();
        }
        board = initial;
        solution = new ArrayList<Board>();
        solve();
    }

    private void solve() {
        MinPQ<Node> pq = new MinPQ<Node>();
        MinPQ<Node> twinpq = new MinPQ<Node>();
        pq.insert(new Node(board, null));
        twinpq.insert(new Node(board.twin(), null));
        Node n = null;
        Node twinn = null;
        while ((n == null || !n.board.isGoal())
                && (twinn == null || !twinn.board.isGoal())) {
            n = pq.delMin();
            twinn = twinpq.delMin();
            for (Board b : n.board.neighbors()) {
                if (n.last == null || !b.equals(n.last.board)) {
                    pq.insert(new Node(b, n));
                }
            }
            for (Board b : twinn.board.neighbors()) {
                if (twinn.last == null || !b.equals(twinn.last.board)) {
                    twinpq.insert(new Node(b, twinn));
                }
            }
        }
        if (twinn.board.isGoal()) {
            solvable = false;
            return;
        }
        solvable = true;
        solution.add(0, n.board);
        while (n.last != null) {
            n = n.last;
            solution.add(0, n.board);
        }
    }

    public boolean isSolvable() {
        return solvable;
    }

    public int moves() {
        return solution.size() - 1;
    }

    public Iterable<Board> solution() {
        if (solution.size() < 1) {
            return null;
        }
        return solution;
    }

    public static void main(String[] args) {

        // create initial board from file
        In in = new In(args[0]);
        int n = in.readInt();
        int[][] blocks = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                blocks[i][j] = in.readInt();
            }
        }
        Board initial = new Board(blocks);

        // solve the puzzle
        Solver solver = new Solver(initial);

        // print solution to standard output
        if (!solver.isSolvable()) {
            StdOut.println("No solution possible");
        } else {
            StdOut.println("Minimum number of moves = " + solver.moves());
            for (Board board : solver.solution()) {
                StdOut.println(board);
            }
        }

    }

    private static class Node implements Comparable<Node> {

        public final Board board;
        public final Node last;
        private final int moves;
        private final int manhattan;
        private final int hamming;

        public Node(Board b, Node last) {
            this.board = b;
            this.last = last;
            if (last == null) {
                this.moves = 0;
            } else {
                this.moves = last.moves + 1;
            }
            this.manhattan = board.manhattan();
            this.hamming = board.hamming();
        }

        private int manhattanPriority() {
            return manhattan + moves;
        }

        private int hammingPriority() {
            return manhattan + moves;
        }

        @Override
        public int compareTo(Node other) {
            return manhattanPriority() - other.manhattanPriority();
        }
    }
}
