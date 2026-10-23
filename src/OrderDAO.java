import java.sql.*;
import java.util.ArrayList;

public class OrderDAO {

    // CREATE
    private static final String INSERT_ORDER =
    "INSERT INTO OrderTable " +
    "(customerId, publicationId, deliveryDays) " +
    "VALUES (?, ?, ?)";

    // READ ONE
    private static final String SELECT_BY_ID =
    "SELECT * FROM OrderTable WHERE orderId = ?";

    // READ THE PUBLICATIONS ALREADY ON A CUSTOMER'S ORDER (used to stop duplicates)
    private static final String SELECT_PUBLICATIONS_FOR_CUSTOMER =
    "SELECT publicationId FROM OrderTable WHERE customerId = ?";

    /**
     * CREATE ORDER
     */
    public boolean addOrder(Order order) {

        try (Connection conn =
                DatabaseConnection.getConnection();

             PreparedStatement stmt =
                conn.prepareStatement(INSERT_ORDER)) {

            stmt.setInt(1, order.getCustomerId());
            stmt.setInt(2, order.getPublicationId());
            stmt.setString(3, order.getDeliveryDays());

            int rowsInserted = stmt.executeUpdate();

            return rowsInserted > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * FIND ORDER BY ID
     */
    public Order findOrderById(int orderId) {

        Order order = null;

        try (Connection conn =
                DatabaseConnection.getConnection();

             PreparedStatement stmt =
                conn.prepareStatement(SELECT_BY_ID)) {

            stmt.setInt(1, orderId);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {

                order = new Order();

                order.setOrderId(rs.getInt("orderId"));
                order.setCustomerId(rs.getInt("customerId"));
                order.setPublicationId(rs.getInt("publicationId"));
                order.setDeliveryDays(rs.getString("deliveryDays"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return order;
    }

    /**
     * FIND THE PUBLICATION IDS ALREADY ON A CUSTOMER'S ORDER
     */
    public int[] getPublicationIdsForCustomer(int customerId) {

        ArrayList<Integer> ids = new ArrayList<>();

        try (Connection conn =
                DatabaseConnection.getConnection();

             PreparedStatement stmt =
                conn.prepareStatement(SELECT_PUBLICATIONS_FOR_CUSTOMER)) {

            stmt.setInt(1, customerId);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                ids.add(rs.getInt("publicationId"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        int[] result = new int[ids.size()];
        for (int i = 0; i < ids.size(); i++) {
            result[i] = ids.get(i);
        }
        return result;
    }
}
