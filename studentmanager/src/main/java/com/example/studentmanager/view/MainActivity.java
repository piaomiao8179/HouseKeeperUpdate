package com.example.studentmanager.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.studentmanager.R;
import com.example.studentmanager.Student;
import com.example.studentmanager.StudentAdapter;
import com.example.studentmanager.StudentDAO;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button mButton;
    private EditText mName;
    private EditText mId;
    private EditText mPhone;
    private ListView mView;
    private StudentAdapter mAdapter;
    private ArrayList<Student> mList;
    private StudentDAO dao;
    private Button mBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mButton = (Button) findViewById(R.id.btn_commit);
        mId = (EditText) findViewById(R.id.et_id);
        mName = (EditText) findViewById(R.id.et_name);
        mPhone = (EditText) findViewById(R.id.et_phone);
        mView = (ListView) findViewById(R.id.lv_show);
        mBtn = (Button) findViewById(R.id.btn_check);
        mList = new ArrayList<>();
        dao = new StudentDAO(this);
        ArrayList<Student> select = dao.select();
        mList = select;
        mAdapter = new StudentAdapter(this, mList);
        mView.setAdapter(mAdapter);
        mButton.setOnClickListener(this);
        mBtn.setOnClickListener(this);

    }
    public Student getData(){
        String id = mId.getEditableText().toString();
        String name = mName.getEditableText().toString();
        String phone = mPhone.getEditableText().toString();
        Student stu = new Student(id,name,phone);
        return stu;
    }
    public void judge(){
        String id = mId.getEditableText().toString();
        String name = mName.getEditableText().toString();
        String phone = mPhone.getEditableText().toString();
        if(id.equals("")||name.equals("")||phone.equals("")){
            mButton.setEnabled(false);
           Toast.makeText(this,"输入框不能为空",Toast.LENGTH_SHORT).show();
        }else {
            mButton.setEnabled(true);

        }
    }

    @Override
    public void onClick(View v) {
        Student data = getData();
        if(v == mButton) {
            if(dao.findByID(data.getId())){
                mButton.setEnabled(false);
                Toast.makeText(this,"对不起ID重复请重新输入",Toast.LENGTH_SHORT).show();
                return;

            }
            dao.add(data);
            Toast.makeText(this,"插入成功",Toast.LENGTH_SHORT).show();
            ArrayList<Student> list = dao.select();
            mView.setAdapter(new StudentAdapter(this, list));
            mButton.setEnabled(false);
        }
        if(v == mBtn){
            judge();
        }
    }
}
