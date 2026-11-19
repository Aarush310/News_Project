package com.newsagent.model;

public class Newsagent {
    private static final int MAX_NAME = 50;
    private static final int MAX_ADDRESS = 150;

    private int newsagentId;
    private String shopName;
    private String address;
    private String phoneNumber;

    public Newsagent() {
    }

    /**
     * Verifies that the shop name has 1 to 50 characters
     * (ignoring spaces at the start and end).
     */
    public boolean verifyShopName(String shopName) {
        return shopName != null
                && !shopName.trim().isEmpty()
                && shopName.trim().length() <= MAX_NAME;
    }

    /**
     * Verifies that the address has 1 to 150 characters
     * (ignoring spaces at the start and end).
     */
    public boolean verifyAddress(String address) {
        return address != null
                && !address.trim().isEmpty()
                && address.trim().length() <= MAX_ADDRESS;
    }

    /**
     * Verifies that the phone number is exactly 10 digits.
     */
    public boolean verifyPhoneNumber(String phoneNumber) {
        return phoneNumber != null
                && phoneNumber.matches("\\d{10}");
    }

    /**
     * Creates the newsagent if all
     * validation rules pass.
     */
    public boolean createNewsagent(
            String shopName,
            String address,
            String phoneNumber) {
        if (verifyShopName(shopName)
                && verifyAddress(address)
                && verifyPhoneNumber(phoneNumber)) {
            this.shopName = shopName.trim();
            this.address = address.trim();
            this.phoneNumber = phoneNumber;
            return true;
        }
        return false;
    }

    public int getNewsagentId() {
        return newsagentId;
    }

    public void setNewsagentId(int newsagentId) {
        this.newsagentId = newsagentId;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
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

    @Override
    public String toString() {
        return "Newsagent ID: " + newsagentId +
        "\nShop Name: " + shopName +
        "\nAddress: " + address +
        "\nPhone Number: " + phoneNumber;
    }
}
