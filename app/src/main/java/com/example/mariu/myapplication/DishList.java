package com.example.mariu.myapplication;

import android.graphics.drawable.Drawable;
import android.nfc.Tag;
import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Observable;

/**
 * Created by mariu on 11/14/2017.
 */

public class DishList {
    private FirebaseDatabase database;
    private FirebaseAuth mAuth;
        private static DishList instance;
        private ArrayList<Dish> dishes;
        private ArrayList<CartItem> cart;
        private DishList(){
            dishes =  new ArrayList<Dish>();
            cart = new ArrayList<CartItem>();
        }

        public static DishList getInstance(){
            if(instance == null){
                instance = new DishList();
            }
            return instance;
        }

        public void addToList(Dish dish){

            Log.d("BUGGG","addtolistbug");
            dishes.add(dish);
        }
        public void loadItems(String name, int price, String cat, String descrption) {
            database = FirebaseDatabase.getInstance();
            DatabaseReference myRef = database.getReference("dishlist");

            myRef.push().setValue(new Dish(name, cat, price, descrption));

        }


    public boolean reloadItems() {
        dishes.clear();
        database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("dishlist");
        Log.d("OBJECTNAME", "TRYING");

        myRef.addChildEventListener(new ChildEventListener() {
                                        @Override
                                        public void onChildAdded(DataSnapshot dataSnapshot, String s) {

                                            Dish o = dataSnapshot.getValue(Dish.class);
                                            

                                            dishes.add(o);
                                       
                                            Log.d("objinfo",o.getresName()+" "+o.getCategory()+" "+o.getPrice()+" " +o.getDescription());
                                        }

                                        @Override
                                        public void onChildChanged(DataSnapshot dataSnapshot, String s) {

                                        }

                                        @Override
                                        public void onChildRemoved(DataSnapshot dataSnapshot) {

                                        }

                                        @Override
                                        public void onChildMoved(DataSnapshot dataSnapshot, String s) {

                                        }

                                        @Override
                                        public void onCancelled(DatabaseError databaseError) {

                                        }
                                    }
        );
        return true;
    }

    public ArrayList<Dish> getList(){
            Log.d("BUGGGG","getlistbug");
            return dishes;
    }
    public ArrayList<Dish> getList(String cat){
        ArrayList<Dish> temp = new ArrayList<Dish>();
        if(cat.equals("all")){
            return dishes;
        } else {
        for(int i=0;i<dishes.size();i++){
        if(dishes.get(i).getCategory().equals(cat)){
            temp.add(dishes.get(i));
        }}

        }
        return temp;
    }
    public static int getId(String resourceName, Class<?> c) {
        try {
            Field idField = c.getDeclaredField(resourceName);
            return idField.getInt(idField);
        } catch (Exception e) {
            throw new RuntimeException("No resource ID found for: "
                    + resourceName + " / " + c, e);
        }

    }


    public void addToCart(Dish obj){
        cart.add(new CartItem(obj,1));
    }
    public Dish getDish(String refname){
        for(int i=0;i<dishes.size();i++){
            if(dishes.get(i).getresName().equals(refname)){
                return dishes.get(i);
            }
        }
        return null;
    }
    public ArrayList<CartItem> getCart(){
        return cart;
    }

    public void deleteFromCart(CartItem obj){
        cart.remove(obj);
    }
    public void saveCart(){

    }

    public void placeOrder(String name, String adress, String phone,String type){
        database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("orders");

        myRef.push().setValue(new Order(cart, name,adress,phone,type));
    }

    public void emptyCart(){
        cart.clear();
    }

}
