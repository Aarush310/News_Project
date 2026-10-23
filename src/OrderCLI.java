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
            System.out.println("3. Exit");

            choice = readInt(scanner, "Enter your choice: ");

            switch (choice) {
                case 1: addOrder(scanner, orderDAO); break;
                case 2: findOrder(scanner, orderDAO); break;
                case 3: System.out.println("Goodbye."); break;
                default: System.out.println("Invalid choice. Enter a number from 1 to 3.");
            }
        } while (choice != 3);

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
