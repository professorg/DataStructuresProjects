/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datastructuresprojects.nbodysimulation;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdIn;

/**
 *
 * @author gvandomelen19
 */
public class NBodySimulation {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        double duration = Double.parseDouble(args[0]);
        double increment = Double.parseDouble(args[1]);

        String varsFile = StdIn.readString();
        In vars = new In(varsFile);

        int numBodies = vars.readInt();
        double universeRadius = vars.readDouble();

    }
    
}
