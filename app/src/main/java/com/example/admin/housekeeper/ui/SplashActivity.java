package com.example.admin.housekeeper.ui;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.example.admin.housekeeper.R;

public class SplashActivity extends AppCompatActivity implements Runnable {
    private AnimationDrawable mDrawable;
    private ImageView mIv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aplash);
        //实例化控件
        mIv = (ImageView) findViewById(R.id.iv_animation);
        //给控件设置图片背景
        mIv.setBackgroundResource(R.drawable.androidy);
        //设置动画样式R.anim.anim
        Animation animation = AnimationUtils.loadAnimation(this,R.anim.anim);
        mIv.startAnimation(animation);
//        mDrawable = (AnimationDrawable) mIv.getBackground();
//        mDrawable.start();
        //启动线程
        new Thread(this).start();

    }

    /**
     * 启动线程让程序让开机动画等待3秒以后跳转到MainActivity
     */
    @Override
    public void run() {
        try {
            Thread.sleep(3000);
            Intent intent = new Intent(SplashActivity.this,MainActivity.class);
            startActivity(intent);
            finish();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
