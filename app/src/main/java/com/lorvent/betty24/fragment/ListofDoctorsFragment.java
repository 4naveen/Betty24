package com.lorvent.betty24.fragment;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.lorvent.betty24.AppSession;
import com.lorvent.betty24.R;
import com.lorvent.betty24.adapters.DoctorAdapter;
import com.lorvent.betty24.model.Doctor;
import com.ss.bottomnavigation.BottomNavigation;
import com.ss.bottomnavigation.events.OnSelectedItemChangeListener;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class ListofDoctorsFragment extends Fragment {
    ArrayList<Doctor> doctorArrayList;
    RecyclerView recyclerView;
    DoctorAdapter adapter;
  //  BottomNavigation bottomNavigation;
  BottomNavigationView bottomNavigation;

    public ListofDoctorsFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_listof_doctors, container, false);
        doctorArrayList=new ArrayList<>();
        ActionBar actionBar=((AppCompatActivity)getActivity()).getSupportActionBar();
        setHasOptionsMenu(true);
        bottomNavigation=(BottomNavigationView)view.findViewById(R.id.bottom_navigation);
        //bottomNavigation.setDefaultItem(2);
        Menu menu=bottomNavigation.getMenu();
        MenuItem menuItem=menu.findItem(R.id.action_music);
        menuItem.setChecked(true);
        AppSession.isSeen=true;
        bottomNavigation.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.action_favorites:
                            {
                                getActivity().finish();
                                break;
                            }
                            case R.id.action_schedules:
                            {
                                getFragmentManager().popBackStack();
                                  break;
                            }


                        }
                        return true;
                    }
                });
       /* bottomNavigation.setOnSelectedItemChangeListener(new OnSelectedItemChangeListener() {
            @Override
            public void onSelectedItemChanged(int i) {
                switch (i)
                {
                    case R.id.details:
                    {
                        Log.i("in ","details");

                        getActivity().finish();
                        break;
                    }
                    case R.id.scan:
                    {
                        Log.i("in ","scan");
                        getFragmentManager().popBackStack();
                        break;
                    }

                    case R.id.list:
                    {
                        break;
                    }

                }

            }
        });*/
        final Toolbar toolbar = (Toolbar)view.findViewById(R.id.toolbar);
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
        if (actionBar != null) {
            actionBar.setTitle("Doctor's List");
            toolbar.setTitle("Doctor's List");
        }
        for (int i = 0; i <10 ; i++) {
            Doctor doctor=new Doctor();
            doctor.setDoctor_name("Dr.Martin Benedict");
            doctor.setAddress("Sgfhdfvghfghgfdbd.2a");
            doctorArrayList.add(doctor);
        }
        recyclerView=(RecyclerView)view.findViewById(R.id.rv);
        adapter = new DoctorAdapter(getActivity(),doctorArrayList);
        recyclerView.setAdapter(adapter);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        // rv.addItemDecoration(new DividerItemDecoration(getActivity(),GridLayoutManager.HORIZONTAL));
        RecyclerView.LayoutManager lmanager=new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(lmanager);
        view.setFocusableInTouchMode(true);
        view.requestFocus();
        view.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (event.getAction() == KeyEvent.ACTION_DOWN) {
                    if (keyCode == KeyEvent.KEYCODE_BACK) {
                        getActivity().finish();
                        return true;
                    }
                }
                return false;
            }
        });
        return view;
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
