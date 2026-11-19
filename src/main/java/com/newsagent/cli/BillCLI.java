
package com.newsagent.cli;

import com.newsagent.dao.BillDAO;
import com.newsagent.dao.CustomerDAO;
import com.newsagent.model.Bill;
import com.newsagent.model.BillLine;
import com.newsagent.model.Customer;
import com.newsagent.model.Order;

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
            System.out.println("2. Return to Main Menu");

            choice = readInt(scanner, "Enter your choice: ");

            switch (choice) {
                case 1:
                    showMonthlyBill(scanner, billDAO);
                    break;

                case 2:
                    System.out.println("Returning to main menu...");
                    break;

                default:
                    System.out.println("Invalid choice. Please enter 1 or 2.");
            }

        } while (choice != 2);

        // Do not close System.in because the Master CLI uses it too.
    }

    /**
     * Displays a monthly bill for a selected customer.
     */
    private static void showMonthlyBill(Scanner scanner, BillDAO billDAO) {

        Customer customer = chooseCustomer(scanner, billDAO);

        if (customer == null) {
            return;
        }

        int customerId = customer.getCustomerId();

        int year = readInt(scanner, "Year (e.g. 2026): ");
        int month = readInt(scanner, "Month (1-12): ");

        Bill bill = new Bill();

        if (!bill.verifyYear(year)) {
            System.out.println("Error: Invalid year. Please enter a valid year.");
            return;
        }

        if (!bill.verifyMonth(month)) {
            System.out.println("Error: Invalid month. Enter a number from 1 to 12.");
            return;
        }

        ArrayList<BillLine> lines = billDAO.getBillLines(customerId);

        String name = customer.getFirstName() + " " + customer.getSurname();

        String monthName = Month.of(month)
                .getDisplayName(TextStyle.FULL, Locale.ENGLISH);

        if (lines.isEmpty()) {
            System.out.println(name + " has no orders, so there is nothing to bill.");
            return;
        }

        System.out.println();
        System.out.println("============================================================");
        System.out.println("MONTHLY BILL");
        System.out.println("Customer: " + name);
        System.out.println("Customer ID: " + customerId);
        System.out.println("Billing period: " + monthName + " " + year);
        System.out.println("============================================================");

        System.out.println(String.format(
                "%-26s %10s %10s %10s",
                "Publication", "Deliveries", "Price", "Cost"
        ));

        System.out.println("------------------------------------------------------------");

        for (BillLine line : lines) {

            int deliveries = bill.countDeliveries(
                    line.getDeliveryDays(),
                    line.getFrequency(),
                    year,
                    month
            );

            double cost = bill.addLine(deliveries, line.getPrice());

            System.out.println(String.format(
                    Locale.ENGLISH,
                    "%-26s %10d %10.2f %10.2f",
                    line.getPublicationName(),
                    deliveries,
                    line.getPrice(),
                    cost
            ));
        }

        System.out.println("------------------------------------------------------------");

        System.out.println(String.format(
                "%-26s %32s",
                "TOTAL DUE",
                String.format(Locale.ENGLISH, "EUR %.2f", bill.getTotal())
        ));

        System.out.println("============================================================");
    }

    /**
     * Finds a customer using their ID or name.
     */
    private static Customer chooseCustomer(Scanner scanner, BillDAO billDAO) {

        System.out.print("Customer ID or name: ");
        String input = scanner.nextLine().trim();

        Bill rules = new Bill();
        CustomerDAO customerDAO = new CustomerDAO();

        if (rules.isCustomerId(input)) {

            int customerId = Integer.parseInt(input);

            Customer customer = customerDAO.findCustomerById(customerId);

            if (customer == null) {
                System.out.println(
                        "Error: Customer " + customerId + " does not exist."
                );
            }

            return customer;
        }

        if (!new Order().verifyCustomerName(input)) {
            System.out.println("Error: Invalid customer name.");
            return null;
        }

        ArrayList<Customer> matches = billDAO.findCustomersByName(input);

        if (matches.isEmpty()) {
            System.out.println(
                    "Error: No customer found for \"" + input + "\"."
            );
            return null;
        }

        if (matches.size() == 1) {
            return matches.get(0);
        }

        System.out.println();
        System.out.println(matches.size() + " matching customers found:");

        for (Customer c : matches) {
            System.out.println(
                    "ID " + c.getCustomerId()
                    + " - " + c.getFirstName()
                    + " " + c.getSurname()
                    + ", " + c.getAddress()
            );
        }

        int chosenId = readInt(
                scanner,
                "Enter the customer ID from the list: "
        );

        for (Customer c : matches) {
            if (c.getCustomerId() == chosenId) {
                return c;
            }
        }

        System.out.println(
                "Error: Customer " + chosenId + " is not in the list."
        );

        return null;
    }

    /**
     * Reads a whole number and handles invalid text input.
     */
    private static int readInt(Scanner scanner, String prompt) {
git diff -- src/main/java/com/newsagent/cli/BillCLI.java
        System.out.print(prompt);

        try {
            return Integer.parseInt(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a whole number.");
            return -1;
        }
    }
}
