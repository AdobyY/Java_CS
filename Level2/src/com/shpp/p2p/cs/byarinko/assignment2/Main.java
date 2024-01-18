package com.shpp.p2p.cs.byarinko.assignment2;

import com.shpp.cs.a.console.TextProgram;

public class Main extends TextProgram {
    public void run() {
        int num = readInt("Введіть число: ");

        for (int i = 0; i < num; i++) {
            if (i % 3 == 0 && i % 5 == 0) {
                println("Fizz Buzz");
            } else if (i % 3 == 0) {
                println("Fizz");
            } else if (i % 5 == 0) {
                println("Buzz");
            } else {
                println(i);
            }
        }
    }
}