package com.menu_ui.menu_ui;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;

public class passengerUI extends AppCompatActivity {

    private static final int NUM_PAGES = 5;
    private ViewPager mPager;
    private ViewPager pager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_passenger_ui);
        ViewPager pager = (ViewPager) findViewById(R.id.viewPager);
        ViewPager mPager = (ViewPager) findViewById(R.id.viewPager);
        pager.setAdapter(new MyPagerAdapter());
        pager.setCurrentItem(3);
        mPager.setPageTransformer(true, new ZoomOutPageTransformer());
    }

    private class MyPagerAdapter extends PagerAdapter {

        public int getCount() {
            return 2;
        }

        public boolean isViewFromObject(View view, Object o) {
            return o==view;
        }
        @Override
        public void destroyItem(View container, int position, Object object) {
            ((ViewPager) container).removeView((View) object);
        }


        public Object instantiateItem(View collection, int position) {
            LayoutInflater inflater = (LayoutInflater)  collection.getContext().
                    getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            int resId = 0;
            switch(position) {

                case 0:
                    resId = showFirstFragment();
                    break;
                case 1:
                    resId = showSecondFragment();
                    break;

            }
            View view = inflater.inflate(resId, null);
            ((ViewPager) collection).addView(view, 0);
            return view;
        }

    }

    public int showFirstFragment(){
        int resId;
        resId = R.layout.passenger_ui_firstfrag;
        return resId;
    }
    public int showSecondFragment(){
        int resId;
        resId = R.layout.passenger_ui_secondfrag;
        return resId;
    }

    //flip page animation
    public class ZoomOutPageTransformer implements ViewPager.PageTransformer {
        private static final float MIN_SCALE = 0.85f;
        private static final float MIN_ALPHA = 0.5f;

        public void transformPage(View view, float position) {
            int pageWidth = view.getWidth();
            int pageHeight = view.getHeight();

            if (position < -1) { // [-Infinity,-1)
                // This page is way off-screen to the left.
                view.setAlpha(0);

            } else if (position <= 1) { // [-1,1]
                // Modify the default slide transition to shrink the page as well
                float scaleFactor = Math.max(MIN_SCALE, 1 - Math.abs(position));
                float vertMargin = pageHeight * (1 - scaleFactor) / 2;
                float horzMargin = pageWidth * (1 - scaleFactor) / 2;
                if (position < 0) {
                    view.setTranslationX(horzMargin - vertMargin / 2);
                } else {
                    view.setTranslationX(-horzMargin + vertMargin / 2);
                }

                // Scale the page down (between MIN_SCALE and 1)
                view.setScaleX(scaleFactor);
                view.setScaleY(scaleFactor);

                // Fade the page relative to its size.
                view.setAlpha(MIN_ALPHA +
                        (scaleFactor - MIN_SCALE) /
                                (1 - MIN_SCALE) * (1 - MIN_ALPHA));

            } else { // (1,+Infinity]
                // This page is way off-screen to the right.
                view.setAlpha(0);
            }
        }
    }



}
