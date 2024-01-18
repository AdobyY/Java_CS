package com.shpp.p2p.cs.byarinko.a.lectures.lec15;

public class HelloWorld {

    // raw:
    // 1) javac -cp com/shpp/cs/rshmelev/consoletest/HelloWorld.java
    // 2) java -cp ./src com.shpp.cs.rshmelev.consoletest.old.fifteen.HelloWorld 1 2

    // intellij idea:
    // 1) compile and go to project root dir
    // 2) java -cp ./out/production/cs-console com.shpp.cs.rshmelev.consoletest.old.fifteen.HelloWorld 1 2

	public static void main(String[] args) {

        System.out.println("Here are the parameters you passed in: ");

		/* List off all the command-line arguments. */
		for (String arg: args) {
			System.out.println(" - " + arg);
		}

		System.out.println("You are a nice person! Have a good day.");
	}
}