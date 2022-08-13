package com.sg.vendingmachine.dto;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;

public class Item {

    private String id;

    private String name;

    private BigDecimal cost;

    private int quantityOnHand;

    public Item(String id) {
        this.id = id;
    }
    public Item(String id, String name, int quantityOnHand, BigDecimal cost) {
        this.id = id;
        this.name = name;
        this.quantityOnHand = quantityOnHand;
        this.cost = cost;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Item item = (Item) o;
        return quantityOnHand == item.quantityOnHand && id.equals(item.id) && name.equals(item.name) && cost.equals(item.cost);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, cost, quantityOnHand);
    }

    @Override
    public String toString() {
        return "Item{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", cost=" + cost +
                ", quantityOnHand=" + quantityOnHand +
                '}';
    }
}
