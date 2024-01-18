package com.shpp.p2p.cs.byarinko.assignment3;

import com.shpp.cs.a.console.TextProgram;

public class Assignment3Part2 extends TextProgram {
    @Override
    public void run() {

        // Read the number
        int num = readInt("Enter a number: ");

        // Call the method
        hailstoneNumbers(num);
    }

    // Method that makes hailstone numbers
    private void hailstoneNumbers(int num) {
        // While num not equal one divide by two if it's even
        // and multiply by three and plus one if it's odd
        while (num != 1) {
            if (num % 2 == 1){
                print(num + " is odd so I make 3n + 1: ");
                num = num * 3 + 1;
                println(num);
            }else {
                print(num + " is even so I take half: ");
                num /= 2;
                println(num);
            }
        }
        println("The end.");
    }
}
