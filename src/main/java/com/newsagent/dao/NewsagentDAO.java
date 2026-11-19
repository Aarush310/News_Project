package com.newsagent.dao;

import com.newsagent.db.DatabaseConnection;
import com.newsagent.model.Newsagent;

import java.sql.*;
import java.util.ArrayList;

public class NewsagentDAO {

    // CREATE
    private static final String INSERT_NEWSAGENT =
    "INSERT INTO NewsagentTable " +
    "(shopName, address, phoneNumber) " +
    "VALUES (?, ?, ?)";

    // READ ONE
    private static final String SELECT_BY_ID =
    "SELECT * FROM NewsagentTable WHERE newsagentId = ?";

    // READ ALL
    private static final String SELECT_ALL =
    "SELECT * FROM NewsagentTable ORDER BY newsagentId";

    // UPDATE
    private static final String UPDATE_NEWSAGENT =
    "UPDATE NewsagentTable " +
    "SET shopName = ?, address = ?, phoneNumber = ? " +
    "WHERE newsagentId = ?";

    // DELETE
    private static final String DELETE_NEWSAGENT =
    "DELETE FROM NewsagentTable WHERE newsagentId = ?";

    // COUNT
    private static final String COUNT_ALL =
    "SELECT COUNT(*) FROM NewsagentTable";

    /**
     * CREATE NEWSAGENT
     * Returns the new ID, or -1 if it was not saved.
     */
    public int addNewsagent(Newsagent newsagent) {

        try (Connection conn =
                DatabaseConnection.getConnection();

             PreparedStatement stmt =
                conn.prepareStatement(INSERT_NEWSAGENT, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, newsagent.getShopName());
            stmt.setString(2, newsagent.getAddress());
            stmt.setString(3, newsagent.getPhoneNumber());

            stmt.executeUpdate();

            try (ResultSet keys = stmt.getGeneratedKeys()) {
                if (keys.next()) {
                    newsagent.setNewsagentId(keys.getInt(1));
                    return newsagent.getNewsagentId();
                }
            }

        } catch (SQLException e) {
            System.out.println("Database error: " + e.getMessage());
        }

        return -1;
    }

    /**
     * FIND NEWSAGENT BY ID
     */
    public Newsagent findNewsagentById(int newsagentId) {

        Newsagent newsagent = null;

        try (Connection conn =
                DatabaseConnection.getConnection();

             PreparedStatement stmt =
                conn.prepareStatement(SELECT_BY_ID)) {

            stmt.setInt(1, newsagentId);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    newsagent = read(rs);
                }
            }

        } catch (SQLException e) {
            System.out.println("Database error: " + e.getMessage());
        }

        return newsagent;
    }

    /**
     * LIST ALL NEWSAGENTS
     */
    public ArrayList<Newsagent> getAllNewsagents() {

        ArrayList<Newsagent> list = new ArrayList<>();

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
     * UPDATE NEWSAGENT
     */
    public boolean updateNewsagent(Newsagent newsagent) {

        try (Connection conn =
                DatabaseConnection.getConnection();

             PreparedStatement stmt =
                conn.prepareStatement(UPDATE_NEWSAGENT)) {

            stmt.setString(1, newsagent.getShopName());
            stmt.setString(2, newsagent.getAddress());
            stmt.setString(3, newsagent.getPhoneNumber());
            stmt.setInt(4, newsagent.getNewsagentId());

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Database error: " + e.getMessage());
            return false;
        }
    }

    /**
     * DELETE NEWSAGENT
     */
    public boolean deleteNewsagent(int newsagentId) {

        try (Connection conn =
                DatabaseConnection.getConnection();

             PreparedStatement stmt =
                conn.prepareStatement(DELETE_NEWSAGENT)) {

            stmt.setInt(1, newsagentId);

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Database error: " + e.getMessage());
            return false;
        }
    }

    /**
     * COUNT NEWSAGENT ACCOUNTS (the last account cannot be deleted)
     */
    public int countNewsagents() {

        try (Connection conn =
                DatabaseConnection.getConnection();

             PreparedStatement stmt =
                conn.prepareStatement(COUNT_ALL);

             ResultSet rs = stmt.executeQuery()) {

            if (rs.next()) {
                return rs.getInt(1);
            }

        } catch (SQLException e) {
            System.out.println("Database error: " + e.getMessage());
        }

        return 0;
    }

    // Builds the object from the current row
    private Newsagent read(ResultSet rs) throws SQLException {
        Newsagent newsagent = new Newsagent();
        newsagent.setNewsagentId(rs.getInt("newsagentId"));
        newsagent.setShopName(rs.getString("shopName"));
        newsagent.setAddress(rs.getString("address"));
        newsagent.setPhoneNumber(rs.getString("phoneNumber"));
        return newsagent;
    }
}
