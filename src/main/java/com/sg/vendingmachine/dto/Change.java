package com.sg.vendingmachine.dto;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Change {

    private String totalPenniesString;

    private BigDecimal changeOwedTotalInPennies;

    private BigDecimal quartersOwed;

    private BigDecimal dimesOwed;

    private BigDecimal nicklesOwed;

    private BigDecimal penniesOwed;

    private BigDecimal remainderChangeOwed;

    public enum Coin {
        QUARTERS(new BigDecimal("25")),
        DIMES(new BigDecimal("10")),
        NICKLES(new BigDecimal("5")),
        PENNIES(new BigDecimal("1"));
        private final BigDecimal value;

        private Coin(BigDecimal value) {
            this.value = value;
        }
    }

    public Change(String totalPenniesString) {
        this.changeOwedTotalInPennies = new BigDecimal(totalPenniesString);
        this.changeOwedTotalInPennies.setScale(2, RoundingMode.HALF_UP);
        this.remainderChangeOwed = this.changeOwedTotalInPennies;
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

    public void setQuartersOwed() {
        this.quartersOwed = remainderChangeOwed.divide(Coin.QUARTERS.value, RoundingMode.HALF_UP);
        setRemainderChangeOwed(this.remainderChangeOwed.remainder(Coin.QUARTERS.value));
    }

    public BigDecimal getDimesOwed() {
        return dimesOwed;
    }

    public void setDimesOwed() {
        this.dimesOwed = remainderChangeOwed.divide(Coin.DIMES.value, RoundingMode.HALF_UP);
        setRemainderChangeOwed(this.remainderChangeOwed.remainder(Coin.DIMES.value));
    }

    public BigDecimal getNicklesOwed() {
        return nicklesOwed;
    }

    public void setNicklesOwed() {
        this.nicklesOwed = remainderChangeOwed.divide(Coin.NICKLES.value, RoundingMode.HALF_UP);
        setRemainderChangeOwed(this.remainderChangeOwed.remainder(Coin.NICKLES.value));
    }

    public BigDecimal getPenniesOwed() {
        return penniesOwed;
    }

    public void setPenniesOwed() {
        this.penniesOwed = remainderChangeOwed.divide(Coin.PENNIES.value, RoundingMode.HALF_UP);
        setRemainderChangeOwed(this.remainderChangeOwed.remainder(Coin.PENNIES.value));
    }

    public BigDecimal getRemainderChangeOwed() {
        return remainderChangeOwed;
    }

    public void setRemainderChangeOwed(BigDecimal remainderChangeOwed) {
        this.remainderChangeOwed = remainderChangeOwed;
    }
}
