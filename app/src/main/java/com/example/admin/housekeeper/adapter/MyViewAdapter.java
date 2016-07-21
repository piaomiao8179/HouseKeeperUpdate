package com.example.admin.housekeeper.adapter;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * Created by admin on 2016/7/12.
 */
public class MyViewAdapter extends PagerAdapter {
    private ArrayList<View> mList;
    //设一个有参的构造函数，为了接收ViewpagerActivity由图片组成的集合 所以泛型化是View
    public MyViewAdapter(ArrayList<View> List) {
        mList = List;
    }

    /**
     * 获取图片的数量，并做出判断，如果集合不为空返回集合长度
     * @return
     */
    @Override
    public int getCount() {
        if(mList != null){
            return mList.size();
        }
        return 0;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    /**
     * 设置显示的集合中的那张图片，以及默认的显示的图片返回的是集合当前位置的图片
     * @param container
     * @param position
     * @return
     */
    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        container.addView(mList.get(position),0);
        return mList.get(position);
    }

    /**
     * 移除滞空的图片。前后内存只加载两张，前边一张后边一张，其他的都移除
     * @param container
     * @param position
     * @param object
     */
    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(mList.get(position));
    }
}
