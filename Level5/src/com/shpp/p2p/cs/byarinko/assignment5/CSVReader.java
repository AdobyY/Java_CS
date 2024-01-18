package com.shpp.p2p.cs.byarinko.assignment5;

import com.shpp.cs.a.console.TextProgram;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class CSVReader extends TextProgram {
    @Override
    public void run() {
        ArrayList<String> columnValues = extractColumn("assets/file.csv", 2);
        for (String value : columnValues) {
            println(value);
        }
    }

    private ArrayList<String> extractColumn(String filename, int columnIndex) {
        ArrayList<String> answer = new ArrayList<>();

        ArrayList<String> fileLines = readFileLines(filename);

        for (String line : fileLines) {
            answer.add(parseCSVLine(line, columnIndex));
        }

        return answer;
    }

    private String parseCSVLine(String line, int columnIndex) {
        StringBuilder wordToAppend = new StringBuilder();
        int currentColumnIndex = 0;
        boolean insideQuotes = false;

        for (char letter : line.toCharArray()) {
            if (letter == '"') {
                insideQuotes = !insideQuotes;
            } else if (letter == ',' && !insideQuotes) {
                currentColumnIndex++;
                if (currentColumnIndex == columnIndex) {
                    return wordToAppend.toString();
                }
                wordToAppend.setLength(0); // Reset the wordToAppend for the next column
            } else {
                wordToAppend.append(letter);
            }
        }

        return wordToAppend.toString();
    }

    private ArrayList<String> readFileLines(String filename) {
        ArrayList<String> lines = new ArrayList<>();

        try {
            BufferedReader br = new BufferedReader(new FileReader(filename));

            while (true) {
                String line = br.readLine();
                if (line == null)
                    break;

                lines.add(line);
            }

            br.close();

        } catch (IOException e) {
            return null;
        }

        return lines;
    }
}
