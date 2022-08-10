package com.sg.vendingmachine.service;

import com.sg.vendingmachine.dao.VendingMachinePersistenceException;
import com.sg.vendingmachine.dto.Item;

import java.util.List;

public interface VendingMachineServiceLayer {

    List<Item> getAllStudents() throws VendingMachinePersistenceException;
}
