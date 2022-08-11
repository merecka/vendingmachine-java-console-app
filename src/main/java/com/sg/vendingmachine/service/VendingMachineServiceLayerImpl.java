package com.sg.vendingmachine.service;

import com.sg.vendingmachine.dao.ItemsDao;
import com.sg.vendingmachine.dao.VendingMachinePersistenceException;
import com.sg.vendingmachine.dto.Change;
import com.sg.vendingmachine.dto.Item;
import com.sg.vendingmachine.dto.UserBalance;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class VendingMachineServiceLayerImpl implements VendingMachineServiceLayer {

    ItemsDao itemsDao;

    UserBalance userBalance;

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

    public VendingMachineServiceLayerImpl(ItemsDao itemsDao, UserBalance userBalance) {
        this.itemsDao = itemsDao;
        this.userBalance = userBalance;
    }

    // Returns a List of Items (name, quantity, price) with on-hand quantity > 0
    @Override
    public List<Item> getAllItems() throws VendingMachinePersistenceException {
        List<Item> allItems = itemsDao.getAllItems();
        return allItems.stream().filter((item) -> item.getQuantityOnHand() > 0).collect(Collectors.toList());
    }

    @Override
    public boolean makePurchase(Item item) throws VendingMachinePersistenceException {
        if (userBalance.getBalance().compareTo(item.getCost()) == -1) {  // evaluates if Item cost is more than current User balance
            return false;
        } else {
            String itemCost = "-" + item.getCost();
            userBalance.setBalance(itemCost);

            itemsDao.updateItemQuantity(item);
            return true;
        }
    }

    // Use this method to return change when no purchase is made
    @Override
    public String calculateChange(BigDecimal userBalance) {
        BigDecimal changeOwedInPennies = userBalance.multiply(new BigDecimal("100"));
        Change userChange = new Change(changeOwedInPennies);

        //BigDecimal remainderOwedInPennies;
        BigDecimal quartersOwed = changeOwedInPennies.divide(Coin.QUARTERS.value);
        BigDecimal quartersRounded = quartersOwed.setScale(0, RoundingMode.DOWN);
        userChange.setQuartersOwed(quartersRounded);
        changeOwedInPennies = changeOwedInPennies.remainder(Coin.QUARTERS.value);

        BigDecimal dimesOwed = changeOwedInPennies.divide(Coin.DIMES.value);
        BigDecimal dimesRounded = dimesOwed.setScale(0, RoundingMode.DOWN);
        userChange.setDimesOwed(dimesRounded);
        changeOwedInPennies = changeOwedInPennies.remainder(Coin.DIMES.value);

        BigDecimal nickelsOwed = changeOwedInPennies.divide(Coin.NICKELS.value);
        BigDecimal nickelsRounded = nickelsOwed.setScale(0, RoundingMode.DOWN);
        userChange.setNicklesOwed(nickelsRounded);
        changeOwedInPennies = changeOwedInPennies.remainder(Coin.NICKELS.value);

        BigDecimal penniesOwed = changeOwedInPennies.divide(Coin.PENNIES.value);
        BigDecimal penniesRounded = penniesOwed.setScale(0, RoundingMode.DOWN);
        userChange.setPenniesOwed(penniesRounded);

        String changeOwedString = "$" + userBalance + " which is made up of: " + userChange.getQuartersOwed() + " X Quarters, " +
                userChange.getDimesOwed() + " X Dimes, " +
                userChange.getNicklesOwed() + " X Nickels, " +
                userChange.getPenniesOwed() + " X Pennies";

        return changeOwedString;
    }

    // Use this method to return change when a purchase is made
    @Override
    public String calculateChange(BigDecimal userBalance, Item purchasedItem) {
        BigDecimal originalOwed = userBalance.subtract(purchasedItem.getCost());
        BigDecimal changeOwedInPennies = userBalance.subtract(purchasedItem.getCost()).multiply(new BigDecimal("100"));
        Change userChange = new Change(changeOwedInPennies);

        //BigDecimal remainderOwedInPennies;
        BigDecimal quartersOwed = changeOwedInPennies.divide(Coin.QUARTERS.value);
        BigDecimal quartersRounded = quartersOwed.setScale(0, RoundingMode.DOWN);
        userChange.setQuartersOwed(quartersRounded);
        changeOwedInPennies = changeOwedInPennies.remainder(Coin.QUARTERS.value);

        BigDecimal dimesOwed = changeOwedInPennies.divide(Coin.DIMES.value);
        BigDecimal dimesRounded = dimesOwed.setScale(0, RoundingMode.DOWN);
        userChange.setDimesOwed(dimesRounded);
        changeOwedInPennies = changeOwedInPennies.remainder(Coin.DIMES.value);

        BigDecimal nickelsOwed = changeOwedInPennies.divide(Coin.NICKELS.value);
        BigDecimal nickelsRounded = nickelsOwed.setScale(0, RoundingMode.DOWN);
        userChange.setNicklesOwed(nickelsRounded);
        changeOwedInPennies = changeOwedInPennies.remainder(Coin.NICKELS.value);

        BigDecimal penniesOwed = changeOwedInPennies.divide(Coin.PENNIES.value);
        BigDecimal penniesRounded = penniesOwed.setScale(0, RoundingMode.DOWN);
        userChange.setPenniesOwed(penniesRounded);

        String changeOwedString = "$" + originalOwed + " which is made up of: " + userChange.getQuartersOwed() + " X Quarters, " +
                userChange.getDimesOwed() + " X Dimes, " +
                userChange.getNicklesOwed() + " X Nickels, " +
                userChange.getPenniesOwed() + " X Pennies";

        return changeOwedString;
    }
}
