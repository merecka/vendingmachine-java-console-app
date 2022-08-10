package com.sg.vendingmachine.dto;

public class Item {
    private String name;

    private String cost;

    private int quantityOnHand;

    public Item(String name, String cost, int quantityOnHand) {
        this.name = name;
        this.cost = cost;
        this.quantityOnHand = quantityOnHand;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }

    public int getQuantityOnHand() {
        return quantityOnHand;
    }

    public void setQuantityOnHand(int quantityOnHand) {
        this.quantityOnHand = quantityOnHand;
    }
}
