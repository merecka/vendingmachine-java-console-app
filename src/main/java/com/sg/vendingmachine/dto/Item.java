package com.sg.vendingmachine.dto;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Item {

    private String id;

    private String name;

    private BigDecimal cost;

    private int quantityOnHand;

    public Item(String id) {
        this.id = id;
    }

    public String getItemId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getCost() {
        return cost;
    }

    public void setCost(String cost) {
        BigDecimal newCost = new BigDecimal(cost);
        this.cost = newCost.setScale(2, RoundingMode.HALF_UP);
    }

    public int getQuantityOnHand() {
        return quantityOnHand;
    }

    public void setQuantityOnHand(int quantityOnHand) {
        this.quantityOnHand = quantityOnHand;
    }
}
