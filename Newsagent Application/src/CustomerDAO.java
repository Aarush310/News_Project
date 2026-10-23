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
}
