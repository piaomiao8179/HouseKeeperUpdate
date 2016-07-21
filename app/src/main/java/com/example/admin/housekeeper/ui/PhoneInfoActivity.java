package com.example.admin.housekeeper.ui;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.admin.housekeeper.R;
import com.example.admin.housekeeper.adapter.MyAdapterInfo;

public class PhoneInfoActivity extends AppCompatActivity {
    private ListView mListViewPhoneInfo;
    ProgressBar mProgressBar;
    TextView mTvBattery;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone_info);
        initView();

    }

    private void initView() {
        mTvBattery = (TextView) findViewById(R.id.tv_battery);
        mProgressBar = (ProgressBar) findViewById(R.id.progressbar);

        mListViewPhoneInfo = (ListView) findViewById(R.id.lv_phone_info);
        // TODO: 2016/7/20 设置适配器 
        mListViewPhoneInfo.setAdapter(new MyAdapterInfo(this));

        //获取广播机制对象
        BroadcastReceiver receiver = new batteryBroadCast();
        //获取意图过滤器
        IntentFilter filter = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);
        //在MainiFest注册
        registerReceiver(receiver,filter);

    }


    public void getBattery(){

    }

    //广播机制   发送广播的类
    public class batteryBroadCast extends BroadcastReceiver{
        @Override
        public void onReceive(Context context, Intent intent) {
            //获取电池信息
            if(intent.getAction().equals(Intent.ACTION_BATTERY_CHANGED)){
            Bundle bundle = intent.getExtras();
            Integer scale = (Integer) bundle.get(BatteryManager.EXTRA_SCALE);//总电量
            Integer level = (Integer) bundle.get(BatteryManager.EXTRA_LEVEL);//当前电量
            Integer temperature = (Integer) bundle.get(BatteryManager.EXTRA_TEMPERATURE);//电池温度
            double tem = temperature / 10.0;
                //设置进度条和显示框
                mTvBattery.setText(level + "%");

                mProgressBar.setProgress(level);
            Log.d("电池", "onReceive: " + scale + "\n" + level + "\n" + tem);
            }
        }
    }
}
