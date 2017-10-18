/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datastructuresprojects.misc;

import edu.princeton.cs.algs4.StdDraw;
import java.util.function.Function;

/**
 *
 * @author gvandomelen19
 */
public class Plot {
    
    private double xLo;
    private double yLo;
    private double xHi;
    private double yHi;
    
    public Plot() {
        this(0.0, 0.0, 1.0, 1.0);
    }
    
    public Plot(double xLo, double yLo, double xHi, double yHi) {
        this.xLo = xLo;
        this.yLo = yLo;
        this.xHi = xHi;
        this.yHi = yHi;
        init();
    }
    
    private void init() {
        double dx = xHi-xLo;
        double dy = yHi-yLo;
        StdDraw.enableDoubleBuffering();
        StdDraw.setXscale(-0.05*dx + xLo, 1.05*dx + xLo);
        StdDraw.setYscale(-0.05*dy + yLo, 1.05*dy + yLo);
        StdDraw.rectangle(xLo + dx/2.0, yLo + dy/2.0, dx/2.0, dy/2.0);
        StdDraw.show();
    }
    
    public void draw(Function<Double, Double> f, double delta) {
        double x = xLo;
        double y;
        double xLast = Double.NaN;
        double yLast = Double.NaN;
        while (x < xHi) {
            try {
                y = f.apply(x);
            } catch (Exception e) {
                continue;
            }
            if (yLast != Double.NaN) {
                if (y <= yHi) StdDraw.line(xLast, yLast, x, y);
                xLast = x;
                yLast = y;
            }
            x += delta;
        }
        StdDraw.show();
    }
    
    public void discrete(Function<Double, Double> f, double delta) {
        double x = xLo;
        double y;
        while (x < xHi) {
            try {
                y = f.apply(x);
            } catch (Exception e) {
                continue;
            }
            StdDraw.point(x, y);
            x += delta;
        }
        StdDraw.show();
    }
    
    public void discrete(Function<Double, Double> f, double delta, double size) {
        double x = xLo;
        double y;
        while (x < xHi) {
            try {
                y = f.apply(x);
            } catch (Exception e) {
                continue;
            }
            StdDraw.filledCircle(x, y, size);
            x += delta;
        }
        StdDraw.show();
    }
    
    public static void main(String[] args) {
        
        Plot plot = new Plot();
        plot.draw((x -> Math.sqrt(1.0-x*x)), 0.01);
    }
    
}
