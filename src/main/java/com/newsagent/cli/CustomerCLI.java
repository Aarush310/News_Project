package com.newsagent.cli;

import com.newsagent.dao.CustomerDAO;
import com.newsagent.model.Customer;

import java.util.Scanner;

public class CustomerCLI {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        CustomerDAO customerDAO = new CustomerDAO();
        int choice;

        do {
            System.out.println();
            System.out.println("===== CUSTOMER MANAGEMENT MENU =====");
            System.out.println("1. Add Customer");
            System.out.println("2. Find Customer By ID");
            System.out.println("3. Update Customer");
            System.out.println("4. Delete Customer");
            System.out.println("5. Exit");

            choice = readInt(scanner, "Enter your choice: ");

            switch (choice) {
                case 1: addCustomer(scanner, customerDAO); break;
                case 2: findCustomer(scanner, customerDAO); break;
                case 3: updateCustomer(scanner, customerDAO); break;
                case 4: deleteCustomer(scanner, customerDAO); break;
                case 5: System.out.println("Goodbye."); break;
                default: System.out.println("Invalid choice. Enter a number from 1 to 5.");
            }
        } while (choice != 5);

        scanner.close();
    }

    /**
     * CREATE CUSTOMER
     */
    private static void addCustomer(Scanner scanner, CustomerDAO customerDAO) {

        System.out.println("=== Create New Customer ===");

        System.out.print("First Name: ");
        String firstName = scanner.nextLine().trim();

        System.out.print("Surname: ");
        String surname = scanner.nextLine().trim();

        System.out.print("Address: ");
        String address = scanner.nextLine().trim();

        System.out.print("Phone Number (10 digits): ");
        String phoneNumber = scanner.nextLine().trim();

        int deliveryArea = readInt(scanner, "Delivery Area (1-24): ");

        Customer customer = new Customer();

        // 1. The PE class checks every rule (the methods tested by JUnit)
        if (!customer.createCustomerAccount(firstName, surname, address, phoneNumber, deliveryArea)) {

            if (!customer.verifyFirstName(firstName)) {
                System.out.println("Error: Invalid First Name");
            }
            if (!customer.verifySurname(surname)) {
                System.out.println("Error: Invalid Surname");
            }
            if (!customer.verifyAddress(address)) {
                System.out.println("Error: Invalid Address");
            }
            if (!customer.verifyPhoneNumber(phoneNumber)) {
                System.out.println("Error: Invalid Phone Number");
            }
            if (!customer.verifyDeliveryArea(deliveryArea)) {
                System.out.println("Error: Invalid Delivery Area");
            }

            System.out.println("Customer not created.");
            return;
        }

        // 2. The DAO saves the object in MySQL
        if (customerDAO.addCustomer(customer)) {
            System.out.println("Customer saved successfully.");
        } else {
            System.out.println("Failed to save customer.");
        }
    }

    /**
     * FIND CUSTOMER BY ID
     */
    private static void findCustomer(Scanner scanner, CustomerDAO customerDAO) {

        int id = readInt(scanner, "Customer ID: ");
        Customer customer = customerDAO.findCustomerById(id);

        if (customer == null) {
            System.out.println("Error: Customer " + id + " not found.");
        } else {
            System.out.println(customer);
        }
    }

    /**
     * UPDATE CUSTOMER
     */
    private static void updateCustomer(Scanner scanner, CustomerDAO customerDAO) {

        int id = readInt(scanner, "Customer ID: ");
        Customer current = customerDAO.findCustomerById(id);

        if (current == null) {
            System.out.println("Error: Customer " + id + " not found.");
            return;
        }

        System.out.println("Current details:");
        System.out.println(current);
        System.out.println("=== Enter New Details ===");

        System.out.print("First Name: ");
        String firstName = scanner.nextLine().trim();

        System.out.print("Surname: ");
        String surname = scanner.nextLine().trim();

        System.out.print("Address: ");
        String address = scanner.nextLine().trim();

        System.out.print("Phone Number (10 digits): ");
        String phoneNumber = scanner.nextLine().trim();

        int deliveryArea = readInt(scanner, "Delivery Area (1-24): ");

        Customer customer = new Customer();

        // 1. The PE class checks every rule (the same rules as Add)
        if (!customer.createCustomerAccount(firstName, surname, address, phoneNumber, deliveryArea)) {

            if (!customer.verifyFirstName(firstName)) {
                System.out.println("Error: Invalid First Name");
            }
            if (!customer.verifySurname(surname)) {
                System.out.println("Error: Invalid Surname");
            }
            if (!customer.verifyAddress(address)) {
                System.out.println("Error: Invalid Address");
            }
            if (!customer.verifyPhoneNumber(phoneNumber)) {
                System.out.println("Error: Invalid Phone Number");
            }
            if (!customer.verifyDeliveryArea(deliveryArea)) {
                System.out.println("Error: Invalid Delivery Area");
            }

            System.out.println("Customer not updated.");
            return;
        }

        // 2. The DAO saves the new details in MySQL
        customer.setCustomerId(id);

        if (customerDAO.updateCustomer(customer)) {
            System.out.println("Customer updated successfully.");
        } else {
            System.out.println("Failed to update customer.");
        }
    }

    /**
     * DELETE CUSTOMER
     */
    private static void deleteCustomer(Scanner scanner, CustomerDAO customerDAO) {

        int id = readInt(scanner, "Customer ID: ");

        if (customerDAO.findCustomerById(id) == null) {
            System.out.println("Error: Customer " + id + " not found.");
            return;
        }

        if (customerDAO.deleteCustomer(id)) {
            System.out.println("Customer deleted successfully.");
        } else {
            System.out.println("Customer not deleted.");
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
}
