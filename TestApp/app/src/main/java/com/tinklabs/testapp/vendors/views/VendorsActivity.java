package com.tinklabs.testapp.vendors.views;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.tinklabs.testapp.R;
import com.tinklabs.testapp.utils.CategoryUtils;


public class VendorsActivity extends AppCompatActivity {
    private PagerAdapter mPagerAdapter;
    private ViewPager mViewPager;
    private TabLayout mTabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vendors);

        mPagerAdapter = new PagerAdapter(getSupportFragmentManager(), this);
        mViewPager = (ViewPager) findViewById(R.id.viewpager);
        mViewPager.setAdapter(mPagerAdapter);
        mTabLayout = (TabLayout) findViewById(R.id.sliding_tabs);
        mTabLayout.setupWithViewPager(mViewPager);
        mTabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
    }

    private static class PagerAdapter extends FragmentPagerAdapter {
        private Context context;

        public PagerAdapter(FragmentManager fm, Context context) {
            super(fm);
            this.context = context;
        }

        @Override
        public Fragment getItem(int position) {
            return VendorsFragment.newInstance(position);
        }

        @Override
        public int getCount() {
            return CategoryUtils.getCount();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return CategoryUtils.getCategoryName(position);
        }
    }
}
