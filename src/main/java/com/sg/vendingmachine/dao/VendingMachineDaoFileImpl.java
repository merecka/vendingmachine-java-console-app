package com.sg.vendingmachine.dao;

import com.sg.vendingmachine.controller.VendingMachineController;
import com.sg.vendingmachine.dto.Item;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;

public class VendingMachineDaoFileImpl implements VendingMachineDao {

    private Map<String, Item> items = new HashMap<>();

    private final String ITEMS_FILE;
    public static final String DELIMITER = "::";

    public VendingMachineDaoFileImpl() {
        this.ITEMS_FILE = "items.txt";
    }

    @Override
    public List<Item> getAllItems() throws VendingMachinePersistenceException {
        loadItems();
        return new ArrayList(items.values());
    }

    private Item unmarshallItem(String itemAsText){
        // Creates Items from String
        // Parses Item into String Array like the following:
        // id::name::count::price
        //
        // Example
        // ____________________________
        // |     |        |    |      |
        // | 0001|Twinkies| 10 | 1.50 |
        // |     |        |    |      |
        // ----------------------------
        //  [0]      [1]   [2]    [3]
        String[] itemTokens = itemAsText.split(DELIMITER);

        String itemId = itemTokens[0];

        // Create new Item from ID
        Item itemFromFile = new Item(itemId);

        // Set Name of new Item
        itemFromFile.setName(itemTokens[1]);

        // Set Quantity of new Item
        int qty = Integer.parseInt(itemTokens[2]);
        itemFromFile.setQuantityOnHand(qty);

        // Set Price of new Item
        itemFromFile.setCost(itemTokens[3]);

        // Return new Item
        return itemFromFile;
    }

    // loads Items from text file
    private void loadItems() throws VendingMachinePersistenceException {
        Scanner scanner;

        try {
            // Create Scanner for reading the file
            scanner = new Scanner(
                    new BufferedReader(
                            new FileReader(ITEMS_FILE)));
        } catch (FileNotFoundException e) {
            throw new VendingMachinePersistenceException(
                    "-_- Could not load Items data into memory.", e);
        }
        // currentItem holds the most recent line read from the file
        String currentLine;
        // currentItem holds the most recent student unmarshalled
        Item currentItem;
        // Go through ITEMS_FILE line by line, decoding each line into a
        // Item object by calling the unmarshallItem method.
        // Process while we have more lines in the file
        while (scanner.hasNextLine()) {
            // get the next line in the file
            currentLine = scanner.nextLine();
            // unmarshall the line into a Student
            currentItem = unmarshallItem(currentLine);

            // We are going to use the student id as the map key for our student object.
            // Put currentStudent into the map using student id as the key
            items.put(currentItem.getItemId(), currentItem);
        }
        // close scanner
        scanner.close();
    }
}
