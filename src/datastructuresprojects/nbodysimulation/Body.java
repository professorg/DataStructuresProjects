package datastructuresprojects.nbodysimulation;

import static datastructuresprojects.nbodysimulation_old.NBodySimulation.PATH;
import edu.princeton.cs.algs4.StdDraw;

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
    
    public Body(Body body) {
        this.xPos = body.xPos;
        this.yPos = body.yPos;
        this.xVel = body.xVel;
        this.yVel = body.yVel;
        this.mass = body.mass;
        this.image = body.image;
    }
    
    public void draw() {
        StdDraw.picture(xPos, yPos, PATH + image);
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
    
//    @Override
//    public Object clone() {
//        try {
//            return super.clone();
//        } catch (CloneNotSupportedException ex) {
//            ex.printStackTrace();
//            Logger.getLogger(Body.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        return null;
//    }
    
    @Override
    public String toString() {
        return String.format("%11.4E %11.4E %11.4E %11.4E %11.4E %13s", xPos, yPos, xVel, yVel, mass, image);
    }

}
