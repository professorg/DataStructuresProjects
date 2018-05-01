
import edu.princeton.cs.algs4.Picture;
import java.awt.Color;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author gvandomelen19
 */
public class SeamCarver {

    private Picture picture;
    private int width;
    private int height;
    private double[][] energies;

    public SeamCarver(Picture picture) {
        this.picture = new Picture(picture);
        width = picture.width();
        height = picture.height();
        energies = new double[width][height];
        calculateEnergies();
    } // create a seam carver object based on the given picture

    private void calculateEnergies() {
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                if (x == 0 || y == 0 || x == width() - 1 || y == height() - 1) {
                    energies[x][y] = 1000;
                }
                energies[x][y] = Math.sqrt(xGradientSquared(x, y) + yGradientSquared(x, y));
            }
        }
    }

    public Picture picture() {
        return new Picture(picture);
    } // current picture

    public int width() {
        return width;
    } // width of current picture

    public int height() {
        return height;
    } // height of current picture

    public double energy(int x, int y) {
        if (x < 0 || y < 0 || x > width() || y > height()) {
            throw new IllegalArgumentException();
        }
        return energies[x][y];
    } // energy of pixel at column x and row y

    private double xGradientSquared(int x, int y) {
        Color left = picture.get(x - 1, y);
        Color right = picture.get(x + 1, y);
        int dR = left.getRed() - right.getRed();
        int dG = left.getGreen() - right.getGreen();
        int dB = left.getBlue() - right.getBlue();
        return dR * dR + dG * dG + dB * dB;
    }

    private double yGradientSquared(int x, int y) {
        Color top = picture.get(x, y - 1);
        Color bottom = picture.get(x, y + 1);
        int dR = top.getRed() - bottom.getRed();
        int dG = top.getGreen() - bottom.getGreen();
        int dB = top.getBlue() - bottom.getBlue();
        return dR * dR + dG * dG + dB * dB;
    }

    public int[] findHorizontalSeam() {
        int[] seam = new int[width()];
    } // sequence of indices for horizontal seam

    public int[] findVerticalSeam() {
        int[] seam = new int[height()];
    } // sequence of indices for vertical seam

    public void removeHorizontalSeam(int[] seam) {
        if (height() < 1 || seam == null) {
            throw new IllegalArgumentException();
        }
        width--;
    } // remove horizontal seam from current picture

    public void removeVerticalSeam(int[] seam) {
        if (width() < 1 || seam == null) {
            throw new IllegalArgumentException();
        }
        height--;
    } // remove vertical seam from current picture

}
