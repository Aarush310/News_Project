package com.newsagent.dao;

import com.newsagent.db.DatabaseConnection;
import com.newsagent.model.Publication;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * Saves publications to the MySQL "PublicationTable" table.
 */
public class PublicationDAO {

	public int insertPublication(Publication p) {
		String sql = "INSERT INTO PublicationTable (name, type, frequency, price, quantityInStock) "
				+ "VALUES (?, ?, ?, ?, ?)";

		try (Connection conn = DatabaseConnection.getConnection();
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

	/**
	 * FIND PUBLICATION BY ID
	 * Returns the publication, or null if no row has that ID.
	 */
	public Publication findPublicationById(int publicationId) {
		String sql = "SELECT * FROM PublicationTable WHERE publicationId = ?";
		Publication p = null;

		try (Connection conn = DatabaseConnection.getConnection();
				PreparedStatement ps = conn.prepareStatement(sql)) {

			ps.setInt(1, publicationId);

			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next()) {
					p = new Publication();
					p.createPublication(
							rs.getString("name"),
							rs.getString("type"),
							rs.getString("frequency"),
							rs.getDouble("price"),
							rs.getInt("quantityInStock"));
					p.setPublicationId(rs.getInt("publicationId"));
				}
			}
		} catch (SQLException e) {
			System.out.println("Database error: " + e.getMessage());
		}
		return p;
	}

	/**
	 * UPDATE PUBLICATION
	 * Saves the new details for the publication with p's ID.
	 * Returns true if a row was updated.
	 */
	public boolean updatePublication(Publication p) {
		String sql = "UPDATE PublicationTable SET name = ?, type = ?, frequency = ?, price = ?, quantityInStock = ? "
				+ "WHERE publicationId = ?";

		try (Connection conn = DatabaseConnection.getConnection();
				PreparedStatement ps = conn.prepareStatement(sql)) {

			ps.setString(1, p.getName());
			ps.setString(2, p.getType());
			ps.setString(3, p.getFrequency());
			ps.setDouble(4, p.getPrice());
			ps.setInt(5, p.getQuantityInStock());
			ps.setInt(6, p.getPublicationId());
			return ps.executeUpdate() > 0;
		} catch (SQLException e) {
			System.out.println("Database error: " + e.getMessage());
		}
		return false;
	}

	/**
	 * LIST ALL PUBLICATIONS
	 */
	public ArrayList<Publication> getAllPublications() {
		String sql = "SELECT * FROM PublicationTable ORDER BY publicationId";
		ArrayList<Publication> publications = new ArrayList<>();

		try (Connection conn = DatabaseConnection.getConnection();
				PreparedStatement ps = conn.prepareStatement(sql);
				ResultSet rs = ps.executeQuery()) {

			while (rs.next()) {
				Publication p = new Publication();
				p.createPublication(
						rs.getString("name"),
						rs.getString("type"),
						rs.getString("frequency"),
						rs.getDouble("price"),
						rs.getInt("quantityInStock"));
				p.setPublicationId(rs.getInt("publicationId"));
				publications.add(p);
			}
		} catch (SQLException e) {
			System.out.println("Database error: " + e.getMessage());
		}
		return publications;
	}

	/**
	 * FIND THE ID OF THE PUBLICATION WITH THIS NAME (used to stop duplicate names)
	 * Returns the ID, or -1 if no publication has that name.
	 */
	public int findPublicationIdByName(String name) {
		String sql = "SELECT publicationId FROM PublicationTable WHERE name = ?";

		try (Connection conn = DatabaseConnection.getConnection();
				PreparedStatement ps = conn.prepareStatement(sql)) {

			ps.setString(1, name.trim());

			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next()) {
					return rs.getInt("publicationId");
				}
			}
		} catch (SQLException e) {
			System.out.println("Database error: " + e.getMessage());
		}
		return -1;
	}

	/**
	 * DELETE PUBLICATION
	 * Returns true if a row was deleted. A publication that is still
	 * on an order cannot be deleted (OrderTable foreign key).
	 */
	public boolean deletePublication(int publicationId) {
		String sql = "DELETE FROM PublicationTable WHERE publicationId = ?";

		try (Connection conn = DatabaseConnection.getConnection();
				PreparedStatement ps = conn.prepareStatement(sql)) {

			ps.setInt(1, publicationId);
			return ps.executeUpdate() > 0;
		} catch (SQLIntegrityConstraintViolationException e) {
			System.out.println("Cannot delete: this publication is still on one or more orders.");
		} catch (SQLException e) {
			System.out.println("Database error: " + e.getMessage());
		}
		return false;
	}
}
