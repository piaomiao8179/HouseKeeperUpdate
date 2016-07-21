package com.example.studentmanager;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by admin on 2016/7/18.
 */
public class DBHelper extends SQLiteOpenHelper {
    public DBHelper(Context context) {
        super(context,"Stduent.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String SQL ="create table student(_id integer primary key autoincrement, id varcher, name varcher, phone varcher)";
        db.execSQL(SQL);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
