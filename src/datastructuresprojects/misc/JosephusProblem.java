/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datastructuresprojects.misc;

import datastructuresprojects.types.CircularQueue;

/**
 *
 * @author gvandomelen19
 */
public class JosephusProblem {
    
    public static final boolean DEBUG = true;
    
    public static void main(String[] args) {
        
        String[] fakeArgs = {"3", "100000"};
        int m;
        int n;
        CircularQueue<Integer> cq = new CircularQueue();
        
        if (DEBUG) {
            m = Integer.parseInt(fakeArgs[0]);
            n = Integer.parseInt(fakeArgs[1]);
        }
        else {
            m = Integer.parseInt(args[0]);
            n = Integer.parseInt(args[1]);
        }
        
        for (int i = 0; i < n; ++i) {
            cq.enqueue(i);
        }
        int i = 1;
        while (cq.size() > 1) {
            int k = cq.dequeue();
            if (i != 0) {
                cq.enqueue(k);
            } else {
                System.out.print(k + " ");
            }
            ++i;
            i %= m;
        }
        System.out.println(cq.dequeue());
    }
    
}
