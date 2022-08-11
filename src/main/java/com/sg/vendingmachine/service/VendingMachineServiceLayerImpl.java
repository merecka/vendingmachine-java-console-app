package com.sg.vendingmachine.service;

import com.sg.vendingmachine.dao.ItemsDao;
import com.sg.vendingmachine.dao.UserBalanceDao;
import com.sg.vendingmachine.dao.VendingMachinePersistenceException;
import com.sg.vendingmachine.dto.Item;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

public class VendingMachineServiceLayerImpl implements VendingMachineServiceLayer {

    ItemsDao itemsDao;

    UserBalanceDao userBalanceDao;

    public VendingMachineServiceLayerImpl(ItemsDao itemsDao, UserBalanceDao userBalanceDao) {
        this.itemsDao = itemsDao;
        this.userBalanceDao = userBalanceDao;
    }

    // Returns a List of Items (name, quantity, price) with on-hand quantity > 0
    @Override
    public List<Item> getAllItems() throws VendingMachinePersistenceException {
        List<Item> allItems = itemsDao.getAllItems();
        List<Item> filteredItems = allItems.stream().filter((item) -> item.getQuantityOnHand() > 0).collect(Collectors.toList());
        return filteredItems;
    }

    @Override
    public boolean makePurchase(Item item) throws VendingMachinePersistenceException {
        if (userBalanceDao.getUserBalance().compareTo(item.getCost()) == -1) {
            return false;
        } else {
            String itemCost = "-" + item.getCost();
            userBalanceDao.updateUserBalance(itemCost);
            itemsDao.updateItemQuantity(item);
            return true;
        }
    }
}
