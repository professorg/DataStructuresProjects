/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datastructuresprojects.threesum;

/**
 *
 * @author gvandomelen19
 */
import edu.princeton.cs.algs4.BinarySearch;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.Stopwatch;
import java.util.Arrays;

public class ThreeSumFast {

    public static int count(int[] a) { // Count triples that sum to 0.
        
        Arrays.sort(a);
        int n = a.length;
        int count = 0;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (BinarySearch.indexOf(a, -a[i] - a[j]) > j) {
                    count++;
                }
            }
        }
        return count;
    }

    public static void main(String[] args) {
        
        int[] ints;
        double[] time = new double[5];
        double t;
        
        for (int i = 0; i < 5; i++) {
            
            int num = 1 << i;
            
            ints = new In(String.format("3sum\\%dKints.txt", num)).readAllInts();
            Stopwatch sw = new Stopwatch();
            count(ints);
            t = sw.elapsedTime();
            
            time[i] = t;
            
            if (i > 0) StdOut.printf("%9s+%f seconds", "", time[i-1]);
            if (i > 1) StdOut.printf("(*%f)", t/time[i-1]);
            StdOut.printf("%n%2dKints : %f seconds%n", num, t);
            
            
        }
        
        ints = new In("3sum\\1Mints.txt").readAllInts();
        Stopwatch sw = new Stopwatch();
        count(ints);
        t = sw.elapsedTime();
        
        StdOut.printf("%n1Mints : %f seconds%n", t);
        
            
        // Arrays.sort
        // Prediction (64K): 15 seconds
        // Prediction (1M): 45 seconds
        
        // Insertion sort
        // Prediction (64K): 16 seconds
        // Prediction (1M): 45 seconds
        
    }
    
    public void insertionSort(int[] a) {
        int n = a.length;
        int swaps = 0;
        for (int i = n-1; i > 0; i--) {
            if (a[i] < a[i-1]) {
                swap(a, i, i-1);
                swaps++;
            }
        }
        if (swaps == 0) return;
        
        for (int i = 2; i < n; i++) {
            int v = a[i];
            int j = i;
            while (v < a[j-1]) {
                a[j] = a[j-1];
                j--;
            }
            a[j] = v;
        }
    }
    
    private void swap(int[] a, int x, int y) {
        a[x] = a[x] ^ a[y];
        a[y] = a[x] ^ a[y];
        a[x] = a[x] ^ a[y];
    }
    
}
