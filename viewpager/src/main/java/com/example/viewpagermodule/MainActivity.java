package com.example.viewpagermodule;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    public static final String SP_CONFIG    = "sp_config";
    public static final String IS_FIRST_RUN = "is_first_run";
    private ViewPager mPager;
    private ArrayList<View> mList;
    private Button mButton;
    int [] pics ={R.drawable.adware_style_applist,R.drawable.adware_style_banner,R.drawable.adware_style_creditswall};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferences sp = getSharedPreferences(SP_CONFIG,MODE_PRIVATE);
        boolean is_first_run = sp.getBoolean(IS_FIRST_RUN, true);
        if(!is_first_run){
            Intent intent = new Intent(MainActivity.this,SecondActivity.class);
            startActivity(intent);
            finish();
        }else {
            setContentView(R.layout.activity_main);
            mList = new ArrayList<>();
            mPager = (ViewPager) findViewById(R.id.vp_show);
            for (int i = 0; i <pics.length ; i++) {
                ImageView iv = new ImageView(this);
                iv.setBackgroundResource(pics[i]);
                mList.add(iv);
            }
            mPager.setAdapter(new MyAdapter(mList));
            mPager.addOnPageChangeListener(listener);
            mPager.setPageTransformer(true,new DepthPageTransformer());
            mButton = (Button) findViewById(R.id.btn_skin);
             sp = getSharedPreferences(SP_CONFIG,MODE_PRIVATE);
            SharedPreferences.Editor edit = sp.edit();
            edit.putBoolean(IS_FIRST_RUN,false);
            edit.commit();
            mButton.setOnClickListener(this);
        }

    }
    ViewPager.OnPageChangeListener listener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
        if(position == pics.length-1){
            mButton.setVisibility(View.VISIBLE);
        }else {
            mButton.setVisibility(View.INVISIBLE);
        }
        }

        @Override
        public void onPageScrollStateChanged(int state) {

         }
    };

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(MainActivity.this,SecondActivity.class);
        startActivity(intent);
        finish();
    }

    public class DepthPageTransformer implements ViewPager.PageTransformer {
        private static final float MIN_SCALE = 0.75f;

        public void transformPage(View view, float position) {
            int pageWidth = view.getWidth();

            if (position < -1) { // [-Infinity,-1)
                // This page is way off-screen to the left.
                view.setAlpha(0);

            } else if (position <= 0) { // [-1,0]
                // Use the default slide transition when moving to the left page
                view.setAlpha(1);
                view.setTranslationX(0);
                view.setScaleX(1);
                view.setScaleY(1);

            } else if (position <= 1) { // (0,1]
                // Fade the page out.
                view.setAlpha(1 - position);

                // Counteract the default slide transition
                view.setTranslationX(pageWidth * -position);

                // Scale the page down (between MIN_SCALE and 1)
                float scaleFactor = MIN_SCALE
                        + (1 - MIN_SCALE) * (1 - Math.abs(position));
                view.setScaleX(scaleFactor);
                view.setScaleY(scaleFactor);

            } else { // (1,+Infinity]
                // This page is way off-screen to the right.
                view.setAlpha(0);
            }
        }
    }

}
