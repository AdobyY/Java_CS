package com.shpp.p2p.cs.pvenher.assignment13;


import java.io.File;

public class Test {
    private static final String FOLDER = "test/";

    public static void main(String[] args) {
        File folder = new File(FOLDER);
        File[] files = folder.listFiles();
        assert files != null;
        for (File file : files) {
            System.out.print(file.getName() + ": ");
            Assignment13Part1.main(new String[]{FOLDER + file.getName()});
        }
//        Assignment13Part1.main(new String[]{"test/8_.jpg"});
    }
}
