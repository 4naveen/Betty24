package com.lorvent.betty24.fragment;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.lorvent.betty24.AppSession;
import com.lorvent.betty24.QrCode2Activity;
import com.lorvent.betty24.QrCodeActivity;
import com.lorvent.betty24.R;
import com.ss.bottomnavigation.BottomNavigation;

import static android.content.Context.MODE_PRIVATE;


/**
 * A simple {@link Fragment} subclass.
 */
public class DetailsFragment extends Fragment {
    Button edit,submit;
    TextView salutation,firstNasme, lastName, email,pin,phone,dob,insurance;
   // BottomNavigation bottomNavigation;
    BottomNavigationView bottomNavigation;

    public DetailsFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_details, container, false);
        setHasOptionsMenu(true);
        bottomNavigation=(BottomNavigationView)view.findViewById(R.id.bottom_navigation);
       // bottomNavigation.setDefaultItem(0);

        edit=(Button)view.findViewById(R.id.edit);
        submit=(Button)view.findViewById(R.id.submit);
        salutation=(TextView)view.findViewById(R.id.salutation);
        firstNasme=(TextView)view.findViewById(R.id.first_name);
        lastName=(TextView)view.findViewById(R.id.last_name);
        email=(TextView)view.findViewById(R.id.email);
        pin=(TextView)view.findViewById(R.id.pin);
        phone=(TextView)view.findViewById(R.id.phone);
        dob=(TextView)view.findViewById(R.id.dob);
        insurance=(TextView)view.findViewById(R.id.insurance);
        ActionBar actionBar=((AppCompatActivity)getActivity()).getSupportActionBar();
        setHasOptionsMenu(true);

     /*   if (AppSession.isSeen)
        {
            bottomNavigation.inflateMenu(R.menu.bottom_navigation_main2);
            Log.i("isSeen", String.valueOf(AppSession.isSeen));
            bottomNavigation.setOnNavigationItemSelectedListener(
                    new BottomNavigationView.OnNavigationItemSelectedListener() {
                        @Override
                        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                            switch (item.getItemId()) {
                                case R.id.action_favorites:
                                {
                                    break;
                                }
                                case R.id.action_schedules:
                                { Intent i = new Intent(getActivity(), QrCode2Activity.class);
                                    startActivity(i);
                                }
                                case R.id.action_music:
                                {
                                    Intent i = new Intent(getActivity(), QrCode2Activity.class);
                                    startActivity(i);
                                    break;
                                }

                            }
                            return true;
                        }
                    });
        }
        else {
            bottomNavigation.inflateMenu(R.menu.bottom_navigation_main0);

        }*/
        if (actionBar != null) {
            actionBar.setIcon(R.mipmap.actionbar_logo2);
        }
        getPreferenceValue();
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // getFragmentManager().popBackStackImmediate();

                Fragment fragment1=new ProfileFragment();
                FragmentTransaction trans1=getFragmentManager().beginTransaction();
                trans1.replace(R.id.frame,fragment1);
                trans1.addToBackStack(null);
                trans1.commit();
            }
        });
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), QrCode2Activity.class);
                startActivity(i);
                //getActivity().finish();
            }
        });
        view.setFocusableInTouchMode(true);
        view.requestFocus();
        view.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (event.getAction() == KeyEvent.ACTION_DOWN) {
                    if (keyCode == KeyEvent.KEYCODE_BACK) {
                        //getFragmentManager().popBackStackImmediate();
                        Fragment fragment1=new ProfileFragment();
                        FragmentTransaction trans1=getFragmentManager().beginTransaction();
                        trans1.replace(R.id.frame,fragment1);
                        trans1.addToBackStack(null);
                        trans1.commit();
                        return true;
                    }
                }
                return false;
            }
        });
        return view;
    }
    private void getPreferenceValue() {
        SharedPreferences preferences = getActivity().getSharedPreferences("My_pref",MODE_PRIVATE);
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
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {

        inflater.inflate(R.menu.menu_info,menu);

        super.onCreateOptionsMenu(menu, inflater);
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId()==R.id.info)
        {
            MaterialDialog dialog1=new MaterialDialog.Builder(getActivity())
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
