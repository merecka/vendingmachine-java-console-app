package com.sg.vendingmachine.ui;

public class VendingMachineView {

    private UserIO io;

    public VendingMachineView(UserIO io) {
        this.io = io;
    }

    public int printMenuAndGetSelection() {
        io.print("Initial Screen");
        io.print("Hi, welcome to the Super Duper Vending Machine!");
        io.print("");
        io.print("Items available for purchase:");
        io.print("Your available balance is:  $_____.  Please insert funds to make a purchase.");
        io.print("");
        io.print("Options:");
        io.print("1.  Insert funds");
        io.print("2.  Make a purchase");
        io.print("3.  Exit");

        return io.readInt("Please select from the above choices.", 1, 5);
    }

    public void displayUnknownCommandBanner() {
        io.print("Unknown Command.  Please try again.");
    }

    public void displayExitBanner() {
        io.print("Thanks for shopping!");
    }
}
