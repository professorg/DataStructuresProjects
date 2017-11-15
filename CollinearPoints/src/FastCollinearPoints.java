
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class FastCollinearPoints {

    private ArrayList<LineSegment> segments;
    private Point[] points;

    public FastCollinearPoints(Point[] points) {

        segments = new ArrayList<LineSegment>();
        this.points = points;

        for (int i = 0; i < points.length; i++) {
            if (points[i] == null) {
                throw new IllegalArgumentException();
            }
        }
        Arrays.sort(this.points);
        for (int i = 0; i < points.length - 1; i++) {
            if (points[i].compareTo(points[i + 1]) == 0) {
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
                        if (m0 == pi.slopeTo(points[k]) && k < points.length) {
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

//            for (int j = i + 1; j < points.length - 2; j++) {
//
//                Point p0 = points[i + 1];
//                double m0 = pi.slopeTo(p0);
//
//                int k = j;
//                while (k < points.length - 1 && pi.slopeTo(points[k + 1]) == m0) {
//                    k++;
//                }
//
//                if (k > j + 1) {
//
//                    Arrays.sort(points, j, k+1);
//
//                    Point min = points[j];
//                    Point max = points[k];
//                    if (pi.compareTo(min) < 0) {
//                        min = pi;
//                    } else if (pi.compareTo(max) > 0) {
//                        max = pi;
//                    }
//                    segments.add(new LineSegment(min, max));
//                    
//                    j = k + 1;
//
//                }
//
//            }
        }

    }

    public int numberOfSegments() {
        return segments.size();
    } // the number of line segments

    public LineSegment[] segments() {
        return segments.toArray(new LineSegment[segments.size()]);
    } // the line segments

}
