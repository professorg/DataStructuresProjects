/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datastructuresprojects.nbodysimulation;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdIn;
import java.util.ArrayList;
import java.util.Collection;

/**
 *
 * @author gvandomelen19
 */
public class NBodySimulation {
    
    public static final String PATH = "src/datastructuresprojects/nbodysimulation"
            + "/nbody/nbody/";

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        double duration = StdIn.readDouble();//Double.parseDouble(args[0]);
        double increment = StdIn.readDouble();//Double.parseDouble(args[1]);

        String varsFile = StdIn.readString();
        In vars = new In(PATH + varsFile);

        int numBodies = vars.readInt();
        double universeRadius = vars.readDouble();
        StdDraw.setScale(-universeRadius, universeRadius);
        
        Collection<Body> bodies = new ArrayList();
        
        for (int i = 0; i < numBodies; i++) {
            double xPos = vars.readDouble();
            double yPos = vars.readDouble();
            double xVel = vars.readDouble();
            double yVel = vars.readDouble();
            double mass = vars.readDouble();
            String image = vars.readString();
            bodies.add(new Body(xPos, yPos, xVel, yVel, mass, image));
        }
        
        for (double t = 0; t < duration; t += increment) {
            long time = System.currentTimeMillis() + 1000/30;
            
            bodies = update(bodies, increment);
            draw(bodies);
            
            while (System.currentTimeMillis() < time) {}
        }

    }
    
    public static Collection<Body> update(Collection<Body> bodies,
            double timeIncrement) {
        return Body.update(bodies, timeIncrement);
    }
    
    public static void draw(Collection<Body> bodies) {
        bodies.forEach(body -> body.draw());
    }
    
}
