package com.sg.vendingmachine.dto;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class UserBalance {

    private BigDecimal currentBalance;

    // need to verify that User has entered only integers for the starting balance
    public UserBalance() {
        String initialBalance = "0.00";
        this.currentBalance = new BigDecimal(initialBalance).setScale(2, RoundingMode.UNNECESSARY);
    }

    public BigDecimal getBalance() {
        return currentBalance;
    }

    public void setBalance(String newAmount) {
        BigDecimal newAmountBD = new BigDecimal(newAmount);
        this.currentBalance = newAmountBD.setScale(2, RoundingMode.UNNECESSARY);
    }

    public void addFunds(String newAmount) {
        BigDecimal newAmountBD = new BigDecimal(newAmount).setScale(2, RoundingMode.UNNECESSARY);
        this.currentBalance = currentBalance.add(newAmountBD);
    }
}
