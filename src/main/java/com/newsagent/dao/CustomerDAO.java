package com.newsagent.dao;

import com.newsagent.db.DatabaseConnection;
import com.newsagent.model.Customer;

import java.sql.*;

public class CustomerDAO {

    // CREATE
    private static final String INSERT_CUSTOMER =
    "INSERT INTO CustomerTable " +
    "(firstName, surname, address, phoneNumber, deliveryArea) " +
    "VALUES (?, ?, ?, ?, ?)";

    // READ ONE
    private static final String SELECT_BY_ID =
    "SELECT * FROM CustomerTable WHERE customerId = ?";

    // UPDATE
    private static final String UPDATE_CUSTOMER =
    "UPDATE CustomerTable " +
    "SET firstName = ?, surname = ?, address = ?, phoneNumber = ?, deliveryArea = ? " +
    "WHERE customerId = ?";

    // DELETE
    private static final String DELETE_CUSTOMER =
    "DELETE FROM CustomerTable WHERE customerId = ?";

    /**
     * CREATE CUSTOMER
     */
    public boolean addCustomer(Customer customer) {

        try (Connection conn =
                DatabaseConnection.getConnection();

             PreparedStatement stmt =
                conn.prepareStatement(INSERT_CUSTOMER)) {

            stmt.setString(1, customer.getFirstName());
            stmt.setString(2, customer.getSurname());
            stmt.setString(3, customer.getAddress());
            stmt.setString(4, customer.getPhoneNumber());
            stmt.setInt(5, customer.getDeliveryArea());

            int rowsInserted = stmt.executeUpdate();

            return rowsInserted > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * FIND CUSTOMER BY ID
     */
    public Customer findCustomerById(int customerId) {

        Customer customer = null;

        try (Connection conn =
                DatabaseConnection.getConnection();

             PreparedStatement stmt =
                conn.prepareStatement(SELECT_BY_ID)) {

            stmt.setInt(1, customerId);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {

                customer = new Customer();

                customer.setCustomerId(rs.getInt("customerId"));
                customer.setFirstName(rs.getString("firstName"));
                customer.setSurname(rs.getString("surname"));
                customer.setAddress(rs.getString("address"));
                customer.setPhoneNumber(rs.getString("phoneNumber"));
                customer.setDeliveryArea(rs.getInt("deliveryArea"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return customer;
    }

    /**
     * UPDATE CUSTOMER
     * Saves the new details for the customer with this customer's ID.
     */
    public boolean updateCustomer(Customer customer) {

        try (Connection conn =
                DatabaseConnection.getConnection();

             PreparedStatement stmt =
                conn.prepareStatement(UPDATE_CUSTOMER)) {

            stmt.setString(1, customer.getFirstName());
            stmt.setString(2, customer.getSurname());
            stmt.setString(3, customer.getAddress());
            stmt.setString(4, customer.getPhoneNumber());
            stmt.setInt(5, customer.getDeliveryArea());
            stmt.setInt(6, customer.getCustomerId());

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Database error: " + e.getMessage());
            return false;
        }
    }

    /**
     * DELETE CUSTOMER
     * A customer who still has orders cannot be deleted (OrderTable foreign key).
     */
    public boolean deleteCustomer(int customerId) {

        try (Connection conn =
                DatabaseConnection.getConnection();

             PreparedStatement stmt =
                conn.prepareStatement(DELETE_CUSTOMER)) {

            stmt.setInt(1, customerId);

            return stmt.executeUpdate() > 0;

        } catch (SQLIntegrityConstraintViolationException e) {
            System.out.println("Cannot delete: this customer still has orders.");
            return false;
        } catch (SQLException e) {
            System.out.println("Database error: " + e.getMessage());
            return false;
        }
    }
}
