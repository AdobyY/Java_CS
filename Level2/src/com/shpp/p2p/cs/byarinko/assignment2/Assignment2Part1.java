package com.shpp.p2p.cs.byarinko.assignment2;

import com.shpp.cs.a.console.TextProgram;


public class Assignment2Part1 extends TextProgram {
    public void run() {

        // Collect data from user for a quadratic equation
        double a = readDouble("Please enter a");

        if (a == 0) {
            println("Wrong");
            return;
        }

        double b = readDouble("Please enter b");
        double c = readDouble("Please enter c");

        // Calculate the discriminant
        double D = b * b - 4 * a * c;

        // Check if D<0
        if (D<0){
            print("There are no real roots");
            return;
        };

        // Find solution
        double x1 = (-b + Math.sqrt(D)) / (2 * a);
        double x2 = (-b - Math.sqrt(D)) / (2 * a);

        // Display the result
        if (x1 == x2){
            print("There is one root: " + x1);
        }else{
            print("There are two roots: " + x1 + " and " + x2);
        }
    }
}
