package com.newsagent.cli;

import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

/**
 * Main menu for the whole Newsagent system.
 * Each option opens that entity's own menu; its "Exit" comes back here.
 */
public class MasterCLI {

    public static void main(String[] args) {

        // Every entity menu makes its own Scanner on System.in and some close it on exit.
        // This shared input never closes and hands over one line at a time,
        // so the keyboard keeps working after each menu returns.
        System.setIn(new SharedInput(System.in));

        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println();
            System.out.println("========== NEWSAGENT SYSTEM ==========");
            System.out.println("1. Customers");
            System.out.println("2. Publications");
            System.out.println("3. Orders");
            System.out.println("4. Delivery Areas");
            System.out.println("5. Delivery Dockets");
            System.out.println("6. Exit");

            choice = readInt(scanner, "Enter your choice: ");

            switch (choice) {
                case 1: CustomerCLI.main(args); break;
                case 2: PublicationCLI.main(args); break;
                case 3: OrderCLI.main(args); break;
                case 4: DeliveryAreaCLI.main(args); break;
                case 5: DeliveryDocketCLI.main(args); break;
                case 6: System.out.println("Goodbye."); break;
                default: System.out.println("Invalid choice. Enter a number from 1 to 6.");
            }
        } while (choice != 6);
    }

    // Reads a whole number; text such as "abc" becomes -1
    private static int readInt(Scanner scanner, String prompt) {
        System.out.print(prompt);
        if (!scanner.hasNextLine()) {
            return 6;
        }
        try {
            return Integer.parseInt(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    // System.in wrapper: close() does nothing, and each read stops at the end of a line
    private static class SharedInput extends FilterInputStream {

        SharedInput(InputStream in) {
            super(in);
        }

        @Override
        public int read(byte[] b, int off, int len) throws IOException {
            if (len == 0) {
                return 0;
            }
            int count = 0;
            while (count < len) {
                int c = in.read();
                if (c == -1) {
                    return count == 0 ? -1 : count;
                }
                b[off + count++] = (byte) c;
                if (c == '\n') {
                    break;
                }
            }
            return count;
        }

        @Override
        public int available() {
            return 0;
        }

        @Override
        public void close() {
        }
    }
}
