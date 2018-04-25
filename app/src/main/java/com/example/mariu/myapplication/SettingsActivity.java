package com.example.mariu.myapplication;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceActivity;
import android.preference.PreferenceFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class SettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getFragmentManager().beginTransaction().replace(android.R.id.content, new MyPreferenceFragment()).commit();

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //addPreferencesFromResource(R.xml.preferences);





        //        setContentView(R.layout.settings_layout);
//        Button save = (Button) findViewById(R.id.setting_save);
//        final EditText name = (EditText) findViewById(R.id.setting_name);
//        final EditText adress = (EditText) findViewById(R.id.setting_address);
//        final EditText phone = (EditText) findViewById(R.id.setting_phone);
//        final SharedPreferences prefs = this.getSharedPreferences("PandaSushiSharedPrefs", Context.MODE_PRIVATE);
//        final SharedPreferences.Editor editor = prefs.edit();
//        name.setText(prefs.getString("name",""));
//        adress.setText(prefs.getString("adress",""));
//        phone.setText(prefs.getString("phone",""));
//        Log.d("sharedprefs","inca");
//
//
//                save.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                String n = name.getText().toString();
//                String a = adress.getText().toString();
//                String p = phone.getText().toString();
//                editor.putString("name",n).commit();
//                editor.putString("adress",a).commit();
//                editor.putString("phone",p).commit();
//                finish();
//            }
//        });
    }
    public boolean onSupportNavigateUp(){
        finish();
        return true;
    }

    public static class MyPreferenceFragment extends PreferenceFragment {
        @Override
        public void onCreate(final Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            addPreferencesFromResource(R.xml.preferences);
        }
    }

}
