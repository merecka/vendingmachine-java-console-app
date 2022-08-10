package com.sg.vendingmachine.controller;

import com.sg.vendingmachine.ui.VendingMachineView;

public class VendingMachineController {
    private VendingMachineView view;

    public VendingMachineController(VendingMachineView view) {
        this.view = view;
    }

    public void run() {
        boolean keepGoing = true;
        int menuSelection = 0;

        while (keepGoing) {

            menuSelection = getMenuSelection();

            switch (menuSelection) {
                case 1:
                    //insertFunds();
                    break;
                case 2:
                    //makePurchase();
                    break;
                case 3:
                    keepGoing = false;
                    break;
                default:
                    unknownCommand();
            }
        }
        exitMessage();
    }
    private int getMenuSelection() {
        return view.printMenuAndGetSelection();
    }

    private void unknownCommand() {
        view.displayUnknownCommandBanner();
    }

    private void exitMessage() {
        view.displayExitBanner();
    }
}
