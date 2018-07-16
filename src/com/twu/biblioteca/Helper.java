package com.twu.biblioteca;

import java.util.Comparator;
import java.util.Scanner;
import java.util.stream.Stream;

public class Helper {

    public static String readUserInput() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    public static void appendSpaces(StringBuilder builder, int numSpaces) {
        for (int i = 0; i < numSpaces; i++)
            builder.append(" ");
    }

    public static int getLongestString(Stream<String> streamOfStrings) {
        return streamOfStrings.max(Comparator.comparing(String::length)).orElse("").length();
    }

    public static void print(String stringToPrint) {
        System.out.println(stringToPrint);
    }
}
