package com.example.studentmanager;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

/**
 * Created by admin on 2016/7/18.
 */
public class StudentDAO {
    private DBHelper mHelper;
    public StudentDAO(Context context) {

        mHelper = new DBHelper(context);
    }
    public void add(Student student){

        SQLiteDatabase database = mHelper.getWritableDatabase();
        ContentValues Value =new ContentValues();
        Value.put("id",student.getId());
        Value.put("name",student.getName());
        Value.put("phone",student.getPhone());
        database.insert("student", null, Value);

    }
    public boolean findByID(String id){
        //select * from student where id = id
        SQLiteDatabase database = mHelper.getWritableDatabase();
        Cursor query = database.query("student", null, "id=?", new String[]{id}, null, null, null);
        if(query.moveToFirst()){
            return true;
        }
        return false;
    }
    public ArrayList<Student> select(){
        ArrayList<Student> list  = new ArrayList<>();
        SQLiteDatabase database = mHelper.getWritableDatabase();
        Cursor query = database.query("student", null, null, null, null, null, null);
        if(query.moveToFirst()){
            do{
                String id = query.getString(query.getColumnIndex("id"));
                String name = query.getString(query.getColumnIndex("name"));
                String phone = query.getString(query.getColumnIndex("phone"));
                list.add(new Student(id,name,phone));
            }while (query.moveToNext());

        }
        return list;
    }
}
