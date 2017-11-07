
import java.util.ArrayList;


public class BruteCollinearPoints {

    private ArrayList<LineSegment> segments;
    private Point[] points;
    
    public BruteCollinearPoints(Point[] points) {
        
        segments = new ArrayList<LineSegment>();
        this.points = points;
        
        findCollinearPoints();
        
    } // finds all line segments containing 4 points
    
    private void findCollinearPoints() {
        
        for (int i = 0; i < points.length; i++) {
            for (int j = i + 1; j < points.length; j++) {
                for (int k = j + 1; k < points.length; k++) {
                    for (int l = k + 1; l < points.length; l++) {
                        Point pi = points[i];
                        Point pj = points[j];
                        Point pk = points[k];
                        Point pl = points[l];
                        double mj = pi.slopeTo(pj);
                        double mk = pi.slopeTo(pk);
                        double ml = pi.slopeTo(pl);
                        if (mj == mk && mk == ml) {
                            Point min = pi;
                            Point max = pi;
                            
                            if (pj.compareTo(min) < 0) min = pj;
                            if (pj.compareTo(max) > 0) max = pj;
                            if (pk.compareTo(min) < 0) min = pk;
                            if (pk.compareTo(max) > 0) max = pk;
                            if (pl.compareTo(min) < 0) min = pl;
                            if (pl.compareTo(max) > 0) max = pl;
                            
                            segments.add(new LineSegment(min, max));
                        }
                    }
                }
            }
        }
        
    }


    public int numberOfSegments() {
        return segments.size();
    } // the number of line segments


    public LineSegment[] segments() {
        return segments.toArray(new LineSegment[segments.size()]);
    } // the line segments
           
}
