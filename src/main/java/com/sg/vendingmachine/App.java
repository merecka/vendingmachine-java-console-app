package com.sg.vendingmachine;

import com.sg.vendingmachine.controller.VendingMachineController;
import com.sg.vendingmachine.dao.*;
import com.sg.vendingmachine.dto.Change;
import com.sg.vendingmachine.dto.UserBalance;
import com.sg.vendingmachine.service.VendingMachineServiceLayer;
import com.sg.vendingmachine.service.VendingMachineServiceLayerImpl;
import com.sg.vendingmachine.ui.UserIO;
import com.sg.vendingmachine.ui.UserIOConsoleImpl;
import com.sg.vendingmachine.ui.VendingMachineView;

public class App {
    public static void main(String[] args) throws VendingMachinePersistenceException {
        // Initiates a new UserBalance of $0
        UserBalance userBalance = new UserBalance();
        // Instantiate the UserIO implementation
        UserIO myIo = new UserIOConsoleImpl();
        // Instantiate the View and wire the UserIO implementation into it
        VendingMachineView myView = new VendingMachineView(myIo);
        // Instantiate the ItemsDAO
        ItemsDao itemsDao = new ItemsDaoFileImpl();
        // Instantiate the ItemsListAuditDAO
        ItemsListAuditDao itemsAuditDao = new ItemsListAuditDaoFileImpl();
        // Instantiate the VendingService Layer and wire the DAO and Audit DAO into it
        VendingMachineServiceLayer myVendingService = new VendingMachineServiceLayerImpl(itemsDao, userBalance, itemsAuditDao);
        // Instantiate the Controller and wire the Service Layer into it
        VendingMachineController controller = new VendingMachineController(myVendingService, myView, userBalance);
        // Kick off the Controller
        controller.run();
    }
}
