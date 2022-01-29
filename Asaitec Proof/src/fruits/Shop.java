package fruits;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Shop {
	public static void main(String[] args) {
		HashMap<String, Float> fruits = loadPrices("src/prices.txt");
		HashMap<String, Integer> purchases = loadPurchases("src/purchase.txt");
		HashMap<String, String> offers = loadOffers("src/offers.txt");

		float totalPrice = 0;
		StringBuilder appliedOffers = new StringBuilder();
		ArrayList<String> fruitsPurchased = new ArrayList<>();

		for (Map.Entry<String, Integer> entry : purchases.entrySet()) {
			fruitsPurchased.add(entry.getKey());
			if (entry.getKey() != null && offers.get(entry.getKey()) != null) {
				appliedOffers.append(" " + offers.get(entry.getKey()));
			}
			totalPrice += fruits.get(entry.getKey()) * entry.getValue();
		}
		System.out.println("Price of total purchase: " + totalPrice + "€");
		System.out.println("List of fruits purchased: " + fruitsPurchased);
		System.out.println("All offers that applie: " + appliedOffers.toString());
	}

	// Loads all the prices of the fruits.
	private static HashMap<String, Float> loadPrices(String route) {
		HashMap<String, Float> fruits = new HashMap<>();
		try {
			File myPrices = new File(route);
			Scanner myReader = new Scanner(myPrices);
			boolean firstLine = false;
			while (myReader.hasNextLine()) {
				String data = myReader.nextLine();
				if (firstLine) {
					if (data.split(",").length == 2) {
						String name = data.split(",")[0].trim();
						float price = Float.parseFloat(data.split(",")[1].trim());
						fruits.put(name, price);
					}
				} else {
					firstLine = true;
				}
			}
			myReader.close();
		} catch (FileNotFoundException e) {
			System.out.println("An error occurred while loading fruit prices.");
			e.printStackTrace();
		}
		return fruits;
	}

	// Loads all the purchases from a client.
	private static HashMap<String, Integer> loadPurchases(String route) {
		HashMap<String, Integer> purchases = new HashMap<>();
		try {
			File myPurchases = new File(route);
			Scanner myReader = new Scanner(myPurchases);
			boolean firstLine = false;
			while (myReader.hasNextLine()) {
				String data = myReader.nextLine();
				if (firstLine) {
					if (data.split(",").length == 2) {
						String fruit = data.split(",")[0].trim();
						Integer quantity = Integer.parseInt(data.split(",")[1].trim());
						if (quantity != 0) {
							purchases.put(fruit, quantity);
						}
					}
				} else {
					firstLine = true;
				}
			}
			myReader.close();
		} catch (FileNotFoundException e) {
			System.out.println("An error occurred while loading purchases.");
			e.printStackTrace();
		}
		return purchases;
	}

	// Loads all the offers from business logic.
	private static HashMap<String, String> loadOffers(String route) {
		HashMap<String, String> offers = new HashMap<>();
		try {
			File myPurchases = new File(route);
			Scanner myReader = new Scanner(myPurchases);
			boolean firstLine = false;
			while (myReader.hasNextLine()) {
				String data = myReader.nextLine();
				if (firstLine) {
					if (data.split(",").length == 2) {
						String fruit = data.split(",")[0].trim();
						String offer = data.split(",")[1].trim();
						offers.put(fruit, offer);
					}
				} else {
					firstLine = true;
				}
			}
			myReader.close();
		} catch (FileNotFoundException e) {
			System.out.println("An error occurred while loading offers.");
			e.printStackTrace();
		}
		return offers;
	}
}
