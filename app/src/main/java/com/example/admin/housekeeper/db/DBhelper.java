package com.example.admin.housekeeper.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by admin on 2016/7/13.
 */
public class DBhelper extends SQLiteOpenHelper{
    public DBhelper(Context context,String name, int version) {
        super(context,name, null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table call( _id integer primary key autoincrement, number varcher)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
