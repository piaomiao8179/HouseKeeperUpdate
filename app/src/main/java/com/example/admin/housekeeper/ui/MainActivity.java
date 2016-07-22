package com.example.admin.housekeeper.ui;

import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.admin.housekeeper.R;
import com.example.admin.housekeeper.db.DBReader;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class MainActivity extends AppCompatActivity implements  AdapterView.OnItemClickListener, Toolbar.OnMenuItemClickListener {
    private long                mStartTime;//开始时间（按两下返回键退出）onkeydown方法
    private GridView            mView;//主界面展示控件
    private Toolbar mToolbar;
    //主界面要展示的图标
    private int[]    mIcon = {R.drawable.icon_rocket, R.drawable.icon_softmgr, R.drawable.icon_phonemgr,
            R.drawable.icon_telmgr, R.drawable.icon_filemgr, R.drawable.icon_sdclean};
    private String[] mName = {"手机加速", "软件管理", "手机检测", "通讯大全", "文件管理", "垃圾清理"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //初始化控件
        mView = (GridView) findViewById(R.id.gv_show);
        mToolbar = (Toolbar) findViewById(R.id.toolbar_main);
        //设置适配器
        mView.setAdapter(mAdapter);
        //设置监听
        mView.setOnItemClickListener(this);
        //设置替代actionbar
        setSupportActionBar(mToolbar);
        //toolbar的菜单监听
        mToolbar.setOnMenuItemClickListener(this);


    }

    /**
     * assets下的文件移动到包名下
     *
     * @param
     */
    public void moveData() throws IOException {
        //判断是否存在数据库文件  不存在   创建
        if (!DBReader.isExsitDBFile()) {
            String path = "data/data/com.example.admin.housekeeper/commonnum.db";
            AssetManager manager = getAssets();
            InputStream is = manager.open("commonnum.db");
            BufferedInputStream bis = new BufferedInputStream(is);
            //      FileOutputStream os = openFileOutput("commonnum.db",MODE_PRIVATE);
            BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(path));
            byte[] buff = new byte[1024];
            int len = 0;
            while ((len = bis.read(buff)) != -1) {
                bos.write(buff, 0, len);
            }
            bos.close();
            bis.close();
        }

    }



    //对返回做出限制，不允许返回动画界面，只能点击两次后退出
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        while (System.currentTimeMillis() - mStartTime > 2000) {
            if (keyCode == KeyEvent.KEYCODE_BACK) {
                Toast.makeText(this, "请在两秒内再次点击退出", Toast.LENGTH_SHORT).show();
                mStartTime = System.currentTimeMillis();
            } else {
                finish();
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
    //点击事件，进行跳转到对应模块界面（如通讯录大全）
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        switch (position){
            case 2:
                //手机检测
                Intent intent1 = new Intent(MainActivity.this, PhoneInfoActivity.class);
                startActivity(intent1);

                break;

            case 3:
                //通讯大全
                try {
                    moveData();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Intent intent = new Intent(MainActivity.this, TelSortActivity.class);
                startActivity(intent);

                break;
            case 1:
                //手机软件信息
                Intent it = new Intent(MainActivity.this, AppInfoActivity.class);
                startActivity(it);

                break;

        }
    }

   //toolbar右侧下拉选项菜单
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main,menu);
        return true;
    }

    /**
     * toolbar的点击事件
     */
    @Override
    public boolean onMenuItemClick(MenuItem item) {
        Toast.makeText(this,"点击了Toolbar",Toast.LENGTH_SHORT).show();
        return false;
    }

    /**
     *主界面的adapter 展示主界面分类的数据信息
     */
    BaseAdapter mAdapter = new BaseAdapter() {
        @Override
        public int getCount() {
            return mIcon.length;
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
            if (convertView == null){
                convertView = LayoutInflater.from(MainActivity.this).inflate(R.layout.gridview_main_item,null);
            }
            ImageView iv_icon = (ImageView) convertView.findViewById(R.id.iv_main_grid);
            TextView tv_name = (TextView) convertView.findViewById(R.id.tv_main_grid);
            iv_icon.setBackgroundResource(mIcon[position]);
            tv_name.setText(mName[position]);
            return convertView;
        }
    };
}
