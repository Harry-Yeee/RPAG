package com.example.javaClasses;

import java.util.ArrayList;

public class Category {
    private String Name;
    private Double Total;
    private ArrayList<Item> ItemList;
    private int itemCount;

    public Category()
    {
        Name = "";
        Total = 0.0;
        ItemList = new ArrayList<Item>();
        itemCount = 0;
    }

//    public Category(String name, Double total)
//    {
//        Name = "";
//        Total = 0.0;
//        ItemList = new ArrayList<Item>();
//        itemCount = 0;
//    }

    public void setName(String name)
    {
        Name = name;
    }

    public void addItem(Item item)
    {
        ItemList.add(item);
        Total += item.getPrice();
        itemCount++;
    }

    public void removeItem(Item item)
    {
        ItemList.remove(item);
        Total -= item.getPrice();
        itemCount--;
    }

    public ArrayList<Item> getList()
    {
        return ItemList;
    }


}
