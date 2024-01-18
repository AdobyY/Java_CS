package com.shpp.p2p.cs.byarinko.assignment10;

public class Test {
    public static void main(String[] args){
        Assignment10Part1.main(new String[]{"2 - 4 ^ 4.1 *a + b / c + 123", "a=2", "b=745.6", "c=556.789"});
        Assignment10Part1.main(new String[]{"2", "a=2", "b=745.6", "c=556.789", "34"});
        Assignment10Part1.main(new String[]{"-1^-a/a+a^-b", "a=2", "b=-1"});
        Assignment10Part1.main(new String[]{"-1^a/a+a^-b", "a=2", "b=-1"});
        Assignment10Part1.main(new String[]{"1.0/2+2^b",  "b=1"});
        Assignment10Part1.main(new String[]{"-1^-a/a+a^-b", "a=-2", "b=-1"});
        Assignment10Part1.main(new String[]{"-1^-a/a+a^+b", "a=-2", "b=-1"});
        Assignment10Part1.main(new String[]{"-1^-a/a+a^-b", "a=-2", "b=-1"});
        Assignment10Part1.main(new String[]{"-1.0^-a/a+a^+b", "a=-2.0", "b=-1.0"});
        Assignment10Part1.main(new String[]{"-1.0^-a/a+a^+b", "a=-2.0"});
        Assignment10Part1.main(new String[]{"-1.0^-a/a+a", "a=- 2.0"});
        Assignment10Part1.main(new String[]{"-1.0^--2/4+4^+-3"});
        Assignment10Part1.main(new String[]{});

    }
}
