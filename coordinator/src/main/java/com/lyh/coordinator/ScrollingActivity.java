package com.lyh.coordinator;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ScrollingActivity extends AppCompatActivity {

    @BindView(R.id.imageview)
    ImageView imageview;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.toolbar_layout)
    CollapsingToolbarLayout toolbarLayout;
    @BindView(R.id.app_bar)
    AppBarLayout appBar;
    @BindView(R.id.viewpager)
    ViewPager viewpager;
    @BindView(R.id.tabLayout)
    TabLayout tabLayout;

    private ActionBar mActionbar;
    private final String[] mTitles = {"Android", "iOS", "前端", "拓展资源"};
    private ViewPager viewPager;
    private ArrayList<Fragment> fragmentList;
    private int[] color = new int[]{
            R.color.colorOne,
            R.color.colorTwo,
            R.color.colorThree,
            R.color.colorFour};
    private int[] mImageArray = new int[]{
            R.mipmap.bg_android,
            R.mipmap.bg_ios,
            R.mipmap.bg_js,
            R.mipmap.bg_other};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrolling);
        ButterKnife.bind(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        initFragment();
        initViewPager();
        initToolbar();
        setupTabLayout(mImageArray, color);
    }

    private void initToolbar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mActionbar = getSupportActionBar();
    }

    private void initFragment() {
        fragmentList = new ArrayList<>();
        for (int i = 0; i < mTitles.length; i++) {
            //fragmentList.add(ScrollingFragment.getInstance(mTitles[i]));
        }
    }

    private void initViewPager() {
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        viewPager.setOffscreenPageLimit(4);
        viewPager.setAdapter(new MyPagerAdapter(getSupportFragmentManager(), fragmentList, mTitles));
    }

    private void setupTabLayout(final int[] imageArray, final int[] colorArray) {
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                imageview.startAnimation(AnimationUtils.loadAnimation(ScrollingActivity.this, R.anim.anim_dismiss));
                imageview.setImageResource(imageArray[tab.getPosition()]);
                if (colorArray != null) {
                    toolbarLayout.setContentScrimColor(
                            ContextCompat.getColor(
                                    ScrollingActivity.this, colorArray[tab.getPosition()]));
                }
                imageview.setAnimation(AnimationUtils.loadAnimation(ScrollingActivity.this, R.anim.anim_show));
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        tabLayout.setupWithViewPager(viewPager);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_scrolling, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    class MyPagerAdapter extends FragmentPagerAdapter {
        private ArrayList<Fragment> mFragments = new ArrayList<>();
        private String[] mTitles;

        public MyPagerAdapter(FragmentManager fm, ArrayList<Fragment> mFragments, String[] mTitles) {
            super(fm);
            this.mFragments = mFragments;
            this.mTitles = mTitles;
        }

        @Override
        public int getCount() {
            return mFragments.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mTitles[position];
        }

        @Override
        public Fragment getItem(int position) {
            return mFragments.get(position);
        }
    }
}
