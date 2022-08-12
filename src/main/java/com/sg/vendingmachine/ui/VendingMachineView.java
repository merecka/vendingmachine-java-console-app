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

        return io.readInt("Please select from the above three Options (1-3).", 1, 3);
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

    public double displayInsertFundsMenu() {
        double input;
        boolean keepGoing = false;
        do {
            input = io.readDouble("How much would you like to deposit (ex: $2.50)?");
            System.out.println("input is " + input);
            if (input <= 0) {
                io.print("Please deposit an amount greater than $0.");
                keepGoing = true;
            } else {
                keepGoing = false;
            }
        } while (keepGoing);
        return input;
    }

    public void displayChange(String changeOwed) {
        io.print("Your change is: " + changeOwed);
        io.readString("Press enter to continue.");
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
