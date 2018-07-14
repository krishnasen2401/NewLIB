package com.example.laksh_000.newlib;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.laksh_000.newlib.Adapters.BooksDisplayAdapter;
import com.example.laksh_000.newlib.Helpers.MainDatabasehelper;

public class Display extends AppCompatActivity {
    MainDatabasehelper dbhelper;
    BooksDisplayAdapter adapter;
    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);
        mRecyclerView = findViewById(R.id.tvAnimalName);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        dbhelper=new MainDatabasehelper(this);
        adapter = new BooksDisplayAdapter(dbhelper.bookviewlist(""), this, mRecyclerView);
        mRecyclerView.setAdapter(adapter);
    }
}
