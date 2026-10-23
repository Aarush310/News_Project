
package com.newsagent.dao;

import com.newsagent.model.DeliveryArea;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DeliveryAreaDAO {

    private final Connection connection;

    public DeliveryAreaDAO(Connection connection) {
        this.connection = connection;
    }

    public boolean addDeliveryArea(DeliveryArea area)
            throws SQLException {

        String sql = "INSERT INTO DeliveryAreaTable " +
                     "(areaNumber, areaName, deliveryPersonId) " +
                     "VALUES (?, ?, ?)";

        try (PreparedStatement stmt =
                 connection.prepareStatement(sql)) {

            stmt.setInt(1, area.getAreaNumber());
            stmt.setString(2, area.getAreaName());
            stmt.setInt(3, area.getDeliveryPersonId());

            return stmt.executeUpdate() == 1;
        }
    }

    public DeliveryArea findDeliveryArea(int areaNumber)
            throws SQLException {

        String sql = "SELECT areaNumber, areaName, " +
                     "deliveryPersonId FROM DeliveryAreaTable " +
                     "WHERE areaNumber = ?";

        try (PreparedStatement stmt =
                 connection.prepareStatement(sql)) {

            stmt.setInt(1, areaNumber);

            try (ResultSet rs = stmt.executeQuery()) {

                if (rs.next()) {
                    return new DeliveryArea(
                        rs.getInt("areaNumber"),
                        rs.getString("areaName"),
                        rs.getInt("deliveryPersonId")
                    );
                }
            }
        }

        return null;
    }

    public boolean updateDeliveryArea(DeliveryArea area)
            throws SQLException {

        String sql = "UPDATE DeliveryAreaTable " +
                     "SET areaName = ?, deliveryPersonId = ? " +
                     "WHERE areaNumber = ?";

        try (PreparedStatement stmt =
                 connection.prepareStatement(sql)) {

            stmt.setString(1, area.getAreaName());
            stmt.setInt(2, area.getDeliveryPersonId());
            stmt.setInt(3, area.getAreaNumber());

            return stmt.executeUpdate() == 1;
        }
    }

    public boolean deleteDeliveryArea(int areaNumber)
            throws SQLException {

        String sql = "DELETE FROM DeliveryAreaTable " +
                     "WHERE areaNumber = ?";

        try (PreparedStatement stmt =
                 connection.prepareStatement(sql)) {

            stmt.setInt(1, areaNumber);

            return stmt.executeUpdate() == 1;
        }
    }
}
