package com.shpp.p2p.cs.byarinko.assignment3;

import com.shpp.cs.a.console.TextProgram;

/**
 * Created by rshmelev on 7/8/15.
 */
public class FunWithText extends TextProgram {
    /* Time to delay between characters. */
    private static final int PAUSE_TIME = 75;

    /**
     * Prints out a line of text one character at a time.
     *
     * @param text The text to print.
     */
    private void printCharByChar(String text) {
		/* Iterate across the characters one at a time, printing each. */
        for (int i = 0; i < text.length(); i++) {
            char ch = text.charAt(i);
            print(ch);
            pause(PAUSE_TIME);
        }
        println();
    }

    /**
     * Given a string, returns the reverse of that string.
     *
     * @param text The text to reverse.
     * @return The reverse of that string.
     */
    private String reverse(String text) {
        String result = "";
        for (int i = 0; i < text.length(); i++) {
            result = text.charAt(i) + result;
        }
        return result;

		/* Here's an alternative implementation.
		 *
		 * String result = "";
		 * for (int i = text.length() - 1; i >= 0; i--) {
		 *   result = result + text.charAt(i);
		 * }
		 *
		 * return result;
		 */

    }

    /**
     * Given a string, returns a new string consisting of all of the
     * letters that appear in the original string in the order in which
     * they appear.
     *
     * @param text The input string.
     * @return The letters of that string in the order in which they appear.
     */
    private String lettersIn(String text) {
        String result = "";
        for (int i = 0; i < text.length(); i++) {
			/* Test if the current character is a letter. If so, add it. */
            char ch = text.charAt(i);
            if (Character.isLetter(ch)) {
                result += ch;
            }
        }
        return result;
    }

    /**
     * Determines whether the input string is a palindrome - a string that's written
     * the same way forwards and backwards.
     *
     * @param text The text in question.
     * @return Whether it's a palindrome.
     */
    private boolean isPalindrome(String text) {
		/* To avoid sensitivity to case and to punctuation, convert
		 * the string to upper case and remove everything other than
		 * the letters.
		 */
        text = lettersIn(text.toUpperCase());
        return text.equals(reverse(text));
    }

    public void run() {
        while (true) {
            String text = readLine("Enter some text: ");
//            printCharByChar("Is that a palindrome? " + isPalindrome(text));
            println("Is that a palindrome? " + isPalindrome(text));
        }
    }
}