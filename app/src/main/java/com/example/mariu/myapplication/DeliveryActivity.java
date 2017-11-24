package com.example.mariu.myapplication;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class DeliveryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delivery);
        setTitle("Checkout");
        final EditText name,adress,phone;
        final String type = getIntent().getStringExtra("type");
        name = (EditText) findViewById(R.id.delivery_name);
        adress = (EditText) findViewById(R.id.delivery_address);
        phone = (EditText) findViewById(R.id.delivery_phone);
        final SharedPreferences prefs = this.getSharedPreferences("PandaSushiSharedPrefs", Context.MODE_PRIVATE);
        final SharedPreferences.Editor editor = prefs.edit();
        name.setText(prefs.getString("name",""));
        adress.setText(prefs.getString("adress",""));
        phone.setText(prefs.getString("phone",""));
        Button btn = (Button) findViewById(R.id.delivery_confirm);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            DishList.getInstance().placeOrder(name.getText().toString(),adress.getText().toString(),phone.getText().toString(),type);
            Intent n = new Intent(DeliveryActivity.this,pickupActivity.class);
            DishList.getInstance().emptyCart();
            finish();
            startActivity(n);
            }
        });
    }
}
