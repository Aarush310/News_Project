package com.newsagent.cli;

import com.newsagent.dao.DeliveryPersonDAO;
import com.newsagent.model.DeliveryPerson;

import java.util.ArrayList;
import java.util.Scanner;

public class DeliveryPersonCLI {

    // Used only to check input rules while the user types
    private static final DeliveryPerson RULES = new DeliveryPerson();

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        DeliveryPersonDAO personDAO = new DeliveryPersonDAO();
        int choice;

        do {
            System.out.println();
            System.out.println("===== DELIVERY PERSON MENU =====");
            System.out.println("1. Add Delivery Person");
            System.out.println("2. Find Delivery Person By ID");
            System.out.println("3. Update Delivery Person");
            System.out.println("4. Delete Delivery Person");
            System.out.println("5. List All Delivery Persons");
            System.out.println("6. Exit");

            choice = readInt(scanner, "Enter your choice: ");

            switch (choice) {
                case 1: addDeliveryPerson(scanner, personDAO); break;
                case 2: findDeliveryPerson(scanner, personDAO); break;
                case 3: updateDeliveryPerson(scanner, personDAO); break;
                case 4: deleteDeliveryPerson(scanner, personDAO); break;
                case 5: listDeliveryPersons(personDAO); break;
                case 6: System.out.println("Goodbye."); break;
                default: System.out.println("Invalid choice. Enter a number from 1 to 6.");
            }
        } while (choice != 6);
    }

    /**
     * CREATE DELIVERY PERSON
     */
    private static void addDeliveryPerson(Scanner scanner, DeliveryPersonDAO personDAO) {

        System.out.println("=== Create New Delivery Person ===");

        String firstName = InputHelper.readString(scanner, "First Name: ", RULES::verifyFirstName, "Invalid First Name (letters only)");

        String surname = InputHelper.readString(scanner, "Surname: ", RULES::verifySurname, "Invalid Surname (letters only)");

        String phoneNumber = InputHelper.readString(scanner, "Phone Number (10 digits): ", RULES::verifyPhoneNumber, "Invalid Phone Number (must be 10 digits)");

        int deliveryArea = InputHelper.readInt(scanner, "Delivery Area (1-24): ", RULES::verifyDeliveryArea, "Invalid Delivery Area (must be 1-24)");

        DeliveryPerson person = new DeliveryPerson();

        // 1. The PE class checks every rule (the methods tested by JUnit)
        if (!person.createDeliveryPerson(firstName, surname, phoneNumber, deliveryArea)) {

            if (!person.verifyFirstName(firstName)) {
                System.out.println("Error: Invalid First Name");
            }
            if (!person.verifySurname(surname)) {
                System.out.println("Error: Invalid Surname");
            }
            if (!person.verifyPhoneNumber(phoneNumber)) {
                System.out.println("Error: Invalid Phone Number");
            }
            if (!person.verifyDeliveryArea(deliveryArea)) {
                System.out.println("Error: Invalid Delivery Area");
            }

            System.out.println("Delivery Person not created.");
            return;
        }

        // 2. The DAO saves the object in MySQL
        int id = personDAO.addDeliveryPerson(person);
        if (id > 0) {
            System.out.println("Delivery Person saved successfully with ID " + id + ".");
        } else {
            System.out.println("Failed to save delivery person.");
        }
    }

    /**
     * FIND DELIVERY PERSON BY ID
     */
    private static void findDeliveryPerson(Scanner scanner, DeliveryPersonDAO personDAO) {

        int id = readInt(scanner, "Delivery Person ID: ");
        DeliveryPerson person = personDAO.findDeliveryPersonById(id);

        if (person == null) {
            System.out.println("Error: Delivery Person " + id + " not found.");
        } else {
            System.out.println(person);
        }
    }

    /**
     * UPDATE DELIVERY PERSON
     */
    private static void updateDeliveryPerson(Scanner scanner, DeliveryPersonDAO personDAO) {

        int id = readInt(scanner, "Delivery Person ID: ");
        DeliveryPerson current = personDAO.findDeliveryPersonById(id);

        if (current == null) {
            System.out.println("Error: Delivery Person " + id + " not found.");
            return;
        }

        System.out.println("Current details:");
        System.out.println(current);
        System.out.println("=== Enter New Details ===");

        String firstName = InputHelper.readString(scanner, "First Name: ", RULES::verifyFirstName, "Invalid First Name (letters only)");

        String surname = InputHelper.readString(scanner, "Surname: ", RULES::verifySurname, "Invalid Surname (letters only)");

        String phoneNumber = InputHelper.readString(scanner, "Phone Number (10 digits): ", RULES::verifyPhoneNumber, "Invalid Phone Number (must be 10 digits)");

        int deliveryArea = InputHelper.readInt(scanner, "Delivery Area (1-24): ", RULES::verifyDeliveryArea, "Invalid Delivery Area (must be 1-24)");

        DeliveryPerson person = new DeliveryPerson();

        // 1. The PE class checks every rule (the same rules as Add)
        if (!person.createDeliveryPerson(firstName, surname, phoneNumber, deliveryArea)) {

            if (!person.verifyFirstName(firstName)) {
                System.out.println("Error: Invalid First Name");
            }
            if (!person.verifySurname(surname)) {
                System.out.println("Error: Invalid Surname");
            }
            if (!person.verifyPhoneNumber(phoneNumber)) {
                System.out.println("Error: Invalid Phone Number");
            }
            if (!person.verifyDeliveryArea(deliveryArea)) {
                System.out.println("Error: Invalid Delivery Area");
            }

            System.out.println("Delivery Person not updated.");
            return;
        }

        // 2. The DAO saves the new details in MySQL
        person.setDeliveryPersonId(id);

        if (personDAO.updateDeliveryPerson(person)) {
            System.out.println("Delivery Person updated successfully.");
        } else {
            System.out.println("Failed to update delivery person.");
        }
    }

    /**
     * DELETE DELIVERY PERSON
     */
    private static void deleteDeliveryPerson(Scanner scanner, DeliveryPersonDAO personDAO) {

        int id = readInt(scanner, "Delivery Person ID: ");

        if (personDAO.findDeliveryPersonById(id) == null) {
            System.out.println("Error: Delivery Person " + id + " not found.");
            return;
        }

        // Rule that needs the database (tested by UAT)
        int areas = personDAO.countAreasForPerson(id);
        if (areas > 0) {
            System.out.println("Cannot delete: this delivery person still does " + areas + " delivery area(s). Give the area(s) to someone else first.");
            System.out.println("Delivery Person not deleted.");
            return;
        }

        if (personDAO.deleteDeliveryPerson(id)) {
            System.out.println("Delivery Person deleted successfully.");
        } else {
            System.out.println("Delivery Person not deleted.");
        }
    }

    /**
     * LIST ALL DELIVERY PERSONS
     */
    private static void listDeliveryPersons(DeliveryPersonDAO personDAO) {

        ArrayList<DeliveryPerson> list = personDAO.getAllDeliveryPersons();

        if (list.isEmpty()) {
            System.out.println("No delivery persons found.");
            return;
        }

        System.out.println("=== All Delivery Persons ===");
        for (DeliveryPerson person : list) {
            System.out.println(person);
            System.out.println("------------------");
        }
        System.out.println(list.size() + " delivery person(s).");
    }

    // Reads a whole number; text such as "abc" is rejected and the user is asked again

    private static int readInt(Scanner scanner, String prompt) {
        return InputHelper.readInt(scanner, prompt);
    }
}
