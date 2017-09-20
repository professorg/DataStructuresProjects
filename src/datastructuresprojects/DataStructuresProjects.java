/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datastructuresprojects;

import datastructuresprojects.types.LinearLinkedList;
import edu.princeton.cs.algs4.StdOut;

/**
 *
 * @author gvandomelen19
 */
public class DataStructuresProjects {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        LinearLinkedList<Double> lll = new LinearLinkedList();
        lll.addFirst(6.4);
        StdOut.println(lll);
        lll.addFirst(7.1);
        StdOut.println(lll);
        lll.addLast(4.4);
        StdOut.println(lll);
        for (int i = 0; i < 3; i++) {
            lll.addFirst(lll.removeLast());
            StdOut.println(lll);
        }
    }
    
}
