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
        db.execSQL("update "+TABLE_NAME+" set "+BOOK_Status+"='N' where "+COLUMN_ID+" like '"+recordid.getBookid()+"'");
        }}
        else {
            Log.i("failed","not created");}
        db.close();
    }
    public void UpdateRecord(String bookid,String date){
        SQLiteDatabase db=this.getWritableDatabase();
        String query="Select * from "+TABLE_Record+"";
        /*Cursor cursor = db.rawQuery(query, null);try{
        if (cursor.moveToFirst()) {
            do {
                Log.i("bookid", cursor.getString(cursor.getColumnIndex(Record_bookid)));
                Log.i("studentid", cursor.getString(cursor.getColumnIndex(Record_studentid)));
                Log.i("sentdate", cursor.getString(cursor.getColumnIndex(Record_sentdate)));
                if(cursor.isNull(3))
                Log.i("recdate1", cursor.getString(3));
            } while (cursor.moveToNext());
        }}catch(Exception e){e.printStackTrace();}*/

        db.execSQL("update "+TABLE_NAME+" set "+BOOK_Status+"='Y' where "+COLUMN_ID+"='"+bookid+"'");//updatepresent record table
        db.execSQL("update "+TABLE_Record+" set "+Record_recevicedate+"='"+date+"' where "+Record_bookid+"='"+bookid+"' and "+Record_recevicedate+" IS NULL");
        //cursor.close();
        db.close();//upda1temain book table status
    }
}
