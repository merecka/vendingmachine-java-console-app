package com.sg.vendingmachine.controller;

import com.sg.vendingmachine.dao.VendingMachinePersistenceException;
import com.sg.vendingmachine.dto.Item;
import com.sg.vendingmachine.service.UserBalanceServiceLayer;
import com.sg.vendingmachine.service.VendingMachineServiceLayer;
import com.sg.vendingmachine.ui.VendingMachineView;

import java.util.List;

public class VendingMachineController {
    private VendingMachineView view;

    private VendingMachineServiceLayer vendingMachineService;

    private UserBalanceServiceLayer userBalanceService;

    public VendingMachineController(VendingMachineServiceLayer vendingMachineService, UserBalanceServiceLayer userBalanceService,VendingMachineView view) {
        this.view = view;
        this.vendingMachineService = vendingMachineService;
        this.userBalanceService = userBalanceService;
    }

    public void run() throws VendingMachinePersistenceException {
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
    private int getMenuSelection() throws VendingMachinePersistenceException {
        List<Item> itemList = vendingMachineService.getAllItems();
        String userBalance = userBalanceService.getUserBalance().toString();
        view.displayWelcomeBanner();
        view.displayItemList(itemList);
        return view.printMenuAndGetSelection(userBalance);
    }

    private void unknownCommand() {
        view.displayUnknownCommandBanner();
    }

    private void exitMessage() {
        view.displayExitBanner();
    }
}
