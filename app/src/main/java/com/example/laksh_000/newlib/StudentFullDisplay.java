package com.example.laksh_000.newlib;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.example.laksh_000.newlib.DataFiles.StudentIDdata;
import com.example.laksh_000.newlib.Helpers.Databasehelper;

import java.util.ArrayList;

public class StudentFullDisplay extends AppCompatActivity {
    private String id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_studen_display_full);
        Bundle bundle = getIntent().getExtras();
        TextView id1=findViewById(R.id.StidDis);
        id1.setText(bundle.getString("id"));
        TextView name=findViewById(R.id.StNameDis);
        name.setText(bundle.getString("name"));
        TextView age=findViewById(R.id.Stuage);
        age.setText(bundle.getString("age"));
        TextView classes=findViewById(R.id.Stclassdis);
        classes.setText(bundle.getString("class"));
        TextView pnam1=findViewById(R.id.Studp1);
        pnam1.setText(bundle.getString("pname1"));
        TextView pnum1=findViewById(R.id.Stupn1);
        pnum1.setText(bundle.getString("pnum1"));
        TextView pnam2=findViewById(R.id.Stup2);
        pnam2.setText(bundle.getString("pname2"));
        TextView pnum2=findViewById(R.id.Stupn2);
        pnum2.setText(bundle.getString("pnum2"));
    }
}
