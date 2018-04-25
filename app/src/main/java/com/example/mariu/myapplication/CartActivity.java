package com.example.mariu.myapplication;

import android.app.Dialog;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.content.Intent;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;


public class CartActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
       private DrawerLayout mDrawerLayout;
       private ActionBarDrawerToggle mToggle;
       private Button b,n;
       private TextView total;
       private ArrayList<CartItem> cart;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        setTitle("Cos");
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        mToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.open, R.string.close);
        mDrawerLayout.addDrawerListener(mToggle);

        mToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        cart = DishList.getInstance().getCart();
        ListView lv = (ListView) findViewById(R.id.listView);
        DishAdapterCart da = new DishAdapterCart(this, DishList.getInstance().getCart());
        lv.setAdapter(da);
        setNavigationViewListner();
        n = (Button) findViewById(R.id.checkout);
        total = (TextView) findViewById(R.id.totalprice);
        setTotal();
        n.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (cart.size() != 0) {
                    final Dialog dialog = new Dialog(CartActivity.this);
                    dialog.setContentView(R.layout.checkout_dialog);
                    getLayoutInflater().inflate(R.layout.checkout_dialog, null);
                    dialog.setTitle("This is my custom dialog box");
                    dialog.setCancelable(true);

                    // there are a lot of settings, for dialog, check them all out!
                    // set up radiobutton
                    final RadioButton rd1 = (RadioButton) dialog.findViewById(R.id.rd_1);
                    final RadioButton rd2 = (RadioButton) dialog.findViewById(R.id.rd_2);
                    rd1.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            rd2.setChecked(false);
                        }
                    });
                    rd2.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            rd1.setChecked(false);
                        }
                    });
                    Button btnOk = (Button) dialog.findViewById(R.id.dialogOk);
                    btnOk.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            if (rd2.isChecked()) {
                                Intent n = new Intent(CartActivity.this, DeliveryActivity.class);
                                n.putExtra("type", "delivery");
                                CartActivity.this.finish();
                                startActivity(n);
                            } else {
                                Intent n = new Intent(CartActivity.this, DeliveryActivity.class);
                                n.putExtra("type", "pickup");
                                CartActivity.this.finish();
                                startActivity(n);
                            }
                        }
                    });
                    Button btnCancel = (Button) dialog.findViewById(R.id.dialogCancel);
                    btnCancel.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            dialog.cancel();
                        }
                    });
                    // now that the dialog is set up, it's time to show it
                    dialog.show();

                } else
                {
                    Toast.makeText(CartActivity.this,"Nimic in cos", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    public void setTotal(){
        int totalprice = 0 ;
        for(int i=0;i<cart.size();i++){
            totalprice+=(cart.get(i).getDish().getPrice()*cart.get(i).getQuant());
        }
        total.setText(Integer.toString(totalprice)+"Lei");
    }


    private void setNavigationViewListner() {
        NavigationView navigationView = (NavigationView) findViewById(R.id.menu_nav);
        navigationView.setNavigationItemSelectedListener(this);
    }



    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        // Handle navigation view item clicks here.
        switch (item.getItemId()) {

            case R.id.MARIUS: {
                goHome();
                mDrawerLayout.closeDrawers();
                break;
            }
            case R.id.abouts : {
                goAboutUs();
                mDrawerLayout.closeDrawers();
                break;
            }
            case R.id.settings : {
                goSettings();
                mDrawerLayout.closeDrawers();
                break;
            }

        }
        //close navigation drawer

        return true;
    }
    public boolean onOptionsItemSelected(MenuItem item) {

        if(mToggle.onOptionsItemSelected(item)){
            return true;
        }

        return true;
    }
    public void goHome(){
        Intent i = new Intent(this, MainActivity.class);
        finish();
        startActivity(i);
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
    public void goSettings(){
        Intent i = new Intent(this, SettingsActivity.class);
        startActivity(i);
    }
    public void goAboutUs(){
        Intent i = new Intent(this, MapsActivity.class);
        startActivity(i);
    }
}
