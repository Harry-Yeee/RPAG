package com.example.rpag;

public class Item {
    private String itemName;
    private double itemPrice;
    private String itemDate;
    private int itemId;

    public Item(String itemName, double itemPrice, String itemDate, int itemId) {
        this.itemName = itemName;
        this.itemPrice = itemPrice;
        this.itemDate = itemDate;
        this.itemId = itemId;
    }

    public Item() {
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public double getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(double itemPrice) {
        this.itemPrice = itemPrice;
    }

    public String getItemDate() {
        return itemDate;
    }

    public void setItemDate(String itemDate) {
        this.itemDate = itemDate;
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    @Override
    public String toString() {
        return "ID: "+ itemId + " " + itemName + " $ " + itemPrice + " Month: " + itemDate;
    }
}
