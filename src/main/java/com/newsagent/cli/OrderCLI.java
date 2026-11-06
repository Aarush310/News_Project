package com.newsagent.cli;

import com.newsagent.dao.OrderDAO;
import com.newsagent.model.Order;

import java.util.Scanner;

public class OrderCLI {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        OrderDAO orderDAO = new OrderDAO();
        int choice;

        do {
            System.out.println();
            System.out.println("===== ORDER MANAGEMENT MENU =====");
            System.out.println("1. Add Order");
            System.out.println("2. Find Order By ID");
            System.out.println("3. Update Order");
            System.out.println("4. Delete Order");
            System.out.println("5. Exit");

            choice = readInt(scanner, "Enter your choice: ");

            switch (choice) {
                case 1: addOrder(scanner, orderDAO); break;
                case 2: findOrder(scanner, orderDAO); break;
                case 3: updateOrder(scanner, orderDAO); break;
                case 4: deleteOrder(scanner, orderDAO); break;
                case 5: System.out.println("Goodbye."); break;
                default: System.out.println("Invalid choice. Enter a number from 1 to 5.");
            }
        } while (choice != 5);

        scanner.close();
    }

    /**
     * CREATE ORDER
     */
    private static void addOrder(Scanner scanner, OrderDAO orderDAO) {

        System.out.println("=== Create New Order ===");

        int customerId = readInt(scanner, "Customer ID: ");

        int publicationId = readInt(scanner, "Publication ID: ");

        System.out.print("Delivery Days (e.g. MON,TUE,WED,THU,FRI): ");
        String deliveryDays = scanner.nextLine().trim().toUpperCase().replace(" ", "");

        Order order = new Order();
        int[] publicationsOnOrder = orderDAO.getPublicationIdsForCustomer(customerId);

        // 1. The PE class checks every rule (the methods tested by JUnit)
        if (!order.createOrder(customerId, publicationId, deliveryDays, publicationsOnOrder)) {

            if (!order.verifyCustomerId(customerId)) {
                System.out.println("Error: Invalid Customer ID");
            }
            if (!order.verifyPublicationId(publicationId)) {
                System.out.println("Error: Invalid Publication ID");
            }
            if (!order.verifyDeliveryDays(deliveryDays)) {
                System.out.println("Error: Invalid Delivery Days");
            }
            if (!order.verifyNewPublication(publicationId, publicationsOnOrder)) {
                System.out.println("Error: Publication already on this order");
            }

            System.out.println("Order not created.");
            return;
        }

        // 2. The DAO saves the object in MySQL
        if (orderDAO.addOrder(order)) {
            System.out.println("Order saved successfully.");
        } else {
            System.out.println("Failed to save order.");
        }
    }

    /**
     * FIND ORDER BY ID
     */
    private static void findOrder(Scanner scanner, OrderDAO orderDAO) {

        int id = readInt(scanner, "Order ID: ");
        Order order = orderDAO.findOrderById(id);

        if (order == null) {
            System.out.println("Error: Order " + id + " not found.");
        } else {
            System.out.println(order);
        }
    }

    /**
     * UPDATE ORDER
     * The customer stays the same; the publication and delivery days can change.
     */
    private static void updateOrder(Scanner scanner, OrderDAO orderDAO) {

        int id = readInt(scanner, "Order ID: ");
        Order current = orderDAO.findOrderById(id);

        if (current == null) {
            System.out.println("Error: Order " + id + " not found.");
            return;
        }

        System.out.println("Current details:");
        System.out.println(current);
        System.out.println("=== Enter New Details ===");

        int publicationId = readInt(scanner, "Publication ID: ");

        System.out.print("Delivery Days (e.g. MON,TUE,WED,THU,FRI): ");
        String deliveryDays = scanner.nextLine().trim().toUpperCase().replace(" ", "");

        // The customer's other publications, without this order's own one,
        // so keeping the same publication is not counted as a duplicate
        int[] onOrder = orderDAO.getPublicationIdsForCustomer(current.getCustomerId());
        int count = 0;
        for (int pubId : onOrder) {
            if (pubId != current.getPublicationId()) count++;
        }
        int[] otherPublications = new int[count];
        int i = 0;
        for (int pubId : onOrder) {
            if (pubId != current.getPublicationId()) otherPublications[i++] = pubId;
        }

        Order order = new Order();

        // 1. The PE class checks every rule (the same rules as Add)
        if (!order.createOrder(current.getCustomerId(), publicationId, deliveryDays, otherPublications)) {

            if (!order.verifyPublicationId(publicationId)) {
                System.out.println("Error: Invalid Publication ID");
            }
            if (!order.verifyDeliveryDays(deliveryDays)) {
                System.out.println("Error: Invalid Delivery Days");
            }
            if (!order.verifyNewPublication(publicationId, otherPublications)) {
                System.out.println("Error: Publication already on this order");
            }

            System.out.println("Order not updated.");
            return;
        }

        // 2. The DAO saves the new details in MySQL
        order.setOrderId(id);

        if (orderDAO.updateOrder(order)) {
            System.out.println("Order updated successfully.");
        } else {
            System.out.println("Failed to update order.");
        }
    }

    /**
     * DELETE ORDER
     */
    private static void deleteOrder(Scanner scanner, OrderDAO orderDAO) {

        int id = readInt(scanner, "Order ID: ");

        if (orderDAO.findOrderById(id) == null) {
            System.out.println("Error: Order " + id + " not found.");
            return;
        }

        if (orderDAO.deleteOrder(id)) {
            System.out.println("Order deleted successfully.");
        } else {
            System.out.println("Order not deleted.");
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
