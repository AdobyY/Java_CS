package com.shpp.p2p.cs.byarinko.assignment5;

import com.shpp.cs.a.console.TextProgram;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Assignment5Part7 extends TextProgram {
    @Override
    public void run() {
        print(extractColumn("assets/file.csv", 2));
    }

    /**
     * Opens a CSV file named filename, and returns in the form of an array all values that
     * are in the columnIndex column (0 for the first column, 1 for the second, and so on).
     * If the file does not exist, the method returns null.
     *
     * @param filename    path to file
     * @param columnIndex desired column
     * @return all value that are in the columnIndex
     */
    private ArrayList<String> extractColumn(String filename, int columnIndex) {
        ArrayList<String> answer = new ArrayList<>();

        ArrayList<String> fileLines;
        try {
            fileLines = readFileLines(filename);
        } catch (IOException e) {
            return null;
        }

        for (String line : fileLines) { // for each line in file
            answer.add(parseCSV(line, columnIndex + 1));
        }

        return answer;
    }

    /**
     * Takes a comma-separated string and returns the desired column
     *
     * @param line        one line of CSV file
     * @param columnIndex desired column
     * @return the value contained in this column
     */
    private String parseCSV(String line, int columnIndex) {
        String wordToAppend = "";
        int numberOfCommas = 0;
        boolean insideQuotes = false;

        for (char letter : line.toCharArray()) { // For each letter in line
            if (letter == '"') {
                insideQuotes = !insideQuotes;
            } else if (letter == ',' && !insideQuotes) {
                numberOfCommas++;
                if (numberOfCommas == columnIndex) {
                    return wordToAppend;
                }
                wordToAppend = ""; // Reset the wordToAppend for the next column

            } else {
                wordToAppend += letter;
            }
        }
        return wordToAppend;
    }

    /**
     * Reads the file whose path is passed in the parameter and returns an array of lines.
     * If file not found returns null
     *
     * @param filename path to the file
     * @return ArrayList of lines
     */
    private ArrayList<String> readFileLines(String filename) throws IOException {
        ArrayList<String> lines = new ArrayList<>();

        // Read file
        BufferedReader br = new BufferedReader(new FileReader(filename));

        // Read lines
        while (true) {
            String line = br.readLine();
            if (line == null)
                break;

            lines.add(line);
        }

        // Close file
        br.close();

        return lines;
    }
}
