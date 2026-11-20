package com.newsagent.model;

public class DeliveryPerson {
    private static final int MIN_AREA = 1;
    private static final int MAX_AREA = 24;
    private static final int MAX_NAME = 50;
    // Letters only, plus apostrophe, hyphen and space (e.g. O'Brien, Mary-Anne, De Burca)
    private static final String NAME_PATTERN = "[A-Za-z][A-Za-z' -]*";

    private int deliveryPersonId;
    private String firstName;
    private String surname;
    private String phoneNumber;
    private int deliveryArea;

    public DeliveryPerson() {
    }

    /**
     * Verifies that the first name has 1 to 50 characters
     * (ignoring spaces at the start and end).
     */
    public boolean verifyFirstName(String firstName) {
        return firstName != null
                && !firstName.trim().isEmpty()
                && firstName.trim().length() <= MAX_NAME
                && firstName.trim().matches(NAME_PATTERN);
    }

    /**
     * Verifies that the surname has 1 to 50 characters
     * (ignoring spaces at the start and end).
     */
    public boolean verifySurname(String surname) {
        return surname != null
                && !surname.trim().isEmpty()
                && surname.trim().length() <= MAX_NAME
                && surname.trim().matches(NAME_PATTERN);
    }

    /**
     * Verifies that the phone number is exactly 10 digits.
     */
    public boolean verifyPhoneNumber(String phoneNumber) {
        return phoneNumber != null
                && phoneNumber.matches("\\d{10}");
    }

    /**
     * Verifies that the delivery area is a number from 1 to 24.
     */
    public boolean verifyDeliveryArea(int deliveryArea) {
        return deliveryArea >= MIN_AREA
                && deliveryArea <= MAX_AREA;
    }

    /**
     * Creates the delivery person if all
     * validation rules pass.
     */
    public boolean createDeliveryPerson(
            String firstName,
            String surname,
            String phoneNumber,
            int deliveryArea) {
        if (verifyFirstName(firstName)
                && verifySurname(surname)
                && verifyPhoneNumber(phoneNumber)
                && verifyDeliveryArea(deliveryArea)) {
            this.firstName = firstName.trim();
            this.surname = surname.trim();
            this.phoneNumber = phoneNumber;
            this.deliveryArea = deliveryArea;
            return true;
        }
        return false;
    }

    public int getDeliveryPersonId() {
        return deliveryPersonId;
    }

    public void setDeliveryPersonId(int deliveryPersonId) {
        this.deliveryPersonId = deliveryPersonId;
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
        return "Delivery Person ID: " + deliveryPersonId +
        "\nFirst Name: " + firstName +
        "\nSurname: " + surname +
        "\nPhone Number: " + phoneNumber +
        "\nDelivery Area: " + deliveryArea;
    }
}
