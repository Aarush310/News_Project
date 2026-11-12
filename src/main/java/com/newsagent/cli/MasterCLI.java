package com.newsagent.cli;

import java.io.FilterInputStream;
import java.io.IOException;
import java.util.Scanner;

/**
 * MASTER CLI
 * One menu that opens the menu of each of the 4 entities:
 * Customer, Order, Publication and Delivery Area.
 */
public class MasterCLI {

    public static void main(String[] args) {

        // Each entity CLI closes its own Scanner when it exits, which would
        // also close the keyboard (System.in). This wrapper ignores close(),
        // so the keyboard stays open and we can come back to this menu.
        System.setIn(new FilterInputStream(System.in) {
            @Override
            public void close() throws IOException {
                // do nothing
            }
        });

        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println();
            System.out.println("===== NEWSAGENT MASTER MENU =====");
            System.out.println("1. Customer Management");
            System.out.println("2. Order Management");
            System.out.println("3. Publication Management");
            System.out.println("4. Delivery Area Management");
            System.out.println("5. Exit");

            choice = readInt(scanner, "Enter your choice: ");

            switch (choice) {
                case 1: CustomerCLI.main(args); break;
                case 2: OrderCLI.main(args); break;
                case 3: PublicationCLI.main(args); break;
                case 4: DeliveryAreaCLI.main(args); break;
                case 5: System.out.println("Goodbye."); break;
                default: System.out.println("Invalid choice. Enter a number from 1 to 5.");
            }
        } while (choice != 5);
    }

    // Reads a whole number; text such as "abc" becomes -1, which is an invalid choice
    private static int readInt(Scanner scanner, String prompt) {
        System.out.print(prompt);
        try {
            return Integer.parseInt(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            return -1;
        }
    }
}
