package com.example.admin.housekeeper.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.admin.housekeeper.R;
import com.example.admin.housekeeper.bean.Live;

import java.util.ArrayList;

/**
 * Created by admin on 2016/7/14.
 * 主界面分类信息  GridView的适配器  （本地通话  银行电话等）
 */
public class MyAdapter extends BaseAdapter {
    private ArrayList<Live> mList;
    private Context         context;
    public MyAdapter(Context context,ArrayList<Live> list) {
        this.context = context;
        mList = list;
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        MyAdapter.ViewHolder holder ;//优化
        if(convertView == null){
            holder = new ViewHolder();
            convertView = View.inflate(context, R.layout.item,null);
            holder.mTextView = (TextView) convertView.findViewById(R.id.tv_name);
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }


//        TextView tvNumber = (TextView) convertView.findViewById(R.id.tv_number);
        holder.mTextView.setText(mList.get(position).getName());
//        tvNumber.setText(mList.get(position).getNumber());
        return convertView;
    }

    //viewholder优化类
    private static class ViewHolder{
        TextView mTextView;
    }
}
