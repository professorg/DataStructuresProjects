
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;
import java.util.ArrayList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author gvandomelen19
 */
public class KdTree {
    
    private static final RectHV CANVAS_RECT = new RectHV(0.0, 0.0, 1.0, 1.0);
    private BSTNode root;
    
    public static void main(String[] args) {
        KdTree kdtree = new KdTree();
        In in = new In("kdtree/input10.txt");
        while (!in.isEmpty()) {
            double x = in.readDouble();
            double y = in.readDouble();
            Point2D p = new Point2D(x, y);
            kdtree.insert(p);
        }
        
        StdOut.println(kdtree.nearest(new Point2D(0.21, 0.66)));
    }
    
    public boolean isEmpty() {
        return root == null;
    }
    
    public int size() {
        if (isEmpty()) return 0;
        return root.size;
    }
    
    public void insert(Point2D point) {
        if (point == null) throw new java.lang.IllegalArgumentException("Argument must not be null.");
        if (isEmpty())
            root = new BSTNode(point);
        else {
            BSTNode current = root;
            BSTNode last;
            boolean vertical = true;
            boolean left = true;
            while (current != null) {
                if (current.point.compareTo(point) == 0)
                    return;
                last = current;
                if (vertical ? point.x() < current.point.x() : point.y() < current.point.y()) {
                    current = current.left;
                } else {
                    current = current.right;
                }
                vertical = !vertical;
            }
            current = root;
            vertical = true;
            do {
                current.size++;
                last = current;
                if (vertical ? point.x() < current.point.x() : point.y() < current.point.y()) {
                    current = current.left;
                    left = true;
                } else {
                    current = current.right;
                    left = false;
                }
                vertical = !vertical;
            } while (current != null);
            if (left) {
                last.left = new BSTNode(point);
            } else {
                last.right = new BSTNode(point);
            }
        }
        
    }
    
    public boolean contains(Point2D point) {
        if (point == null) throw new java.lang.IllegalArgumentException("Argument must not be null.");
        BSTNode current = root;
        boolean vertical = true;
        while (current != null) {
            if (point.equals(current.point))
                return true;
            current = (vertical ? point.x() < current.point.x() : point.y() < current.point.y()) ? current.left : current.right;
            vertical = !vertical;
        }
        return false;
    }
    
    public void draw() {
        StdDraw.setPenRadius();
        draw(root, CANVAS_RECT, true);
    }
    
    private void draw(BSTNode node, RectHV bounding, boolean vertical) {
        if (node == null)
            return;
        double x = node.point.x();
        double y = node.point.y();
        if (vertical) {
            draw(node.left, new RectHV(bounding.xmin(), bounding.ymin(), x, bounding.ymax()), !vertical);
            StdDraw.setPenColor(StdDraw.RED);
            StdDraw.line(x, bounding.ymax(), x, bounding.ymin());
            node.point.draw();
            draw(node.right, new RectHV(x, bounding.ymin(), bounding.xmax(), bounding.ymax()), !vertical);
        } else {
            draw(node.left, new RectHV(bounding.xmin(), bounding.ymin(), bounding.xmax(), y), !vertical);
            StdDraw.setPenColor(StdDraw.BLUE);
            StdDraw.line(bounding.xmin(), y, bounding.xmax(), y);
            StdDraw.setPenColor(StdDraw.BLACK);
            node.point.draw();
            draw(node.right, new RectHV(bounding.xmin(), y, bounding.xmax(), bounding.ymax()), !vertical);
        }
    }
    
    public Iterable<Point2D> range(RectHV rect) {
        if (rect == null) throw new java.lang.IllegalArgumentException("Argument must not be null.");
        ArrayList<Point2D> range = new ArrayList<Point2D>();
        rangeHelper(range, rect, CANVAS_RECT, root, true);
        return range;
    }
    
    private void rangeHelper(ArrayList<Point2D> range, RectHV rect, RectHV bounding, BSTNode node, boolean vertical) {
        if (node == null || !rect.intersects(bounding))
            return;
        double x = node.point.x();
        double y = node.point.y();
        if (rect.contains(node.point))
            range.add(node.point);
        if (vertical) {
            RectHV left = new RectHV(bounding.xmin(), bounding.ymin(), x, bounding.ymax());
            RectHV right = new RectHV(x, bounding.ymin(), bounding.xmax(), bounding.ymax());
            rangeHelper(range, rect, left, node.left, !vertical);
            rangeHelper(range, rect, right, node.right, !vertical);
        } else {
            RectHV bottom = new RectHV(bounding.xmin(), bounding.ymin(), bounding.xmax(), y);
            RectHV top = new RectHV(bounding.xmin(), y, bounding.xmax(), bounding.ymax());
            rangeHelper(range, rect, bottom, node.left, !vertical);
            rangeHelper(range, rect, top, node.right, !vertical);
        }
    }
    
    public Point2D nearest(Point2D point) {
        if (point == null) throw new java.lang.IllegalArgumentException("Argument must not be null.");
        if (root == null) return null;
        return nearestHelper(point, root, true, point.distanceSquaredTo(root.point));
    }
    
    private Point2D nearestHelper(Point2D point, BSTNode node, boolean vertical, double lastNearestDistance) {
        double diff = vertical ? point.x() - node.point.x() : point.y() - node.point.y();
        double distance = point.distanceSquaredTo(node.point);
        Point2D nearestPoint = node.point;
        double nearestDistance = lastNearestDistance;
        if (distance < lastNearestDistance) {
            nearestDistance = distance;
        }
        if (diff < 0) { // left/bottom
            if (node.left != null) {
                Point2D left = nearestHelper(point, node.left, !vertical, nearestDistance);
                double leftDistance = point.distanceSquaredTo(left);
                if (leftDistance < nearestDistance) {
                    nearestPoint = left;
                    nearestDistance = leftDistance;
                }
            }
            if (node.right != null && diff * diff < nearestDistance) {
                Point2D right = nearestHelper(point, node.right, !vertical, nearestDistance);
                double rightDistance = point.distanceSquaredTo(right);
                if (rightDistance < nearestDistance) {
                    nearestPoint = right;
                }
            }
        } else { // right/top
            if (node.right != null) {
                Point2D right = nearestHelper(point, node.right, !vertical, nearestDistance);
                double rightDistance = point.distanceSquaredTo(right);
                if (rightDistance < nearestDistance) {
                    nearestPoint = right;
                    nearestDistance = rightDistance;
                }
            }
            if (node.left != null && diff * diff < nearestDistance) {
                Point2D left = nearestHelper(point, node.left, !vertical, nearestDistance);
                double leftDistance = point.distanceSquaredTo(left);
                if (leftDistance < nearestDistance) {
                    nearestPoint = left;
                }
            }
        }
        return nearestPoint;
//        if (node == null)
//            return null;
//        double distanceSquared = point.distanceSquaredTo(node.point);
//        if (vertical) {
//            if (point.x() < node.point.x()) {
//                Point2D leftNearest = nearestHelper(point, node.left, !vertical);
//                Point2D rightNearest = null;
//                if (leftNearest == null || Math.pow(node.point.x() - point.x(), 2) < point.distanceSquaredTo(leftNearest)) {
//                    rightNearest = nearestHelper(point, node.right, !vertical);
//                }
//                if (leftNearest == null) {
//                    if (rightNearest == null) {
//                        return node.point;
//                    }
//                    if (point.distanceSquaredTo(rightNearest) < distanceSquared)
//                        return rightNearest;
//                    return node.point;
//                }
//                if (rightNearest == null) {
//                    if (point.distanceSquaredTo(leftNearest) < distanceSquared)
//                        return leftNearest;
//                    return node.point;
//                }
//                if (point.distanceSquaredTo(leftNearest) < point.distanceSquaredTo(rightNearest)) {
//                    if (point.distanceSquaredTo(leftNearest) < distanceSquared)
//                        return leftNearest;
//                    return node.point;
//                }
//                if (point.distanceSquaredTo(rightNearest) < distanceSquared)
//                    return rightNearest;
//                return node.point;
//            } else {
//                Point2D rightNearest = nearestHelper(point, node.right, !vertical);
//                Point2D leftNearest = null;
//                if (rightNearest == null || Math.pow(node.point.x() - point.x(), 2) < point.distanceSquaredTo(rightNearest)) {
//                    leftNearest = nearestHelper(point, node.left, !vertical);
//                }
//                if (rightNearest == null) {
//                    if (leftNearest == null) {
//                        return node.point;
//                    }
//                    if (point.distanceSquaredTo(leftNearest) < distanceSquared)
//                        return leftNearest;
//                    return node.point;
//                }
//                if (leftNearest == null) {
//                    if (point.distanceSquaredTo(rightNearest) < distanceSquared)
//                        return rightNearest;
//                    return node.point;
//                }
//                if (point.distanceSquaredTo(rightNearest) < point.distanceSquaredTo(leftNearest)) {
//                    if (point.distanceSquaredTo(rightNearest) < distanceSquared)
//                        return rightNearest;
//                    return node.point;
//                }
//                if (point.distanceSquaredTo(leftNearest) < distanceSquared)
//                    return leftNearest;
//                return node.point;
//            }
//        } else {
//            if (point.y() < node.point.y()) {
//                Point2D bottomNearest = nearestHelper(point, node.left, !vertical);
//                Point2D topNearest = null;
//                if (bottomNearest == null || Math.pow(node.point.y() - point.y(), 2) < point.distanceSquaredTo(bottomNearest)) {
//                    topNearest = nearestHelper(point, node.right, !vertical);
//                }
//                if (bottomNearest == null) {
//                    if (topNearest == null) {
//                        return node.point;
//                    }
//                    if (point.distanceSquaredTo(topNearest) < distanceSquared)
//                        return topNearest;
//                    return node.point;
//                }
//                if (topNearest == null) {
//                    if (point.distanceSquaredTo(bottomNearest) < distanceSquared)
//                        return bottomNearest;
//                    return node.point;
//                }
//                if (point.distanceSquaredTo(bottomNearest) < point.distanceSquaredTo(topNearest)) {
//                    if (point.distanceSquaredTo(bottomNearest) < distanceSquared)
//                        return bottomNearest;
//                    return node.point;
//                }
//                if (point.distanceSquaredTo(topNearest) < distanceSquared)
//                    return topNearest;
//                return node.point;
//            } else {
//                Point2D rightNearest = nearestHelper(point, node.right, !vertical);
//                Point2D leftNearest = null;
//                if (rightNearest == null || Math.pow(node.point.x() - point.x(), 2) < point.distanceSquaredTo(rightNearest)) {
//                    leftNearest = nearestHelper(point, node.left, !vertical);
//                }
//                if (rightNearest == null) {
//                    if (leftNearest == null) {
//                        return node.point;
//                    }
//                    if (point.distanceSquaredTo(leftNearest) < distanceSquared)
//                        return leftNearest;
//                    return node.point;
//                }
//                if (leftNearest == null) {
//                    if (point.distanceSquaredTo(rightNearest) < distanceSquared)
//                        return rightNearest;
//                    return node.point;
//                }
//                if (point.distanceSquaredTo(rightNearest) < point.distanceSquaredTo(leftNearest)) {
//                    if (point.distanceSquaredTo(rightNearest) < distanceSquared)
//                        return rightNearest;
//                    return node.point;
//                }
//                if (point.distanceSquaredTo(leftNearest) < distanceSquared)
//                    return leftNearest;
//                return node.point;
//            }
//        }
    }
    
    private static class BSTNode {
        
        public Point2D point;
        public BSTNode left;
        public BSTNode right;
        public int size;
        
        public BSTNode(Point2D point) {
            this.point = point;
            this.size = 1;
        }
        
    }
    
}
