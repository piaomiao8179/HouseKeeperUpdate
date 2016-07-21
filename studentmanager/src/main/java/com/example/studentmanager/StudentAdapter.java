package com.example.studentmanager;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by admin on 2016/7/18.
 */
public class StudentAdapter extends BaseAdapter {
    ArrayList<Student> mList;
    Context mContext;

    public StudentAdapter( Context context,ArrayList<Student> list) {
        mList = list;
        mContext = context;
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
        if(convertView == null){
            convertView = View.inflate(mContext,R.layout.itemd,null);
        }
        TextView stuID = (TextView) convertView.findViewById(R.id.tv_id);
        TextView stuNAME = (TextView) convertView.findViewById(R.id.tv_name);
        TextView stuPHONE = (TextView) convertView.findViewById(R.id.tv_phone);
        stuID.setText(mList.get(position).getId());
        stuNAME.setText(mList.get(position).getName());
        stuPHONE.setText(mList.get(position).getPhone());
        return convertView;
    }
}
