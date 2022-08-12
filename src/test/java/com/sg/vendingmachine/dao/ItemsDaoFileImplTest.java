package com.sg.vendingmachine.dao;

import com.sg.vendingmachine.dto.Item;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ItemsDaoFileImplTest {

    ItemsDaoFileImpl testItemsDao;

    public static final String TEST_FILE = "testItems.txt";

    public static final String DELIMITER = "::";

    Item item1 = new Item("0001", "Twinkies", 10, new BigDecimal("1.50"));
    Item item2 = new Item("0002", "Chips", 10, new BigDecimal("0.75"));

    Item item3 = new Item("0003", "Cookies", 10, new BigDecimal("0.44"));

    Item item4 = new Item("0004", "Granola Bar", 10, new BigDecimal("0.80"));

    Item item5 = new Item("0005", "Candy", 10, new BigDecimal("1.23"));
    String[] testItems = {"0001::Twinkies::10::1.50", "0002::Chips::10::0.75", "0003::Cookies::10::0.44", "0004::Granola Bar::10::0.80", "0005::Candy::10::1.23"};

    @BeforeEach
    void setUp() throws IOException, VendingMachinePersistenceException {
        testItemsDao = new ItemsDaoFileImpl(TEST_FILE);
        writeItems(this.testItems);
    }

    @Test
    public void testGetAllItems() throws Exception {
        List<Item> allItems = testItemsDao.getAllItems();
        assertNotNull(allItems, "The list of items must not null");
        assertEquals(5, allItems.size(), "List of items should have 5 items.");

        assertTrue(testItemsDao.getAllItems().contains(item1),
                "The list of items should include Twinkies.");
        assertTrue(testItemsDao.getAllItems().contains(item2),
                "The list of items should include Chips.");
        assertTrue(testItemsDao.getAllItems().contains(item3),
                "The list of items should include Cookies.");
        assertTrue(testItemsDao.getAllItems().contains(item4),
                "The list of items should include Granola Bar.");
        assertTrue(testItemsDao.getAllItems().contains(item5),
                "The list of items should include Candy.");
    }

    @Test
    public void testGetItem() throws Exception {
        Item retrievedItem1 = testItemsDao.getItem(item1.getItemId());
        Item retrievedItem2 = testItemsDao.getItem(item2.getItemId());

        assertEquals(retrievedItem1.getItemId(), item1.getItemId(), "Checking Item ID.");
        assertEquals(retrievedItem1.getName(), item1.getName(), "Checking Item name.");
        assertEquals(retrievedItem1.getQuantityOnHand(), item1.getQuantityOnHand(), "Checking Item name.");
        assertEquals(retrievedItem1.getCost(), item1.getCost(), "Checking Item name.");

        assertEquals(retrievedItem2.getItemId(), item2.getItemId(), "Checking Item ID.");
        assertEquals(retrievedItem2.getName(), item2.getName(), "Checking Item name.");
        assertEquals(retrievedItem2.getQuantityOnHand(), item2.getQuantityOnHand(), "Checking Item name.");
        assertEquals(retrievedItem2.getCost(), item2.getCost(), "Checking Item name.");
    }

    @Test
    public void testUpdateItemQuantity() throws Exception {
        Item retrievedItem1 = testItemsDao.getItem(item1.getItemId());
        testItemsDao.updateItemQuantity(retrievedItem1);
        System.out.println(retrievedItem1.toString());
        Item retrievedItem2 = testItemsDao.getItem(item2.getItemId());
        testItemsDao.updateItemQuantity(retrievedItem2);
        System.out.println(retrievedItem2.toString());

        assertEquals(9, retrievedItem1.getQuantityOnHand());
        assertEquals( 9, retrievedItem2.getQuantityOnHand());
    }

    void writeItems(String[] testItems) throws IOException {

        PrintWriter out;

        try {
            out = new PrintWriter(new FileWriter(TEST_FILE));
        } catch (IOException e) {
            throw new IOException("Test file not written", e);
        }

        String itemAsText;
        for (String currentItem : testItems) {
            // write the Item to the file
            out.println(currentItem);
            // force PrintWriter to write line to the file
            out.flush();
        }
        // Clean up
        out.close();
    }
}