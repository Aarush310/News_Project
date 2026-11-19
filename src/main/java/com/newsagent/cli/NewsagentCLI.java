package com.newsagent.cli;

import com.newsagent.dao.NewsagentDAO;
import com.newsagent.model.Newsagent;

import java.util.ArrayList;
import java.util.Scanner;

public class NewsagentCLI {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        NewsagentDAO newsagentDAO = new NewsagentDAO();
        int choice;

        do {
            System.out.println();
            System.out.println("===== NEWSAGENT ACCOUNT MENU =====");
            System.out.println("1. Add Newsagent");
            System.out.println("2. Find Newsagent By ID");
            System.out.println("3. Update Newsagent");
            System.out.println("4. Delete Newsagent");
            System.out.println("5. List All Newsagents");
            System.out.println("6. Exit");

            choice = readInt(scanner, "Enter your choice: ");

            switch (choice) {
                case 1: addNewsagent(scanner, newsagentDAO); break;
                case 2: findNewsagent(scanner, newsagentDAO); break;
                case 3: updateNewsagent(scanner, newsagentDAO); break;
                case 4: deleteNewsagent(scanner, newsagentDAO); break;
                case 5: listNewsagents(newsagentDAO); break;
                case 6: System.out.println("Goodbye."); break;
                default: System.out.println("Invalid choice. Enter a number from 1 to 6.");
            }
        } while (choice != 6);
    }

    /**
     * CREATE NEWSAGENT
     */
    private static void addNewsagent(Scanner scanner, NewsagentDAO newsagentDAO) {

        System.out.println("=== Create New Newsagent ===");

        System.out.print("Shop Name: ");
        String shopName = scanner.nextLine().trim();

        System.out.print("Address: ");
        String address = scanner.nextLine().trim();

        System.out.print("Phone Number (10 digits): ");
        String phoneNumber = scanner.nextLine().trim();

        Newsagent newsagent = new Newsagent();

        // 1. The PE class checks every rule (the methods tested by JUnit)
        if (!newsagent.createNewsagent(shopName, address, phoneNumber)) {

            if (!newsagent.verifyShopName(shopName)) {
                System.out.println("Error: Invalid Shop Name");
            }
            if (!newsagent.verifyAddress(address)) {
                System.out.println("Error: Invalid Address");
            }
            if (!newsagent.verifyPhoneNumber(phoneNumber)) {
                System.out.println("Error: Invalid Phone Number");
            }

            System.out.println("Newsagent not created.");
            return;
        }

        // 2. The DAO saves the object in MySQL
        int id = newsagentDAO.addNewsagent(newsagent);
        if (id > 0) {
            System.out.println("Newsagent saved successfully with ID " + id + ".");
        } else {
            System.out.println("Failed to save newsagent.");
        }
    }

    /**
     * FIND NEWSAGENT BY ID
     */
    private static void findNewsagent(Scanner scanner, NewsagentDAO newsagentDAO) {

        int id = readInt(scanner, "Newsagent ID: ");
        Newsagent newsagent = newsagentDAO.findNewsagentById(id);

        if (newsagent == null) {
            System.out.println("Error: Newsagent " + id + " not found.");
        } else {
            System.out.println(newsagent);
        }
    }

    /**
     * UPDATE NEWSAGENT
     */
    private static void updateNewsagent(Scanner scanner, NewsagentDAO newsagentDAO) {

        int id = readInt(scanner, "Newsagent ID: ");
        Newsagent current = newsagentDAO.findNewsagentById(id);

        if (current == null) {
            System.out.println("Error: Newsagent " + id + " not found.");
            return;
        }

        System.out.println("Current details:");
        System.out.println(current);
        System.out.println("=== Enter New Details ===");

        System.out.print("Shop Name: ");
        String shopName = scanner.nextLine().trim();

        System.out.print("Address: ");
        String address = scanner.nextLine().trim();

        System.out.print("Phone Number (10 digits): ");
        String phoneNumber = scanner.nextLine().trim();

        Newsagent newsagent = new Newsagent();

        // 1. The PE class checks every rule (the same rules as Add)
        if (!newsagent.createNewsagent(shopName, address, phoneNumber)) {

            if (!newsagent.verifyShopName(shopName)) {
                System.out.println("Error: Invalid Shop Name");
            }
            if (!newsagent.verifyAddress(address)) {
                System.out.println("Error: Invalid Address");
            }
            if (!newsagent.verifyPhoneNumber(phoneNumber)) {
                System.out.println("Error: Invalid Phone Number");
            }

            System.out.println("Newsagent not updated.");
            return;
        }

        // 2. The DAO saves the new details in MySQL
        newsagent.setNewsagentId(id);

        if (newsagentDAO.updateNewsagent(newsagent)) {
            System.out.println("Newsagent updated successfully.");
        } else {
            System.out.println("Failed to update newsagent.");
        }
    }

    /**
     * DELETE NEWSAGENT
     */
    private static void deleteNewsagent(Scanner scanner, NewsagentDAO newsagentDAO) {

        int id = readInt(scanner, "Newsagent ID: ");

        if (newsagentDAO.findNewsagentById(id) == null) {
            System.out.println("Error: Newsagent " + id + " not found.");
            return;
        }

        // Rule that needs the database (tested by UAT)
        if (newsagentDAO.countNewsagents() <= 1) {
            System.out.println("Cannot delete: this is the last newsagent account.");
            System.out.println("Newsagent not deleted.");
            return;
        }

        if (newsagentDAO.deleteNewsagent(id)) {
            System.out.println("Newsagent deleted successfully.");
        } else {
            System.out.println("Newsagent not deleted.");
        }
    }

    /**
     * LIST ALL NEWSAGENTS
     */
    private static void listNewsagents(NewsagentDAO newsagentDAO) {

        ArrayList<Newsagent> list = newsagentDAO.getAllNewsagents();

        if (list.isEmpty()) {
            System.out.println("No newsagents found.");
            return;
        }

        System.out.println("=== All Newsagents ===");
        for (Newsagent newsagent : list) {
            System.out.println(newsagent);
            System.out.println("------------------");
        }
        System.out.println(list.size() + " newsagent(s).");
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
}
