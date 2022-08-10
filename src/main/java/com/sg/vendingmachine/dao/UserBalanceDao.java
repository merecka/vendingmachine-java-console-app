package com.sg.vendingmachine.dao;

import java.math.BigDecimal;

public interface UserBalanceDao {

    void updateUserBalance(String amount);

    BigDecimal getUserBalance();

}
