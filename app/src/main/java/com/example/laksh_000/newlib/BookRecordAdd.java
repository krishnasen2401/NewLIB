package com.example.laksh_000.newlib;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.laksh_000.newlib.DataFiles.BookHistory;
import com.example.laksh_000.newlib.Helpers.BookRecord;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class BookRecordAdd extends AppCompatActivity {
    Date now;
    String Datenew;
    BookRecord t;
public void SubmitRec(View view){
    EditText bookid11=findViewById(R.id.BookidRec);
    EditText studentid11=findViewById(R.id.StudentIdRec);
    String bookid112=bookid11.getText().toString().toUpperCase();
    String studentid112=studentid11.getText().toString().toUpperCase();
    now= Calendar.getInstance().getTime();
    Datenew=new SimpleDateFormat("dd-MM-yyyy").format(now);
    BookHistory hs=new BookHistory(bookid112,studentid112,Datenew,null);
    t.addNewRecord(hs,this);
    bookid11.setText(null);
    studentid11.setText(null);
}
public void ReceviedBk(View v){
    EditText ReceBkid1=findViewById(R.id.ReceBkid);
    String ReceBkid112=ReceBkid1.getText().toString().toUpperCase();
    now=Calendar.getInstance().getTime();
    Datenew=new SimpleDateFormat("dd-MM-yyyy").format(now);
    t=new BookRecord(this);
    t.UpdateRecord(ReceBkid112,Datenew);
}
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_record_add);
        t=new BookRecord(this);
    }
}
