package com.sg.vendingmachine.dao;

import com.sg.vendingmachine.dto.Item;

import java.io.*;
import java.util.*;

public class ItemsDaoFileImpl implements ItemsDao {

    private Map<String, Item> items = new HashMap<>();

    private final String ITEMS_FILE;
    public static final String DELIMITER = "::";

    public ItemsDaoFileImpl() {
        this.ITEMS_FILE = "items.txt";
    }

    public ItemsDaoFileImpl(String itemsTextFile) {
        this.ITEMS_FILE = itemsTextFile;
    }


    @Override
    public List<Item> getAllItems() throws VendingMachinePersistenceException {
        loadItems();
        return new ArrayList(items.values());
    }

    public Item getItem(String itemId) throws VendingMachinePersistenceException {
        loadItems();
        return items.get(itemId);
    }

    public void updateItemQuantity(Item itemToUpdate) throws VendingMachinePersistenceException {
        itemToUpdate.setQuantityOnHand(itemToUpdate.getQuantityOnHand() - 1);
        writeItems();
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

    private String marshallItem(Item item){
        // We need to turn an Item object into a line of text for our file.
        // For example, we need an in memory object to end up like this:
        // 0001::Chips::10::1.20

        // Start with the student id, since that's supposed to be first.
        String itemAsText = item.getItemId() + DELIMITER;

        // name
        itemAsText += item.getName() + DELIMITER;

        // LastName
        itemAsText += item.getQuantityOnHand() + DELIMITER;

        // Cohort - don't forget to skip the DELIMITER here.
        itemAsText += item.getCost();

        // We have now turned a student to text! Return it!
        return itemAsText;
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

    private void writeItems() throws VendingMachinePersistenceException {

        PrintWriter out;

        try {
            out = new PrintWriter(new FileWriter(ITEMS_FILE));
        } catch (IOException e) {
            throw new VendingMachinePersistenceException(
                    "Could not save item data.", e);
        }

        String itemAsText;
        List<Item> itemList = this.getAllItems();
        for (Item currentItem : itemList) {
            // turn an Item into a String
            itemAsText = marshallItem(currentItem);
            // write the Student object to the file
            out.println(itemAsText);
            // force PrintWriter to write line to the file
            out.flush();
        }
        // Clean up
        out.close();
    }
}
