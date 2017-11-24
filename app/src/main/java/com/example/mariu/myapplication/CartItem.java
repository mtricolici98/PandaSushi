package com.example.mariu.myapplication;

/**
 * Created by mariu on 11/23/2017.
 */

public class CartItem {
    private Dish dish;
    private int quant;
    public CartItem(Dish d, int quant){
        dish=d;
        this.quant=quant;
    }

    public Dish getDish() {
        return dish;
    }
    public int getQuant(){
        return quant;
    }
    public void setDish(Dish dish){
        this.dish=dish;
    }
    public void setQuant(int i){
        quant = i ;
    }
}
