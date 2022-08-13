package com.sg.vendingmachine.service;

import com.sg.vendingmachine.dao.ItemsListAuditDao;
import com.sg.vendingmachine.dao.VendingMachinePersistenceException;
import com.sg.vendingmachine.dto.Item;

import java.math.BigDecimal;

public class ItemsListAuditDaoStubImpl implements ItemsListAuditDao {

    public void writeAuditEntry(String entry) throws VendingMachinePersistenceException {
    }
}
