package com.newsagent.cli;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Scanner;
import java.util.function.DoublePredicate;
import java.util.function.IntPredicate;
import java.util.function.Predicate;

/**
 * Input validation for every CLI menu.
 * Each method keeps asking until the user enters valid data,
 * so a wrong value never sends the user back to the menu.
 * The rules come from each entity's verify methods (the ones tested by JUnit).
 */
public final class InputHelper {

    private InputHelper() {
    }

    /**
     * Reads a whole number; asks again if the user types text such as "abc".
     */
    public static int readInt(Scanner scanner, String prompt) {
        while (true) {
            System.out.print(prompt);
            String text = scanner.nextLine().trim();
            try {
                return Integer.parseInt(text);
            } catch (NumberFormatException e) {
                System.out.println("Invalid input: \"" + text + "\" is not a whole number. Please enter the data again.");
            }
        }
    }

    /**
     * Reads a whole number that must also pass a rule; asks again until it does.
     */
    public static int readInt(Scanner scanner, String prompt, IntPredicate rule, String error) {
        while (true) {
            int value = readInt(scanner, prompt);
            if (rule.test(value)) {
                return value;
            }
            System.out.println("Error: " + error + ". Please enter the data again with valid input.");
        }
    }

    /**
     * Reads a decimal number that must pass a rule; asks again until it does.
     */
    public static double readDouble(Scanner scanner, String prompt, DoublePredicate rule, String error) {
        while (true) {
            System.out.print(prompt);
            String text = scanner.nextLine().trim();
            try {
                double value = Double.parseDouble(text);
                if (rule.test(value)) {
                    return value;
                }
                System.out.println("Error: " + error + ". Please enter the data again with valid input.");
            } catch (NumberFormatException e) {
                System.out.println("Invalid input: \"" + text + "\" is not a number. Please enter the data again.");
            }
        }
    }

    /**
     * Reads text that must pass a rule; asks again until it does.
     */
    public static String readString(Scanner scanner, String prompt, Predicate<String> rule, String error) {
        while (true) {
            System.out.print(prompt);
            String text = scanner.nextLine().trim();
            if (rule.test(text)) {
                return text;
            }
            System.out.println("Error: " + error + ". Please enter the data again with valid input.");
        }
    }

    /**
     * Reads a date (YYYY-MM-DD, blank = today) that must pass a rule; asks again until it does.
     */
    public static LocalDate readDate(Scanner scanner, String prompt, Predicate<LocalDate> rule, String error) {
        while (true) {
            System.out.print(prompt);
            String text = scanner.nextLine().trim();
            LocalDate date;
            if (text.isEmpty()) {
                date = LocalDate.now();
            } else {
                try {
                    date = LocalDate.parse(text);
                } catch (DateTimeParseException e) {
                    System.out.println("Invalid input: \"" + text + "\" is not a date (YYYY-MM-DD). Please enter the data again.");
                    continue;
                }
            }
            if (rule.test(date)) {
                return date;
            }
            System.out.println("Error: " + error + ". Please enter the data again with valid input.");
        }
    }
}
