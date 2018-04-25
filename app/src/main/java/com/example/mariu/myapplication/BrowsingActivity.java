package com.example.mariu.myapplication;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

import java.util.Observable;
import java.util.Observer;

public class BrowsingActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mToggle;
    private FirebaseAuth mAuth;
    // ...
    private Button b,s;
    private DishList dishlist;
    private GridView gridView;
    public DishAdapterGrid dishAdapter;
    private TextView name, category, price, description,resid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.e("HERE","RIGHTHERE");
        super.onCreate(savedInstanceState);
        dishlist = DishList.getInstance();
        setContentView(R.layout.testlistview);
        setTitle(getIntent().getStringExtra("category").toUpperCase());
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawerLayoutBrowsing);
        mToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.open, R.string.close);
        mDrawerLayout.addDrawerListener(mToggle);
        mToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        gridView = (GridView) findViewById(R.id.gridView);
        String cat = getIntent().getStringExtra("category");


        dishAdapter= new DishAdapterGrid(this, dishlist.getList(cat));
        Log.d("BUGGG", Integer.toString(dishlist.getList().size()));
        //ListView listView = (ListView) findViewById(R.id.listView);
        Log.d("BUGGG", Integer.toString(dishAdapter.getCount()));
        gridView.setAdapter(dishAdapter);
        Log.d("BUGG",String.valueOf(dishAdapter.getCount()));


        Log.d("BUGGG", "avtersetadaptorbug");
        setNavigationViewListner();
    }

    private void switchView() throws InterruptedException {
        setContentView(R.layout.testlistview);


    }
    private void setNavigationViewListner() {
        NavigationView navigationView = (NavigationView) findViewById(R.id.menu_nav);
        navigationView.setNavigationItemSelectedListener(this);
    }
    public boolean onOptionsItemSelected(MenuItem item) {

        if(mToggle.onOptionsItemSelected(item)){
            return true;
        }
        super.onOptionsItemSelected(item);
        switch (item.getItemId()){
            case R.id.shopping:
                ShoppingMenuitem();
                break;

        }
        return true;
    }
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        // Handle navigation view item clicks here.
        switch (item.getItemId()) {

            case R.id.MARIUS: {
                goHome(); mDrawerLayout.closeDrawers();
                finish();
                break;
            }
            case R.id.settings: {
                goSettings(); mDrawerLayout.closeDrawers();
                break;
            }
            case R.id.abouts : {
                goAboutUs(); mDrawerLayout.closeDrawers();
                break;
            }
        }
        //close navigation drawer

        return true;
    }
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_add_to_cart, menu);
        return super.onCreateOptionsMenu(menu);
    }
    @SuppressLint("ResourceType")
    public void ShoppingMenuitem(){

        Intent i = new Intent(BrowsingActivity.this, CartActivity.class);
        startActivity(i);
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
