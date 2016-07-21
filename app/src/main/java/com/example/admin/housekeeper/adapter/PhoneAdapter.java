package com.example.admin.housekeeper.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.admin.housekeeper.R;
import com.example.admin.housekeeper.bean.PhoneNumber;

import java.util.ArrayList;

/**
 * Created by admin on 2016/7/14.
 */
public class PhoneAdapter extends BaseAdapter {
    private ArrayList<PhoneNumber> mList;
    private Context                mContext;

    public PhoneAdapter(Context context, ArrayList<PhoneNumber> list) {
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
        ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = View.inflate(mContext, R.layout.phone_number, null);
            holder.mTvID = (TextView) convertView.findViewById(R.id.tv_phone_id);
            holder.mTvName = (TextView) convertView.findViewById(R.id.tv_phone_name);
            holder.mTvNumber = (TextView) convertView.findViewById(R.id.tv_phone_number);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.mTvID.setText(mList.get(position).getId());
        holder.mTvName.setText(mList.get(position).getName());
        holder.mTvNumber.setText(mList.get(position).getNumber());
        return convertView;
    }

    public final class ViewHolder {
        TextView mTvID;
        TextView mTvName;
        TextView mTvNumber;
    }
}
