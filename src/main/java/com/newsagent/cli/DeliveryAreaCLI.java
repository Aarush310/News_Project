
package com.newsagent.cli;

import com.newsagent.model.DeliveryArea;
import com.newsagent.dao.DeliveryAreaDAO;
import com.newsagent.dao.DeliveryPersonDAO;
import com.newsagent.db.DatabaseConnection;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class DeliveryAreaCLI {

    public static void main(String[] args) {

        try (Connection connection =
                 DatabaseConnection.getConnection();
             Scanner scanner = new Scanner(System.in)) {

            DeliveryAreaDAO dao = new DeliveryAreaDAO(connection);
            boolean running = true;

            while (running) {

                System.out.println("\n===== DELIVERY AREA MENU =====");
                System.out.println("1. Add Delivery Area");
                System.out.println("2. Find Delivery Area By ID");
                System.out.println("3. Update Delivery Area");
                System.out.println("4. Delete Delivery Area");
                System.out.println("5. List All Delivery Areas");
                System.out.println("6. Exit");
                System.out.print("Enter your choice: ");

                String choice = scanner.nextLine();

                switch (choice) {

                    case "1":
                        try {
                            System.out.print("Area Number (1-24): ");
                            int number = Integer.parseInt(
                                scanner.nextLine().trim());

                            System.out.print("Area Name: ");
                            String name = scanner.nextLine();

                            System.out.print("Delivery Person ID: ");
                            int personId = Integer.parseInt(
                                scanner.nextLine().trim());

                            DeliveryArea area = new DeliveryArea();

                            if (!area.verifyAreaNumber(number)) {
                                System.out.println("Error: Invalid Area Number");
                            } else if (!area.verifyAreaName(name)) {
                                System.out.println("Error: Invalid Area Name");
                            } else if (!area.verifyDeliveryPersonId(personId)) {
                                System.out.println("Error: Invalid Delivery Person ID");
                            } else if (new DeliveryPersonDAO().findDeliveryPersonById(personId) == null) {
                                // Rule that needs the database: the delivery person must exist
                                System.out.println("Error: Delivery Person " + personId + " not found.");
                            } else if (dao.findDeliveryArea(number) != null) {
                                // Rule that needs the database: area numbers are unique
                                System.out.println("Error: Delivery Area " + number + " already exists.");
                                System.out.println("Delivery Area not created.");
                            } else {
                                area.createDeliveryArea(number, name, personId);

                                if (dao.addDeliveryArea(area)) {
                                    System.out.println(
                                        "Delivery Area saved successfully.");
                                } else {
                                    System.out.println("Failed to save delivery area.");
                                }
                            }

                        } catch (NumberFormatException e) {
                            System.out.println("Error: Enter a whole number.");
                        } catch (SQLException e) {
                            System.out.println(
                                "Database error: " + e.getMessage());
                        }
                        break;

                    case "2":
                        try {
                            System.out.print("Area Number (1-24): ");
                            int number = Integer.parseInt(
                                scanner.nextLine().trim());

                            if (number < 1 || number > 24) {
                                System.out.println("Error: Invalid Area Number");
                                break;
                            }

                            DeliveryArea area =
                                dao.findDeliveryArea(number);

                            if (area == null) {
                                System.out.println("Error: Delivery Area not found.");
                            } else {
                                System.out.println("\n" + area);
                            }

                        } catch (NumberFormatException e) {
                            System.out.println("Error: Enter a whole number.");
                        } catch (SQLException e) {
                            System.out.println(
                                "Database error: " + e.getMessage());
                        }
                        break;

                    case "3":
                        try {
                            System.out.print("Area Number (1-24): ");
                            int number = Integer.parseInt(
                                scanner.nextLine().trim());

                            DeliveryArea current =
                                dao.findDeliveryArea(number);

                            if (current == null) {
                                System.out.println("Error: Delivery Area not found.");
                                break;
                            }

                            System.out.println("\nCurrent details:\n" + current);

                            System.out.print("New Area Name: ");
                            String name = scanner.nextLine();

                            System.out.print("New Delivery Person ID: ");
                            int personId = Integer.parseInt(
                                scanner.nextLine().trim());

                            DeliveryArea area = new DeliveryArea();

                            if (!area.verifyAreaName(name)) {
                                System.out.println("Error: Invalid Area Name");
                            } else if (!area.verifyDeliveryPersonId(personId)) {
                                System.out.println("Error: Invalid Delivery Person ID");
                            } else if (new DeliveryPersonDAO().findDeliveryPersonById(personId) == null) {
                                // Rule that needs the database: the delivery person must exist
                                System.out.println("Error: Delivery Person " + personId + " not found.");
                            } else {
                                area.createDeliveryArea(number, name, personId);

                                if (dao.updateDeliveryArea(area)) {
                                    System.out.println(
                                        "Delivery Area updated successfully.");
                                } else {
                                    System.out.println("Delivery Area not updated.");
                                }
                            }

                        } catch (NumberFormatException e) {
                            System.out.println("Error: Enter a whole number.");
                        } catch (SQLException e) {
                            System.out.println(
                                "Database error: " + e.getMessage());
                        }
                        break;

                    case "4":
                        try {
                            System.out.print("Area Number (1-24): ");
                            int number = Integer.parseInt(
                                scanner.nextLine().trim());

                            if (dao.findDeliveryArea(number) == null) {
                                System.out.println("Error: Delivery Area not found.");
                                break;
                            }

                            int customers = dao.countCustomersInArea(number);

                            if (customers > 0) {
                                System.out.println(
                                    "Cannot delete: " + customers +
                                    " customer(s) are in this area.");
                            } else if (dao.deleteDeliveryArea(number)) {
                                System.out.println(
                                    "Delivery Area deleted successfully.");
                            } else {
                                System.out.println("Delivery Area not deleted.");
                            }

                        } catch (NumberFormatException e) {
                            System.out.println("Error: Enter a whole number.");
                        } catch (SQLException e) {
                            System.out.println(
                                "Database error: " + e.getMessage());
                        }
                        break;

                    case "5":
                        try {
                            ArrayList<DeliveryArea> areas = dao.getAllDeliveryAreas();

                            if (areas.isEmpty()) {
                                System.out.println("No delivery areas found.");
                            } else {
                                System.out.println("=== All Delivery Areas ===");
                                for (DeliveryArea a : areas) {
                                    System.out.println(a);
                                    System.out.println("------------------");
                                }
                                System.out.println(areas.size() + " delivery area(s).");
                            }

                        } catch (SQLException e) {
                            System.out.println(
                                "Database error: " + e.getMessage());
                        }
                        break;

                    case "6":
                        System.out.println("Goodbye.");
                        running = false;
                        break;

                    default:
                        System.out.println("Invalid choice. Enter a number from 1 to 6.");
                }
            }

        } catch (SQLException e) {
            System.out.println(
                "Could not connect to MySQL: " + e.getMessage());
        }
    }
}
