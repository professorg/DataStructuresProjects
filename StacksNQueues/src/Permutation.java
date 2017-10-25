
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author gvandomelen19
 */
public class Permutation {
    
    public static void main(String[] args) {
        
        int k = Integer.parseInt(args[0]);
        
        String s;
        do {
            s = StdIn.readString();
        } while (s != null);
        String[] strings = StdIn.readAllStrings();
        StdRandom.shuffle(strings);
        for (int i = 0; i < k; i++) {
            StdOut.println(strings[i]);
        }
    }
    
}
