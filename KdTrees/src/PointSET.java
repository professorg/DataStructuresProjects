
import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import java.util.ArrayList;
import java.util.TreeSet;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author gvandomelen19
 */
public class PointSET {
    
    private final TreeSet<Point2D> points;
    
    public PointSET() {
        points = new TreeSet<Point2D>();
    }
    
    public boolean isEmpty() {
        return points.isEmpty();
    }
    
    public int size() {
        return points.size();
    }
    
    public void insert(Point2D point) {
        if (point == null) throw new java.lang.IllegalArgumentException("Argument must not be null.");
        points.add(point);
    }
    
    public boolean contains(Point2D point) {
        if (point == null) throw new java.lang.IllegalArgumentException("Argument must not be null.");
        return points.contains(point);
    }
    
    public void draw() {
        for (Point2D point : points) {
            point.draw();
        }
    }
    
    public Iterable<Point2D> range(RectHV rect) {
        if (rect == null) throw new java.lang.IllegalArgumentException("Argument must not be null.");
        ArrayList<Point2D> range = new ArrayList<Point2D>();
        for (Point2D point : points) {
            if (rect.contains(point)) range.add(point);
        }
        return range;
    }
    
    public Point2D nearest(Point2D point) {
        if (point == null) throw new java.lang.IllegalArgumentException("Argument must not be null.");
        Point2D nearest = null;
        double nearestDistanceSquared = -1;
        for (Point2D other : points) {
            double distanceSquared = point.distanceSquaredTo(other);
            if (nearestDistanceSquared < 0 || distanceSquared < nearestDistanceSquared) {
                nearest = other;
                nearestDistanceSquared = distanceSquared;
            }
        }
        return nearest;
    }
    
}
