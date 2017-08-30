package com.lorvent.betty24.fragment;


import android.app.DatePickerDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.codetroopers.betterpickers.calendardatepicker.CalendarDatePickerDialogFragment;
import com.codetroopers.betterpickers.datepicker.DatePickerBuilder;
import com.codetroopers.betterpickers.datepicker.DatePickerDialogFragment;
import com.lorvent.betty24.R;
import com.lorvent.betty24.fragment.DetailsFragment;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;


import static android.content.Context.MODE_PRIVATE;


/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileFragment extends Fragment  implements CalendarDatePickerDialogFragment.OnDateSetListener{
    Spinner salutation;
    ArrayList<String> salutationList;
    int next_day,next_month,next_year;
    Button save;
    TextInputLayout input_first_name,input_last_name,input_email,input_phone,input_pin,input_insurance,input_dob;
    EditText  firstNasme,lastName, email,pin,phone,dob,insurance;
     SimpleDateFormat simpleDateFormat;
    private static final String FRAG_TAG_DATE_PICKER = "fragment_date_picker_name";

    public ProfileFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_profile, container, false);
        salutation=(Spinner)view.findViewById(R.id.salutation);
        save=(Button)view.findViewById(R.id.save);
        setHasOptionsMenu(true);
        //input_salutaion=(TextInputLayout)view.findViewById(R.id.input_layout_salutation);
        input_first_name=(TextInputLayout)view.findViewById(R.id.input_layout_first_name);
        input_last_name=(TextInputLayout)view.findViewById(R.id.input_layout_last_name);
        input_email=(TextInputLayout)view.findViewById(R.id.input_layout_email);
        input_phone=(TextInputLayout)view.findViewById(R.id.input_layout_phone);
        input_pin=(TextInputLayout)view.findViewById(R.id.input_layout_pin);
        input_insurance=(TextInputLayout)view.findViewById(R.id.input_layout_insurance);
        input_dob=(TextInputLayout)view.findViewById(R.id.input_layout_dob);

        firstNasme = (EditText) view.findViewById(R.id.first_name);
        lastName = (EditText)view.findViewById(R.id.last_name);
        email = (EditText)view.findViewById(R.id.email);
        pin = (EditText)view.findViewById(R.id.pin);
        phone = (EditText)view.findViewById(R.id.phone);

        insurance = (EditText)view.findViewById(R.id.insurance);
        dob = (EditText)view. findViewById(R.id.dob);
        next_year= Calendar.getInstance().get(Calendar.YEAR);
        next_month=Calendar.getInstance().get(Calendar.MONTH);
        next_day=Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
        salutationList=new ArrayList<>();
        salutationList.add("Mr.");
        salutationList.add("Mrs.");
        ActionBar actionBar=((AppCompatActivity)getActivity()).getSupportActionBar();
        setHasOptionsMenu(true);

        if (actionBar != null) {
            //actionBar.setTitle("Detail Entry Form");
            actionBar.setIcon(R.mipmap.actionbar_logo1);
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            dob.setShowSoftInputOnFocus(false);

        }
        getPreferenceValue();
        ArrayAdapter<String> salutationArrayAdapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_item,salutationList);
        salutationArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        salutation.setAdapter(salutationArrayAdapter);
        dob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InputMethodManager imm = (InputMethodManager)getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(dob.getWindowToken(), 0);
                DatePickerDialog d = new DatePickerDialog(getActivity(), dateListener,next_year,next_month,next_day);
                d.show();
        /*        CalendarDatePickerDialogFragment cdp = new CalendarDatePickerDialogFragment()
                        .setOnDateSetListener(new CalendarDatePickerDialogFragment.OnDateSetListener() {
                            @Override
                            public void onDateSet(CalendarDatePickerDialogFragment dialog, int year, int monthOfYear, int dayOfMonth) {

                            }
                        });
                cdp.show(getFragmentManager(), FRAG_TAG_DATE_PICKER);*/


            }
        });
        dob.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus)
                {

                    InputMethodManager imm = (InputMethodManager)getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(dob.getWindowToken(), 0);
                    DatePickerDialog d = new DatePickerDialog(getActivity(), dateListener,next_year,next_month,next_day);
                    d.show();


    /*                CalendarDatePickerDialogFragment cdp = new CalendarDatePickerDialogFragment()
                            .setOnDateSetListener(new CalendarDatePickerDialogFragment.OnDateSetListener() {
                                @Override
                                public void onDateSet(CalendarDatePickerDialogFragment dialog, int year, int monthOfYear, int dayOfMonth) {

                                }
                            });
                    cdp.show(getFragmentManager(), FRAG_TAG_DATE_PICKER);*/


                }
            }
        });

        firstNasme.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                input_first_name.setError("");

            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        lastName.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                input_last_name.setError("");

            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        email.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                input_email.setError("");

            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        pin.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                input_pin.setError("");

            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        phone.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                input_phone.setError("");

            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        insurance.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                input_insurance.setError("");

            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        dob.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                input_dob.setError("");

            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InputMethodManager imm = (InputMethodManager)getActivity(). getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(v.getWindowToken(), 0);

                if (firstNasme.getText().toString().trim().isEmpty()) {
                    input_first_name.setError("please enter first name");
                    return;
                }
                else if (lastName.getText().toString().trim().isEmpty()) {
                    input_last_name.setError("please enter last name");
                    return;
                }
                else if (dob.getText().toString().trim().isEmpty()) {
                    input_dob.setError("please enter Date of Birth");
                    return;
                }
                else if (pin.getText().toString().trim().isEmpty()) {
                    input_pin.setError("please enter pin");
                    return;
                }
                else if (insurance.getText().toString().trim().isEmpty()) {
                    input_email.setError("please enter insurance");
                    return;
                }
                else if (!email.getText().toString().trim().isEmpty())
                {
                    if (!isValidEmail(email.getText().toString()))
                    {
                        input_email.setError("Please enter valid  email");
                        return;
                    }
                }
                else if (phone.getText().toString().trim().isEmpty()) {
                    input_phone.setError("please enter phone number");
                    return;
                }
                else {
                    //do nothing
                }
                saveDataPreference();
                Fragment fragment1=new DetailsFragment();
                FragmentTransaction trans1=getFragmentManager().beginTransaction();
                trans1.replace(R.id.frame,fragment1);
                trans1.addToBackStack(null);
                trans1.commit();
            }
        });
        view.setFocusableInTouchMode(true);
        view.requestFocus();
        view.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (event.getAction() == KeyEvent.ACTION_DOWN) {
                    if (keyCode == KeyEvent.KEYCODE_BACK) {
                      //getFragmentManager().popBackStack();
                        Fragment fragment1=new InformationFragment();
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
    DatePickerDialog.OnDateSetListener dateListener = new DatePickerDialog.OnDateSetListener() {

        public void onDateSet(DatePicker view, int year,
                              int monthOfYear, int dayOfMonth) {
            next_year = year;
            next_month= monthOfYear;
            next_day = dayOfMonth;
           // String text_date = year + "-" + next_day + "-" + (next_month + 1);
            Date date = new Date(year - 1900, monthOfYear, dayOfMonth);
            simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy", Locale.ENGLISH);
            String formated_date = simpleDateFormat.format(date);
            dob.setText(formated_date);
            dob.clearFocus();
        }
    };
    public static boolean isValidEmail(CharSequence target) {
        if (target == null) {
            return false;
        } else {
            return android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
        }
    }

    private void saveDataPreference() {
        SharedPreferences preferences = getActivity().getSharedPreferences("My_pref",MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("salutation",salutation.getSelectedItem().toString());
        editor.putString("first_name",firstNasme.getText().toString());
        editor.putString("last_name",lastName.getText().toString());
        editor.putString("email",email.getText().toString());
        editor.putString("phone",phone.getText().toString());
        editor.putString("insurance",insurance.getText().toString());
        editor.putString("pin",pin.getText().toString());
        editor.putString("dob",dob.getText().toString());
        editor.apply();
        editor.commit();
    }
    private void getPreferenceValue() {
        SharedPreferences preferences = getActivity().getSharedPreferences("My_pref",MODE_PRIVATE);
        //salutation.set(preferences.getString("salutation", null));
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

    @Override
    public void onDateSet(CalendarDatePickerDialogFragment dialog, int year, int monthOfYear, int dayOfMonth) {

    }
}
