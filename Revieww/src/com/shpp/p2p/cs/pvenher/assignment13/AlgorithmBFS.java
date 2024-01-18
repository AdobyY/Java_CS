package com.shpp.p2p.cs.pvenher.assignment13;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.LinkedList;
import java.util.Queue;

import static com.shpp.p2p.cs.pvenher.assignment13.Assignment13Part1.boolOutImage;

public class AlgorithmBFS implements Constants {

    /**
     * Using BFS algorithm for analysing image
     *
     * @param image     - original image
     * @param visited   - boolean matrix for analysing
     * @param column    - x-coordinate of pixel
     * @param row       - y-coordinate of pixel
     * @param backColor - background color
     * @return - count of pixels of silhouettes
     */
    public static int doBFC(BufferedImage image, boolean[][] visited, int column, int row, Color backColor) {

        int res = 1;
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{column, row});

        int[] offsets = {1, -1};
        int[] iterations = {0, 1};

        while (!queue.isEmpty()) {
            int[] point = queue.remove();

            for (int offset : offsets) {
                for (int iteration : iterations) {
                    int[] firstPoint = {point[0] + offset * iteration, point[1] + offset * (iteration == 0 ? 1 : 0)};
                    if (checkPoint(image, visited, firstPoint[0], firstPoint[1], backColor)) {
                        queue.add(firstPoint);
                        res++;
                    }
                }
            }
        }

        return res;
    }

    /**
     * Check if point is a part of silhouette
     *
     * @param image     - original image
     * @param visited   - boolean array of arrays for analysing
     * @param column    - x-coordinate of pixel
     * @param row       - y-coordinate of pixel
     * @param backColor - background color
     * @return - count of pixels of silhouettes
     */
    public static boolean checkPoint(BufferedImage image, boolean[][] visited, int column, int row, Color backColor) {
        if (isExistingPoint(column, row, image) && !visited[row][column]) {
            visited[row][column] = true;
            if (isDiffColor(image, column, row, backColor)) {
                boolOutImage[row][column] = true;
                return true;
            }
        }

        return false;
    }

    /**
     * Check if two colors are different
     *
     * @param image     - original image
     * @param column    - x-coordinate of pixel
     * @param row       - y-coordinate of pixel
     * @param backColor - background color
     * @return - boolean if colors are different
     */
    public static boolean isDiffColor(BufferedImage image, int column, int row, Color backColor) {
        Color pointColor = new Color(image.getRGB(column, row), true);
        int dist = countDistColors(backColor, pointColor);
        return dist > DIFFERENT_COLORS_DIST;
    }

    /**
     * @param firstColor  - color of first pixel
     * @param secondColor - color of second pixel
     * @return - distance between pixels
     */
    public static int countDistColors(Color firstColor, Color secondColor) {
        // Distance between colors
        return Math.abs(firstColor.getRed() - secondColor.getRed()) +
                Math.abs(firstColor.getGreen() - secondColor.getGreen()) +
                Math.abs(firstColor.getBlue() - secondColor.getBlue()) +
                Math.abs(firstColor.getAlpha() - secondColor.getAlpha());
    }

    /**
     * Check if point is exist
     *
     * @param column - x-coordinate of pixel
     * @param row    - y-coordinate of pixel
     * @param image  - original image
     * @return - boolean if point is exist
     */
    private static boolean isExistingPoint(int column, int row, BufferedImage image) {
        return !(row < 0 || column < 0 || column > image.getWidth() - 1 || row > image.getHeight() - 1);
    }
}