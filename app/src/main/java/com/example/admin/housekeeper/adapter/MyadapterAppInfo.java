package com.example.admin.housekeeper.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.admin.housekeeper.R;
import com.example.admin.housekeeper.dao.AppInfoDao;

/**
 * Created by Administrator on 2016/7/19.
 */
public class MyadapterAppInfo extends BaseAdapter {
    private Context    mContext;
    private AppInfoDao mInfoDao;

    public MyadapterAppInfo(Context context) {
        mContext = context;
        mInfoDao = new AppInfoDao(mContext);
        mInfoDao.getAppInfo();
    }

    @Override
    public int getCount() {
        if (0 != mInfoDao.mListIcon.size()) {
            return mInfoDao.mListIcon.size();
        }
        return 0;
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
        //view holder优化
        ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = LayoutInflater.from(mContext).inflate(R.layout.listview_item_appinfo, null);
            holder.mImageView = (ImageView) convertView.findViewById(R.id.iv_appInfo);
            holder.mTvPackage = (TextView) convertView.findViewById(R.id.tv_package);
            holder.mTvApp = (TextView) convertView.findViewById(R.id.tv_app);
            holder.mTvVersion = (TextView) convertView.findViewById(R.id.tv_version);
            //给convertView一个标记
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }

        holder.mImageView.setImageDrawable(mInfoDao.mListIcon.get(position).getDrawable());
        holder.mTvPackage.setText("包名:  "+mInfoDao.mListPakageName.get(position).getText());
        holder.mTvApp.setText("应用名:  "+mInfoDao.mListAppName.get(position).getText());
        holder.mTvVersion.setText("版本号:  "+mInfoDao.mListVersionName.get(position).getText());
        return convertView;
    }

    private static class ViewHolder{
        ImageView mImageView;
        TextView mTvPackage;
        TextView mTvApp;
        TextView mTvVersion;
    }
}
