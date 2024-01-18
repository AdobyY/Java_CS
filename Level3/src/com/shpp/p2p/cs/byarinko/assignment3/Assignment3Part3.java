package com.shpp.p2p.cs.byarinko.assignment3;

import com.shpp.cs.a.console.TextProgram;

public class Assignment3Part3 extends TextProgram {
    @Override
    public void run() {

        // There are small inaccuracies, but the program works
        check(-0.4, 4);
        check(-2, 4);
        check(-0.2, -6);
        check(-0.895, -3);
        check(-0.895, 0);

    }

    // Check method raiseToPower with Math.pow
    private void check(double b, int e) {
        println(Math.pow(b, e) + " is equal " + raiseToPower(b, e));
    }

    // Method that raises parameter base in to power exponent
    private double raiseToPower(double base, int exponent) {
        if (exponent == 0) {
            return 1.0;
        } else if (exponent > 0) {
            double result = 1.0;
            for (int i = 0; i < exponent; i++) {
                result *= base;
            }
            return result;
        } else {
            // Handle negative exponent by taking the reciprocal of the result
            double result = 1.0;
            for (int i = 0; i > exponent; i--) {
                result /= base;
            }
            return result;
        }
    }

}
