package com.sg.vendingmachine.dao;

public interface ItemsListAuditDao {
    void writeAuditEntry(String entry) throws VendingMachinePersistenceException;
}
