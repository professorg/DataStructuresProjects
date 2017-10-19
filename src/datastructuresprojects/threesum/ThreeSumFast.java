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
        
        In in;
        int[] ints;
        int n;
        double t;
        
        for (int i = 1; i <= 32; i = i << 1) {
            
            in = new In(String.format("3sum\\%dKints.txt", i));
            ints = in.readAllInts();
            Stopwatch sw = new Stopwatch();
            n = count(ints);
            t = sw.elapsedTime();
            
            System.out.printf("%dKints : %f seconds%n", i, t);
            
        }
        
        in = new In("3sum\\1Mints.txt");
        ints = in.readAllInts();
        Stopwatch sw = new Stopwatch();
        n = count(ints);
        t = sw.elapsedTime();

        System.out.printf("1Mints : %f seconds%n", t);

        
    }
}
