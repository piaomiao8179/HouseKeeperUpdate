package com.example.admin.housekeeper.ui;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.admin.housekeeper.R;
import com.example.admin.housekeeper.adapter.PhoneAdapter;
import com.example.admin.housekeeper.bean.PhoneNumber;
import com.example.admin.housekeeper.db.DBReader;

import java.util.ArrayList;
import java.util.List;

public class ShowNumberActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {
    private ListView          mListView;
    private SQLiteDatabase    mDB;
    private List<PhoneNumber> mList;
    private PhoneAdapter      mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_class);
        //获得intent 上个界面所传过来的intent
        Intent intent = getIntent();
        //获得intent所携带的额外数据
        String id = intent.getStringExtra("id");
        //将获取到的额外数据从类型string转换成int
        int i = Integer.parseInt(id);
        //实例化集合 listview
        mList = new ArrayList<>();
        mListView = (ListView) findViewById(R.id.lv_showclass_phone);
        mAdapter = new PhoneAdapter(this, (ArrayList<PhoneNumber>) mList);
        //利用sqlite查询所在data/data/com.example.admin.housekeeper文件夹的下的数据库
        mDB = SQLiteDatabase.openOrCreateDatabase(DBReader.telFile,null);
                Cursor cursor = mDB.rawQuery("select * from table"+i, null);
                    if(cursor.moveToFirst()){
                    do{
                        String name = cursor.getString(cursor.getColumnIndex("name"));
                        String number = cursor.getString(cursor.getColumnIndex("number"));
                        String id1 = cursor.getString(cursor.getColumnIndex("_id"));
                        mList.add(new PhoneNumber(name,number,id1));
                    }while (cursor.moveToNext());
                }
        mListView.setAdapter(mAdapter);
        mListView.setOnItemClickListener(this);
    }
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        String s = mList.get(position).getNumber();
        long number = Long.parseLong(s);
        //intent跳转。。隐式意图
        Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:"+number));
        startActivity(intent);
    }
}
