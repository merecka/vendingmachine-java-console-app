package com.sg.vendingmachine.service;

import com.sg.vendingmachine.dao.VendingMachinePersistenceException;
import com.sg.vendingmachine.dto.Item;

import java.math.BigDecimal;
import java.util.List;

public interface VendingMachineServiceLayer {

    List<Item> getAllItems() throws VendingMachinePersistenceException;

    boolean makePurchase(Item item) throws VendingMachinePersistenceException, InsufficientFundsException;

    String calculateChange(BigDecimal userBalance) throws VendingMachinePersistenceException;
    String calculateChange(BigDecimal userBalance, Item purchasedItem) throws VendingMachinePersistenceException;

    void insertFunds(String insertedFunds) throws VendingMachinePersistenceException;
}
