
import edu.princeton.cs.algs4.BreadthFirstDirectedPaths;
import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author gvandomelen19
 */
public class SAP {

    private static final int NO_PATH = -1;
    private final Digraph digraph;

    private final Map<Set<Integer>, Ancestor> ancestorCache;

    // constructor takes a digraph (not necessarily a DAG)
    public SAP(Digraph g) {
        digraph = copyOf(g);
        ancestorCache = new HashMap<>();
    }

    // length of shortest ancestral path between v and w; -1 if no such path
    public int length(int v, int w) {
        Set<Integer> vertices = new TreeSet<>(Arrays.asList(v, w));
        if (ancestorCache.containsKey(vertices)) {
            return ancestorCache.get(vertices).length;
        } else {
            Ancestor ancestor = new Ancestor(v, w);
            ancestorCache.put(vertices, ancestor);
            return ancestor.length;
        }
    }

    // a common ancestor of v and w that participates in a shortest ancestral path; -1 if no such path
    public int ancestor(int v, int w) {
        Set<Integer> vertices = new TreeSet<>(Arrays.asList(v, w));
        if (ancestorCache.containsKey(vertices)) {
            return ancestorCache.get(vertices).ancestor;
        } else {
            Ancestor ancestor = new Ancestor(v, w);
            ancestorCache.put(vertices, ancestor);
            return ancestor.ancestor;
        }
    }

    // length of shortest ancestral path between any vertex in v and any vertex in w; -1 if no such path
    public int length(Iterable<Integer> v, Iterable<Integer> w) {
        Ancestor ancestor = new Ancestor(v, w);
        return ancestor.length;
    }

    // a common ancestor that participates in shortest ancestral path; -1 if no such path
    public int ancestor(Iterable<Integer> v, Iterable<Integer> w) {
        Ancestor ancestor = new Ancestor(v, w);
        return ancestor.ancestor;
    }

    private static Digraph copyOf(Digraph g) {
        int numVertices = g.V();
        Digraph copy = new Digraph(numVertices);
        for (int fromVertex = 0; fromVertex < numVertices; fromVertex++) {
            for (int toVertex : g.adj(fromVertex)) {
                copy.addEdge(fromVertex, toVertex);
            }
        }
        return copy;
    }

    private class Ancestor {

        public int ancestor;
        public int length;

        public Ancestor(int fromV, int fromW) {
            shortestAncestor(Arrays.asList(fromV), Arrays.asList(fromW));
        }
        
        public Ancestor(Iterable<Integer> fromV, Iterable<Integer> fromW) {
            shortestAncestor(fromV, fromW);
        }

        private void shortestAncestor(Iterable<Integer> fromV, Iterable<Integer> fromW) {
            BreadthFirstDirectedPaths pathsFromV = new BreadthFirstDirectedPaths(digraph, fromV);
            BreadthFirstDirectedPaths pathsFromW = new BreadthFirstDirectedPaths(digraph, fromW);

            int numVertices = digraph.V();
            int shortestAncestor = NO_PATH;
            int shortestPathLength = NO_PATH;
            for (int vertex = 0; vertex < numVertices; vertex++) {
                if (pathsFromV.hasPathTo(vertex) && pathsFromW.hasPathTo(vertex)) {
                    int pathLength = pathsFromV.distTo(vertex) + pathsFromW.distTo(vertex);
                    if (shortestAncestor == NO_PATH || pathLength < shortestPathLength) {
                        shortestAncestor = vertex;
                        shortestPathLength = pathLength;
                    }
                }
            }
            this.length = shortestPathLength;
            this.ancestor = shortestAncestor;
        }

    }

    // do unit testing of this class
    public static void main(String[] args) {
        // Empty

        StdOut.println(new SAP(new Digraph(new In("wordnet/digraph1.txt"))).ancestor(3, 3));
    }
}
