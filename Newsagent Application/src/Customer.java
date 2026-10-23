public class Customer {

    private static final int MIN_AREA = 1;
    private static final int MAX_AREA = 24;

    private int customerId;
    private String firstName;
    private String surname;
    private String address;
    private String phoneNumber;
    private int deliveryArea;

    public Customer() {
    }

    public Customer(String firstName, String surname, String address,
            String phoneNumber, int deliveryArea) {
        this.firstName = firstName;
        this.surname = surname;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.deliveryArea = deliveryArea;
    }

    /**
     * Validates that the first name contains
     * at least one character.
     */
    public boolean verifyFirstName(String firstName) {
        return firstName != null
                && !firstName.trim().isEmpty();
    }

    /**
     * Validates that the surname contains
     * at least one character.
     */
    public boolean verifySurname(String surname) {
        return surname != null
                && !surname.trim().isEmpty();
    }

    /**
     * Validates that the address contains
     * at least one character.
     */
    public boolean verifyAddress(String address) {
        return address != null
                && !address.trim().isEmpty();
    }

    /**
     * Validates that the phone number
     * contains exactly 10 digits.
     */
    public boolean verifyPhoneNumber(String phoneNumber) {
        return phoneNumber != null
                && phoneNumber.matches("\\d{10}");
    }

    /**
     * Validates that the delivery area
     * is from 1 to 24.
     */
    public boolean verifyDeliveryArea(int deliveryArea) {
        return deliveryArea >= MIN_AREA
                && deliveryArea <= MAX_AREA;
    }

    /**
     * Creates a customer account if all
     * validation rules pass.
     */
    public boolean createCustomerAccount(
            String firstName,
            String surname,
            String address,
            String phoneNumber,
            int deliveryArea) {
        if (verifyFirstName(firstName)
                && verifySurname(surname)
                && verifyAddress(address)
                && verifyPhoneNumber(phoneNumber)
                && verifyDeliveryArea(deliveryArea)) {
            this.firstName = firstName;
            this.surname = surname;
            this.address = address;
            this.phoneNumber = phoneNumber;
            this.deliveryArea = deliveryArea;
            return true;
        }
        return false;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public int getDeliveryArea() {
        return deliveryArea;
    }

    public void setDeliveryArea(int deliveryArea) {
        this.deliveryArea = deliveryArea;
    }

    @Override
    public String toString() {
        return "Customer ID: " + customerId +
        "\nFirst Name: " + firstName +
        "\nSurname: " + surname +
        "\nAddress: " + address +
        "\nPhone Number: " + phoneNumber +
        "\nDelivery Area: " + deliveryArea;
    }
}
