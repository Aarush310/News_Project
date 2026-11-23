package com.newsagent.dao;

import com.newsagent.db.DatabaseConnection;
import com.newsagent.model.DeliveryPerson;

import java.sql.*;
import java.util.ArrayList;

public class DeliveryPersonDAO {    

    // CREATE
    private static final String INSERT_DELIVERY_PERSON =
    "INSERT INTO DeliveryPersonTable " +
    "(firstName, surname, phoneNumber, deliveryArea) " +
    "VALUES (?, ?, ?, ?)";

    // READ ONE
    private static final String SELECT_BY_ID =
    "SELECT * FROM DeliveryPersonTable WHERE deliveryPersonId = ?";

    // READ ALL
    private static final String SELECT_ALL =
    "SELECT * FROM DeliveryPersonTable ORDER BY deliveryPersonId";

    // UPDATE
    private static final String UPDATE_DELIVERY_PERSON =
    "UPDATE DeliveryPersonTable " +
    "SET firstName = ?, surname = ?, phoneNumber = ?, deliveryArea = ? " +
    "WHERE deliveryPersonId = ?";

    // DELETE
    private static final String DELETE_DELIVERY_PERSON =
    "DELETE FROM DeliveryPersonTable WHERE deliveryPersonId = ?";

    // DELIVERY AREAS DONE BY ONE PERSON
    private static final String COUNT_AREAS =
    "SELECT COUNT(*) FROM DeliveryAreaTable WHERE deliveryPersonId = ?";

    /**
     * CREATE DELIVERY PERSON
     * Returns the new ID, or -1 if it was not saved.
     */
    public int addDeliveryPerson(DeliveryPerson person) {

        try (Connection conn =
                DatabaseConnection.getConnection();

             PreparedStatement stmt =
                conn.prepareStatement(INSERT_DELIVERY_PERSON, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, person.getFirstName());
            stmt.setString(2, person.getSurname());
            stmt.setString(3, person.getPhoneNumber());
            stmt.setInt(4, person.getDeliveryArea());

            stmt.executeUpdate();

            try (ResultSet keys = stmt.getGeneratedKeys()) {
                if (keys.next()) {
                    person.setDeliveryPersonId(keys.getInt(1));
                    return person.getDeliveryPersonId();
                }
            }

        } catch (SQLException e) {
            System.out.println("Database error: " + e.getMessage());
        }

        return -1;
    }

    /**
     * FIND DELIVERY PERSON BY ID
     */
    public DeliveryPerson findDeliveryPersonById(int deliveryPersonId) {

        DeliveryPerson person = null;

        try (Connection conn =
                DatabaseConnection.getConnection();

             PreparedStatement stmt =
                conn.prepareStatement(SELECT_BY_ID)) {

            stmt.setInt(1, deliveryPersonId);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    person = read(rs);
                }
            }

        } catch (SQLException e) {
            System.out.println("Database error: " + e.getMessage());
        }

        return person;
    }

    /**
     * LIST ALL DELIVERY PERSONS
     */
    public ArrayList<DeliveryPerson> getAllDeliveryPersons() {

        ArrayList<DeliveryPerson> list = new ArrayList<>();

        try (Connection conn =
                DatabaseConnection.getConnection();

             PreparedStatement stmt =
                conn.prepareStatement(SELECT_ALL);

             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                list.add(read(rs));
            }

        } catch (SQLException e) {
            System.out.println("Database error: " + e.getMessage());
        }

        return list;
    }

    /**
     * UPDATE DELIVERY PERSON
     */
    public boolean updateDeliveryPerson(DeliveryPerson person) {

        try (Connection conn =
                DatabaseConnection.getConnection();

             PreparedStatement stmt =
                conn.prepareStatement(UPDATE_DELIVERY_PERSON)) {

            stmt.setString(1, person.getFirstName());
            stmt.setString(2, person.getSurname());
            stmt.setString(3, person.getPhoneNumber());
            stmt.setInt(4, person.getDeliveryArea());
            stmt.setInt(5, person.getDeliveryPersonId());

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Database error: " + e.getMessage());
            return false;
        }
    }

    /**
     * DELETE DELIVERY PERSON
     */
    public boolean deleteDeliveryPerson(int deliveryPersonId) {

        try (Connection conn =
                DatabaseConnection.getConnection();

             PreparedStatement stmt =
                conn.prepareStatement(DELETE_DELIVERY_PERSON)) {

            stmt.setInt(1, deliveryPersonId);

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Database error: " + e.getMessage());
            return false;
        }
    }

    /**
     * COUNT THE DELIVERY AREAS THIS PERSON DOES (a person with areas cannot be deleted)
     */
    public int countAreasForPerson(int deliveryPersonId) {

        try (Connection conn =
                DatabaseConnection.getConnection();

             PreparedStatement stmt =
                conn.prepareStatement(COUNT_AREAS)) {

            stmt.setInt(1, deliveryPersonId);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1);
                }
            }

        } catch (SQLException e) {
            System.out.println("Database error: " + e.getMessage());
        }

        return 0;
    }

    // Builds the object from the current row
    private DeliveryPerson read(ResultSet rs) throws SQLException {
        DeliveryPerson person = new DeliveryPerson();
        person.setDeliveryPersonId(rs.getInt("deliveryPersonId"));
        person.setFirstName(rs.getString("firstName"));
        person.setSurname(rs.getString("surname"));
        person.setPhoneNumber(rs.getString("phoneNumber"));
        person.setDeliveryArea(rs.getInt("deliveryArea"));
        return person;
    }
}
