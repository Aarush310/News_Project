package com.newsagent.model;

import java.time.LocalDate;

/**
 * A delivery docket: one per delivery area per day (User Stories 21-24).
 * A docket starts as PENDING and is marked DELIVERED when the round is done.
 */
public class DeliveryDocket {

    private static final int MIN_AREA = 1;
    private static final int MAX_AREA = 24;
    public static final String PENDING = "PENDING";
    public static final String DELIVERED = "DELIVERED";

    private int docketId;
    private int areaNumber;
    private LocalDate docketDate;
    private String status;

    public DeliveryDocket() {
    }

    /**
     * Validates that the area number
     * is from 1 to 24.
     */
    public boolean verifyAreaNumber(int areaNumber) {
        return areaNumber >= MIN_AREA
                && areaNumber <= MAX_AREA;
    }

    /**
     * Validates that a docket date
     * has been entered.
     */
    public boolean verifyDocketDate(LocalDate docketDate) {
        return docketDate != null;
    }

    /**
     * Validates that the status is
     * "PENDING" or "DELIVERED".
     */
    public boolean verifyStatus(String status) {
        return PENDING.equals(status)
                || DELIVERED.equals(status);
    }

    /**
     * A docket can only be deleted while it is PENDING
     * (deliveries already marked must not be lost).
     */
    public boolean canDelete(String status) {
        return PENDING.equals(status);
    }

    /**
     * Creates a PENDING docket if all
     * validation rules pass.
     */
    public boolean createDeliveryDocket(int areaNumber, LocalDate docketDate) {
        if (verifyAreaNumber(areaNumber)
                && verifyDocketDate(docketDate)) {
            this.areaNumber = areaNumber;
            this.docketDate = docketDate;
            this.status = PENDING;
            return true;
        }
        return false;
    }

    /**
     * Changes the date and status if both are valid.
     */
    public boolean updateDeliveryDocket(LocalDate docketDate, String status) {
        if (verifyDocketDate(docketDate)
                && verifyStatus(status)) {
            this.docketDate = docketDate;
            this.status = status;
            return true;
        }
        return false;
    }

    public int getDocketId() {
        return docketId;
    }

    public void setDocketId(int docketId) {
        this.docketId = docketId;
    }

    public int getAreaNumber() {
        return areaNumber;
    }

    public void setAreaNumber(int areaNumber) {
        this.areaNumber = areaNumber;
    }

    public LocalDate getDocketDate() {
        return docketDate;
    }

    public void setDocketDate(LocalDate docketDate) {
        this.docketDate = docketDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Docket ID: " + docketId +
        "\nArea Number: " + areaNumber +
        "\nDate: " + docketDate +
        "\nStatus: " + status;
    }
}
