package com.example.laksh_000.newlib.Helpers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.laksh_000.newlib.DataFiles.BookHistory;

public class BookRecord extends MainDatabasehelper {
    public BookRecord(Context context) {
        super(context);
    }
    public void addNewRecord(BookHistory recordid, Context context){
        SQLiteDatabase db=this.getWritableDatabase();
        String query="Select * from "+TABLE_NAME+" where "+COLUMN_ID+"='"+recordid.getBookid()+"'and "+BOOK_Status+"='Y'";
        Cursor cursor = db.rawQuery(query, null);
        Log.i("Cursor count", String.valueOf(cursor.getCount()));
        ContentValues values=new ContentValues();
        if(cursor.getCount()==1){
        query="Select * from "+TABLE_STUDENTList+" where "+STUDENTList_ID+"='"+recordid.getStudentid()+"'";
        cursor = db.rawQuery(query, null);
        if(cursor.getCount()==1){
        values.put(Record_bookid,recordid.getBookid());
        values.put(Record_studentid,recordid.getStudentid());
        values.put(Record_sentdate,recordid.getSentdate());
        values.put(Record_recevicedate,recordid.getRecevieddate());
        db.insert(TABLE_Record,null,values);
        db.execSQL("update "+TABLE_NAME+" set "+BOOK_Status+"='N' where "+COLUMN_ID+"='"+recordid.getBookid()+"'");
        }}
        else {
            Log.i("failed","not created");}
        db.close();
    }
}
