package com.newsagent.model;
import java.time.LocalDate;

/**
 * PRODUCT SKELETON (TDD step 1) for Order.
 * Every method exists so OrderTest (and the DAO/CLI) compile, but nothing is written yet,
 * so every test fails with "No Product Written Yet".
 * To use: copy this file over src/main/java/com/newsagent/model/Order.java, run OrderTest (all fail),
 * then restore the real class (Source Control -> Discard Changes).
 */
public class Order {

	public Order() {
	}

	public Order(int customerId, int publicationId, String deliveryDays) {
		throw new UnsupportedOperationException("No Product Written Yet");
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

	public boolean createOrder(int customerId, int publicationId, String deliveryDays, int[] publicationsOnOrder) {
		throw new UnsupportedOperationException("No Product Written Yet");
	}

	public boolean isDueOn(String deliveryDays, String frequency, LocalDate date) {
		throw new UnsupportedOperationException("No Product Written Yet");
	}

	public int getOrderId() {
		throw new UnsupportedOperationException("No Product Written Yet");
	}

	public void setOrderId(int orderId) {
		throw new UnsupportedOperationException("No Product Written Yet");
	}

	public int getCustomerId() {
		throw new UnsupportedOperationException("No Product Written Yet");
	}

	public void setCustomerId(int customerId) {
		throw new UnsupportedOperationException("No Product Written Yet");
	}

	public int getPublicationId() {
		throw new UnsupportedOperationException("No Product Written Yet");
	}

	public void setPublicationId(int publicationId) {
		throw new UnsupportedOperationException("No Product Written Yet");
	}

	public String getDeliveryDays() {
		throw new UnsupportedOperationException("No Product Written Yet");
	}

	public void setDeliveryDays(String deliveryDays) {
		throw new UnsupportedOperationException("No Product Written Yet");
	}

	@Override
	public String toString() {
		return "No Product Written Yet";
	}
}
