package com.sg.vendingmachine.dao;

import com.sg.vendingmachine.dto.Item;

import java.util.List;

public interface VendingMachineDao {

    // returns a List of all the Items for purchase.
    List<Item> getAllItems() throws VendingMachinePersistenceException;
}
