package com.example.rpag;

public class Category {
    private String categoryName;
    private double categoryBudget;
    private int categoryID;

    public Category(String categoryName, double categoryBudget, int categoryID) {
        this.categoryName = categoryName;
        this.categoryBudget = categoryBudget;
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
        return categoryName + "  Budget: $ " + categoryBudget;
    }
}
