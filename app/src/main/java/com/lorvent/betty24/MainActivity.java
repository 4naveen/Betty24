package com.lorvent.betty24;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import com.lorvent.betty24.fragment.DetailsFragment;
import com.lorvent.betty24.fragment.InformationFragment;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle("");
        }
        SharedPreferences preferences = this.getSharedPreferences("My_pref",MODE_PRIVATE);
         if (preferences.getString("first_name",null)!=null)
         {
             Log.i("go to","details");

             Fragment fragment1=new DetailsFragment();
             FragmentTransaction trans1=getSupportFragmentManager().beginTransaction();
             trans1.replace(R.id.frame,fragment1);
             trans1.addToBackStack(null);
             trans1.commit();
           /*  Intent intent=new Intent(MainActivity.this,ScrollingActivity.class);
             startActivity(intent);*/
         }
         else {
             Fragment fragment1=new InformationFragment();
             FragmentTransaction trans1=getSupportFragmentManager().beginTransaction();
             trans1.replace(R.id.frame,fragment1);
             trans1.addToBackStack(null);
             trans1.commit();
         }

    }
}
