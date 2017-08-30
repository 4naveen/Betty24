package com.lorvent.betty24.fragment;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.lorvent.betty24.R;
import com.lorvent.betty24.fragment.ProfileFragment;

import cn.pedant.SweetAlert.SweetAlertDialog;


/**
 * A simple {@link Fragment} subclass.
 */
public class InformationFragment extends Fragment {
    Button next;
    public InformationFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_information, container, false);
        next=(Button)view.findViewById(R.id.next);
        ActionBar actionBar=((AppCompatActivity)getActivity()).getSupportActionBar();
        setHasOptionsMenu(true);
        if (actionBar != null) {

          actionBar.setIcon(R.mipmap.logo);
            actionBar.setTitle("");
        }
         next.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              new MaterialDialog.Builder(getActivity())
                      .title("Terms and Conditions ")
                      .customView(R.layout.info_dialog, true)
                      .positiveText("ok")
                      .positiveColorRes(R.color.colorPrimary)
                      .onPositive(new MaterialDialog.SingleButtonCallback() {
                          @Override
                          public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {


                              Fragment fragment1=new ProfileFragment();
                              FragmentTransaction transaction=getFragmentManager().beginTransaction();
                              transaction.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left, R.anim.enter_from_left, R.anim.exit_to_right);
                              transaction.replace(R.id.frame,fragment1);
                              transaction.addToBackStack(null);
                              transaction.commit();
                          }
                      })
                      .show();
          }
      });
        view.setFocusableInTouchMode(true);
        view.requestFocus();
        view.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (event.getAction() == KeyEvent.ACTION_DOWN) {
                    if (keyCode == KeyEvent.KEYCODE_BACK) {
                        new SweetAlertDialog(getActivity(), SweetAlertDialog.WARNING_TYPE)
                                .setTitleText("Are you sure?")
                                .setContentText("you want to exit!")
                                .setConfirmText("Yes,Exit!")
                                .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                    @Override
                                    public void onClick(SweetAlertDialog sDialog) {
                                        sDialog.dismissWithAnimation();
                                        getActivity().finish();
                                    }
                                })
                                .setCancelText("No,Stay!")
                                .show();
                        return true;
                    }
                }
                return false;
            }
        });
        return view;
    }

}
