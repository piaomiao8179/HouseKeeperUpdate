package com.example.viewpagermodule;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * Created by admin on 2016/7/12.
 */
public class MyAdapter extends PagerAdapter {
    private ArrayList<View> mList;
    public MyAdapter(ArrayList<View> list) {
        mList = list;
    }

    @Override
    public int getCount() {
        if(mList != null){
            return mList.size();
        }
        return 0;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        container.addView(mList.get(position),0);
        return mList.get(position);
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view ==  object;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(mList.get(position));

    }
}
