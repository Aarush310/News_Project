package com.newsagent.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Order {

    private static final int MIN_ID = 1;
    private static final int MAX_DAYS = 7;
    private static final List<String> VALID_DAYS =
            Arrays.asList("MON", "TUE", "WED", "THU", "FRI", "SAT", "SUN");

    private int orderId;
    private int customerId;
    private int publicationId;
    private String deliveryDays;

    public Order() {
    }

    public Order(int customerId, int publicationId, String deliveryDays) {
        this.customerId = customerId;
        this.publicationId = publicationId;
        this.deliveryDays = deliveryDays;
    }

    /**
     * Validates that the customer ID
     * is 1 or more.
     */
    public boolean verifyCustomerId(int customerId) {
        return customerId >= MIN_ID;
    }

    /**
     * Validates that the publication ID
     * is 1 or more.
     */
    public boolean verifyPublicationId(int publicationId) {
        return publicationId >= MIN_ID;
    }

    /**
     * Validates that the delivery days are 1 to 7
     * different days from MON to SUN.
     */
    public boolean verifyDeliveryDays(String deliveryDays) {
        if (deliveryDays == null || deliveryDays.isEmpty()) {
            return false;
        }
        // -1 keeps empty parts, so "MON," is rejected instead of read as "MON"
        String[] days = deliveryDays.split(",", -1);
        if (days.length > MAX_DAYS) {
            return false;
        }
        List<String> seen = new ArrayList<>();
        for (String day : days) {
            if (!VALID_DAYS.contains(day)
                    || seen.contains(day)) {
                return false;
            }
            seen.add(day);
        }
        return true;
    }

    /**
     * Validates that the publication is not
     * already on the customer's order.
     */
    public boolean verifyNewPublication(int publicationId, int[] publicationsOnOrder) {
        for (int id : publicationsOnOrder) {
            if (id == publicationId) {
                return false;
            }
        }
        return true;
    }

    /**
     * Validates a customer name typed in to search for orders:
     * 1 to 50 characters, not blank.
     */
    public boolean verifyCustomerName(String customerName) {
        throw new RuntimeException("No Product Written Yet");
    }

    /**
     * Creates an order if all
     * validation rules pass.
     */
    public boolean createOrder(
            int customerId,
            int publicationId,
            String deliveryDays,
            int[] publicationsOnOrder) {
        if (verifyCustomerId(customerId)
                && verifyPublicationId(publicationId)
                && verifyDeliveryDays(deliveryDays)
                && verifyNewPublication(publicationId, publicationsOnOrder)) {
            this.customerId = customerId;
            this.publicationId = publicationId;
            this.deliveryDays = deliveryDays;
            return true;
        }
        return false;
    }

    /**
     * Checks whether a delivery is due on a date.
     * Monthly publications are due only on the
     * first chosen day of the month.
     */
    public boolean isDueOn(String deliveryDays, String frequency, LocalDate date) {
        String today = date.getDayOfWeek().name().substring(0, 3);
        if (!Arrays.asList(deliveryDays.split(",")).contains(today)) {
            return false;
        }
        if ("monthly".equals(frequency)) {
            return isFirstChosenDayOfMonth(deliveryDays, date);
        }
        return true;
    }

    /**
     * True when no earlier date in the same month falls on
     * one of the chosen days (works for one or more chosen days).
     */
    private boolean isFirstChosenDayOfMonth(String deliveryDays, LocalDate date) {
        List<String> chosen = Arrays.asList(deliveryDays.split(","));
        for (int dayOfMonth = 1; dayOfMonth < date.getDayOfMonth(); dayOfMonth++) {
            String name = date.withDayOfMonth(dayOfMonth)
                    .getDayOfWeek().name().substring(0, 3);
            if (chosen.contains(name)) {
                return false;
            }
        }
        return true;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public int getPublicationId() {
        return publicationId;
    }

    public void setPublicationId(int publicationId) {
        this.publicationId = publicationId;
    }

    public String getDeliveryDays() {
        return deliveryDays;
    }

    public void setDeliveryDays(String deliveryDays) {
        this.deliveryDays = deliveryDays;
    }

    @Override
    public String toString() {
        return "Order ID: " + orderId +
        "\nCustomer ID: " + customerId +
        "\nPublication ID: " + publicationId +
        "\nDelivery Days: " + deliveryDays;
    }
}
