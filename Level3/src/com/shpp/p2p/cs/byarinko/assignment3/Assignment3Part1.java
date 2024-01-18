package com.shpp.p2p.cs.byarinko.assignment3;

import com.shpp.cs.a.console.TextProgram;

public class Assignment3Part1 extends TextProgram {
    // Define global variables in which the minutes of aerobic exercises are stored
    private static int day1, day2, day3, day4, day5, day6, day7;

    @Override
    public void run() {

        // Request information from the user
        day1 = readInt("How many minutes did you do on day 1?");
        day2 = readInt("How many minutes did you do on day 2?");
        day3 = readInt("How many minutes did you do on day 3?");
        day4 = readInt("How many minutes did you do on day 4?");
        day5 = readInt("How many minutes did you do on day 5?");
        day6 = readInt("How many minutes did you do on day 6?");
        day7 = readInt("How many minutes did you do on day 7?");

        // Check the number of days where the minutes are more than 30
        int numOfDaysMoreThan30 = countNumOfDays(30);

        // Check the number of days where the minutes are more than 40
        int numOfDaysMoreThan40 = countNumOfDays(40);

        // Display info about Cardiovascular health
        cardio(numOfDaysMoreThan30);

        // Display info about Blood pressure
        bloodPressure(numOfDaysMoreThan40);
    }

    // Count the number of days where classes were more than N minutes
    int countNumOfDays(int n) {
        int num = 0;

        if (day1 >= n) num += 1;
        if (day2 >= n) num += 1;
        if (day3 >= n) num += 1;
        if (day4 >= n) num += 1;
        if (day5 >= n) num += 1;
        if (day6 >= n) num += 1;
        if (day7 >= n) num += 1;

        return num;
    }

    // Makes a conclusion on cardiovascular health
    private void cardio(int n) {
        println("Cardiovascular health:");

        if(n>=5){
            println("\tGreat job! You've done enough exercise for cardiovascular health.");
        }else{
            println("\tYou needed to train hard for at least " + (5 - n) + " more day(s) a week!");
        }
    }

    // Makes a conclusion on blood pressure
    private void bloodPressure(int n) {
        println("Blood pressure:");

        if(n>=3){
            println("\tGreat job! You've done enough exercise to keep a low blood pressure.");
        }else{
            println("\tYou needed to train hard for at least " + (3 - n) + " more day(s) a week!");
        }
    }
}
