
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
                            int mini = i;
                            int maxi = i;

                            if (pj.compareTo(points[mini]) < 0) {
                                mini = j;
                            }
                            if (pj.compareTo(points[maxi]) > 0) {
                                maxi = j;
                            }
                            if (pk.compareTo(points[mini]) < 0) {
                                mini = k;
                            }
                            if (pk.compareTo(points[maxi]) > 0) {
                                maxi = k;
                            }
                            if (pn.compareTo(points[mini]) < 0) {
                                mini = n;
                            }
                            if (pn.compareTo(points[maxi]) > 0) {
                                maxi = n;
                            }
                            
                            Point min = points[mini];
                            Point max = points[maxi];
                                                        
                            LineSegment segment = new LineSegment(min, max);
                            segments.add(segment);
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
