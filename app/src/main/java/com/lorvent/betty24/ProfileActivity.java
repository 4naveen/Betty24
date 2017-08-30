package com.lorvent.betty24;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.lorvent.betty24.fragment.ProfileFragment;

public class ProfileActivity extends AppCompatActivity {
    Button edit,submit;
    TextView salutation,firstNasme, lastName, email,pin,phone,dob,insurance;
    // BottomNavigation bottomNavigation;
    BottomNavigationView bottomNavigation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
     //   bottomNavigation=(BottomNavigationView)findViewById(R.id.bottom_navigation);
        // bottomNavigation.setDefaultItem(0);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
       /* edit=(Button)findViewById(R.id.edit);
        submit=(Button)findViewById(R.id.submit);
        salutation=(TextView)findViewById(R.id.salutation);
        firstNasme=(TextView)findViewById(R.id.first_name);
        lastName=(TextView)findViewById(R.id.last_name);
        email=(TextView)findViewById(R.id.email);
        pin=(TextView)findViewById(R.id.pin);
        phone=(TextView)findViewById(R.id.phone);
        dob=(TextView)findViewById(R.id.dob);
        insurance=(TextView)findViewById(R.id.insurance);

        getPreferenceValue();
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // getFragmentManager().popBackStackImmediate();

                Fragment fragment1=new ProfileFragment();
                FragmentTransaction trans1=getSupportFragmentManager().beginTransaction();
                trans1.replace(R.id.frame,fragment1);
                trans1.addToBackStack(null);
                trans1.commit();
            }
        });
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ProfileActivity.this, QrCode2Activity.class);
                startActivity(i);
                //getActivity().finish();
            }
        });*/

    }
    private void getPreferenceValue() {
        SharedPreferences preferences = this.getSharedPreferences("My_pref",MODE_PRIVATE);
        salutation.setText(preferences.getString("salutation", null));
        firstNasme.setText(preferences.getString("first_name", null));
        lastName.setText(preferences.getString("last_name", null));
        email.setText(preferences.getString("email", null));
        pin.setText(preferences.getString("pin", null));
        phone.setText(preferences.getString("phone", null));
        dob.setText(preferences.getString("dob", null));
        insurance.setText(preferences.getString("insurance", null));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_info,menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId()==R.id.info)
        {
            MaterialDialog dialog1=new MaterialDialog.Builder(this)
                    .title("Terms and Conditions ")
                    .customView(R.layout.info_dialog, true)
                    .positiveText("ok")
                    .positiveColorRes(R.color.colorPrimary)
                    .onPositive(new MaterialDialog.SingleButtonCallback() {
                        @Override
                        public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {

                        }
                    })

                    .show();
        }
        return super.onOptionsItemSelected(item);
    }
}
