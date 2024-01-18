package com.shpp.p2p.cs.pvenher.assignment13;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import static com.shpp.p2p.cs.pvenher.assignment13.AlgorithmBFS.*;

/**
 * Program gets an array of Strings where first string is filename of image.
 * Program counts silhouettes on image.
 */
public class Assignment13Part1 implements Constants {
    
    private static final int BLACK_COLOR = 0xff000000;
    private static final int WHITE_COLOR = 0xffffffff;

    /**
     * boolean array of arrays for output image where true is a black points
     */
    public static boolean[][] boolOutImage;

    /**
     * Count silhouettes on image by filename
     *
     * @param args - string with filename or path to file
     */
    public static void main(String[] args) {
        try {
            BufferedImage image = getImage(args);
            if (image != null) {
                boolOutImage = new boolean[image.getHeight()][image.getWidth()];
                int count = countSilhouettes(image);
                if (DEV_MODE) {
                    createOutputImage(image);
                }
                System.out.println(count);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Count silhouettes on image
     *
     * @param image - image for analysing
     * @return - count of silhouettes
     */
    private static int countSilhouettes(BufferedImage image) {
        int imageHeight = image.getHeight();
        int imageWidth = image.getWidth();

        // Boolean array of array where true is visited point
        boolean[][] visited = new boolean[imageHeight][imageWidth];
        Color backColor = new Color(image.getRGB(0, 0), true);
        visited[0][0] = true;

        ArrayList<Integer> results = new ArrayList<>();

        for (int row = 0; row < imageHeight; row++) {
            for (int col = 0; col < imageWidth; col++) {
                // if point is visited
                if (!checkPoint(image, visited, col, row, backColor)) continue;

                int res = doBFC(image, visited, col, row, backColor);
                if (res != 0 && DEV_MODE) System.out.println(res);
                results.add(res);
            }
        }

        return countImportanceSilhouettes(results);
    }

    private static int countImportanceSilhouettes(ArrayList<Integer> results) {

        double maximum = findMax(results);

        int count = 0;
        for (Integer result : results) {
            if (result / maximum >= IMPORTANCE / 100.) {
                count++;
            }
        }

        return count;
    }

    private static double findMax(ArrayList<Integer> results) {
        double maximum = results.get(0);
        for (int i = 1; i < results.size(); i++) {
            if (maximum < results.get(i))
                maximum = results.get(i);
        }
        return maximum;
    }

    /**
     * Getting image from image by filename
     *
     * @param args - string with filename or path to file
     * @return - image from file
     */
    private static BufferedImage getImage(String[] args) {

        String filename = args.length != 0 ? args[0] : "test.jpg";
        File file = new File(PATH_TO_DIRECTORY + filename);

        BufferedImage image = null;
        try {
            image = ImageIO.read(file);
        } catch (IOException e) {
            System.err.println("File not found");
        }

        return image;
    }

    /**
     * Create output image in dev mode
     *
     * @param image - original image
     */
    private static void createOutputImage(BufferedImage image) {
        BufferedImage img = getBufferedImage(image);

        try {
            File file = new File("MyFile.png");
            ImageIO.write(img, "PNG", file);

            // Open file
            Desktop desktop = Desktop.getDesktop();
            if (file.exists()) desktop.open(file);

            // Delete file
            file.deleteOnExit();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        
    }

    /**
     * Create a BufferedImage from matrix of boolean values
     *
     * @param image - original image
     * @return - image from matrix
     */
    private static BufferedImage getBufferedImage(BufferedImage image) {
        BufferedImage img = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_INT_ARGB);

        for (int a = 0; a < boolOutImage.length; a++) {
            for (int b = 0; b < boolOutImage[0].length; b++) {
                if (boolOutImage[a][b]) {
                    // Black color
                    img.setRGB(b, a, BLACK_COLOR);
                } else {
                    // White color
                    img.setRGB(b, a, WHITE_COLOR);
                }
            }
        }
        return img;
    }
}