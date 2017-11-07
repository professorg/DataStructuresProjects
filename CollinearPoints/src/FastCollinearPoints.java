
import java.util.ArrayList;
import java.util.Arrays;

public class FastCollinearPoints {

    private ArrayList<LineSegment> segments;
    private Point[] points;

    public FastCollinearPoints(Point[] points) {

        segments = new ArrayList<LineSegment>();
        this.points = points;

        findCollinearPoints();
    } // finds all line segments containing 4 or more points

    private void findCollinearPoints() {

        for (int i = 0; i < points.length; i++) {
            Arrays.sort(points, i + 1, points.length, points[i].slopeOrder());

            Point pi = points[i];

            for (int j = i + 1; j < points.length - 2; j++) {
                Point p0 = points[j];
                Point p1 = points[j + 1];
                Point p2 = points[j + 2];
                double m0 = pi.slopeTo(p0);
                double m1 = pi.slopeTo(p1);
                double m2 = pi.slopeTo(p2);
                if (m0 == m1 && m1 == m2) {
                    Point min = pi;
                    Point max = pi;

                    if (p0.compareTo(min) < 0) min = p0;
                    if (p0.compareTo(max) > 0) max = p0;
                    if (p1.compareTo(min) < 0) min = p1;
                    if (p1.compareTo(max) > 0) max = p1;
                    if (p2.compareTo(min) < 0) min = p2;
                    if (p2.compareTo(max) > 0) max = p2;

                    segments.add(new LineSegment(min, max));
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
