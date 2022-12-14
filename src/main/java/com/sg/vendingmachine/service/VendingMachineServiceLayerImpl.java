package com.sg.vendingmachine.service;

import com.sg.vendingmachine.dao.ItemsDao;
import com.sg.vendingmachine.dao.ItemsListAuditDao;
import com.sg.vendingmachine.dao.VendingMachinePersistenceException;
import com.sg.vendingmachine.dto.Change;
import com.sg.vendingmachine.dto.Item;
import com.sg.vendingmachine.dto.UserBalance;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.stream.Collectors;

public class VendingMachineServiceLayerImpl implements VendingMachineServiceLayer {

    ItemsDao itemsDao;

    UserBalance userBalance;

    ItemsListAuditDao itemsListAuditDao;

    public enum Coin {
        QUARTERS(new BigDecimal("25")),
        DIMES(new BigDecimal("10")),
        NICKELS(new BigDecimal("5")),
        PENNIES(new BigDecimal("1"));
        private final BigDecimal value;

        private Coin(BigDecimal value) {
            this.value = value;
        }

        public BigDecimal getCoin() {
            return this.value;
        }
    }

    public VendingMachineServiceLayerImpl(ItemsDao itemsDao, UserBalance userBalance, ItemsListAuditDao itemsAuditDao) {
        this.itemsDao = itemsDao;
        this.userBalance = userBalance;
        this.itemsListAuditDao = itemsAuditDao;
    }

    // Returns a List of Items (name, quantity, price) with on-hand quantity > 0
    @Override
    public List<Item> getAllItems() throws VendingMachinePersistenceException {
        List<Item> allItems = itemsDao.getAllItems();
        List<Item> allItemsFiltered = allItems.stream().filter((item) -> item.getQuantityOnHand() > 0).collect(Collectors.toList());
        return allItemsFiltered;
    }

    @Override
    public String makePurchase(int itemNumberChosen) throws VendingMachinePersistenceException, InsufficientFundsException {
        List<Item> itemList = getAllItems();  // returns a List of Items that have an onHandQuantity > 0
        Item purchasedItem = itemList.get(--itemNumberChosen);  // convert the menu number to the itemList index number
        if (userBalance.getBalance().compareTo(purchasedItem.getCost()) == -1) {  // evaluates if Item cost is more than current User balance
            throw new InsufficientFundsException("Not enough funds to purchase item.  Please make another selection or type 0 to quit.");
        } else {  // user able to make purchase
            itemsDao.updateItemQuantity(purchasedItem);
            itemsListAuditDao.writeAuditEntry("Item " + purchasedItem.getItemId() + " " + purchasedItem.getName() + " $" + purchasedItem.getCost() + " purchased.");
            return calculateChange(userBalance.getBalance(), purchasedItem);
        }
    }

    // Use this method to return change when no purchase is made
    @Override
    public String calculateChange(BigDecimal userBalance) throws VendingMachinePersistenceException {
        BigDecimal changeOwedInPennies = userBalance.multiply(new BigDecimal("100"));
        Change userChange = new Change();

        //BigDecimal remainderOwedInPennies;
        BigDecimal quartersOwed = changeOwedInPennies.divide(Coin.QUARTERS.value, RoundingMode.UNNECESSARY).setScale(0, RoundingMode.DOWN);
        userChange.setQuartersOwed(quartersOwed);
        changeOwedInPennies = changeOwedInPennies.remainder(Coin.QUARTERS.value);

        BigDecimal dimesOwed = changeOwedInPennies.divide(Coin.DIMES.value, RoundingMode.UNNECESSARY).setScale(0, RoundingMode.DOWN);
        userChange.setDimesOwed(dimesOwed);
        changeOwedInPennies = changeOwedInPennies.remainder(Coin.DIMES.value);

        BigDecimal nickelsOwed = changeOwedInPennies.divide(Coin.NICKELS.value, RoundingMode.UNNECESSARY).setScale(0, RoundingMode.DOWN);
        userChange.setNicklesOwed(nickelsOwed);
        changeOwedInPennies = changeOwedInPennies.remainder(Coin.NICKELS.value);

        BigDecimal penniesOwed = changeOwedInPennies.divide(Coin.PENNIES.value, RoundingMode.UNNECESSARY).setScale(0, RoundingMode.DOWN);
        userChange.setPenniesOwed(penniesOwed);

        String changeOwedString = "$" + userBalance + " which is made up of: " + userChange.getQuartersOwed() + " X Quarters, " +
                userChange.getDimesOwed() + " X Dimes, " +
                userChange.getNicklesOwed() + " X Nickels, " +
                userChange.getPenniesOwed() + " X Pennies";

        itemsListAuditDao.writeAuditEntry("$" +  userBalance + " change given.");
        this.userBalance.setBalance(new BigDecimal("0.00").setScale(2, RoundingMode.UNNECESSARY));
        return changeOwedString;
    }

    // Use this method to return change when a purchase is made
    @Override
    public String calculateChange(BigDecimal userBalance, Item purchasedItem) throws VendingMachinePersistenceException {
        BigDecimal originalOwed = userBalance.subtract(purchasedItem.getCost());
        BigDecimal changeOwedInPennies = userBalance.subtract(purchasedItem.getCost()).multiply(new BigDecimal("100"));
        Change userChange = new Change();

        //BigDecimal remainderOwedInPennies;
        BigDecimal quartersOwed = changeOwedInPennies.divide(Coin.QUARTERS.value, RoundingMode.UNNECESSARY).setScale(0, RoundingMode.DOWN);
        userChange.setQuartersOwed(quartersOwed);
        changeOwedInPennies = changeOwedInPennies.remainder(Coin.QUARTERS.value);

        BigDecimal dimesOwed = changeOwedInPennies.divide(Coin.DIMES.value, RoundingMode.UNNECESSARY).setScale(0, RoundingMode.DOWN);
        userChange.setDimesOwed(dimesOwed);
        changeOwedInPennies = changeOwedInPennies.remainder(Coin.DIMES.value);

        BigDecimal nickelsOwed = changeOwedInPennies.divide(Coin.NICKELS.value, RoundingMode.UNNECESSARY).setScale(0, RoundingMode.DOWN);
        userChange.setNicklesOwed(nickelsOwed);
        changeOwedInPennies = changeOwedInPennies.remainder(Coin.NICKELS.value);

        BigDecimal penniesOwed = changeOwedInPennies.divide(Coin.PENNIES.value, RoundingMode.UNNECESSARY).setScale(0, RoundingMode.DOWN);
        userChange.setPenniesOwed(penniesOwed);

        String changeOwedString = "$" + originalOwed + " which is made up of: " + userChange.getQuartersOwed() + " X Quarters, " +
                userChange.getDimesOwed() + " X Dimes, " +
                userChange.getNicklesOwed() + " X Nickels, " +
                userChange.getPenniesOwed() + " X Pennies";

        itemsListAuditDao.writeAuditEntry("$" +  originalOwed + " change given.");
        this.userBalance.setBalance(new BigDecimal("0.00").setScale(2, RoundingMode.UNNECESSARY));
        return changeOwedString;
    }

    public void insertFunds(String newFunds) throws VendingMachinePersistenceException {
        BigDecimal newFundsBD = new BigDecimal((newFunds)).setScale(2, RoundingMode.UNNECESSARY);
        userBalance.setBalance(userBalance.getBalance().add(newFundsBD).setScale(2, RoundingMode.HALF_UP));
        itemsListAuditDao.writeAuditEntry("New funds in the amount of $" + newFundsBD + " inserted.");
    }
}
