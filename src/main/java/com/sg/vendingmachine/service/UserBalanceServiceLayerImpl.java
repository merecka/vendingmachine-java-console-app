package com.sg.vendingmachine.service;

import com.sg.vendingmachine.dao.UserBalanceDao;

import java.math.BigDecimal;

public class UserBalanceServiceLayerImpl implements UserBalanceServiceLayer {

    private UserBalanceDao userBalanceDao;

    public UserBalanceServiceLayerImpl(UserBalanceDao userBalanceDao) {
        this.userBalanceDao = userBalanceDao;
    }

    public BigDecimal getUserBalance() {
        return userBalanceDao.getUserBalance();
    }
}
