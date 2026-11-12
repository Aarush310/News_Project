package com.newsagent.model;

import java.time.LocalDate;

/**
 * PRODUCT SKELETON (TDD step 1) for DeliveryDocket.
 * Every method exists so DeliveryDocketTest (and the DAO/CLI) compile, but nothing is written yet,
 * so every test fails with "No Product Written Yet".
 * To use: copy this file over src/main/java/com/newsagent/model/DeliveryDocket.java, run DeliveryDocketTest (all fail),
 * then restore the real class (Source Control -> Discard Changes).
 */
public class DeliveryDocket {

	public static final String PENDING = "PENDING";
	public static final String DELIVERED = "DELIVERED";

	public DeliveryDocket() {
	}

	public boolean verifyAreaNumber(int areaNumber) {
		throw new UnsupportedOperationException("No Product Written Yet");
	}

	public boolean verifyDocketDate(LocalDate docketDate) {
		throw new UnsupportedOperationException("No Product Written Yet");
	}

	public boolean verifyStatus(String status) {
		throw new UnsupportedOperationException("No Product Written Yet");
	}

	public boolean canDelete(String status) {
		throw new UnsupportedOperationException("No Product Written Yet");
	}

	public boolean createDeliveryDocket(int areaNumber, LocalDate docketDate) {
		throw new UnsupportedOperationException("No Product Written Yet");
	}

	public boolean updateDeliveryDocket(LocalDate docketDate, String status) {
		throw new UnsupportedOperationException("No Product Written Yet");
	}

	public int getDocketId() {
		throw new UnsupportedOperationException("No Product Written Yet");
	}

	public void setDocketId(int docketId) {
		throw new UnsupportedOperationException("No Product Written Yet");
	}

	public int getAreaNumber() {
		throw new UnsupportedOperationException("No Product Written Yet");
	}

	public void setAreaNumber(int areaNumber) {
		throw new UnsupportedOperationException("No Product Written Yet");
	}

	public LocalDate getDocketDate() {
		throw new UnsupportedOperationException("No Product Written Yet");
	}

	public void setDocketDate(LocalDate docketDate) {
		throw new UnsupportedOperationException("No Product Written Yet");
	}

	public String getStatus() {
		throw new UnsupportedOperationException("No Product Written Yet");
	}

	public void setStatus(String status) {
		throw new UnsupportedOperationException("No Product Written Yet");
	}
}
