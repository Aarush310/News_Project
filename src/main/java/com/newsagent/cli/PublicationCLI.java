package com.newsagent.cli;

import com.newsagent.dao.PublicationDAO;
import com.newsagent.model.Publication;
import java.util.Scanner;

/**
 * End-to-end: user input -> validate (Publication) -> add / find / update / delete in MySQL (PublicationDAO)
 */
public class PublicationCLI {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		PublicationDAO publicationDAO = new PublicationDAO();
		boolean running = true;

		while (running) {
			System.out.println("\n===== PUBLICATION MENU =====");
			System.out.println("1. Add Publication");
			System.out.println("2. Find Publication By ID");
			System.out.println("3. Update Publication");
			System.out.println("4. Delete Publication");
			System.out.println("5. Exit");
			System.out.print("Enter your choice: ");

			switch (in.nextLine().trim()) {
				case "1":
					addPublication(in, publicationDAO);
					break;
				case "2":
					findPublication(in, publicationDAO);
					break;
				case "3":
					updatePublication(in, publicationDAO);
					break;
				case "4":
					deletePublication(in, publicationDAO);
					break;
				case "5":
					running = false;
					System.out.println("Goodbye.");
					break;
				default:
					System.out.println("Invalid choice. Enter 1 to 5.");
			}
		}
		in.close();
	}

	private static void addPublication(Scanner in, PublicationDAO publicationDAO) {
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
			return;
		}

		Publication p = new Publication();
		if (!p.verifyName(name)) System.out.println("Invalid Name");
		if (!p.verifyType(type)) System.out.println("Invalid Type");
		if (!p.verifyFrequency(frequency)) System.out.println("Invalid Frequency");
		if (!p.verifyPrice(price)) System.out.println("Invalid Price");
		if (!p.verifyQuantityInStock(quantityInStock)) System.out.println("Invalid Quantity");

		if (p.createPublication(name, type, frequency, price, quantityInStock)) {
			int id = publicationDAO.insertPublication(p);
			if (id > 0)
				System.out.println("Publication created successfully with ID " + id);
			else
				System.out.println("Publication not created (database error)");
		} else {
			System.out.println("Publication not created");
		}
	}

	private static void findPublication(Scanner in, PublicationDAO publicationDAO) {
		System.out.print("Enter Publication ID: ");
		int id;
		try {
			id = Integer.parseInt(in.nextLine().trim());
		} catch (NumberFormatException e) {
			System.out.println("Error: ID must be a number.");
			return;
		}

		Publication p = publicationDAO.findPublicationById(id);
		if (p == null) {
			System.out.println("No publication found with ID " + id);
		} else {
			System.out.println("ID: " + p.getPublicationId()
					+ " | Name: " + p.getName()
					+ " | Type: " + p.getType()
					+ " | Frequency: " + p.getFrequency()
					+ " | Price: " + p.getPrice()
					+ " | Stock: " + p.getQuantityInStock());
		}
	}

	private static void updatePublication(Scanner in, PublicationDAO publicationDAO) {
		Integer id = readId(in);
		if (id == null) return;

		Publication current = publicationDAO.findPublicationById(id);
		if (current == null) {
			System.out.println("No publication found with ID " + id);
			return;
		}
		System.out.println("Current: " + current.getName() + " | " + current.getType() + " | "
				+ current.getFrequency() + " | " + current.getPrice() + " | " + current.getQuantityInStock());
		System.out.println("Enter the new details:");

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
			System.out.println("Error: price and quantity must be numbers. Publication not updated.");
			return;
		}

		Publication p = new Publication();
		if (!p.verifyName(name)) System.out.println("Invalid Name");
		if (!p.verifyType(type)) System.out.println("Invalid Type");
		if (!p.verifyFrequency(frequency)) System.out.println("Invalid Frequency");
		if (!p.verifyPrice(price)) System.out.println("Invalid Price");
		if (!p.verifyQuantityInStock(quantityInStock)) System.out.println("Invalid Quantity");

		if (p.createPublication(name, type, frequency, price, quantityInStock)) {
			p.setPublicationId(id);
			if (publicationDAO.updatePublication(p))
				System.out.println("Publication " + id + " updated successfully");
			else
				System.out.println("Publication not updated (database error)");
		} else {
			System.out.println("Publication not updated");
		}
	}

	private static void deletePublication(Scanner in, PublicationDAO publicationDAO) {
		Integer id = readId(in);
		if (id == null) return;

		if (publicationDAO.findPublicationById(id) == null) {
			System.out.println("No publication found with ID " + id);
			return;
		}
		if (publicationDAO.deletePublication(id))
			System.out.println("Publication " + id + " deleted successfully");
		else
			System.out.println("Publication not deleted");
	}

	private static Integer readId(Scanner in) {
		System.out.print("Enter Publication ID: ");
		try {
			return Integer.parseInt(in.nextLine().trim());
		} catch (NumberFormatException e) {
			System.out.println("Error: ID must be a number.");
			return null;
		}
	}
}
