package com.shpp.p2p.cs.byarinko.assignment6.tm;

public class ToneMatrixLogic {
    /**
     * Given the contents of the tone matrix, returns a string of notes that should be played
     * to represent that matrix.
     *
     * @param toneMatrix The contents of the tone matrix.
     * @param column     The column number that is currently being played.
     * @param samples    The sound samples associated with each row.
     * @return A sound sample corresponding to all notes currently being played.
     */
    public static double[] matrixToMusic(boolean[][] toneMatrix, int column, double[][] samples) {

        double[] result = new double[ToneMatrixConstants.sampleSize()];

        // Iterate through the rows of the matrix
        for (int row = 0; row < toneMatrix.length; row++) {
            if (toneMatrix[row][column]) {
                // If the cell is enabled, add the appropriate sound
                for (int i = 0; i < result.length; i++) {
                    result[i] += samples[row][i];
                }
            }
        }

        return normalize(result);
    }

    /**
     * Normalize the result of ToneMatrix
     *
     * @param result String of notes that should be played
     * @return Normalize result
     */
    private static double[] normalize(double[] result) {
        // Find max Amplitude
        double maxAmplitude = 0.0;
        for (double sample : result) {
            maxAmplitude = Math.max(maxAmplitude, Math.abs(sample));
        }

        // Normalize
        if (maxAmplitude > 0.0) {
            for (int i = 0; i < result.length; i++) {
                result[i] /= maxAmplitude;
            }
        }

        return result;
    }
}
