
import java.util.ArrayList;
import java.util.Arrays;

public class BruteCollinearPoints {

    private final ArrayList<LineSegment> segments;
    private final Point[] points;

    public BruteCollinearPoints(Point[] points) {

        if (points == null) {
            throw new IllegalArgumentException();
        }
        
        segments = new ArrayList<LineSegment>();
        this.points = new Point[points.length];

        for (int i = 0; i < points.length; i++) {
            if (points[i] == null) {
                throw new IllegalArgumentException();
            }
            this.points[i] = points[i];
        }
        Arrays.sort(this.points);
        for (int i = 0; i < this.points.length - 1; i++) {
            if (this.points[i].compareTo(this.points[i + 1]) == 0) {
                throw new IllegalArgumentException();
            }
        }

        findCollinearPoints();

    } // finds all line segments containing 4 points

    private void findCollinearPoints() {

        Arrays.sort(points);
        
        for (int i = 0; i < points.length; i++) {
            Point pi = points[i];
            for (int j = i + 1; j < points.length; j++) {
                Point pj = points[j];
                for (int k = j + 1; k < points.length; k++) {
                    Point pk = points[k];
                    for (int n = k + 1; n < points.length; n++) {
                        Point pn = points[n];
                        double mj = pi.slopeTo(pj);
                        double mk = pi.slopeTo(pk);
                        double mn = pi.slopeTo(pn);
                        if (mj == mk && mk == mn) {
                            Point min = pi;
                            Point max = pi;

                            if (pj.compareTo(min) < 0) {
                                min = pj;
                            }
                            if (pj.compareTo(max) > 0) {
                                max = pj;
                            }
                            if (pk.compareTo(min) < 0) {
                                min = pk;
                            }
                            if (pk.compareTo(max) > 0) {
                                max = pk;
                            }
                            if (pn.compareTo(min) < 0) {
                                min = pn;
                            }
                            if (pn.compareTo(max) > 0) {
                                max = pn;
                            }
                            
                            LineSegment segment = new LineSegment(min, max);
                            boolean repeat = false;

                            for (LineSegment s : segments) {
                                if (segment.toString().equals(s.toString())) {
                                    repeat = true;
                                    break;
                                }
                            }
                            if (!repeat) {
                                segments.add(segment);
                            }
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
