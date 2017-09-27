/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datastructuresprojects.types;

/**
 *
 * @author gvandomelen19
 */
public class DisjointUnionSet {
    
    public int n;
    public int[] rank;
    public int[] parent;
    
    public DisjointUnionSet(int n) {
        
        this.n = n;
        this.rank = new int[n];
        this.parent = new int[n];
        makeSet();
    }
    
    private void makeSet() {
        
        for (int i = 0; i < n; i++) {
            parent[i] = i;
        }
    }
    
    public boolean connected(int x, int y) {
        return find(x) == find(y);
    }
    
    public int find(int x) {
        
        if (parent[x] != x) parent[x] = find(parent[x]);
        return parent[x];
    }
    
    public boolean union(int x, int y) {
        
        int xRoot = find(x);
        int yRoot = find(y);
        if (xRoot == yRoot) return true;
        if (rank[xRoot] < rank[yRoot]) parent[xRoot] = yRoot;
        else if (rank[yRoot] < rank[xRoot]) parent[yRoot] = xRoot;
        else {
            parent[yRoot] = xRoot;
            rank[xRoot]++;
        }
        return false;
    }
    
}
