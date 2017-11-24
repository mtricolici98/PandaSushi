package com.example.mariu.myapplication;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Field;
import java.util.ArrayList;

/**
 * Created by mariu on 11/22/2017.
 */

public class DishAdapterCart extends ArrayAdapter<CartItem> {
    public Activity context;
    public DishAdapterCart(Activity context, ArrayList<CartItem> cartItems){
        super(context,0,cartItems);
        this.context=context;
    }


    public View getView(int position, View convertView, ViewGroup parent){
        View listItemView = convertView;
        if(listItemView == null){
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.list,parent,false);
        }

        final CartItem currentDish = getItem(position);
        ImageView icon = (ImageView) listItemView.findViewById(R.id.displayimage);
        icon.setImageResource(getId(currentDish.getDish().getresName(),R.drawable.class));
        TextView name = (TextView) listItemView.findViewById(R.id.displayname);
        name.setText(getId(currentDish.getDish().getresName(),R.string.class));
        TextView price = (TextView) listItemView.findViewById(R.id.displayprice);
        price.setText(Integer.toString(currentDish.getDish().getPrice()*currentDish.getQuant())+"Lei");
        final Button btn = (Button) listItemView.findViewById(R.id.delteproduct);
        Button btn1 = (Button) listItemView.findViewById(R.id.cartItemIncrement);
        Button btn2 = (Button) listItemView.findViewById(R.id.cartItemDecrement);
        TextView quant = (TextView) listItemView.findViewById(R.id.displayquant);
        quant.setText(Integer.toString(currentDish.getQuant()));
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                currentDish.setQuant(currentDish.getQuant()+1);
                notifyDataSetChanged();
                ((CartActivity) context).setTotal();

            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(currentDish.getQuant()>1){
                currentDish.setQuant(currentDish.getQuant()-1);
                notifyDataSetChanged();
                    ((CartActivity) context).setTotal();
            } else if(currentDish.getQuant()==1){

                    Toast.makeText(context,"Cant have less than one",Toast.LENGTH_SHORT).show();

                }

            }
        });

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                DishList.getInstance().deleteFromCart(currentDish);
                notifyDataSetChanged();
                ((CartActivity) context).setTotal();

                Log.d("object","deletedFromCart");
            }
        });

        return listItemView;
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

}
