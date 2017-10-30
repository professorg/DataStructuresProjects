
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
            n++;
            if (n > k) {
                if (StdRandom.bernoulli(1.0/n)) {
                    rq.dequeue();
                    rq.enqueue(StdIn.readString());
                }
            } else {
                rq.enqueue(StdIn.readString());
            }
        }
        for (String s : rq) {
            StdOut.println(s);
        }
    }
    
}
