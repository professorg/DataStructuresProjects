/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datastructuresprojects.misc;

import edu.princeton.cs.algs4.StdDraw;

/**
 *
 * @author gvandomelen19
 */
public class Plot {
    
    public Plot(double xLo, double xHi, double yLo, double yHi) {
        init(xLo, xHi, yLo, yHi);
    }
    
    public void init(double xLo, double xHi, double yLo, double yHi) {
        StdDraw.enableDoubleBuffering();
        StdDraw.setXscale(-0.05*xLo, 1.05*xHi);
        StdDraw.setYscale(-0.05*yLo, 1.05*yHi);
        StdDraw.rectangle((xHi-xLo)/2.0, (yHi-yLo)/2.0,
                          (xHi-xLo)/2.0, (yHi-yLo)/2.0);
    }
    
}
