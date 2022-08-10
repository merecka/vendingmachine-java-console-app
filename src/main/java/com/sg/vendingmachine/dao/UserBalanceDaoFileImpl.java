package com.sg.vendingmachine.dao;

import com.sg.vendingmachine.dto.UserBalance;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class UserBalanceDaoFileImpl implements UserBalanceDao {

    private UserBalance userBalance;

    public UserBalanceDaoFileImpl(UserBalance userBalance) {
        this.userBalance = userBalance;
    }

    @Override
    public void updateUserBalance(String amount) {
        BigDecimal newAmount = new BigDecimal(amount);

        BigDecimal newBalance = this.userBalance.getBalance().add(newAmount);
        newBalance.setScale(2, RoundingMode.HALF_UP);
        this.userBalance.setBalance(newBalance);
    }

    @Override
    public BigDecimal getUserBalance() {
        return this.userBalance.getBalance();
    }
}
