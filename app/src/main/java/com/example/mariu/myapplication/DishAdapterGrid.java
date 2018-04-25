package com.example.mariu.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.media.Image;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Field;
import java.util.ArrayList;

import static android.support.v4.content.ContextCompat.startActivity;

/**
 * Created by mariu on 11/19/2017.
 */

public class DishAdapterGrid extends ArrayAdapter<Dish> {
    public Activity context;
public DishAdapterGrid(Activity context, ArrayList<Dish> dishes){
    super(context,0,dishes);
    this.context=context;
}

public View getView(int position, View convertView, ViewGroup parent){
    View listItemView = convertView;
    if(listItemView == null){
        listItemView = LayoutInflater.from(getContext()).inflate(R.layout.list_item,parent,false);
    }

    final Dish currentDish = getItem(position);
    ImageView icon = (ImageView) listItemView.findViewById(R.id.drawImage);
    icon.setImageResource(getId(currentDish.getresName(),R.drawable.class));
    TextView name = (TextView) listItemView.findViewById(R.id.drawName);
    name.setText(getId(currentDish.getresName(),R.string.class));
    TextView price = (TextView) listItemView.findViewById(R.id.drawPrice);
    price.setText(Integer.toString(currentDish.getPrice())+"Lei");
    final Button btn = (Button) listItemView.findViewById(R.id.addToCart);
    if(isInCart(currentDish)){
    btn.setText("Added to cart");
    } else {
        btn.setText(R.string.addtocart);
    }
    btn.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if(!isInCart(currentDish)){
            DishList.getInstance().addToCart(currentDish);
            btn.setText(R.string.addedtocart);

            } else {
                Toast.makeText(context,"Deja In Cos",Toast.LENGTH_SHORT).show();
            }
        Log.d("object","addedToCart");
        }
    });
    FrameLayout fl = (FrameLayout) listItemView.findViewById(R.id.drawFrame);
    fl.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Log.d("TAG","Trying to useonclick");
            Intent n = new Intent(context,Pop.class);
            n.putExtra("refname",currentDish.getresName());
            context.startActivity(n);

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
    public boolean isInCart(Dish dish){

        for(int i=0;i<DishList.getInstance().getCart().size();i++) {
            if(DishList.getInstance().getCart().get(i).getDish().getresName().equals(dish.getresName())){
                Log.d("Contains","True");
                return true;

            }
        }
        Log.d("Contains","false");
        return false;
    }
}
