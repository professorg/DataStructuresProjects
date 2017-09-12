package datastructuresprojects.nbodysimulation;

import static datastructuresprojects.nbodysimulation.NBodySimulation.PATH;
import edu.princeton.cs.algs4.StdDraw;
import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Stream;

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
        StdDraw.picture(xPos, yPos, PATH + image);
    }
    
    public static Collection<Body> update(Collection<Body> bodies,
            double timeIncrement) {
        try {
            Collection<Body> updated = bodies.getClass().newInstance();
            bodies.forEach(body -> {
                Body next = (Body)body.clone();
                updated.add(next);
                bodies.forEach(b -> {
                        if (b != body)
                            next.update(b, timeIncrement);
                                    });
                next.move(timeIncrement);
            });
            return updated;
        } catch (InstantiationException | IllegalAccessException ex) {
            ex.printStackTrace();
            Logger.getLogger(Body.class.getName()).log(Level.SEVERE, null, ex);
        }
        return bodies;
    }
    
    public Body update(Body other, double timeIncrement) {
        double force = G*mass*other.getMass();
        double dist = distanceTo(other);
        double xForce = force * (xPos - other.xPos) / dist;
        double yForce = force * (yPos - other.yPos) / dist;
        double xAccel = xForce / mass;
        double yAccel = yForce / mass;
        
        xVel += xAccel * timeIncrement;
        yVel += yAccel * timeIncrement;
        
        return this;
    }
    
    public Body move(double timeIncrement) {
        xPos += xVel * timeIncrement;
        yPos += yVel * timeIncrement;
        
        return this;
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
    
    @Override
    public Object clone() {
        try {
            return super.clone();
        } catch (CloneNotSupportedException ex) {
            ex.printStackTrace();
            Logger.getLogger(Body.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    @Override
    public String toString() {
        return "";
    }

}
