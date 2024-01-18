package com.shpp.p2p.cs.odenysenko.assignment12;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

public class Assignment12Part1 implements Constants {

    private static int sizeSilhouette; // silhouette size in pixels

    public static void main(String[] args) {
        BufferedImage image = null;
        try {
            if (args.length < 1) {//if there are no parameters
                image = ImageIO.read(new File(TEST_FILE));
            } else {
                image = ImageIO.read(new File(args[0]));
            }
            showTheImage(image); // Show the image

            BufferedImage imageGray = HistogramEqualizationImageTransforms.toGrayscale(image); // grayscale image
            int[] histogram = HistogramEqualizationImageTransforms.histogram(image); // image histogram
            BufferedImage binaryImage = ImageBinarization.imageBinary(histogram, imageGray); // binarized image

            System.out.println("There are " + findSilhouettes(binaryImage) + " silhouettes found in this image");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * The method displays the image on the screen
     *
     * @param image User image
     */
    private static void showTheImage(BufferedImage image) {
        JFrame frame = new JFrame("Зображення"); //creating a window
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // close the window
        frame.getContentPane().add(new JLabel(new ImageIcon(image))); // display the image
        frame.pack(); //window size adjustment
        frame.setVisible(true); // window visibility
    }

    /**
     * The method counts the number of silhouettes on a binarized image
     *
     * @param binaryImage the binarized image
     * @return number of silhouettes
     */
    private static int findSilhouettes(BufferedImage binaryImage) {
        int countSilhouettes = 0;
        Color colorSilhouettes = colorSilhouettes(binaryImage); // silhouette color
        Color passedPixel = Color.blue; // the color of the passed pixel
        int passedPixelRGB = passedPixel.getRGB();

        for (int col = 0; col < binaryImage.getHeight(); col++) { //we go through the entire image
            for (int row = 0; row < binaryImage.getWidth(); row++) {
                Color colorPixel = new Color(binaryImage.getRGB(row, col)); // pixel color
                if (colorPixel.equals(colorSilhouettes)) { //the color of the pixel corresponds to the color of the silhouette
                    dfs(row, col, binaryImage, passedPixelRGB, colorSilhouettes); // we recursively walk along the silhouette
                    if (((double) sizeSilhouette / (binaryImage.getHeight() * binaryImage.getWidth())) >= MINIMUM_SILHOETTE_SIZE) {
                        countSilhouettes++;
                    }
                    sizeSilhouette = 0;
                }
            }
        }
        return countSilhouettes;
    }

    /**
     * Recursive depth-of-silhouette search
     *
     * @param row              X value of the first pixel of the silhouette
     * @param col              value of the first pixel of the silhouette
     * @param binaryImage      the image
     * @param passedPixelRGB   the RGB color of the passed pixel
     * @param colorSilhouettes the color of the silhouette
     */
    private static void dfs(int row, int col, BufferedImage binaryImage, int passedPixelRGB, Color colorSilhouettes) {
        Color colorPixel = new Color(binaryImage.getRGB(row, col)); // pixel color
        if (colorPixel.equals(colorSilhouettes) && row < (binaryImage.getWidth() - 1) && row > 0 && col < (binaryImage.getHeight() - 1) && col > 0) {
            sizeSilhouette++;
            binaryImage.setRGB(row, col, passedPixelRGB); // we notice the pixel in blue
            dfs(row, col - 1, binaryImage, passedPixelRGB, colorSilhouettes); // up
            dfs(row - 1, col, binaryImage, passedPixelRGB, colorSilhouettes); // to the left
            dfs(row + 1, col, binaryImage, passedPixelRGB, colorSilhouettes); // to the right
            dfs(row, col + 1, binaryImage, passedPixelRGB, colorSilhouettes); // down
        }
    }

    /**
     * The method determines the background color by counting pixels of which color is more along the edges of the image
     *
     * @param binaryImage the binarized image
     * @return background color
     */
    private static Color colorSilhouettes(BufferedImage binaryImage) {
        Color colorSilhouettes;
        Color color;
        int black = 0;
        int white = 0;

        for (int row = 0; row < binaryImage.getWidth(); row++) {
            color = new Color(binaryImage.getRGB(row, 0)); // top frame
            if (color.equals(Color.white)) {
                white++;
            } else {
                black++;
            }
            color = new Color(binaryImage.getRGB(row, (binaryImage.getHeight() - 1))); // bottom frame
            if (color.equals(Color.white)) {
                white++;
            } else {
                black++;
            }
        }
        for (int col = 0; col < binaryImage.getHeight(); col++) {
            color = new Color(binaryImage.getRGB(0, col)); // left frame
            if (color.equals(Color.white)) {
                white++;
            } else {
                black++;
            }
            color = new Color(binaryImage.getRGB((binaryImage.getWidth() - 1), col)); // right frame
            if (color.equals(Color.white)) {
                white++;
            } else {
                black++;
            }
        }
        // System.out.println(black+"  "+white);
        if (black < white) {
            colorSilhouettes = Color.black;
        } else {
            colorSilhouettes = Color.white;
        }
        return colorSilhouettes;
    }
}
