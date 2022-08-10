package com.sg.vendingmachine.dto;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class VendingMachine {

    private BigDecimal balance;

    // need to verify that User has entered only integers for the starting balance
    public VendingMachine(String balance) {
        this.balance = new BigDecimal(balance);
        this.balance.setScale(2, RoundingMode.HALF_UP);
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = new BigDecimal(balance);
        this.balance.setScale(2, RoundingMode.HALF_UP);
    }
}
