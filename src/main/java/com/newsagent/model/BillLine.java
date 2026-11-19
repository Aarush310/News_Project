package com.newsagent.model;

/**
 * BILL LINE
 * One publication on a customer's order, with what is needed to bill it.
 */
public class BillLine {

    private final String publicationName;
    private final String frequency;
    private final double price;
    private final String deliveryDays;

    public BillLine(String publicationName, String frequency, double price, String deliveryDays) {
        this.publicationName = publicationName;
        this.frequency = frequency;
        this.price = price;
        this.deliveryDays = deliveryDays;
    }

    public String getPublicationName() {
        return publicationName;
    }

    public String getFrequency() {
        return frequency;
    }

    public double getPrice() {
        return price;
    }

    public String getDeliveryDays() {
        return deliveryDays;
    }
}
