package com.shpp.p2p.cs.byarinko.assignment5;

import acm.util.ErrorException;
import com.shpp.cs.a.console.TextProgram;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;


public class Assignment5Part3 extends TextProgram {
    public void run() {
        while (true) {
            String string = readLine("Give me three letters: ");

            // Check if the input has exactly three letters
            if (string.length() != 3) {
                println("Please enter exactly three letters.");
                continue; // Skip the rest of the loop iteration
            }

            ArrayList<String> words = findMatches(string.toLowerCase());

            if (words.isEmpty()) {
                println("   Can't make a word out of these letters.");
            } else {
                for (String item : words) {
                    println("   " + item);
                }
            }
        }
    }

    /**
     * Outputs words that can be made from given letters
     * @param string letters with which we are looking for a match
     * @return all matches words
     */
    private ArrayList<String> findMatches(String string) {
        char[] threeLetters = string.toCharArray();

        ArrayList<String> arrayOfWords = readWords("assets/en-dictionary.txt");

        ArrayList<String> matchesWords = new ArrayList<>();

        // Through every word
        for (String word : arrayOfWords) {
            int count = 0;
            // Through every letter in the word
            for (char letter : word.toCharArray()) {
                if (count == 3) break;
                // If the letter in the word matches, then we increase the counter
                if (letter == threeLetters[count]) {
                    count++;
                }
            }
            // If all three letters match add the word to the array
            if (count == 3) {
                matchesWords.add(word);
            }
        }
        return matchesWords;
    }

    /**
     * Reads the file whose path is specified parameter and return
     * list of all words
     *
     * @param path path to the file
     * @return ArrayList of all words from the file
     */
    private ArrayList<String> readWords(String path) {
        ArrayList<String> words;
        try {
            BufferedReader br = new BufferedReader(new FileReader(path));

            words = new ArrayList<>();

            while (true) {
                String word = br.readLine();
                if (word == null)
                    break;

                words.add(word);
            }

            br.close();

        } catch (IOException e) {
            throw new ErrorException(e);
        }

        return words;
    }
}
