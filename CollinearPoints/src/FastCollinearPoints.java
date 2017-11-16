
import java.util.ArrayList;
import java.util.Arrays;

public class FastCollinearPoints {

    private ArrayList<LineSegment> segments;
    private Point[] points;

    public FastCollinearPoints(Point[] points) {

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
    } // finds all line segments containing 4 or more points

    private void findCollinearPoints() {

        for (int i = 0; i < points.length - 3; i++) {

            Point pi = points[i];
            Arrays.sort(points, i + 1, points.length);
            Arrays.sort(points, i + 1, points.length, pi.slopeOrder());

            int j = i + 1;
            int k = j + 2;
            int state = 0;  // 0 : after leap
            // 1 : going backwards
            // 2 : going forwards

            double m0 = pi.slopeTo(points[j]);

            loop: while (true) {
                
                switch (state) {

                    case 0:
                        if (k >= points.length) {
                            break loop;
                        }
                        if (m0 == pi.slopeTo(points[k])) {
                            state = 2;
                            k++;
                        } else {
                            state = 1;
                            m0 = pi.slopeTo(points[k]);
                            k--;
                        }
                        break;

                    case 1:
                        if (m0 == pi.slopeTo(points[k])) {
                            k--;
                        } else {
                            state = 0;
                            k++;
                            j = k;
                            k = j + 2;
                        }
                        break;

                    case 2:
                        if (k < points.length && m0 == pi.slopeTo(points[k])) {
                            k++;
                        } else {
                            Point min = pi;
                            Point max = pi;
                            while (j < k) {
                                min = min.compareTo(points[j]) < 0 ? min : points[j];
                                max = max.compareTo(points[j]) > 0 ? max : points[j];
                                j++;
                            }
                            segments.add(new LineSegment(min, max));
                            if (k >= points.length) {
                                break loop;
                            }
                            state = 0;
                            k = j + 2;
                            m0 = pi.slopeTo(points[j]);
                        }
                        break;

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
