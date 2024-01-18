package com.shpp.p2p.cs.byarinko.assignment5;

import com.shpp.cs.a.console.TextProgram;

public class Assignment5Part2 extends TextProgram {
    public void run() {
        /* Sit in a loop, reading numbers and adding them. */
        while (true) {
            String n1 = readLine("Enter first number:  ");
            String n2 = readLine("Enter second number: ");
            println(n1 + " + " + n2 + " = " + addNumericStrings(n1, n2));
            println();
        }
    }

    /**
     * Given two string representations of nonnegative integers, adds the
     * numbers represented by those strings and returns the result.
     *
     * @param n1 The first number.
     * @param n2 The second number.
     * @return A String representation of n1 + n2
     */
    private String addNumericStrings(String n1, String n2) {
        // Reverse the input strings
        StringBuilder reversedN1 = new StringBuilder(n1).reverse();
        StringBuilder reversedN2 = new StringBuilder(n2).reverse();

        StringBuilder result = new StringBuilder();
        int carry = 0;

        int maxLength = Math.max(reversedN1.length(), reversedN2.length());

        for (int i = 0; i < maxLength; i++) {
            int digit1 = (i < reversedN1.length()) ? Character.getNumericValue(reversedN1.charAt(i)) : 0;
            int digit2 = (i < reversedN2.length()) ? Character.getNumericValue(reversedN2.charAt(i)) : 0;

            int sum = digit1 + digit2 + carry;
            carry = sum / 10;
            result.append(sum % 10);
        }

        // If there's a carry left, append it
        if (carry > 0) {
            result.append(carry);
        }

        // Reverse the result to get the correct sum
        return result.reverse().toString();
    }

}
