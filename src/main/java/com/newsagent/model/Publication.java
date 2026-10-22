package com.newsagent.model;

public class Publication {

	private static final int MIN_NAME_LENGTH = 2;
	private static final int MAX_NAME_LENGTH = 50;

	private int publicationId;
	private String name;
	private String type;
	private String frequency;
	private double price;
	private int quantityInStock;

	public boolean verifyName(String name) {
		return name != null
				&& name.length() >= MIN_NAME_LENGTH
				&& name.length() <= MAX_NAME_LENGTH;
	}

	public boolean verifyType(String type) {
		return "newspaper".equals(type)
				|| "magazine".equals(type);
	}

	public boolean verifyFrequency(String frequency) {
		return "daily".equals(frequency)
				|| "weekly".equals(frequency)
				|| "monthly".equals(frequency);
	}

	public boolean verifyPrice(double price) {
		return price > 0;
	}

	public boolean verifyQuantityInStock(int quantityInStock) {
		return quantityInStock >= 0;
	}

	public boolean createPublication(
			String name,
			String type,
			String frequency,
			double price,
			int quantityInStock) {
		if (verifyName(name)
				&& verifyType(type)
				&& verifyFrequency(frequency)
				&& verifyPrice(price)
				&& verifyQuantityInStock(quantityInStock)) {
			this.name = name;
			this.type = type;
			this.frequency = frequency;
			this.price = price;
			this.quantityInStock = quantityInStock;
			return true;
		}
		return false;
	}

	public int getPublicationId() {
		return publicationId;
	}

	public void setPublicationId(int publicationId) {
		this.publicationId = publicationId;
	}

	public String getName() {
		return name;
	}

	public String getType() {
		return type;
	}

	public String getFrequency() {
		return frequency;
	}

	public double getPrice() {
		return price;
	}

	public int getQuantityInStock() {
		return quantityInStock;
	}
}
