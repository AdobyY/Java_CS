package com.shpp.p2p.cs.byarinko.assignment12;

import acm.graphics.GImage;

/**
 * The program counts the number of silhouettes in the picture
 * The path to the image is passed to the program input as the first parameter
 */
public class Assignment12Part1 {
    /**
     * A matrix of zeros and ones, where one of them corresponds to the background,
     * the other to the silhouette
     */
    private static int[][] booleanMatrix;
    /**
     * The probable background color needed to separate the silhouette from the background
     */
    private static int bgColour;
    /**
     * The difference by which a pixel must be different from the background
     * to be considered a silhouette
     */
    private static int difference;
    /**
     * Determines what should be the silhouette and what should
     * be the background in the boolean matrix (1 by default)
     */
    private static int background = 1;
    /**
     * Determines the percentage below which the value will be considered garbage
     */
    private static final int trashPercentage = 10;

    /**
     * @param args a picture in which you need to count the number of silhouettes
     */
    public static void main(String[] args) {
        String fileName;
        if (args.length > 0) {
            fileName = args[0];
        } else {
            fileName = "assets/test.jpg";
        }

        GImage image = new GImage(fileName);

        bgColour = defineBackground(image.getPixelArray());
        difference = calculateDifference(image.getPixelArray());

        booleanMatrix = new int[(int) image.getWidth()][(int) image.getHeight()];
        defineBooleanMatrix(image.getPixelArray());
        defineBackgroundInBooleanMatrix();

        int result = findSilhouettes(booleanMatrix);

        System.out.println("Number of silhouettes in " + fileName + ": " + result);
    }

    /**
     * Defines the background color by taking the average of the colors of all pixels
     *
     * @param image picture in which the background should be defined
     * @return background colour
     */
    private static int defineBackground(int[][] image) {
        int colour = 0;

        for (int[] x : image) {
            for (int y = 0; y < image[0].length; y++) {
                colour += GImage.getRed(x[y]);
            }
        }

        // Return the average
        return colour / (image.length * image[0].length);
    }

    /**
     * Calculates the standard deviation to determine how much the silhouette should differ from the background
     *
     * @param image a 2D array representing the pixel colors of the image
     * @return the calculated standard deviation of the pixel colors
     */
    private static int calculateDifference(int[][] image) {
        int totalPixels = image.length * image[0].length;
        long sum = 0;

        for (int[] x : image) {
            for (int y = 0; y < image[0].length; y++) {
                int color = GImage.getRed(x[y]); // Can be any color
                sum += (long) Math.pow(color - bgColour, 2);
            }
        }

        return (int) Math.sqrt((double) sum / totalPixels);
    }

    /**
     * Creates a matrix of 0's and 1's, allowing the silhouette to be distinguished from the background
     *
     * @param image a 2D array on the basis of which you need to create a matrix
     */
    private static void defineBooleanMatrix(int[][] image) {
        for (int x = 0; x < image.length; x++) {
            for (int y = 0; y < image[0].length; y++) {
                if (GImage.getRed(image[x][y]) > bgColour - difference &&
                        GImage.getRed(image[x][y]) < bgColour + difference) {
                    booleanMatrix[y][x] = 0;
                } else {
                    booleanMatrix[y][x] = 1;
                }
            }
        }
    }

    /**
     * Determines what will be considered a silhouette in the Boolean matrix, one or zero
     */
    private static void defineBackgroundInBooleanMatrix() {
        int zeroCount = 0;
        int oneCount = 0;

        for (int x = 0; x < booleanMatrix.length; x++) {
            for (int y = 0; y < booleanMatrix[0].length; y++) {
                if (x < 2 || y < 2 || booleanMatrix[x][y] == 0) {
                    zeroCount++;
                }
                if (x < 2 || y < 2 || booleanMatrix[x][y] == 1) {
                    oneCount++;
                }
            }
        }
        if (oneCount > zeroCount) {
            background = 1;
        } else {
            background = 0;
        }
    }

    /**
     * Counts the number of silhouettes in a binary matrix using dfs.
     * Silhouettes with a size less than 10% of the total matrix size are considered as trash.
     *
     * @param matrix a 2D binary array where 1 or 0 represents silhouette (depends on the background field)
     * @return the count of valid silhouettes in the matrix
     */
    public static int findSilhouettes(int[][] matrix) {
        boolean[][] visited = new boolean[matrix.length][matrix[0].length];
        int silhouetteCount = 0;

        for (int x = 0; x < matrix.length; x++) {
            for (int y = 0; y < matrix[0].length; y++) {
                // Check if the current pixel is part of a silhouette
                if (matrix[x][y] == Math.abs(background - 1)) {
                    int size = dfs(matrix, visited, x, y);

                    if (size >= matrix.length / trashPercentage) {
                        silhouetteCount++;
                    }
                }
            }
        }

        return silhouetteCount;
    }

    /**
     * Depth-First Search method used to explore connected cells in a 2D matrix.
     * It calculates the size of the connected cells starting from the specified row and column.
     *
     * @param matrix    a 2D array representing the matrix where the search is performed
     * @param visited a 2D boolean array to keep track of visited cells
     * @param row     the current row index
     * @param col     the current column index
     * @return the size of the connected region starting from the given row and column
     */
    private static int dfs(int[][] matrix, boolean[][] visited, int row, int col) {
        int rows = matrix.length;
        int cols = matrix[0].length;

        // Check if the current cell is out of bounds, a background cell, or already visited
        if (row < 0 || row >= rows || col < 0 || col >= cols || matrix[row][col] == background || visited[row][col]) {
            return 0;
        }

        visited[row][col] = true;

        int size = 1;

        size += dfs(matrix, visited, row + 1, col);
        size += dfs(matrix, visited, row - 1, col);
        size += dfs(matrix, visited, row, col + 1);
        size += dfs(matrix, visited, row, col - 1);

        return size;
    }
}