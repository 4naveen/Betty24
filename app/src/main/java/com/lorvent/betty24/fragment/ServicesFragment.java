package com.lorvent.betty24.fragment;


import android.content.Context;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import com.lorvent.betty24.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class ServicesFragment extends Fragment {
     TextView textView1,textView2,textView3,textView4,textView5,textView6,textView7,textView8,textView9,textView10,textView11;
    Animation animFadein;
    public ServicesFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_services, container, false);
       // textView1=(TextView)view.findViewById(R.id.title);
        textView2=(TextView)view.findViewById(R.id.text1);
        textView3=(TextView)view.findViewById(R.id.text2);
        textView4=(TextView)view.findViewById(R.id.text3);
        textView5=(TextView)view.findViewById(R.id.text4);
        textView6=(TextView)view.findViewById(R.id.text5);
        textView7=(TextView)view.findViewById(R.id.text6);
        textView8=(TextView)view.findViewById(R.id.text7);
        textView9=(TextView)view.findViewById(R.id.text8);
        textView10=(TextView)view.findViewById(R.id.text9);
        textView11=(TextView)view.findViewById(R.id.text10);

       // textView1.setPaintFlags(textView1.getPaintFlags()| Paint.UNDERLINE_TEXT_FLAG);

        Typeface tf = Typeface.createFromAsset(getActivity().getAssets(),"fonts/roboto_regular.ttf");
        textView2.setTypeface(tf);
        textView3.setTypeface(tf);
        textView4.setTypeface(tf);
        textView5.setTypeface(tf);
        textView6.setTypeface(tf);
        textView8.setTypeface(tf);
        textView7.setTypeface(tf);
        textView9.setTypeface(tf);
        textView10.setTypeface(tf);
        textView11.setTypeface(tf);

        animFadein = AnimationUtils.loadAnimation(getActivity(),
                R.anim.fade_in);
        textView2.startAnimation(animFadein);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        animFadein = AnimationUtils.loadAnimation(getActivity(),
                R.anim.fade_in);
        textView2.startAnimation(animFadein);
        textView3.startAnimation(animFadein);
        textView4.startAnimation(animFadein);
        textView5.startAnimation(animFadein);
        textView6.startAnimation(animFadein);
        textView8.startAnimation(animFadein);
        textView7.startAnimation(animFadein);
        textView9.startAnimation(animFadein);
        textView10.startAnimation(animFadein);
        textView11.startAnimation(animFadein);

    }


}
