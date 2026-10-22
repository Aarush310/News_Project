package com.newsagent.cli;

import com.newsagent.dao.PublicationDAO;
import com.newsagent.model.Publication;
import java.util.Scanner;

/**
 * End-to-end: user input -> validate (Publication) -> save to MySQL (PublicationDAO)
 */
public class PublicationCLI {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);

		System.out.println("=== Create Publication ===");
		System.out.print("Name (2-50 characters): ");
		String name = in.nextLine().trim();
		System.out.print("Type (newspaper / magazine): ");
		String type = in.nextLine().trim();
		System.out.print("Frequency (daily / weekly / monthly): ");
		String frequency = in.nextLine().trim();

		double price;
		int quantityInStock;
		try {
			System.out.print("Price in euro (greater than 0): ");
			price = Double.parseDouble(in.nextLine().trim());
			System.out.print("Quantity in stock (0 or more): ");
			quantityInStock = Integer.parseInt(in.nextLine().trim());
		} catch (NumberFormatException e) {
			System.out.println("Error: price and quantity must be numbers. Publication not created.");
			in.close();
			return;
		}

		Publication p = new Publication();
		if (!p.verifyName(name)) System.out.println("Invalid Name");
		if (!p.verifyType(type)) System.out.println("Invalid Type");
		if (!p.verifyFrequency(frequency)) System.out.println("Invalid Frequency");
		if (!p.verifyPrice(price)) System.out.println("Invalid Price");
		if (!p.verifyQuantityInStock(quantityInStock)) System.out.println("Invalid Quantity");

		if (p.createPublication(name, type, frequency, price, quantityInStock)) {
			int id = new PublicationDAO().insertPublication(p);
			if (id > 0)
				System.out.println("Publication created successfully with ID " + id);
			else
				System.out.println("Publication not created (database error)");
		} else {
			System.out.println("Publication not created");
		}
		in.close();
	}
}
