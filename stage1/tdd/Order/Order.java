import java.time.LocalDate;

/**
 * PRODUCT SKELETON (TDD step 1).
 * Every method exists so the tests compile, but nothing is written yet,
 * so every test fails with "No Product Written Yet".
 */
public class Order {

    public Order() {
    }

    public boolean verifyCustomerId(int customerId) {
        throw new UnsupportedOperationException("No Product Written Yet");
    }

    public boolean verifyPublicationId(int publicationId) {
        throw new UnsupportedOperationException("No Product Written Yet");
    }

    public boolean verifyDeliveryDays(String deliveryDays) {
        throw new UnsupportedOperationException("No Product Written Yet");
    }

    public boolean verifyNewPublication(int publicationId, int[] publicationsOnOrder) {
        throw new UnsupportedOperationException("No Product Written Yet");
    }

    public boolean createOrder(int customerId, int publicationId,
            String deliveryDays, int[] publicationsOnOrder) {
        throw new UnsupportedOperationException("No Product Written Yet");
    }

    public boolean isDueOn(String deliveryDays, String frequency, LocalDate date) {
        throw new UnsupportedOperationException("No Product Written Yet");
    }

    public int getCustomerId() {
        throw new UnsupportedOperationException("No Product Written Yet");
    }

    public int getPublicationId() {
        throw new UnsupportedOperationException("No Product Written Yet");
    }

    public String getDeliveryDays() {
        throw new UnsupportedOperationException("No Product Written Yet");
    }
}
