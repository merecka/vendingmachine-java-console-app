package com.sg.vendingmachine.controller;

import com.sg.vendingmachine.dao.VendingMachinePersistenceException;
import com.sg.vendingmachine.dto.Item;
import com.sg.vendingmachine.dto.UserBalance;
import com.sg.vendingmachine.service.InsufficientFundsException;
import com.sg.vendingmachine.service.VendingMachineServiceLayer;
import com.sg.vendingmachine.ui.VendingMachineView;

import java.math.BigDecimal;
import java.util.List;

public class VendingMachineController {
    private VendingMachineView view;

    private VendingMachineServiceLayer vendingMachineService;

    private UserBalance userBalance;

    public VendingMachineController(VendingMachineServiceLayer vendingMachineService, VendingMachineView view, UserBalance userBalance) {
        this.view = view;
        this.vendingMachineService = vendingMachineService;
        this.userBalance = userBalance;
    }

    public void run() throws VendingMachinePersistenceException {
        boolean keepGoing = true;
        int menuSelection;

        while (keepGoing) {

            menuSelection = getMenuSelection();

            switch (menuSelection) {
                case 1:
                    insertFunds();
                    break;
                case 2:
                    makePurchase();
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

    private int getMenuSelection() throws VendingMachinePersistenceException {
        List<Item> itemList = vendingMachineService.getAllItems();
        BigDecimal userBalance = this.userBalance.getBalance();
        view.displayWelcomeBanner();
        view.displayItemList(itemList);
        view.displayAvailableBalance(userBalance);
        return view.printMenuAndGetSelection();
    }

    private void insertFunds() throws VendingMachinePersistenceException {
        double newFunds = view.displayInsertFundsMenu();
        String newFundsString = String.valueOf(newFunds);
        vendingMachineService.insertFunds(newFundsString);
    }

    private void makePurchase() throws VendingMachinePersistenceException {
        BigDecimal userBalance = this.userBalance.getBalance();
        if (userBalance.compareTo(new BigDecimal("0")) == 0) {
            view.displayNoFunds();
            return;
        }
        boolean hasErrors = false;
        do {
            List<Item> itemList = vendingMachineService.getAllItems();
            try {
                int itemNumberChosen = view.displayItemList(itemList, userBalance);
                String changeOwed;
                if (itemNumberChosen == 0) {  // user wants to exit Item selection screen
                    changeOwed = vendingMachineService.calculateChange(userBalance);
                    view.displayChange(changeOwed);
                } else {
                    changeOwed = vendingMachineService.makePurchase(itemNumberChosen);
                }
                view.displayChange(changeOwed);
                hasErrors = false;
            } catch (InsufficientFundsException e) {
                hasErrors = true;
                view.displayErrorMessage(e.getMessage());
            }
        } while (hasErrors);
    }

    private void unknownCommand() {
        view.displayUnknownCommandBanner();
    }

    private void exitMessage() {
        view.displayExitBanner();
    }
}
