package com.example.laksh_000.newlib;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.laksh_000.newlib.DataFiles.BookidData;
import com.example.laksh_000.newlib.Helpers.MainDatabasehelper;

public class AddBook extends AppCompatActivity {
    public MainDatabasehelper h1;
    public void AddBook(View view){
        h1=new MainDatabasehelper(this);
        EditText id=findViewById(R.id.BookIdReg);
        EditText name=findViewById(R.id.BookNameReg);
        String id1=id.getText().toString().toUpperCase();
        String name1=name.getText().toString();
        BookidData bookidData=new BookidData(id1,name1,"Y");
        h1.saveNewbook(bookidData,this);
        id.getText().clear();
        name.getText().clear();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_book);
    }
}
