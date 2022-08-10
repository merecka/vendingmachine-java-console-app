package com.sg.vendingmachine;

import com.sg.vendingmachine.controller.VendingMachineController;
import com.sg.vendingmachine.dao.*;
import com.sg.vendingmachine.dto.UserBalance;
import com.sg.vendingmachine.service.UserBalanceServiceLayer;
import com.sg.vendingmachine.service.UserBalanceServiceLayerImpl;
import com.sg.vendingmachine.service.VendingMachineServiceLayer;
import com.sg.vendingmachine.service.VendingMachineServiceLayerImpl;
import com.sg.vendingmachine.ui.UserIO;
import com.sg.vendingmachine.ui.UserIOConsoleImpl;
import com.sg.vendingmachine.ui.VendingMachineView;

public class App {
    public static void main(String[] args) throws VendingMachinePersistenceException {
        // Initiates a new UserBalance of $0
        UserBalance userBalance = new UserBalance();
        // Passes new UserBalance to UserBalanceDao
        UserBalanceDao userBalanceDao = new UserBalanceDaoFileImpl(userBalance);
        // Instantiate the UserBalanceService Layer and wire the DAO into it
        UserBalanceServiceLayer myUserBalanceService = new UserBalanceServiceLayerImpl(userBalanceDao);
        // Instantiate the UserIO implementation
        UserIO myIo = new UserIOConsoleImpl();
        // Instantiate the View and wire the UserIO implementation into it
        VendingMachineView myView = new VendingMachineView(myIo);
        // Instantiate the ItemsDAO
        ItemsDao itemsDao = new ItemsDaoFileImpl();
        // Instantiate the VendingService Layer and wire the DAO and Audit DAO into it
        VendingMachineServiceLayer myVendingService = new VendingMachineServiceLayerImpl(itemsDao);
        // Instantiate the Controller and wire the Service Layer into it
        VendingMachineController controller = new VendingMachineController(myVendingService, myUserBalanceService, myView);
        // Kick off the Controller
        controller.run();
    }
}
