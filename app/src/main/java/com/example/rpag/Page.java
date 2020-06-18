package com.example.rpag;

import java.util.ArrayList;

public class Page {

    private String Name; // name == month
    private ArrayList<Category> CategoryList;
    private Double Budget;
    private Double Spent;

    public void Page()
    {
        Name = "";
        Budget = 0.0;
        Spent = 0.0;
        CategoryList = new ArrayList<Category>();
    }

    public void setName(String name)
    {
        Name = name;
    }

    public void AddCategory(Category category)
    {
        CategoryList.add(category);
    }

    public Double getBudget()
    {
        return Budget;
    }

    public Double getSpent()
    {
        return Spent;
    }

    public void CreateReport()
    {

    }



}
