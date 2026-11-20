package com.newsagent.cli;

import com.newsagent.dao.PublicationDAO;
import com.newsagent.model.Publication;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * End-to-end: user input -> validate (Publication) -> add / find / update / delete in MySQL (PublicationDAO)
 */
public class PublicationCLI {

	// Used only to check input rules while the user types
	private static final Publication RULES = new Publication();

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
			System.out.println("5. List All Publications");
			System.out.println("6. Exit");
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
					listPublications(publicationDAO);
					break;
				case "6":
					running = false;
					System.out.println("Goodbye.");
					break;
				default:
					System.out.println("Invalid choice. Enter a number from 1 to 6.");
			}
		}
		in.close();
	}

	private static void addPublication(Scanner in, PublicationDAO publicationDAO) {
		System.out.println("=== Create Publication ===");
		String name = InputHelper.readString(in, "Name (2-50 characters): ", RULES::verifyName, "Invalid Name (2-50 characters)");
		String type = InputHelper.readString(in, "Type (newspaper / magazine): ", RULES::verifyType, "Invalid Type (newspaper or magazine)");
		String frequency = InputHelper.readString(in, "Frequency (daily / weekly / monthly): ", RULES::verifyFrequency, "Invalid Frequency (daily, weekly or monthly)");

		double price = InputHelper.readDouble(in, "Price in euro (greater than 0): ", RULES::verifyPrice, "Invalid Price (must be greater than 0)");
		int quantityInStock = InputHelper.readInt(in, "Quantity in stock (0 or more): ", RULES::verifyQuantityInStock, "Invalid Quantity (must be 0 or more)");

		Publication p = new Publication();
		if (!p.verifyName(name)) System.out.println("Error: Invalid Name");
		if (!p.verifyType(type)) System.out.println("Error: Invalid Type");
		if (!p.verifyFrequency(frequency)) System.out.println("Error: Invalid Frequency");
		if (!p.verifyPrice(price)) System.out.println("Error: Invalid Price");
		if (!p.verifyQuantityInStock(quantityInStock)) System.out.println("Error: Invalid Quantity");

		if (!p.createPublication(name, type, frequency, price, quantityInStock)) {
			System.out.println("Publication not created.");
			return;
		}

		// Rule that needs the database: no two publications with the same name
		if (publicationDAO.findPublicationIdByName(name) > 0) {
			System.out.println("Error: A publication called \"" + name + "\" already exists.");
			System.out.println("Publication not created.");
			return;
		}

		int id = publicationDAO.insertPublication(p);
		if (id > 0)
			System.out.println("Publication saved successfully with ID " + id + ".");
		else
			System.out.println("Failed to save publication.");
	}

	private static void findPublication(Scanner in, PublicationDAO publicationDAO) {
		int id = InputHelper.readInt(in, "Enter Publication ID: ");

		Publication p = publicationDAO.findPublicationById(id);
		if (p == null) {
			System.out.println("Error: Publication " + id + " not found.");
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
			System.out.println("Error: Publication " + id + " not found.");
			return;
		}
		System.out.println("Current: " + current.getName() + " | " + current.getType() + " | "
				+ current.getFrequency() + " | " + current.getPrice() + " | " + current.getQuantityInStock());
		System.out.println("Enter the new details:");

		String name = InputHelper.readString(in, "Name (2-50 characters): ", RULES::verifyName, "Invalid Name (2-50 characters)");
		String type = InputHelper.readString(in, "Type (newspaper / magazine): ", RULES::verifyType, "Invalid Type (newspaper or magazine)");
		String frequency = InputHelper.readString(in, "Frequency (daily / weekly / monthly): ", RULES::verifyFrequency, "Invalid Frequency (daily, weekly or monthly)");

		double price = InputHelper.readDouble(in, "Price in euro (greater than 0): ", RULES::verifyPrice, "Invalid Price (must be greater than 0)");
		int quantityInStock = InputHelper.readInt(in, "Quantity in stock (0 or more): ", RULES::verifyQuantityInStock, "Invalid Quantity (must be 0 or more)");

		Publication p = new Publication();
		if (!p.verifyName(name)) System.out.println("Error: Invalid Name");
		if (!p.verifyType(type)) System.out.println("Error: Invalid Type");
		if (!p.verifyFrequency(frequency)) System.out.println("Error: Invalid Frequency");
		if (!p.verifyPrice(price)) System.out.println("Error: Invalid Price");
		if (!p.verifyQuantityInStock(quantityInStock)) System.out.println("Error: Invalid Quantity");

		if (!p.createPublication(name, type, frequency, price, quantityInStock)) {
			System.out.println("Publication not updated.");
			return;
		}

		// Rule that needs the database: the new name must not belong to another publication
		int sameName = publicationDAO.findPublicationIdByName(name);
		if (sameName > 0 && sameName != id) {
			System.out.println("Error: A publication called \"" + name + "\" already exists.");
			System.out.println("Publication not updated.");
			return;
		}

		p.setPublicationId(id);
		if (publicationDAO.updatePublication(p))
			System.out.println("Publication " + id + " updated successfully.");
		else
			System.out.println("Publication not updated.");
	}

	private static void deletePublication(Scanner in, PublicationDAO publicationDAO) {
		Integer id = readId(in);
		if (id == null) return;

		if (publicationDAO.findPublicationById(id) == null) {
			System.out.println("Error: Publication " + id + " not found.");
			return;
		}
		if (publicationDAO.deletePublication(id))
			System.out.println("Publication " + id + " deleted successfully.");
		else
			System.out.println("Publication not deleted.");
	}

	private static void listPublications(PublicationDAO publicationDAO) {
		ArrayList<Publication> publications = publicationDAO.getAllPublications();
		if (publications.isEmpty()) {
			System.out.println("No publications found.");
			return;
		}
		System.out.println("=== All Publications ===");
		for (Publication p : publications) {
			System.out.println("ID: " + p.getPublicationId()
					+ " | Name: " + p.getName()
					+ " | Type: " + p.getType()
					+ " | Frequency: " + p.getFrequency()
					+ " | Price: " + p.getPrice()
					+ " | Stock: " + p.getQuantityInStock());
		}
		System.out.println(publications.size() + " publication(s).");
	}

	private static Integer readId(Scanner in) {
		return InputHelper.readInt(in, "Enter Publication ID: ");
	}
}
