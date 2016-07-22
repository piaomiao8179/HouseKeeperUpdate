package com.example.admin.housekeeper.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.admin.housekeeper.R;
import com.example.admin.housekeeper.adapter.MyViewAdapter;

import java.util.ArrayList;

public class ViewpagerActivity extends AppCompatActivity implements View.OnClickListener {
    public static final String SP_CONFIG = "sp_config";
    public static final String IS_FIRST_RUN = "is_first_run";
    private ViewPager mPager;
    private Button mButton;
    private ArrayList<View> mList;
    SharedPreferences mSp;
    int[] pics ={R.drawable.adware_style_applist,R.drawable.adware_style_banner,R.drawable.adware_style_creditswall};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //生成一个本地的xml文件用来记录是否是第一次开启程序，如果是就显示引导页面，如果不是就直接跳过引导页面直接进入程序。
        SharedPreferences mSp = getSharedPreferences(SP_CONFIG,MODE_PRIVATE);
        boolean is_first_run = mSp.getBoolean(IS_FIRST_RUN, true);
        //如果不是就跳过引导页面直接进入程序
        if(!is_first_run){
            Intent  intent = new Intent(ViewpagerActivity.this,SplashActivity.class);
            startActivity(intent);
            finish();
        }else {
            //判断本地xml文件中是否是第一次启动的标记是否为true,如果是就进入引导页面。
            setContentView(R.layout.activity_viewpager);
            mPager = (ViewPager) findViewById(R.id.vp_show);
            mButton = (Button) findViewById(R.id.btn_skip);
            mList = new ArrayList<>();
            //建一个ImageView 用来放置ViewpPager中的图片，然后放到集合中传到MyViewAdapter中
            for (int i = 0; i < pics.length; i++) {
                ImageView iv = new ImageView(this);
                iv.setBackgroundResource(pics[i]);
                mList.add(iv);
            }
            //给ViewPager加适配器
            mPager.setAdapter(new MyViewAdapter(mList));
            //在ViewPager加一个动画
            mPager.setPageTransformer(true, new DepthPageTransformer());
            //给ViewPager加监听
            mPager.addOnPageChangeListener(listener);
            //给引导页面上的最后一页的Button加点击监听
            mButton.setOnClickListener(this);

        }
    }

    /**
     * 在引导页面最后一一页实现一个跳转的按钮，只有滑到最后一张图片的时候才出现，当从第三张滑动回去的时候隐藏
     */
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

    /**
     * 实现在引导页面当点击跳转的时候跳转到SplashActivity并为第二次再进入程序时为直接跳转过引导页面做标记
     * @param v
     */
    @Override
    public void onClick(View v) {
        //获取getSharedPreferences 设置创建文件的名称：SP_CONFIG，以及模式：MODE_PRIVATE
        mSp = getSharedPreferences(SP_CONFIG,MODE_PRIVATE);
        //获取
        SharedPreferences.Editor edit = mSp.edit();
        edit.putBoolean(IS_FIRST_RUN,false);
        edit.commit();
        Intent  intent = new Intent(ViewpagerActivity.this,SplashActivity.class);
        startActivity(intent);
        finish();
    }

    /**
     * viewpager的动画效果，滑动时由下而上有层次感。（此处 不用看）
     */
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
