package com.newsagent.cli;

import com.newsagent.dao.DeliveryDocketDAO;
import com.newsagent.model.DeliveryDocket;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Scanner;

public class DeliveryDocketCLI {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        DeliveryDocketDAO docketDAO = new DeliveryDocketDAO();
        int choice;

        do {
            System.out.println();
            System.out.println("===== DELIVERY DOCKET MENU =====");
            System.out.println("1. Create Delivery Docket");
            System.out.println("2. Find Delivery Docket By ID");
            System.out.println("3. Update Delivery Docket");
            System.out.println("4. Delete Delivery Docket");
            System.out.println("5. Exit");

            choice = readInt(scanner, "Enter your choice: ");

            switch (choice) {
                case 1: createDocket(scanner, docketDAO); break;
                case 2: findDocket(scanner, docketDAO); break;
                case 3: updateDocket(scanner, docketDAO); break;
                case 4: deleteDocket(scanner, docketDAO); break;
                case 5: System.out.println("Goodbye."); break;
                default: System.out.println("Invalid choice. Enter a number from 1 to 5.");
            }
        } while (choice != 5);
    }

    /**
     * CREATE DOCKET
     */
    private static void createDocket(Scanner scanner, DeliveryDocketDAO docketDAO) {

        System.out.println("=== Create New Delivery Docket ===");

        int areaNumber = readInt(scanner, "Area Number (1-24): ");
        LocalDate date = readDate(scanner, "Date (YYYY-MM-DD, blank = today): ");

        DeliveryDocket docket = new DeliveryDocket();

        // 1. The PE class checks every rule (the methods tested by JUnit)
        if (!docket.createDeliveryDocket(areaNumber, date)) {

            if (!docket.verifyAreaNumber(areaNumber)) {
                System.out.println("Error: Invalid Area Number");
            }
            if (!docket.verifyDocketDate(date)) {
                System.out.println("Error: Invalid Date");
            }

            System.out.println("Docket not created.");
            return;
        }

        // 2. The DAO saves the object in MySQL
        int id = docketDAO.addDocket(docket);

        if (id > 0) {
            System.out.println("Docket created successfully with ID " + id + ".");
            printDocket(docket, docketDAO);
        } else {
            System.out.println("Docket not created.");
        }
    }

    /**
     * FIND DOCKET BY ID (shows the deliveries due on it)
     */
    private static void findDocket(Scanner scanner, DeliveryDocketDAO docketDAO) {

        int id = readInt(scanner, "Docket ID: ");
        DeliveryDocket docket = docketDAO.findDocketById(id);

        if (docket == null) {
            System.out.println("Error: Docket " + id + " not found.");
        } else {
            printDocket(docket, docketDAO);
        }
    }

    /**
     * UPDATE DOCKET
     * The area stays the same; the date and status can change.
     */
    private static void updateDocket(Scanner scanner, DeliveryDocketDAO docketDAO) {

        int id = readInt(scanner, "Docket ID: ");
        DeliveryDocket docket = docketDAO.findDocketById(id);

        if (docket == null) {
            System.out.println("Error: Docket " + id + " not found.");
            return;
        }

        System.out.println("Current details:");
        System.out.println(docket);
        System.out.println("=== Enter New Details ===");

        LocalDate date = readDate(scanner, "Date (YYYY-MM-DD, blank = today): ");

        System.out.print("Status (PENDING / DELIVERED): ");
        String status = scanner.nextLine().trim().toUpperCase();

        // 1. The PE class checks every rule
        if (!docket.updateDeliveryDocket(date, status)) {

            if (!docket.verifyDocketDate(date)) {
                System.out.println("Error: Invalid Date");
            }
            if (!docket.verifyStatus(status)) {
                System.out.println("Error: Invalid Status");
            }

            System.out.println("Docket not updated.");
            return;
        }

        // 2. The DAO saves the new details in MySQL
        if (docketDAO.updateDocket(docket)) {
            System.out.println("Docket updated successfully.");
        } else {
            System.out.println("Failed to update docket.");
        }
    }

    /**
     * DELETE DOCKET
     * Only a PENDING docket can be deleted.
     */
    private static void deleteDocket(Scanner scanner, DeliveryDocketDAO docketDAO) {

        int id = readInt(scanner, "Docket ID: ");
        DeliveryDocket docket = docketDAO.findDocketById(id);

        if (docket == null) {
            System.out.println("Error: Docket " + id + " not found.");
            return;
        }

        if (!docket.canDelete(docket.getStatus())) {
            System.out.println("Error: Docket already delivered. It cannot be deleted.");
            return;
        }

        if (docketDAO.deleteDocket(id)) {
            System.out.println("Docket deleted successfully.");
        } else {
            System.out.println("Docket not deleted.");
        }
    }

    // Prints the docket and every delivery due in its area on its date
    private static void printDocket(DeliveryDocket docket, DeliveryDocketDAO docketDAO) {

        System.out.println(docket);

        List<String> deliveries =
                docketDAO.getDeliveries(docket.getAreaNumber(), docket.getDocketDate());

        System.out.println("Deliveries (" + deliveries.size() + "):");
        if (deliveries.isEmpty()) {
            System.out.println("  No deliveries due in this area on this date.");
        }
        for (String line : deliveries) {
            System.out.println("  " + line);
        }
    }

    // Reads a whole number; text such as "abc" becomes -1, which the verify methods reject
    private static int readInt(Scanner scanner, String prompt) {
        System.out.print(prompt);
        try {
            return Integer.parseInt(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    // Reads a date; blank means today, anything not YYYY-MM-DD becomes null, which verifyDocketDate rejects
    private static LocalDate readDate(Scanner scanner, String prompt) {
        System.out.print(prompt);
        String text = scanner.nextLine().trim();
        if (text.isEmpty()) {
            return LocalDate.now();
        }
        try {
            return LocalDate.parse(text);
        } catch (DateTimeParseException e) {
            return null;
        }
    }
}
