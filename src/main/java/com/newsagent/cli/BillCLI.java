package com.newsagent.cli;

import com.newsagent.dao.BillDAO;
import com.newsagent.dao.CustomerDAO;
import com.newsagent.model.Bill;
import com.newsagent.model.BillLine;
import com.newsagent.model.Customer;

import java.time.Month;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

public class BillCLI {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        BillDAO billDAO = new BillDAO();
        int choice;

        do {
            System.out.println();
            System.out.println("===== MONTHLY BILLS MENU =====");
            System.out.println("1. Monthly Bill for a Customer");
            System.out.println("2. Exit");

            choice = readInt(scanner, "Enter your choice: ");

            switch (choice) {
                case 1: showMonthlyBill(scanner, billDAO); break;
                case 2: System.out.println("Goodbye."); break;
                default: System.out.println("Invalid choice. Enter 1 or 2.");
            }
        } while (choice != 2);

        scanner.close();
    }

    /**
     * MONTHLY BILL FOR A CUSTOMER
     * Counts each publication's deliveries in the month and adds up the cost.
     */
    private static void showMonthlyBill(Scanner scanner, BillDAO billDAO) {

        int customerId = readInt(scanner, "Customer ID: ");

        Customer customer = new CustomerDAO().findCustomerById(customerId);
        if (customer == null) {
            System.out.println("Error: Customer " + customerId + " does not exist.");
            return;
        }

        int year = readInt(scanner, "Year (e.g. 2026): ");
        int month = readInt(scanner, "Month (1-12): ");

        // The PE class checks the rules (the methods tested by JUnit)
        Bill bill = new Bill();
        if (!bill.verifyYear(year)) {
            System.out.println("Error: Invalid Year");
            return;
        }
        if (!bill.verifyMonth(month)) {
            System.out.println("Error: Invalid Month");
            return;
        }

        ArrayList<BillLine> lines = billDAO.getBillLines(customerId);
        String name = customer.getFirstName() + " " + customer.getSurname();
        String monthName = Month.of(month).getDisplayName(TextStyle.FULL, Locale.ENGLISH);

        if (lines.isEmpty()) {
            System.out.println(name + " has no orders, so there is nothing to bill.");
            return;
        }

        System.out.println();
        System.out.println("=== Bill for " + name + " (ID " + customerId + ") - " + monthName + " " + year + " ===");
        System.out.println(String.format("%-26s %10s %10s %10s", "Publication", "Deliveries", "Price", "Cost"));

        for (BillLine line : lines) {
            int deliveries = bill.countDeliveries(line.getDeliveryDays(), line.getFrequency(), year, month);
            double cost = bill.addLine(deliveries, line.getPrice());
            System.out.println(String.format("%-26s %10d %10.2f %10.2f",
                    line.getPublicationName(), deliveries, line.getPrice(), cost));
        }

        System.out.println("------------------------------------------------------------");
        System.out.println(String.format("%-26s %32s", "TOTAL DUE", String.format("EUR %.2f", bill.getTotal())));
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
