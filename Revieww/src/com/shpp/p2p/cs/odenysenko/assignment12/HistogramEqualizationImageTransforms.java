package com.shpp.p2p.cs.odenysenko.assignment12;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 *Finding the histogram of an image
 * By analogy with Assignment 6 */
public class HistogramEqualizationImageTransforms implements Constants {

    /**
     * The method returns the histogram of the image
     *
     * @param image Image from the user
     * @return A histogram.
     */
    public static int[] histogram(BufferedImage image) {
        BufferedImage imageGray = toGrayscale(image); //grayscale image
        int[][] luminances = imageToLuminances(imageGray); // Image brightness
        int[] histogram = new int[MAX_LUMINANCE + 1];

        for (int[] luminance : luminances) { // we walk through an array of brightnesses
            for (int brightness : luminance) {// brightness of each pixel
                histogram[brightness]++;//add a pixel with this brightness
            }
        }
        return histogram;
    }

    /**
     * The method converts the image into shades of gray
     *
     * @param image initial image
     * @return grayscale image
     */
    public static BufferedImage toGrayscale(BufferedImage image) {
        BufferedImage imageGray = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_INT_RGB);
        Color colorPixel;

        for (int row = 0; row < image.getWidth(); ++row) {
            for (int col = 0; col < image.getHeight(); ++col) {
                int pixelColorRGB = image.getRGB(row, col); // the RGB value of the pixel color
                colorPixel = new Color(pixelColorRGB);
                int intensity = (int) (0.3D * (double) colorPixel.getRed() + 0.59D * (double) colorPixel.getGreen() + 0.11D * (double) colorPixel.getBlue() + 0.5D); //the formula is taken from the code assigment6
                // int intensity = (int) (0.2125 * (double) color.getRed() + 0.7154 * (double) color.getGreen() + 0.0721 * (double) color.getBlue()); // formula from the net
                imageGray.setRGB(row, col, new Color(intensity, intensity, intensity).getRGB());
            }
        }
        return imageGray;
    }

    /**
     * The method outputs the brightness of the pixel.
     * By analogy with Assignment 6
     *
     * @param imageGray grayscale image
     * @return an array of image luminances
     */
    public static int[][] imageToLuminances(BufferedImage imageGray) {
        int[][] luminances = new int[imageGray.getWidth()][imageGray.getHeight()];
        Color color;

        for (int row = 0; row < imageGray.getWidth(); ++row) {
            for (int col = 0; col < imageGray.getHeight(); ++col) {
                int pixelColor = imageGray.getRGB(row, col);
                color = new Color(pixelColor);
                luminances[row][col] = color.getRed();
            }
        }
        return luminances;
    }
}
