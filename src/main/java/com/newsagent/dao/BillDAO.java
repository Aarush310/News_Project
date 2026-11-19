package com.newsagent.dao;

import com.newsagent.db.DatabaseConnection;
import com.newsagent.model.BillLine;
import com.newsagent.model.Customer;

import java.sql.*;
import java.util.ArrayList;

public class BillDAO {

    // Every publication on the customer's order, with its frequency and price
    private static final String SELECT_LINES_FOR_CUSTOMER =
    "SELECT p.name, p.frequency, p.price, o.deliveryDays " +
    "FROM OrderTable o " +
    "JOIN PublicationTable p ON o.publicationId = p.publicationId " +
    "WHERE o.customerId = ? " +
    "ORDER BY p.name";

    // Customers whose first name, surname or full name contains the search words
    private static final String SELECT_CUSTOMERS_BY_NAME =
    "SELECT * FROM CustomerTable " +
    "WHERE firstName LIKE ? OR surname LIKE ? " +
    "OR CONCAT(firstName, ' ', surname) LIKE ? " +
    "ORDER BY surname, firstName";

    /**
     * FIND CUSTOMERS BY NAME
     * Matches part of the first name, surname or full name, so "Kelly"
     * or "Mary Kelly" both work. Empty if nobody matches.
     */
    public ArrayList<Customer> findCustomersByName(String name) {

        ArrayList<Customer> customers = new ArrayList<>();
        String pattern = "%" + name.trim() + "%";

        try (Connection conn =
                DatabaseConnection.getConnection();

             PreparedStatement stmt =
                conn.prepareStatement(SELECT_CUSTOMERS_BY_NAME)) {

            stmt.setString(1, pattern);
            stmt.setString(2, pattern);
            stmt.setString(3, pattern);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Customer customer = new Customer();
                    customer.setCustomerId(rs.getInt("customerId"));
                    customer.setFirstName(rs.getString("firstName"));
                    customer.setSurname(rs.getString("surname"));
                    customer.setAddress(rs.getString("address"));
                    customer.setPhoneNumber(rs.getString("phoneNumber"));
                    customer.setDeliveryArea(rs.getInt("deliveryArea"));
                    customers.add(customer);
                }
            }

        } catch (SQLException e) {
            System.out.println("Database error: " + e.getMessage());
        }

        return customers;
    }

    /**
     * GET BILL LINES FOR A CUSTOMER
     * One line per publication the customer gets; empty if they have no orders.
     */
    public ArrayList<BillLine> getBillLines(int customerId) {

        ArrayList<BillLine> lines = new ArrayList<>();

        try (Connection conn =
                DatabaseConnection.getConnection();

             PreparedStatement stmt =
                conn.prepareStatement(SELECT_LINES_FOR_CUSTOMER)) {

            stmt.setInt(1, customerId);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    lines.add(new BillLine(
                            rs.getString("name"),
                            rs.getString("frequency"),
                            rs.getDouble("price"),
                            rs.getString("deliveryDays")));
                }
            }

        } catch (SQLException e) {
            System.out.println("Database error: " + e.getMessage());
        }

        return lines;
    }
}
