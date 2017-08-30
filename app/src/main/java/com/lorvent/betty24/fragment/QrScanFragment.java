package com.lorvent.betty24.fragment;


import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.google.zxing.Result;
import com.lorvent.betty24.AppSession;
import com.lorvent.betty24.QrCodeActivity;
import com.lorvent.betty24.R;
import com.ss.bottomnavigation.BottomNavigation;
import com.ss.bottomnavigation.events.OnSelectedItemChangeListener;

import cn.pedant.SweetAlert.SweetAlertDialog;
import me.dm7.barcodescanner.zxing.ZXingScannerView;

/**
 * A simple {@link Fragment} subclass.
 */
public class QrScanFragment extends Fragment implements ZXingScannerView.ResultHandler{

    private static final String TAG = "BarcodeMain";
    public static final int MY_PERMISSIONS_REQUEST_CAMERA = 101;
    private ZXingScannerView mScannerView;
   // BottomNavigation bottomNavigation;
    BottomNavigationView bottomNavigation;
    View view;
    public QrScanFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

         view=inflater.inflate(R.layout.fragment_qr_scan, container, false);
        mScannerView = new ZXingScannerView(getActivity());

        if (ContextCompat.checkSelfPermission(getActivity(),
                Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(getActivity(),
                    new String[]{Manifest.permission.CAMERA},
                    MY_PERMISSIONS_REQUEST_CAMERA);
        }
        else {
            ViewGroup contentFrame = (ViewGroup)view.findViewById(R.id.content_frame);
            mScannerView = new ZXingScannerView(getActivity());
            contentFrame.addView(mScannerView);
            mScannerView.setResultHandler(QrScanFragment.this); // Register ourselves as a handler for scan results.
            mScannerView.startCamera();
        }
        bottomNavigation=(BottomNavigationView)view.findViewById(R.id.bottom_navigation);

        if (AppSession.isSeen)
        {
            bottomNavigation.inflateMenu(R.menu.bottom_navigation_main2);
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

                                }
                                case R.id.action_music:
                                {
                                    Fragment fragment1=new ListofDoctorsFragment();
                                    FragmentTransaction trans1=getFragmentManager().beginTransaction();
                                    trans1.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left, R.anim.enter_from_left, R.anim.exit_to_right);
                                    trans1.replace(R.id.frame,fragment1);
                                    trans1.addToBackStack(null);
                                    trans1.commit();
                                    break;
                                }

                            }
                            return true;
                        }
                    });
        }
        else {
            bottomNavigation.inflateMenu(R.menu.bottom_navigation_main1);
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

                                }

                            }
                            return true;
                        }
                    });
        }

       // bottomNavigation.setDefaultItem(1);

       /* bottomNavigation.setOnSelectedItemChangeListener(new OnSelectedItemChangeListener() {
            @Override
            public void onSelectedItemChanged(int i) {
                switch (i)
                {
                    case R.id.detail:
                    {
                        Log.i("in ","details");
                      //  mScannerView.stopCamera();

                        getActivity().finish();
                        break;
                    }
                    case R.id.scan:
                    {
                        break;
                    }
                    case R.id.list:
                    {
                        break;
                    }
                }
            }
        });*/
        Menu menu=bottomNavigation.getMenu();
        MenuItem menuItem=menu.findItem(R.id.action_schedules);
        menuItem.setChecked(true);
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
    public void handleResult(Result result) {
        // Do something with the result here
        Log.v(TAG, result.getText()); // Prints scan results
        Log.v(TAG, result.getBarcodeFormat().toString()); // Prints the scan format (qrcode, pdf417 etc.)
        if (result!=null)
        {
            new SweetAlertDialog(getActivity(), SweetAlertDialog.SUCCESS_TYPE)
                    .setTitleText("Success!")
                    .setContentText("Code Successfully Scanned !")
                    .setConfirmText("Proceed")
                    .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                        @Override
                        public void onClick(SweetAlertDialog sweetAlertDialog) {
                            Fragment fragment1=new ListofDoctorsFragment();
                            FragmentTransaction trans1=getFragmentManager().beginTransaction();
                            trans1.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left, R.anim.enter_from_left, R.anim.exit_to_right);
                            trans1.replace(R.id.frame,fragment1);
                            trans1.addToBackStack(null);
                            trans1.commit();
                            sweetAlertDialog.dismiss();
                        }
                    })
                    .show();
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        mScannerView.stopCamera();
    }

    @Override
    public void onResume() {
        super.onResume();

            mScannerView.setResultHandler(this);
            mScannerView.startCamera();

       /* mScannerView.setResultHandler(this);
        mScannerView.startCamera();*/
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_CAMERA: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                         Log.i("in permission","access");
                    ViewGroup contentFrame = (ViewGroup)view.findViewById(R.id.content_frame);
                    mScannerView = new ZXingScannerView(getActivity());
                    contentFrame.addView(mScannerView);
                    mScannerView.setResultHandler(QrScanFragment.this); // Register ourselves as a handler for scan results.
                    mScannerView.startCamera();
                } else {

                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                }
                return;
            }

            // other 'case' lines to check for other
            // permissions this app might request
        }
    }
}
