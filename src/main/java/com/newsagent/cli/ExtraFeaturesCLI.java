
package com.newsagent.cli;

import com.newsagent.dao.ExtraFeaturesDAO;
import java.util.Scanner;

public class ExtraFeaturesCLI {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ExtraFeaturesDAO dao = new ExtraFeaturesDAO();

        int choice;

        do {
            System.out.println();
            System.out.println("===== EXTRA FEATURES MENU =====");
            System.out.println("1. Search Publications by Name");
            System.out.println("2. Show Orders by Delivery Area");
            System.out.println("3. Show Low Stock Publications");
            System.out.println("4. Customer Order Summary");
            System.out.println("5. Return to Main Menu");
            System.out.print("Enter choice: ");

            choice = readNumber(scanner);

            switch (choice) {
                case 1:
                    System.out.print("Enter publication name: ");
                    String name = scanner.nextLine().trim();

                    if (name.isEmpty()) {
                        System.out.println("Please enter a name.");
                    } else {
                        dao.searchPublications(name);
                    }
                    break;

                case 2:
                    System.out.print("Enter delivery area (1-24): ");
                    int area = readNumber(scanner);

                    if (area >= 1 && area <= 24) {
                        dao.showOrdersByArea(area);
                    } else {
                        System.out.println("Invalid delivery area.");
                    }
                    break;

                case 3:
                    System.out.print("Enter low stock threshold: ");
                    int threshold = readNumber(scanner);

                    if (threshold > 0) {
                        System.out.println(
                            "Publications with stock below " + threshold + ":"
                        );
                        dao.showLowStock(threshold);
                    } else {
                        System.out.println(
                            "Please enter a positive stock threshold."
                        );
                    }
                    break;

                case 4:
                    System.out.print("Enter Customer ID: ");
                    int customerId = readNumber(scanner);

                    if (customerId > 0) {
                        dao.showCustomerOrders(customerId);
                    } else {
                        System.out.println("Invalid Customer ID.");
                    }
                    break;

                case 5:
                    System.out.println("Returning to main menu.");
                    break;

                default:
                    System.out.println("Invalid choice.");
            }

        } while (choice != 5);
    }

    private static int readNumber(Scanner scanner) {
        try {
            return Integer.parseInt(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            return -1;
        }
    }
}
