package com.example.rpag;

public class Category {
    private String categoryName;
    private double categoryBudget;
    private double categorySpent;
    private double categoryRemaining;
    private String categoryMonth;
    private int categoryID;

    public Category() {
    }

    public Category(String categoryName, double categoryBudget, double categorySpent, double categoryRemaining,
                    String categoryMonth, int categoryID) {
        this.categoryName = categoryName;
        this.categoryBudget = categoryBudget;
        this.categorySpent = categorySpent;
        this.categoryRemaining = categoryRemaining;
        this.categoryMonth = categoryMonth;
        this.categoryID = categoryID;
    }

    public double getCategorySpent() {
        return categorySpent;
    }

    public void setCategorySpent(double categorySpent) {
        this.categorySpent = categorySpent;
    }

    public String getCategoryMonth() {
        return categoryMonth;
    }

    public void setCategoryMonth(String categoryMonth) {
        this.categoryMonth = categoryMonth;
    }

    public double getCategoryRemaining() {
        return categoryRemaining;
    }

    public void setCategoryRemaining(double categoryRemaining) {
        this.categoryRemaining = categoryRemaining;
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
        if(categoryName.equals("Total Budget")){
            return categoryName + " for " + categoryMonth + "\n    Budget: $ " + (float)categoryBudget + "\n    Spent:   $ " + (float)categorySpent +
                    "   Remaining: $ " + (float)categoryRemaining;
        }
        return categoryName + "\n    Budget: $ " + (float)categoryBudget + "\n    Spent:   $ " + (float)categorySpent +
                               "   Remaining: $ " + (float)categoryRemaining;
    }
}
