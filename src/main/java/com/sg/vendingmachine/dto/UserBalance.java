package com.sg.vendingmachine.dto;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class UserBalance {


    private String initialBalance = "1";
    private BigDecimal currentBalance;

    // need to verify that User has entered only integers for the starting balance
    public UserBalance() {
        this.currentBalance = new BigDecimal(initialBalance);
        this.currentBalance.setScale(2, RoundingMode.HALF_UP);
    }

    public BigDecimal getBalance() {
        return currentBalance;
    }

    public void setBalance(BigDecimal balance) {
        this.currentBalance = balance;
    }
}
