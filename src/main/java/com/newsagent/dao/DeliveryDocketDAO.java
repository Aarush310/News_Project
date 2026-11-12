package com.newsagent.dao;

import com.newsagent.db.DatabaseConnection;
import com.newsagent.model.DeliveryDocket;
import com.newsagent.model.Order;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class DeliveryDocketDAO {

    // CREATE
    private static final String INSERT_DOCKET =
    "INSERT INTO DeliveryDocketTable (areaNumber, docketDate, status) VALUES (?, ?, ?)";

    // READ ONE
    private static final String SELECT_BY_ID =
    "SELECT * FROM DeliveryDocketTable WHERE docketId = ?";

    // UPDATE (the area stays the same; the date and status can change)
    private static final String UPDATE_DOCKET =
    "UPDATE DeliveryDocketTable SET docketDate = ?, status = ? WHERE docketId = ?";

    // DELETE
    private static final String DELETE_DOCKET =
    "DELETE FROM DeliveryDocketTable WHERE docketId = ?";

    // EVERY ORDER FOR THE CUSTOMERS IN ONE AREA (used to build the docket's delivery list)
    private static final String SELECT_ORDERS_IN_AREA =
    "SELECT c.customerId, c.firstName, c.surname, c.address, " +
    "p.name, p.frequency, o.deliveryDays " +
    "FROM OrderTable o " +
    "JOIN CustomerTable c ON o.customerId = c.customerId " +
    "JOIN PublicationTable p ON o.publicationId = p.publicationId " +
    "WHERE c.deliveryArea = ? " +
    "ORDER BY c.customerId, p.name";

    /**
     * CREATE DOCKET
     * Returns the new docket ID, or -1 if it was not saved.
     */
    public int addDocket(DeliveryDocket docket) {

        try (Connection conn =
                DatabaseConnection.getConnection();

             PreparedStatement stmt =
                conn.prepareStatement(INSERT_DOCKET, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setInt(1, docket.getAreaNumber());
            stmt.setDate(2, Date.valueOf(docket.getDocketDate()));
            stmt.setString(3, docket.getStatus());
            stmt.executeUpdate();

            try (ResultSet keys = stmt.getGeneratedKeys()) {
                if (keys.next()) {
                    docket.setDocketId(keys.getInt(1));
                    return docket.getDocketId();
                }
            }

        } catch (SQLIntegrityConstraintViolationException e) {
            System.out.println("Error: that area does not exist, or it already has a docket for that date.");
        } catch (SQLException e) {
            System.out.println("Database error: " + e.getMessage());
        }
        return -1;
    }

    /**
     * FIND DOCKET BY ID
     */
    public DeliveryDocket findDocketById(int docketId) {

        DeliveryDocket docket = null;

        try (Connection conn =
                DatabaseConnection.getConnection();

             PreparedStatement stmt =
                conn.prepareStatement(SELECT_BY_ID)) {

            stmt.setInt(1, docketId);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    docket = new DeliveryDocket();
                    docket.setDocketId(rs.getInt("docketId"));
                    docket.setAreaNumber(rs.getInt("areaNumber"));
                    docket.setDocketDate(rs.getDate("docketDate").toLocalDate());
                    docket.setStatus(rs.getString("status"));
                }
            }

        } catch (SQLException e) {
            System.out.println("Database error: " + e.getMessage());
        }

        return docket;
    }

    /**
     * THE DELIVERIES ON A DOCKET
     * One line per publication due in the area on that date,
     * using Order.isDueOn (delivery days + monthly rule).
     */
    public List<String> getDeliveries(int areaNumber, LocalDate date) {

        List<String> lines = new ArrayList<>();
        Order rules = new Order();

        try (Connection conn =
                DatabaseConnection.getConnection();

             PreparedStatement stmt =
                conn.prepareStatement(SELECT_ORDERS_IN_AREA)) {

            stmt.setInt(1, areaNumber);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    if (rules.isDueOn(rs.getString("deliveryDays"), rs.getString("frequency"), date)) {
                        lines.add("Customer " + rs.getInt("customerId") + " - "
                                + rs.getString("firstName") + " " + rs.getString("surname") + ", "
                                + rs.getString("address") + ": " + rs.getString("name"));
                    }
                }
            }

        } catch (SQLException e) {
            System.out.println("Database error: " + e.getMessage());
        }

        return lines;
    }

    /**
     * UPDATE DOCKET
     */
    public boolean updateDocket(DeliveryDocket docket) {

        try (Connection conn =
                DatabaseConnection.getConnection();

             PreparedStatement stmt =
                conn.prepareStatement(UPDATE_DOCKET)) {

            stmt.setDate(1, Date.valueOf(docket.getDocketDate()));
            stmt.setString(2, docket.getStatus());
            stmt.setInt(3, docket.getDocketId());

            return stmt.executeUpdate() > 0;

        } catch (SQLIntegrityConstraintViolationException e) {
            System.out.println("Error: this area already has a docket for that date.");
        } catch (SQLException e) {
            System.out.println("Database error: " + e.getMessage());
        }
        return false;
    }

    /**
     * DELETE DOCKET
     */
    public boolean deleteDocket(int docketId) {

        try (Connection conn =
                DatabaseConnection.getConnection();

             PreparedStatement stmt =
                conn.prepareStatement(DELETE_DOCKET)) {

            stmt.setInt(1, docketId);

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Database error: " + e.getMessage());
            return false;
        }
    }
}
