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
 * 本类是手机管理应用软件的适配器
 */
public class MyadapterAppInfo extends BaseAdapter {
    private Context    mContext;
    private AppInfoDao mInfoDao;

    public MyadapterAppInfo(Context context) {
        //添加构造器，初始化要展示的数据
        //数据来源  是从AppInfoDao自定义的类获取
        mContext = context;
        mInfoDao = new AppInfoDao(mContext);
        //实例化对象获取具体的信息（包名  应用名  版本号  图标等）
        mInfoDao.getAppInfo();
    }

    @Override
    public int getCount() {
        //此处的集合是在AppInfoDao自定义的类中定义的集合 里面存储了对应的数据
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

    /**
     * 自定义ViewHolder类优化getView（）
     */
    private static class ViewHolder{
        ImageView mImageView;
        TextView mTvPackage;
        TextView mTvApp;
        TextView mTvVersion;
    }
}
