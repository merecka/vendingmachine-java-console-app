package com.sg.vendingmachine.dto;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Change {

    private String totalPenniesString;

    private BigDecimal changeOwedTotalInPennies;

    private BigDecimal quartersOwed;

    private BigDecimal dimesOwed;

    private BigDecimal nickelsOwed;

    private BigDecimal penniesOwed;

    private BigDecimal remainderChangeOwed;


    public Change(BigDecimal totalPennies) {
        totalPennies.setScale(2, RoundingMode.HALF_UP);
        this.remainderChangeOwed = totalPennies;
    }

    public BigDecimal getChangeOwedTotalInPennies() {
        return changeOwedTotalInPennies;
    }

    public void setChangeOwedTotalInPennies(BigDecimal changeOwedTotalInPennies) {
        this.changeOwedTotalInPennies = changeOwedTotalInPennies;
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

    public BigDecimal getRemainderChangeOwed() {
        return remainderChangeOwed;
    }

    public void setRemainderChangeOwed(BigDecimal remainderChangeOwed) {
        this.remainderChangeOwed = remainderChangeOwed;
    }
}
