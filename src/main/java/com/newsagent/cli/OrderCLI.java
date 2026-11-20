package com.newsagent.cli;

import com.newsagent.dao.CustomerDAO;
import com.newsagent.dao.OrderDAO;
import com.newsagent.dao.PublicationDAO;
import com.newsagent.model.Customer;
import com.newsagent.model.Order;
import com.newsagent.model.Publication;

import java.util.ArrayList;
import java.util.Scanner;

public class OrderCLI {

    // Used only to check input rules while the user types
    private static final Order RULES = new Order();

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        OrderDAO orderDAO = new OrderDAO();
        int choice;

        do {
            System.out.println();
            System.out.println("===== ORDER MANAGEMENT MENU =====");
            System.out.println("1. Add Order");
            System.out.println("2. Find Order By ID");
            System.out.println("3. Find Orders By Customer Name");
            System.out.println("4. List All Orders");
            System.out.println("5. Update Order");
            System.out.println("6. Delete Order");
            System.out.println("7. Exit");

            choice = readInt(scanner, "Enter your choice: ");

            switch (choice) {
                case 1: addOrder(scanner, orderDAO); break;
                case 2: findOrder(scanner, orderDAO); break;
                case 3: findOrdersByCustomerName(scanner, orderDAO); break;
                case 4: listOrders(orderDAO); break;
                case 5: updateOrder(scanner, orderDAO); break;
                case 6: deleteOrder(scanner, orderDAO); break;
                case 7: System.out.println("Goodbye."); break;
                default: System.out.println("Invalid choice. Enter a number from 1 to 7.");
            }
        } while (choice != 7);

        scanner.close();
    }

    /**
     * CREATE ORDER
     */
    private static void addOrder(Scanner scanner, OrderDAO orderDAO) {

        System.out.println("=== Create New Order ===");

        int customerId = InputHelper.readInt(scanner, "Customer ID: ", RULES::verifyCustomerId, "Invalid Customer ID");

        // Look the customer up so an order can only be made for a real customer
        Customer customer = new CustomerDAO().findCustomerById(customerId);
        if (customer == null) {
            System.out.println("Error: Customer " + customerId + " does not exist.");
            System.out.println("Order not created.");
            return;
        }
        System.out.println("Customer: " + customer.getFirstName() + " " + customer.getSurname());

        int publicationId = InputHelper.readInt(scanner, "Publication ID: ", RULES::verifyPublicationId, "Invalid Publication ID");

        // Look the publication up so only a real publication can be ordered
        Publication publication = new PublicationDAO().findPublicationById(publicationId);
        if (publication == null) {
            System.out.println("Error: Publication " + publicationId + " does not exist.");
            System.out.println("Order not created.");
            return;
        }
        System.out.println("Publication: " + publication.getName());

        String deliveryDays = InputHelper.readString(scanner, "Delivery Days (e.g. MON,TUE,WED,THU,FRI): ",
                d -> RULES.verifyDeliveryDays(d.toUpperCase().replace(" ", "")),
                "Invalid Delivery Days").toUpperCase().replace(" ", "");

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
     * FIND ORDERS BY CUSTOMER NAME
     * Shows every publication each matching customer gets.
     */
    private static void findOrdersByCustomerName(Scanner scanner, OrderDAO orderDAO) {

        String name = InputHelper.readString(scanner, "Customer name (first name, surname or both): ",
                RULES::verifyCustomerName, "Invalid Customer Name");

        // The PE class checks the rule (the method tested by JUnit)
        Order rules = new Order();
        if (!rules.verifyCustomerName(name)) {
            System.out.println("Error: Invalid Customer Name");
            return;
        }

        ArrayList<Order> orders = orderDAO.findOrdersByCustomerName(name);

        if (orders.isEmpty()) {
            System.out.println("No orders found for \"" + name.trim() + "\".");
            return;
        }

        CustomerDAO customerDAO = new CustomerDAO();
        PublicationDAO publicationDAO = new PublicationDAO();

        System.out.println("=== Orders for \"" + name.trim() + "\" ===");
        for (Order order : orders) {
            Customer customer = customerDAO.findCustomerById(order.getCustomerId());
            Publication publication = publicationDAO.findPublicationById(order.getPublicationId());

            System.out.println("Order ID: " + order.getOrderId());
            System.out.println("Customer: " + (customer == null ? "?" :
                    customer.getFirstName() + " " + customer.getSurname())
                    + " (ID " + order.getCustomerId() + ")");
            System.out.println("Publication: " + (publication == null ? "?" : publication.getName())
                    + " (ID " + order.getPublicationId() + ")");
            System.out.println("Delivery Days: " + order.getDeliveryDays());
            System.out.println("------------------");
        }
        System.out.println(orders.size() + " order(s).");
    }

    /**
     * LIST ALL ORDERS
     */
    private static void listOrders(OrderDAO orderDAO) {

        ArrayList<Order> orders = orderDAO.getAllOrders();

        if (orders.isEmpty()) {
            System.out.println("No orders found.");
            return;
        }

        System.out.println("=== All Orders ===");
        for (Order order : orders) {
            System.out.println(order);
            System.out.println("------------------");
        }
        System.out.println(orders.size() + " order(s).");
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

        int publicationId = InputHelper.readInt(scanner, "Publication ID: ", RULES::verifyPublicationId, "Invalid Publication ID");

        // Look the publication up so only a real publication can be ordered
        Publication publication = new PublicationDAO().findPublicationById(publicationId);
        if (publication == null) {
            System.out.println("Error: Publication " + publicationId + " does not exist.");
            System.out.println("Order not updated.");
            return;
        }
        System.out.println("Publication: " + publication.getName());

        String deliveryDays = InputHelper.readString(scanner, "Delivery Days (e.g. MON,TUE,WED,THU,FRI): ",
                d -> RULES.verifyDeliveryDays(d.toUpperCase().replace(" ", "")),
                "Invalid Delivery Days").toUpperCase().replace(" ", "");

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

    // Reads a whole number; text such as "abc" is rejected and the user is asked again

    private static int readInt(Scanner scanner, String prompt) {
        return InputHelper.readInt(scanner, prompt);
    }
}
