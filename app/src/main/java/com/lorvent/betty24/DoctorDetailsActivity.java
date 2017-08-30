package com.lorvent.betty24;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ImageSpan;
import android.util.Log;
import android.view.MenuItem;

import com.lorvent.betty24.fragment.HolidaysFragment;
import com.lorvent.betty24.fragment.NewsFragment;
import com.lorvent.betty24.fragment.ServicesFragment;

import java.util.ArrayList;
import java.util.List;

public class DoctorDetailsActivity extends AppCompatActivity {
    Adapter adapter;
    TabLayout tabLayout;
    private void setupViewPager(ViewPager viewPager) {
        adapter = new Adapter(getSupportFragmentManager(),this);
        adapter.addFragment(new NewsFragment(), "News");
        adapter.addFragment(new ServicesFragment(), "Services");
        adapter.addFragment(new HolidaysFragment(), "Holidays");
        viewPager.setAdapter(adapter);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_details);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar!=null)
        {
            actionBar.setTitle("Dr.Martin Benedict");
            actionBar.setHomeButtonEnabled(true);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        tabLayout = (TabLayout)findViewById(R.id.tab_layout);
        final ViewPager viewPager = (ViewPager)findViewById(R.id.pager);
        setupViewPager(viewPager);
        //tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        tabLayout.setupWithViewPager(viewPager);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
    }
    public static  class Adapter extends FragmentStatePagerAdapter {
        //int mNumOfTabs;

        private final List<Fragment> mFragments = new ArrayList<>();
        private final List<String> mFragmentTitles = new ArrayList<>();
        Context context;

        Adapter(FragmentManager fm, Context context) {
            super(fm);
            this.context=context;

        }

        void addFragment(Fragment fragment, String title) {
            mFragments.add(fragment);
            mFragmentTitles.add(title);
        }

        @Override
        public Fragment getItem(int position) {

            return mFragments.get(position);
        }
        @Override
        public int getCount() {
            return mFragments.size();
        }
        @Override
        public CharSequence getPageTitle(int position) {

            int[]tabIcon={R.mipmap.news,R.mipmap.ic_settings_white_24dp,R.mipmap.sunny};
            Drawable image = context.getResources().getDrawable(tabIcon[position]);
            image.setBounds(0, 0, image.getIntrinsicWidth(), image.getIntrinsicHeight());
            SpannableString sb = new SpannableString("    "+mFragmentTitles.get(position));
            ImageSpan imageSpan = new ImageSpan(image, ImageSpan.ALIGN_BOTTOM);
            sb.setSpan(imageSpan, 0, 1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            return sb;
            //return mFragmentTitles.get(position);
        }
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId()==android.R.id.home)
        {
            Log.i("option","selected");
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
