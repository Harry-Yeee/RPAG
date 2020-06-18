package com.example.rpag;

public class Item {

    private String Name;
    private Double Price;
    private Double Date;

    public Item()
    {
        Name = "";
        Price = 0.0;
        Date = 0.0;
    }

    public Item(String name, Double price, Double date)
    {
        Name = name;
        Price = price;
        Date = date;
    }


    public void setName(String name) {
        Name = name;
    }

    public void changePrice(Double price) {
        Price = price;
    }

    public String getName() {
        return Name;
    }

    public Double getPrice() {
        return Price;
    }
}
