package com.sg.vendingmachine.ui;

import com.sg.vendingmachine.dto.Item;

import java.math.BigDecimal;
import java.util.List;

public class VendingMachineView {

    private UserIO io;

    public VendingMachineView(UserIO io) {
        this.io = io;
    }

    public void displayWelcomeBanner() {
        io.print("Hi, welcome to the Super Duper Vending Machine!!");
        io.print("");
    }

    public void displayAvailableBalance(BigDecimal userBalance) {
        BigDecimal zeroBigDecimal = new BigDecimal("0");

        if (userBalance.compareTo(zeroBigDecimal) == 0) {
            io.print("");
            io.print("Your available balance is:  $" + userBalance + ".  Please insert funds to make a purchase.");
        } else {
            io.print("");
            io.print("Your available balance is:  $" + userBalance);
        }
    }

    public int printMenuAndGetSelection(BigDecimal userBalance) {
        io.print("");
        io.print("Options:");
        io.print("1.  Insert funds");
        io.print("2.  Make a purchase");
        io.print("3.  Exit");

        return io.readInt("Please select from the above three Options (1-3).", 1, 5);
    }

    public void displayItemList(List<Item> itemList) {
        io.print("Items Available for Purchase");
        int count = 1;
        for (Item currentItem : itemList) {
            String itemInfo = String.format("%s. %s - $%s/ea - Quantity: %s",
                    count++,
                    currentItem.getName(),
                    currentItem.getCost(),
                    currentItem.getQuantityOnHand());
            io.print(itemInfo);
        }
    }

    public int readUserPurchaseChoice() {
        return io.readInt("Please choose one of the available items by its corresponding number.");
    }

    public String displayInsertFundsMenu() {
        String input = io.readString("How much would you like to deposit (ex: $2.50)?  $");
        // TODO:  Need to verify user enters a valid formatted value (with a .) and a value greater than 0.
        return input.trim();
    }

    public void displayChange(String changeOwed) {
        io.readString("Your change is: " + changeOwed);
    }
    public void displayUnknownCommandBanner() {
        io.print("Unknown Command.  Please try again.");
    }

    public void displayNotEnoughFunds() {
        io.readString("Not enough funds available for this purchase.  Please deposit more funds.");
    }

    public void displayNoFunds() {
        io.readString("You must first deposit funds before making a purchase.");
    }

    public void displayExitBanner() {
        io.print("Thanks for shopping!");
    }
}
