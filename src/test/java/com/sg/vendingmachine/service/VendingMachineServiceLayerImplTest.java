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

    private ItemsDao itemsDao;

    public VendingMachineServiceLayerImplTest() {
        itemsDao = new ItemsListDaoStubImpl();
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
        assertEquals(2, allListItems.size());
    }

    @Test
    void testMakePurchase() throws VendingMachinePersistenceException, InsufficientFundsException {
        // Test that valid purchase is working
        service.insertFunds("2.00");
        Item twinkies = itemsDao.getItem("0001");
        System.out.println("twinkies is " + twinkies.toString());
        String changeOwed = service.makePurchase(1);

        assertEquals(9, twinkies.getQuantityOnHand());
        assertEquals(new BigDecimal("0.00"), userBalance.getBalance());
        assertEquals("$0.50 which is made up of: 2 X Quarters, 0 X Dimes, 0 X Nickels, 0 X Pennies", changeOwed);

        // Test that invalid purchase is working (not enough funds to make purchase)
        service.insertFunds("2.00");
        Item candy = itemsDao.getItem("0003");
        System.out.println("0003 is " + candy.toString());
        try {
            service.makePurchase(2);
        } catch (InsufficientFundsException e) {
            service.calculateChange(userBalance.getBalance());
            assertEquals(10, candy.getQuantityOnHand());
            assertEquals(new BigDecimal("0.00"), userBalance.getBalance());
        }
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