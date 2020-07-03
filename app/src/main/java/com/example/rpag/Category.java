package com.example.rpag;

public class Category {
    private String categoryName;
    private double categoryBudget;
    private double categorySpent;
    private int categoryID;

    public double getCategorySpent() {
        return categorySpent;
    }

    public void setCategorySpent(double categorySpent) {
        this.categorySpent = categorySpent;
    }

    public Category(String categoryName, double categoryBudget, double categorySpent, int categoryID) {
        this.categoryName = categoryName;
        this.categoryBudget = categoryBudget;
        this.categorySpent = categorySpent;
        this.categoryID = categoryID;
    }

    public Category() {
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public double getCategoryBudget() {
        return categoryBudget;
    }

    public void setCategoryBudget(double categoryBudget) {
        this.categoryBudget = categoryBudget;
    }

    public int getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(int categoryID) {
        this.categoryID = categoryID;
    }

    @Override
    public String toString() {
        return categoryName + "  Budget: $ " + categoryBudget + " Spent: $" + categorySpent;
    }
}
