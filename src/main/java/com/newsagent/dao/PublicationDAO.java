package com.newsagent.dao;

import com.newsagent.db.DBConnection;
import com.newsagent.model.Publication;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Saves publications to the MySQL "publication" table.
 */
public class PublicationDAO {

	public int insertPublication(Publication p) {
		String sql = "INSERT INTO publication (name, type, frequency, price, quantity_in_stock) "
				+ "VALUES (?, ?, ?, ?, ?)";

		try (Connection conn = DBConnection.getConnection();
				PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

			ps.setString(1, p.getName());
			ps.setString(2, p.getType());
			ps.setString(3, p.getFrequency());
			ps.setDouble(4, p.getPrice());
			ps.setInt(5, p.getQuantityInStock());
			ps.executeUpdate();

			try (ResultSet keys = ps.getGeneratedKeys()) {
				if (keys.next()) {
					int id = keys.getInt(1);
					p.setPublicationId(id);
					return id;
				}
			}
		} catch (SQLException e) {
			System.out.println("Database error: " + e.getMessage());
		}
		return -1;
	}
}
