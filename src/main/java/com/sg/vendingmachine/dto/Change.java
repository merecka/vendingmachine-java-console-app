package com.sg.vendingmachine.dto;

import java.math.BigDecimal;

public class Change {

    private BigDecimal quartersOwed;

    private BigDecimal dimesOwed;

    private BigDecimal nickelsOwed;

    private BigDecimal penniesOwed;



    public Change() {
    }

    public BigDecimal getQuartersOwed() {
        return quartersOwed;
    }

    public void setQuartersOwed(BigDecimal quarters) {
        this.quartersOwed = quarters;
    }

    public BigDecimal getDimesOwed() {
        return dimesOwed;
    }

    public void setDimesOwed(BigDecimal dimes) {
        this.dimesOwed = dimes;
    }

    public BigDecimal getNicklesOwed() {
        return nickelsOwed;
    }

    public void setNicklesOwed(BigDecimal nickels) {
        this.nickelsOwed = nickels;
    }

    public BigDecimal getPenniesOwed() {
        return penniesOwed;
    }

    public void setPenniesOwed(BigDecimal pennies) {
        this.penniesOwed = pennies;
    }
}
