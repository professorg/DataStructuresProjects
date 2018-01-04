
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

    private Board board;
    private boolean solvable;
    List<Board> solution;

    public Solver(Board initial) {
        board = initial;
        solve();
    }

    private void solve() {
        MinPQ<Node> pq = new MinPQ();
        MinPQ<Node> twinpq = new MinPQ();
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
        solution = new ArrayList();
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

    private static class Node implements Comparable {

        public Board board;
        public Node last;
        private int manhattan;

        public Node(Board b, Node last) {
            this.board = b;
            this.last = last;
            this.manhattan = board.manhattan();
        }

        public int compareTo(Node other) {
            return manhattan - other.manhattan;
        }

        @Override
        public int compareTo(Object other) {
            if (other instanceof Node) {
                return manhattan - ((Node) other).manhattan;
            }
            return 0;
        }
    }
}
