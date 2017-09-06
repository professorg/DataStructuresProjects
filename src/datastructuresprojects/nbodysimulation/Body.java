package datastructuresprojects.nbodysimulation;

import edu.princeton.cs.algs4.StdDraw;
import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Body implements Cloneable {

    private double xPos;
    private double yPos;
    private double xVel;
    private double yVel;
    private final double mass;
    private final String image;
    public static final double G = 6.67E-11;
    
    public Body(double xPos, double yPos, double xVel, double yVel, double mass,
            String image) {
        this.xPos = xPos;
        this.yPos = yPos;
        this.xVel = xVel;
        this.yVel = yVel;
        this.mass = mass;
        this.image = image;
    }
    
    public void draw() {
        StdDraw.picture(xPos, yPos, image);
    }
    
    public static Collection<Body> update(Collection<Body> bodies) {
        try {
            Collection<Body> updated = bodies.getClass().newInstance();
            bodies.forEach(body -> {
                // TODO: Update
            });
            return updated;
        } catch (InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(Body.class.getName()).log(Level.SEVERE, null, ex);
        }
        return bodies;
    }
    
    public void update(Body other) {
        double force = G*mass*other.getMass();
        double dist = distanceTo(other);
        double xForce = force * (xPos - other.xPos) / dist;
        double yForce = force * (yPos - other.yPos) / dist;
        double xAccel = xForce / mass;
        double yAccel = yForce / mass;
        
        xVel += xAccel;
        yVel += xAccel;
    }
    
    public double distanceTo(Body other) {
        return Math.sqrt(
                Math.pow(xPos - other.getxPos(), 2)
                        + Math.pow(yPos - other.getyPos(), 2)
        );
    }

    public double getMass() {
        return mass;
    }

    public String getImage() {
        return image;
    }

    public double getxPos() {
        return xPos;
    }

    public void setxPos(double xPos) {
        this.xPos = xPos;
    }

    public double getyPos() {
        return yPos;
    }

    public void setyPos(double yPos) {
        this.yPos = yPos;
    }

    public double getxVel() {
        return xVel;
    }

    public void setxVel(double xVel) {
        this.xVel = xVel;
    }

    public double getyVel() {
        return yVel;
    }

    public void setyVel(double yVel) {
        this.yVel = yVel;
    }
    
    public Object clone() {
        return new Body(xPos, yPos, xVel, yVel, mass, image);
    }

}
