/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datastructuresprojects.misc;

import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

/**
 *
 * @author gvandomelen19
 */
public class ElementaryCellularAutomata {
    
    private static final int STEPS = 10000;
    
    public static void main(String[] args) {
        
        byte[][] state = new byte[STEPS+1][STEPS*2-1];
        state[1][STEPS+1] = 1;
        StdDraw.setCanvasSize(800, 400);
        StdDraw.setXscale(-STEPS, STEPS);
        StdDraw.setYscale(STEPS, 0.0);
        StdDraw.setPenRadius(1.0);
        StdDraw.enableDoubleBuffering();
        StdOut.print("Enter the desired rule: Rule ");
        byte rule = StdIn.readByte();
        
        for (int i = 2; i <= STEPS; i++) {
            
            for (int j = 0; j < STEPS*2-1; j++) {
                byte pattern;
                if (j == 0) {
                    pattern = (byte) (0b100 | state[i][j] | state[i][j+1]);
                } else if (j == STEPS * 2 - 1) {
                    pattern = (byte) (state[i][j-1] | state[i][j] | 0b001);
                } else {
                    pattern = (byte) (state[i][j-1] | state[i][j] | state[i][j+1]);
                }
            }
            
            for (int j = 1; j <= STEPS; j++) {
                for (int k = 0; k < STEPS*2-1; k++) {
                    if (state[j][k] != 0) {
                        StdDraw.point(j, k);
                    }
                }
            }
            
            StdDraw.pause(100);
        }
        
        StdDraw.show();
    }
    
}
