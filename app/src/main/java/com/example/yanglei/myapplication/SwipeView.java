//package com.example.yanglei.myapplication;
//
//import android.os.Bundle;
//import android.support.v4.app.Fragment;
//import android.support.v4.app.FragmentManager;
//import android.support.v4.app.FragmentPagerAdapter;
//import android.support.v4.app.FragmentTransaction;
//import android.support.v4.view.ViewPager;
//import android.support.v7.app.ActionBar;
//import android.support.v7.app.AppCompatActivity;
//import android.support.v7.widget.Toolbar;
//import android.util.Log;
//import android.view.View;
//
//
//import java.util.Locale;
//
//import static com.example.yanglei.myapplication.RegisterFirstPage.canswip;
//import static com.example.yanglei.myapplication.RegisterFirstPage.name;
//import static com.example.yanglei.myapplication.RegisterFirstPage.pw;
//
//
//public class SwipeView extends AppCompatActivity implements ActionBar.TabListener{
//
//    SectionsPagerAdapter mSectionsPagerAdapter;
//
//    static ViewPager mViewPager;
//
//    static final String LOG_TAG = "SlidingTabsBasicFragment";
//
//    private SlidingTabLayout mSlidingTabLayout;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_register);
//
//        Toolbar topToolBar = (Toolbar)findViewById(R.id.toolbar);
//        setSupportActionBar(topToolBar);
//
//        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());
//
//        // Set up the ViewPager with the sections adapter.
//        mViewPager = (ViewPager) findViewById(R.id.pager);
//        mViewPager.setAdapter(mSectionsPagerAdapter);
//
//        mSlidingTabLayout = (SlidingTabLayout) findViewById(R.id.sliding_tabs);
//        mSlidingTabLayout.setViewPager(mViewPager);
//
//        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
//            @Override
//            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
//                if(RegisterFirstPage.CheckEmpty(name,pw) || !RegisterFirstPage.canswip){
//                    Log.i("Scrolled: ", " Cannot move to next page" + canswip);
//                    mViewPager.setCurrentItem(0);
//                }
//            }
//
//            @Override
//            public void onPageSelected(int position) {
//                if(RegisterFirstPage.CheckEmpty(name,pw) || !RegisterFirstPage.canswip){
//                    Log.i("Selected", " Cannot move to next page" + canswip);
//                    mViewPager.setCurrentItem(0);
//                }
//            }
//
//            @Override
//            public void onPageScrollStateChanged(int state) {
//
//            }
//        });
//    }
//
//
//
//    @Override
//    public void onTabSelected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
//        mViewPager.setCurrentItem(tab.getPosition());
//    }
//
//    @Override
//    public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
//
//    }
//
//    @Override
//    public void onTabReselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
//
//    }
//
//    public static void jumpTopage(View view) {
//        mViewPager.setCurrentItem(1);
//    }
//
//    public class SectionsPagerAdapter extends FragmentPagerAdapter {
//
//        public SectionsPagerAdapter(FragmentManager fm) {
//            super(fm);
//        }
//
//        @Override
//        public Fragment getItem(int position) {
//            Fragment fragment = null;
//            switch (position){
//                case 0:
//                    fragment = new RegisterFirstPage();
//                    break;
//                case 1:
//                    fragment = new RegisterForthPage();
//                    break;
//            }
//            return fragment;
//        }
//
//        @Override
//        public int getCount() {
//            return 2;
//        }
//
//        @Override
//        public CharSequence getPageTitle(int position) {
//            Locale l = Locale.getDefault();
//            switch (position) {
//                case 0:
//                    return "Personal Detail".toUpperCase(l);
//                case 1:
//                    return "Camera Verification".toUpperCase(l);
//            }
//            return null;
//        }
//    }
//
//}
