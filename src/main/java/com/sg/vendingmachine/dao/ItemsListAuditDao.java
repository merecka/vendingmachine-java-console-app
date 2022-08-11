package com.sg.vendingmachine.dao;

public interface ItemsListAuditDao {
    public void writeAuditEntry(String entry) throws VendingMachinePersistenceException;
}
