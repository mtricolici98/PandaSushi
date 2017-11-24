package com.example.mariu.myapplication;

/**
 * Created by mariu on 11/14/2017.
 */

public class Dish {
    private String resName;
    private int price;
    private String description;
    private String category;
    private Dish(){}
    public Dish(String resName,String category, int price, String description){
        this.resName= resName;
        this.price = price;
        this.description=description;
        this.category=category;

    }
    public String getresName() {
        return resName;
    }
    public int getPrice() {
        return price;
    }
    public String getDescription(){
        return description;
    }
    public String getCategory(){
        return category;
    }






}
