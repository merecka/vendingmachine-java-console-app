package com.sg.vendingmachine.service;

import com.sg.vendingmachine.dao.ItemsDao;
import com.sg.vendingmachine.dao.VendingMachinePersistenceException;
import com.sg.vendingmachine.dto.Item;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class ItemsListDaoStubImpl implements ItemsDao{

    public Item testItemWithQty1;
    public Item testItemWithQty2;
    public Item testItemNoQty;

    public ItemsListDaoStubImpl() {
        this.testItemWithQty1 = new Item("0001", "Twinkies", 10, new BigDecimal("1.50"));
        this.testItemNoQty =  new Item("0002", "Chips", 0, new BigDecimal("0.75"));
        this.testItemWithQty2 =  new Item("0003", "Candy", 10, new BigDecimal("2.45"));

    }

    public List<Item> getAllItems() {
        List<Item> itemList = new ArrayList<>();
        itemList.add(testItemWithQty1);
        itemList.add(testItemNoQty);
        itemList.add(testItemWithQty2);
        System.out.println("itemList.size() is " + itemList.size());
        return itemList;
    }

    public Item getItem(String itemId) {
        if (itemId.equals(testItemWithQty1.getItemId())) {
            return testItemWithQty1;
        } else if (itemId.equals(testItemNoQty.getItemId())) {
            return testItemNoQty;
        } else if (itemId.equals(testItemWithQty2.getItemId())) {
            return testItemWithQty2;
        } else {
            return null;
        }
    }

    public void updateItemQuantity(Item item) {
        if (item.getItemId().equals(testItemWithQty1.getItemId()) && item.getQuantityOnHand() > 0) {
            testItemWithQty1.setQuantityOnHand(testItemWithQty1.getQuantityOnHand() -1);
        }
    }
}
