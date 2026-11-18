
package com.newsagent.dao;

import com.newsagent.db.DatabaseConnection;
import java.sql.*;

public class ExtraFeaturesDAO {

    public void searchPublications(String name) {
        String sql = "SELECT publicationId, name, type, price, quantityInStock "
                + "FROM PublicationTable WHERE name LIKE ? ORDER BY name";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, "%" + name + "%");

            try (ResultSet rs = ps.executeQuery()) {
                boolean found = false;

                while (rs.next()) {
                    found = true;
                    System.out.println(
                        "ID: " + rs.getInt("publicationId")
                        + " | Name: " + rs.getString("name")
                        + " | Type: " + rs.getString("type")
                        + " | Price: EUR " + rs.getBigDecimal("price")
                        + " | Stock: " + rs.getInt("quantityInStock")
                    );
                }

                if (!found) {
                    System.out.println("No publications found.");
                }
            }
        } catch (SQLException e) {
            System.out.println("Database error: " + e.getMessage());
        }
    }

    public void showOrdersByArea(int areaNumber) {
        String sql =
            "SELECT o.orderId, c.firstName, c.surname, "
            + "p.name AS publication, o.deliveryDays "
            + "FROM OrderTable o "
            + "JOIN CustomerTable c ON o.customerId = c.customerId "
            + "JOIN PublicationTable p ON o.publicationId = p.publicationId "
            + "WHERE c.deliveryArea = ? "
            + "ORDER BY c.surname, c.firstName, o.orderId";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, areaNumber);

            try (ResultSet rs = ps.executeQuery()) {
                boolean found = false;

                while (rs.next()) {
                    found = true;
                    System.out.println(
                        "Order ID: " + rs.getInt("orderId")
                        + " | Customer: " + rs.getString("firstName")
                        + " " + rs.getString("surname")
                        + " | Publication: " + rs.getString("publication")
                        + " | Days: " + rs.getString("deliveryDays")
                    );
                }

                if (!found) {
                    System.out.println("No orders found for this area.");
                }
            }
        } catch (SQLException e) {
            System.out.println("Database error: " + e.getMessage());
        }
    }

    public void showLowStock(int limit) {
        String sql =
            "SELECT publicationId, name, quantityInStock "
            + "FROM PublicationTable "
            + "WHERE quantityInStock < ? "
            + "ORDER BY quantityInStock, name";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, limit);

            try (ResultSet rs = ps.executeQuery()) {
                boolean found = false;

                while (rs.next()) {
                    found = true;
                    System.out.println(
                        "ID: " + rs.getInt("publicationId")
                        + " | Publication: " + rs.getString("name")
                        + " | Stock: " + rs.getInt("quantityInStock")
                        + " | LOW STOCK"
                    );
                }

                if (!found) {
                    System.out.println("No low stock publications.");
                }
            }
        } catch (SQLException e) {
            System.out.println("Database error: " + e.getMessage());
        }
    }

    public void showCustomerOrders(int customerId) {
        String sql =
            "SELECT c.firstName, c.surname, o.orderId, "
            + "p.name AS publication, p.price, o.deliveryDays "
            + "FROM CustomerTable c "
            + "LEFT JOIN OrderTable o ON c.customerId = o.customerId "
            + "LEFT JOIN PublicationTable p ON o.publicationId = p.publicationId "
            + "WHERE c.customerId = ? "
            + "ORDER BY o.orderId";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, customerId);

            try (ResultSet rs = ps.executeQuery()) {
                boolean foundCustomer = false;
                int count = 0;

                while (rs.next()) {
                    if (!foundCustomer) {
                        foundCustomer = true;
                        System.out.println(
                            "Customer: " + rs.getString("firstName")
                            + " " + rs.getString("surname")
                        );
                    }

                    int orderId = rs.getInt("orderId");

                    if (!rs.wasNull()) {
                        count++;
                        System.out.println(
                            "Order ID: " + orderId
                            + " | Publication: " + rs.getString("publication")
                            + " | Price: EUR " + rs.getBigDecimal("price")
                            + " | Days: " + rs.getString("deliveryDays")
                        );
                    }
                }

                if (!foundCustomer) {
                    System.out.println("Customer not found.");
                } else {
                    System.out.println("Total orders: " + count);
                }
            }
        } catch (SQLException e) {
            System.out.println("Database error: " + e.getMessage());
        }
    }
}
