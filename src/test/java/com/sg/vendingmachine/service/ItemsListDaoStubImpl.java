package com.sg.vendingmachine.service;

import com.sg.vendingmachine.dao.ItemsDao;
import com.sg.vendingmachine.dao.VendingMachinePersistenceException;
import com.sg.vendingmachine.dto.Item;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class ItemsListDaoStubImpl implements ItemsDao{

    public Item testItemWithQty;
    public Item testItemNoQty;

    public ItemsListDaoStubImpl() {
        this.testItemWithQty = new Item("0001", "Twinkies", 10, new BigDecimal("1.50"));
        this.testItemNoQty =  new Item("0002", "Chips", 0, new BigDecimal("0.75"));
    }

    public List<Item> getAllItems() {
        List<Item> itemList = new ArrayList<>();
        itemList.add(testItemWithQty);
        itemList.add(testItemNoQty);
        System.out.println("itemList.size() is " + itemList.size());
        return itemList;
    }

    public Item getItem(String itemId) {
        if (itemId.equals(testItemWithQty.getItemId())) {
            return testItemWithQty;
        } else if (itemId.equals(testItemNoQty.getItemId())) {
            return testItemNoQty;
        } else {
            return null;
        }
    }

    public void updateItemQuantity(Item item) {
        if (item.getItemId().equals(testItemWithQty.getItemId()) && item.getQuantityOnHand() > 0) {
            testItemWithQty.setQuantityOnHand(testItemWithQty.getQuantityOnHand() -1);
        }
    }
}
