
import java.util.ArrayList;


public class BruteCollinearPoints {

    ArrayList<LineSegment> segments;
    
    public BruteCollinearPoints(Point[] points) {
        
        segments = new ArrayList<LineSegment>();
        
    } // finds all line segments containing 4 points


    public int numberOfSegments() {
        return segments.size();
    } // the number of line segments


    public LineSegment[] segments() {
        return (LineSegment[]) segments.toArray();
    } // the line segments
           
}
