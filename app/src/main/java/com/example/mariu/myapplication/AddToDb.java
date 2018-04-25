package com.example.mariu.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddToDb extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_to_db);

        Button addb = (Button) findViewById(R.id.addButton);

        addb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText name = (EditText) findViewById(R.id.addName);
                EditText cat = (EditText) findViewById(R.id.addCat);
                EditText price = (EditText) findViewById(R.id.addPrice);
                EditText desc = (EditText) findViewById(R.id.addDesc);
                DishList.getInstance().loadItems(name.getText().toString(),Integer.parseInt(price.getText().toString()),cat.getText().toString(),desc.getText().toString());
            }
        });



    }
}
