package com.newsagent.model;

import java.time.LocalDate;

/**
 * BILL
 * Works out a customer's monthly bill: how many deliveries each
 * publication on their order gets in a month, and what they cost.
 * The delivery rule comes from Order.isDueOn, so the bill always
 * matches the delivery dockets.
 */
public class Bill {

    private static final int MIN_MONTH = 1;
    private static final int MAX_MONTH = 12;
    private static final int MIN_YEAR = 2000;
    private static final int MAX_YEAR = 2100;

    private double total;

    public Bill() {
        this.total = 0.0;
    }

    /**
     * Validates that the month is 1 (January) to 12 (December).
     */
    public boolean verifyMonth(int month) {
        return month >= MIN_MONTH && month <= MAX_MONTH;
    }

    /**
     * Validates that the year is 2000 to 2100.
     */
    public boolean verifyYear(int year) {
        return year >= MIN_YEAR && year <= MAX_YEAR;
    }

    /**
     * Counts the deliveries of one order in a month: every date in
     * the month on which Order.isDueOn says the publication is due.
     */
    public int countDeliveries(String deliveryDays, String frequency, int year, int month) {
        Order rules = new Order();
        LocalDate first = LocalDate.of(year, month, 1);
        int count = 0;
        for (int day = 1; day <= first.lengthOfMonth(); day++) {
            if (rules.isDueOn(deliveryDays, frequency, first.withDayOfMonth(day))) {
                count++;
            }
        }
        return count;
    }

    /**
     * The cost of one publication for the month, rounded to the cent.
     */
    public double lineTotal(int deliveries, double price) {
        return Math.round(deliveries * price * 100) / 100.0;
    }

    /**
     * Adds one publication's line to the bill and returns that line's cost.
     */
    public double addLine(int deliveries, double price) {
        double line = lineTotal(deliveries, price);
        total = Math.round((total + line) * 100) / 100.0;
        return line;
    }

    public double getTotal() {
        return total;
    }
}
