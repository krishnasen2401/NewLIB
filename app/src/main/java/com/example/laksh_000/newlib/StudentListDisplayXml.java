package com.example.laksh_000.newlib;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.laksh_000.newlib.Adapters.StudentListDisplay;
import com.example.laksh_000.newlib.Helpers.MainDatabasehelper;

public class StudentListDisplayXml extends AppCompatActivity {
    MainDatabasehelper dbhelper;
    StudentListDisplay adaper1;
    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_list_display);
        mRecyclerView=findViewById(R.id.StudentAll);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        dbhelper=new MainDatabasehelper(this);
        adaper1=new StudentListDisplay(dbhelper.studentviewlist(""),this,mRecyclerView);
        mRecyclerView.setAdapter(adaper1);
    }
}
