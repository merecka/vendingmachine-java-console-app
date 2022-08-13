package com.sg.vendingmachine.service;

import com.sg.vendingmachine.dao.ItemsDao;
import com.sg.vendingmachine.dao.ItemsDaoFileImpl;
import com.sg.vendingmachine.dao.ItemsListAuditDao;
import com.sg.vendingmachine.dao.VendingMachinePersistenceException;
import com.sg.vendingmachine.dto.Item;
import com.sg.vendingmachine.dto.UserBalance;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class VendingMachineServiceLayerImplTest {

    private VendingMachineServiceLayer service;
    private UserBalance userBalance;

    public VendingMachineServiceLayerImplTest() {
        ItemsDao itemsDao = new ItemsListDaoStubImpl();
        ItemsListAuditDao itemsAuditDao = new ItemsListAuditDaoStubImpl();
        userBalance = new UserBalance();

        service = new VendingMachineServiceLayerImpl(itemsDao, userBalance, itemsAuditDao) {
        };
    }

    @BeforeEach
    void setUp() {
    }

    @Test
    public void testGetAllItems() throws VendingMachinePersistenceException {
        List<Item> allListItems = service.getAllItems();
        assertEquals(1, allListItems.size());
    }

    @Test
    void makePurchase() {
    }

    @Test
    void calculateChange() {
    }

    @Test
    void testCalculateChange() {
    }

    @Test
    void insertFunds() throws VendingMachinePersistenceException {
        String newFunds = "1.50";
        service.insertFunds(newFunds);
        assertEquals(new BigDecimal("1.50"), this.userBalance.getBalance());
   }
}