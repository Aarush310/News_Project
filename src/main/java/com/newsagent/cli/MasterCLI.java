
package com.newsagent.cli;

import java.io.FilterInputStream;
import java.io.IOException;
import java.util.Scanner;

/**
 * MASTER CLI
 * Main menu for all Newsagent entities and extra features.
 */
public class MasterCLI {

    public static void main(String[] args) {

        // Keep System.in open when returning from other menus
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
            System.out.println("5. Delivery Docket Management");
            System.out.println("6. Extra Features");
            System.out.println("7. Monthly Bills");
            System.out.println("8. Delivery Persons");
            System.out.println("9. Newsagent Account");
            System.out.println("10. Exit");

            choice = readInt(scanner, "Enter your choice: ");

            switch (choice) {
                case 1:
                    CustomerCLI.main(args);
                    break;

                case 2:
                    OrderCLI.main(args);
                    break;

                case 3:
                    PublicationCLI.main(args);
                    break;

                case 4:
                    DeliveryAreaCLI.main(args);
                    break;

                case 5:
                    DeliveryDocketCLI.main(args);
                    break;

                case 6:
                    ExtraFeaturesCLI.main(args);
                    break;

                case 7:
                    BillCLI.main(args);
                    break;

                case 8:
                    DeliveryPersonCLI.main(args);
                    break;

                case 9:
                    NewsagentCLI.main(args);
                    break;

                case 10:
                    System.out.println("Goodbye.");
                    break;

                default:
                    System.out.println(
                        "Invalid choice. Enter a number from 1 to 10."
                    );
            }

        } while (choice != 10);
    }

    private static int readInt(Scanner scanner, String prompt) {
        System.out.print(prompt);

        try {
            return Integer.parseInt(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            return -1;
        }
    }
}
