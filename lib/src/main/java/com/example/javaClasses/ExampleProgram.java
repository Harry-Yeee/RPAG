package com.example.javaClasses;

public class ExampleProgram
{
    public static void main(String[] args)
    {
        Item eggs = new Item("Eggs", 5.00, 116112.0);
        System.out.println(eggs.getPrice());
    }
}