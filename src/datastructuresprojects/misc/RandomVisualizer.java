/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datastructuresprojects.misc;

import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdRandom;

/**
 *
 * @author gvandomelen19
 */
public class RandomVisualizer {
    
    public static final int DELAY = 100;
    
    public static void main(String[] args) {
        
        int lo = 0;
        int hi = 10;
        
        if (args.length == 1) {
            hi = Integer.parseInt(args[0]);
        }
        if (args.length == 2) {
            lo = Integer.parseInt(args[0]);
            hi = Integer.parseInt(args[1]);
        }
        
        int n = hi - lo;
        int[] count = new int[n];
        
        StdDraw.enableDoubleBuffering();
        
        int nums = 0;
        
        while (!StdDraw.hasNextKeyTyped()) {
            
            nums++;
            StdDraw.clear();
            StdDraw.setPenColor(StdDraw.BLACK);
            StdDraw.setXscale(-0.05*n, 1.05*n);
            StdDraw.setYscale(-0.05*nums, 1.05*nums);
            StdDraw.filledRectangle(n/2.0, nums/2.0, n/2.0, nums/2.0);
            
            int r = StdRandom.uniform(lo, hi);
            //int r = (int)(Math.random()*n+lo);
            count[r-lo]++;
            
            StdDraw.textRight(1.0*n, 1.025*nums, "nums = " + nums);
            
            for (int i = 0; i < n; i++) {
                StdDraw.text(i+0.5, -0.025*nums, ""+(i+lo));
            }
            
            StdDraw.setPenColor(StdDraw.WHITE);
            
            for (int i = 0; i < n; i++) {
                StdDraw.filledRectangle(i+0.5, count[i]/2.0, 0.45, count[i]/2.0);
                StdDraw.text(i+0.5, count[i]/2.0+0.1*nums, ""+count[i]);
            }
            
            StdDraw.setPenColor(StdDraw.GRAY);
            
            StdDraw.filledRectangle(n/2.0, (double)(nums)/n, n/2.0, 0.0025*nums);
            
            StdDraw.show();
            StdDraw.pause(DELAY);
        }
    }
}
