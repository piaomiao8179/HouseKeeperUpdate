package com.example.admin.housekeeper.ui;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import com.example.admin.housekeeper.R;
import com.example.admin.housekeeper.adapter.MyAdapter;
import com.example.admin.housekeeper.bean.Live;
import com.example.admin.housekeeper.db.DBReader;
import com.example.admin.housekeeper.db.DBhelper;

import java.util.ArrayList;

public class TelSortActivity extends AppCompatActivity implements AdapterView.OnItemClickListener, Toolbar.OnMenuItemClickListener {
    private DBhelper        mBhelper;
    private SQLiteDatabase  mDatabase;
    private ArrayList<Live> mList;
//    private ListView        mView;
    private MyAdapter       mAdapter;
    private SQLiteDatabase  mdb;
    private GridView        mView;
    private Toolbar         mToolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_telphone);
        //初始化Button toolbar adapter
        mView = (GridView) findViewById(R.id.gridView);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mList = new ArrayList<>();
        Context context = getApplicationContext();
        //获取要查询数据库所在的文件夹以及名称
        mdb = SQLiteDatabase.openOrCreateDatabase(DBReader.telFile,null);
        //查询所对应的表
        Cursor cursor = mdb.rawQuery("select * from classlist",null);
        //遍历表的数据
        if (cursor.moveToFirst()) {
            do{
                String name = cursor.getString(cursor.getColumnIndex("name"));
                String idx = cursor.getString(cursor.getColumnIndex("idx"));
                //将表的数据以封装成Bean对象加载到数据集合中
                mList.add(new Live(name,idx));
            }while (cursor.moveToNext());
        }
        mAdapter = new MyAdapter(context,mList);
        //给listview加适配器
        mView.setAdapter(mAdapter);
        //设置监听
        mView.setOnItemClickListener(this);
        //建立toolbar
        setSupportActionBar(mToolbar);
        //给toolbar加监听
        mToolbar.setOnMenuItemClickListener(this);
    }

    /**
     * LISTVIEW的item点击监听
     * @param parent
     * @param view
     * @param position
     * @param id
     */
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //跳转页面
                Intent intent = new Intent(TelSortActivity.this,ShowNumberActivity.class);
                //通过点击的位置获取点击位置所对应的数据
                String str = mList.get(position).getNumber();
                //用intent携带数据到跳转的页面为了查询的时候做一个标示
                intent.putExtra("id",str);
                startActivity(intent);
            }

    /**
     * 将建好的menuc菜单加载到toolbar中
     * @param menu
     * @return
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);

        return true;
    }

    /**
     * toolbar加的三个点击按钮对应做出的处理
     * @param item
     * @return
     */
    @Override
    public boolean onMenuItemClick(MenuItem item) {
        switch (item.getItemId()){
            case R.id.it:
                Toast.makeText(this,"点击了搜索",Toast.LENGTH_SHORT).show();
                 break;
            case R.id.it_1:
                Toast.makeText(this,"点击了错号",Toast.LENGTH_SHORT).show();
                break;
            case R.id.it_2:
                Toast.makeText(this,"点击了隐藏",Toast.LENGTH_SHORT).show();
                break;
        }
        return true;
    }
}
