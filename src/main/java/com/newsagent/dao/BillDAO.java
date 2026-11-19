package com.newsagent.dao;

import com.newsagent.db.DatabaseConnection;
import com.newsagent.model.BillLine;

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
