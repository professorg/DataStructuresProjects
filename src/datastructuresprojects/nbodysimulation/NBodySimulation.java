/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datastructuresprojects.nbodysimulation;

import datastructuresprojects.types.LinearLinkedList;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdAudio;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import java.util.Collection;
import java.util.List;

/**
 *
 * @author gvandomelen19
 */
public class NBodySimulation {
    
    public static final String PATH = "src\\datastructuresprojects\\nbodysimulation"
            + "\\nbody\\nbody\\";
    public static final double G = 6.67E-11;
    public static final boolean DEBUG = true;
    public static final int FPS = 1000;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        double duration;
        double increment;
        String varsFile;
        
        if (DEBUG) {
            String[] fakeArgs = {"1E100", "5E4", "double-suns.txt"};
            duration = Double.parseDouble(fakeArgs[0]);
            increment = Double.parseDouble(fakeArgs[1]);
            varsFile = fakeArgs[2];
        }
        else {
            increment = StdIn.readDouble();
            duration = StdIn.readDouble();
            varsFile = StdIn.readString();
        }
        In vars = new In(PATH + varsFile);

        int numBodies = vars.readInt();
        double universeRadius = vars.readDouble();
        StdDraw.setScale(-universeRadius, universeRadius);
        StdDraw.enableDoubleBuffering();
        
        List<Body> bodies = new LinearLinkedList();
        
        for (int i = 0; i < numBodies; i++) {
            double xPos = vars.readDouble();
            double yPos = vars.readDouble();
            double xVel = vars.readDouble();
            double yVel = vars.readDouble();
            double mass = vars.readDouble();
            String image = vars.readString();
            bodies.add(new Body(xPos, yPos, xVel, yVel, mass, image));
        }
        
        //StdAudio.play(PATH + "2001.wav");

        for (double t = 0; t < duration; t += increment) {
            long time = System.currentTimeMillis() + 1000/FPS;
            
            List<Body> old = bodies;
            bodies = new LinearLinkedList();
            
            //StdDraw.clear();
            StdDraw.picture(0, 0, PATH + "starfield.jpg", 2*universeRadius, 2*universeRadius);

            for (Body body : old) {
                
                double netAccelX = 0.0;
                double netAccelY = 0.0;
                Body next = new Body(body);
                
                for (Body other : old) {
                    
                    if (body == other) continue;
                    double dx = other.getxPos()-body.getxPos();
                    double dy = other.getyPos()-body.getyPos();
                    double r2 = dx*dx+dy*dy;
                    
                    //double r3Halves = Math.pow(dx*dx + dy*dy, 1.5);
                    //double forceR = G*other.getMass()/r3Halves;
                    double force = G*other.getMass() / Math.pow(r2, 3.0/2.0);
                    //double forceX = forceR*dx;
                    double forceX = force*dx;
                    //double forceY = forceR*dy;
                    double forceY = force*dy;
                    
                    netAccelX += forceX;
                    netAccelY += forceY;
                    
                }
                
                next.setxVel(next.getxVel()+netAccelX*increment);
                next.setyVel(next.getyVel()+netAccelY*increment);
                
                next.setxPos(next.getxPos()+next.getxVel()*increment);
                next.setyPos(next.getyPos()+next.getyVel()*increment);
                
                next.draw();
                
                bodies.add(next);
            }
            
            StdDraw.show();
                    
            //draw(bodies);
            
            while (System.currentTimeMillis() < time) {}
        }
        
        StdOut.println(numBodies);
        StdOut.println(universeRadius);
        bodies.forEach(b -> StdOut.println(b));

    }
        
    public static void draw(Collection<Body> bodies) {
        bodies.forEach(body -> body.draw());
    }
    
}
