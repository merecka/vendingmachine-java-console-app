package com.sg.vendingmachine.dto;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class UserBalance {


    private String initialBalance = "0.00";
    private BigDecimal currentBalance;

    // need to verify that User has entered only integers for the starting balance
    public UserBalance() {
        this.currentBalance = new BigDecimal(initialBalance);
        this.currentBalance.setScale(2, RoundingMode.CEILING);
    }

    public BigDecimal getBalance() {
        return currentBalance;
    }

    public void setBalance(String newAmount) {
        BigDecimal newAmountBD = new BigDecimal(newAmount);
        newAmountBD.setScale(2, RoundingMode.HALF_UP);
        BigDecimal newBalance = this.getBalance().add(newAmountBD);
        System.out.println("newBalance is: " + newBalance);
        this.currentBalance = newBalance;
        System.out.println("New user balance is: " + this.getBalance());
    }
}
