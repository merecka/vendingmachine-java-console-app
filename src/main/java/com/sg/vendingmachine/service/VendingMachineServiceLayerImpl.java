package com.sg.vendingmachine.service;

import com.sg.vendingmachine.dao.ItemsDao;
import com.sg.vendingmachine.dao.VendingMachinePersistenceException;
import com.sg.vendingmachine.dto.Item;

import java.math.BigDecimal;
import java.util.List;

public class VendingMachineServiceLayerImpl implements VendingMachineServiceLayer {

    ItemsDao dao;

    public VendingMachineServiceLayerImpl(ItemsDao dao) {
        this.dao = dao;
    }

    // Returns a List of Items (name, quantity, price)
    @Override
    public List<Item> getAllItems() throws VendingMachinePersistenceException {
        return dao.getAllItems();
    }
}
