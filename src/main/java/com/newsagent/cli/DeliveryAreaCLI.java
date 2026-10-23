
package com.newsagent.cli;

import com.newsagent.model.DeliveryArea;
import com.newsagent.dao.DeliveryAreaDAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

public class DeliveryAreaCLI {

    public static void main(String[] args) {

        String url = System.getenv("NEWSAGENT_DB_URL");
        String user = System.getenv("NEWSAGENT_DB_USER");
        String password = System.getenv("NEWSAGENT_DB_PASSWORD");

        if (url == null || user == null || password == null) {
            System.out.println("Database settings are missing.");
            System.out.println("Set NEWSAGENT_DB_URL, USER and PASSWORD.");
            return;
        }

        try (Connection connection =
                 DriverManager.getConnection(url, user, password);
             Scanner scanner = new Scanner(System.in)) {

            DeliveryAreaDAO dao = new DeliveryAreaDAO(connection);
            boolean running = true;

            while (running) {

                System.out.println("\n===== DELIVERY AREA MENU =====");
                System.out.println("1. Add Delivery Area");
                System.out.println("2. Find Delivery Area by ID");
                System.out.println("3. Exit");
                System.out.print("Enter your choice: ");

                String choice = scanner.nextLine();

                switch (choice) {

                    case "1":
                        try {
                            System.out.print("Enter Area Number (1-24): ");
                            int number = Integer.parseInt(
                                scanner.nextLine().trim());

                            System.out.print("Enter Area Name: ");
                            String name = scanner.nextLine();

                            System.out.print("Enter Delivery Person ID: ");
                            int personId = Integer.parseInt(
                                scanner.nextLine().trim());

                            DeliveryArea area = new DeliveryArea();

                            if (!area.verifyAreaNumber(number)) {
                                System.out.println("Invalid Area Number");
                            } else if (!area.verifyAreaName(name)) {
                                System.out.println("Invalid Area Name");
                            } else if (!area.verifyDeliveryPersonId(personId)) {
                                System.out.println("Invalid Delivery Person ID");
                            } else {
                                area.createDeliveryArea(number, name, personId);

                                if (dao.addDeliveryArea(area)) {
                                    System.out.println(
                                        "Delivery Area created successfully!");
                                }
                            }

                        } catch (NumberFormatException e) {
                            System.out.println("Please enter a valid number.");
                        } catch (SQLException e) {
                            System.out.println(
                                "Could not save Delivery Area: " +
                                e.getMessage());
                        }
                        break;

                    case "2":
                        try {
                            System.out.print("Enter Area Number: ");
                            int number = Integer.parseInt(
                                scanner.nextLine().trim());

                            if (number < 1 || number > 24) {
                                System.out.println("Invalid Area Number");
                                break;
                            }

                            DeliveryArea area =
                                dao.findDeliveryArea(number);

                            if (area == null) {
                                System.out.println("Delivery Area not found.");
                            } else {
                                System.out.println("\n" + area);
                            }

                        } catch (NumberFormatException e) {
                            System.out.println("Please enter a valid number.");
                        } catch (SQLException e) {
                            System.out.println(
                                "Database error: " + e.getMessage());
                        }
                        break;

                    case "3":
                        System.out.println("Goodbye!");
                        running = false;
                        break;

                    default:
                        System.out.println("Invalid choice. Try again.");
                }
            }

        } catch (SQLException e) {
            System.out.println(
                "Could not connect to MySQL: " + e.getMessage());
        }
    }
}
