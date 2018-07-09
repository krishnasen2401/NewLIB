package com.example.laksh_000.newlib;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class StudentFullDisplay extends AppCompatActivity {
    private String id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_studen_display_full);
        id = getIntent().getStringExtra("Dataid");
        TextView id1=findViewById(R.id.StidDis);
        id1.setText(id);
    }
}
