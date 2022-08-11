package com.sg.vendingmachine.service;

import com.sg.vendingmachine.dao.ItemsDao;
import com.sg.vendingmachine.dao.VendingMachinePersistenceException;
import com.sg.vendingmachine.dto.Item;
import com.sg.vendingmachine.dto.UserBalance;

import java.util.List;
import java.util.stream.Collectors;

public class VendingMachineServiceLayerImpl implements VendingMachineServiceLayer {

    ItemsDao itemsDao;

    UserBalance userBalance;

    public VendingMachineServiceLayerImpl(ItemsDao itemsDao, UserBalance userBalance) {
        this.itemsDao = itemsDao;
        this.userBalance = userBalance;
    }

    // Returns a List of Items (name, quantity, price) with on-hand quantity > 0
    @Override
    public List<Item> getAllItems() throws VendingMachinePersistenceException {
        List<Item> allItems = itemsDao.getAllItems();
        return allItems.stream().filter((item) -> item.getQuantityOnHand() > 0).collect(Collectors.toList());
    }

    @Override
    public boolean makePurchase(Item item) throws VendingMachinePersistenceException {
        if (userBalance.getBalance().compareTo(item.getCost()) == -1) {  // evaluates if Item cost is more than current User balance
            return false;
        } else {
            String itemCost = "-" + item.getCost();
            userBalance.setBalance(itemCost);

            itemsDao.updateItemQuantity(item);
            return true;
        }
    }
}
