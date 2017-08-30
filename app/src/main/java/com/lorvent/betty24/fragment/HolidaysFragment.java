package com.lorvent.betty24.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lorvent.betty24.R;
import com.lorvent.betty24.adapters.HolidayAdapter;
import com.lorvent.betty24.model.Holiday;
import com.lorvent.betty24.model.Substitute;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class HolidaysFragment extends Fragment {
    ArrayList<Holiday> holidayArrayList;
    ArrayList<Substitute> substituteArrayList;

    RecyclerView recyclerView;
    HolidayAdapter adapter;

    public HolidaysFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_holidays, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerview);
        holidayArrayList = new ArrayList<>();
        substituteArrayList = new ArrayList<>();
        setHasOptionsMenu(true);
        for (int i = 0; i < 10; i++) {
            Holiday holiday = new Holiday();
            holiday.setDate("15.06.2017 to 17.06.2017");
            holidayArrayList.add(holiday);
        }

        Substitute substitute1 = new Substitute();
        Substitute substitute2 = new Substitute();

        substitute1.setName("Dr.med.Axel Schmidt-Wetter");
        substitute1.setAddrees("Kaisedtrstr. 26, 7645646 Karlsruhe");
        substitute2.setName("DR.Frau Angela Lingg");
        substitute2.setAddrees("Neuensteinstraße 1476227 Karlsruhe");

        substituteArrayList.add(substitute1);
        substituteArrayList.add(substitute2);


        adapter = new HolidayAdapter(getActivity(), holidayArrayList, substituteArrayList);
        recyclerView.setAdapter(adapter);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        // rv.addItemDecoration(new DividerItemDecoration(getActivity(),GridLayoutManager.HORIZONTAL));
        RecyclerView.LayoutManager lmanager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(lmanager);
        return view;

    }

}
