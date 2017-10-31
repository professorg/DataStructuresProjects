
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
        int n = 0;
        RandomizedQueue<String> rq = new RandomizedQueue<String>();
        while (!StdIn.isEmpty()) {
            if (k == 0) break;
            n++;
            String s = StdIn.readString();
            if (n > k) {
                int j = StdRandom.uniform(n);
                if (j < k) {
                    rq.dequeue();
                    rq.enqueue(s);
                }
            } else {
                rq.enqueue(s);
            }
        }
        for (String s : rq) {
            StdOut.println(s);
        }
    }
    
}
