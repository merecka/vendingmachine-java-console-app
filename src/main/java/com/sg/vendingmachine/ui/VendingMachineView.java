package com.sg.vendingmachine.ui;

import com.sg.vendingmachine.dto.Item;

import java.util.List;

public class VendingMachineView {

    private UserIO io;

    public VendingMachineView(UserIO io) {
        this.io = io;
    }

    public int printMenuAndGetSelection(String userBalance) {
        if (userBalance.equals("0")) {
            io.print("");
            io.print("Your available balance is:  $" + userBalance + ".  Please insert funds to make a purchase.");
        } else {
            io.print("");
            io.print("Your available balance is:  $" + userBalance);
        }
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
            String itemInfo = String.format("%s. %s - $%s/ea",
                    count++,
                    currentItem.getName(),
                    currentItem.getCost());
            io.print(itemInfo);
        }
    }

    public void displayWelcomeBanner() {
        io.print("Hi, welcome to the Super Duper Vending Machine!!");
        io.print("");
    }
    public void displayUnknownCommandBanner() {
        io.print("Unknown Command.  Please try again.");
    }

    public void displayExitBanner() {
        io.print("Thanks for shopping!");
    }
}
