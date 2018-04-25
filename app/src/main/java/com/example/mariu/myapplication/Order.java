package com.example.mariu.myapplication;

import java.util.ArrayList;

/**
 * Created by mariu on 11/24/2017.
 */

public class Order {


    private String name;
    private ArrayList<CartItem> cartItems;
    private String adress;
    private String phonenr;
    private boolean done;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    private String type;
    private Order(){

    }

    public Order(ArrayList<CartItem> cartItems, String name, String adress, String phonenr,String type){
        this.cartItems=cartItems;
        this.name=name;
        this.adress=adress;
        this.phonenr=phonenr;
        this.type=type;
        done = false;
    }

    public void done(){
        done = true;
    }

    public boolean getDone(){
        return done;
    }
    public String getAdress() {
        return adress;
    }

    public ArrayList<CartItem> getCartItems() {
        return cartItems;
    }

    public void setCartItems(ArrayList<CartItem> cartItems) {
        this.cartItems = cartItems;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }
    public String getPhonenr() {
        return phonenr;
    }

    public void setPhonenr(String phonenr) {
        this.phonenr = phonenr;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
