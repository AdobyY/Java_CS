package com.shpp.p2p.cs.byarinko.assignment3;

import com.shpp.cs.a.console.TextProgram;
import java.util.Random;

public class Assignment3Part5 extends TextProgram {
    @Override
    public void run() {
        int totalMoney = 0;
        int gamesPlayed = 0;

        // Run the following code block as long as totalMoney is less than 20
        while (totalMoney < 20) {
            int moneyEarned = playGame(); // Call the playGame() method and store the result in moneyEarned
            totalMoney += moneyEarned; // Add moneyEarned to totalMoney.
            gamesPlayed++; // Increment the gamesPlayed counter

            // Print the money earned in the current game and the total money
            println("This game, you earned $" + moneyEarned);
            println("Your total is $" + totalMoney);
        }

        // Print the number of games played to reach $20.
        println("It took " + gamesPlayed + " games to earn $20");

    }

    // Define a private method playGame() that returns an integer
    private int playGame() {
        int earnedPerGame = 1;
        Random coin = new Random();// Create a random number generator object

        while (true){
            boolean isHead = coin.nextBoolean(); // Generate a random boolean value (true or false)

            // If isHead is true (representing a "heads" outcome), double the earnedPerGame
            if(isHead){
                earnedPerGame *= 2;
            }else return earnedPerGame; // If isHead is false (representing a "tails" outcome), return earnedPerGame
        }
    }
}
