package com.example.mariu.myapplication;

import android.app.Activity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by sergi on 11/21/2017.
 */

public class Pop extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        Log.d("TAG","creating pop");
        super.onCreate(savedInstanceState);
        Log.d("TAG","creating pop");
        setContentView(R.layout.popwindow);
        String s = getIntent().getStringExtra("refname");
        Dish d = DishList.getInstance().getDish(s);
        Log.d("poptest",d.getresName());
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        ImageView im = findViewById(R.id.pupupimage);
        TextView tv = findViewById(R.id.popuptext);
        TextView dv = findViewById(R.id.popupdesc);
        dv.setText(d.getDescription());
        tv.setText(DishAdapterGrid.getId(d.getresName(),R.string.class));
        im.setImageResource(DishAdapterGrid.getId(d.getresName(),R.drawable.class));
        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int)(width*.8),(int)(height*.7));

    }





}
