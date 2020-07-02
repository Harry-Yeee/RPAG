package com.example.rpag;

public class Item {
    private String itemName;
    private double itemPrice;
    private int itemDate;
    private int id;

    public Item(String itemName, double itemPrice, int itemDate, int id) {
        this.itemName = itemName;
        this.itemPrice = itemPrice;
        this.itemDate = itemDate;
        this.id = id;
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

    public int getItemDate() {
        return itemDate;
    }

    public void setItemDate(int itemDate) {
        this.itemDate = itemDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "ID: "+ id + " " + itemName + " $ " + itemPrice + " Month: " + itemDate;
    }
}
