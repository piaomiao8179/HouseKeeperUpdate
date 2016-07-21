package com.example.admin.housekeeper.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.admin.housekeeper.R;
import com.example.admin.housekeeper.dao.GetPhoneInfoDao;

import java.util.Arrays;

/**
 * Created by Administrator on 2016/7/20.
 */
public class MyAdapterInfo extends BaseAdapter {
    public static String[] sTextTop;
    public static String[] sTextBelow;
    public static int[]    sDrawable;

    private Context mContext;

    public MyAdapterInfo(Context context) {
        mContext = context;
        GetPhoneInfoDao InfoDao = new GetPhoneInfoDao(context);
        InfoDao.getDeviceInfo();
        InfoDao.getCpu();
        InfoDao.getMemory();
        InfoDao.getMetricx();
        InfoDao.getRadio();
        sTextTop = new String[]{"设备品牌:"+InfoDao.mBrand, "总运存:"+InfoDao.mTotalMemSize,
                "CPU名称:"+Arrays.toString(InfoDao.mCpuName),
                "屏幕分辨率:"+InfoDao.mWidthPixels + "*" + InfoDao.mHeightPixels,
                "基带版本:"+InfoDao.mRadioVersion};
        sTextBelow = new String[]{"设备版本:"+InfoDao.mVersion, "空闲运存:"+InfoDao.mAvailMemSize,
                "CPU数量:"+String.valueOf(InfoDao.mCpuNumber), "相机分辨率:"+InfoDao.getCameraMetrics(),
                "是否ROOT:"+InfoDao.getIsRoot()+""};
        sDrawable = new int[]{R.mipmap.setting_info_icon_version, R.mipmap.setting_info_icon_space,
                R.mipmap.setting_info_icon_cpu, R.mipmap.setting_info_icon_camera,
                R.mipmap.setting_info_icon_root};
    }


    @Override
    public int getCount() {
        return 5;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = LayoutInflater.from(mContext).inflate(R.layout.list_item_phone_info, null);
            holder.mImageView = (ImageView) convertView.findViewById(R.id.iv_phone_icon);
            holder.mTextViewTop = (TextView) convertView.findViewById(R.id.tv_top);
            holder.mTextViewBelow = (TextView) convertView.findViewById(R.id.tv_below);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.mImageView.setImageResource(sDrawable[position]);
        holder.mTextViewTop.setText(sTextTop[position]);
        holder.mTextViewBelow.setText(sTextBelow[position]);
        return convertView;
    }

    private static class ViewHolder {
        ImageView mImageView;
        TextView  mTextViewTop;
        TextView  mTextViewBelow;
    }
}
