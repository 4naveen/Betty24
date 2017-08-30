package com.lorvent.betty24.fragment;


import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lorvent.betty24.R;
import com.lorvent.betty24.adapters.NewsAdapter;
import com.lorvent.betty24.model.News;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class NewsFragment extends Fragment {
    private LinearLayoutManager layoutManager;
    RecyclerView recyclerView;
    ArrayList<News> newsArrayList;
    public NewsFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_news, container, false);
        setHasOptionsMenu(true);
        recyclerView=(RecyclerView)view.findViewById(R.id.recyclerview);
        newsArrayList=new ArrayList<>();
        News news1=new News();
        News news2=new News();
        News news3=new News();
        News news4=new News();
        News news5=new News();
        News news6=new News();
        News news7=new News();
        News news8=new News();
        news1.setHeading("Ambulente Opreations");
        news2.setHeading("Nasenen Opreations");
        news3.setHeading("Hi-tech Billdiagonostik");
        news4.setHeading("Ngdgfhseug Opreations");
        news5.setHeading("Heart Bypass surgery");
        news6.setHeading("Micro surgery");
        news7.setHeading("Key hole surgery");
        news8.setHeading("Gender Reassignment");

        news1.setContent("A medical operation in which a developing baby is removed from a woman’s body so that it is not born alive");
        news2.setContent("A medical operation to repair an artery (=tube carrying blood around the body) that has become blocked or too narrow");
        news3.setContent("An operation for gastroesophageal reflux performed through a thoracic incision; the fundus is wrapped 270 degrees around the circumference of the esophagus, leaving its posterior wall free  extraction of a cataract through a corneal incision without cutting the iris. ");
        news4.setContent("Medical operation in which blood is directed around a blocked blood vessel in the heart in order to operate on it");
        news5.setContent("A medical operation in which a baby is born by being removed from a woman’s body through a cut made in her abdomen, because the baby cannot be born in the normal way.");
        news6.setContent("A medical operation in which part of the colon is removed and a hole is made in the stomach through which solid waste can leave the body");
        news7.setContent("A medical operation in which a narrow tube called a laparoscope is put into a part of the body in order to operate on it or examine it");
        news8.setContent("Medical operations that are done using very small pieces of equipment and a very powerful microscopea medical operation in which skin is taken from one part of someone’s body and put on another part of their body where skin has been damaged");

        newsArrayList.add(news1);
        newsArrayList.add(news2);
        newsArrayList.add(news3);
        newsArrayList.add(news4);
        newsArrayList.add(news5);
        newsArrayList.add(news6);
        newsArrayList.add(news7);
        newsArrayList.add(news8);
       /* Configuration config = getResources().getConfiguration();
        if (config.smallestScreenWidthDp >= 600) {
            gaggeredGridLayoutManager = new StaggeredGridLayoutManager(3,1);
        }
        else {
            gaggeredGridLayoutManager = new StaggeredGridLayoutManager(2,1);
        }*/

        RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(new NewsAdapter(getActivity(), newsArrayList));
        return view;
    }

}
