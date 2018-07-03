package com.example.laksh_000.newlib.Helpers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import com.example.laksh_000.newlib.DataFiles.BookidData;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.LinkedList;
import java.util.List;
import com.example.laksh_000.newlib.DataFiles.StudentIDdata;
public class Databasehelper extends SQLiteOpenHelper {
    Context mycontext;
    public static final String DATABASE_NAME = "booksmain";
    //private static final int DATABASE_VERSION = 3 ;// in case app is failing it is because of version 0
    //booklist table and columns
    public static final String TABLE_NAME = "booklist";
    public static final String COLUMN_ID = "bookid";
    public static final String Book_NAME = "bookname";
    public static final String BOOK_Status = "status";
    //Student table and columns
    public static final String TABLE_STUDENTList = "Studentlist";
    public static final String STUDENTList_ID = "studentid";
    public static final String STUDENTList_NAME="SName";
    public static final String STUDENTList_CLASS="Class";
    public static final String STUDENTList_PNAME1="Pname1";
    public static final String STUDENTList_PNUMBER1="Pnumber1";
    public static final String STUDENTList_PNAME2="Pname2";
    public static final String STUDENTList_PNUMBER2="Pnumber2";
    public Databasehelper(Context context) {
        super(context, DATABASE_NAME , null, 3);
        this.mycontext=context;
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
      //bookdb
        db.execSQL(" CREATE TABLE " + TABLE_NAME + " (" +COLUMN_ID + " Varchar PRIMARY KEY, " +Book_NAME + " Varchar NOT NULL, " +BOOK_Status + " Varchar NOT NULL);");
    //studentdb
        db.execSQL(" CREATE TABLE " + TABLE_STUDENTList + " (" + STUDENTList_ID + " Varchar PRIMARY KEY,"+STUDENTList_NAME+" varchar NOT NULL,"+STUDENTList_CLASS+" varchar NOT NUll,"+STUDENTList_PNAME1+" varchar not null,"+STUDENTList_PNUMBER1+" decimal(10,0) not null,"+STUDENTList_PNAME2+" varchar not null,"+STUDENTList_PNUMBER2+" decimal(10,0) not null);");
    }
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
                    // you can implement here migration process
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
       db.execSQL("DROP TABLE IF EXISTS " + TABLE_STUDENTList);
       Log.i("Change in version","increased to"+newVersion);
        this.onCreate(db);}

    public void addNewStudent(StudentIDdata studentid,Context context){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(STUDENTList_ID,studentid.getId());
        values.put(STUDENTList_NAME,studentid.getName());
        values.put(STUDENTList_CLASS,studentid.getSClass());
        values.put(STUDENTList_PNAME1,studentid.getName1());
        values.put(STUDENTList_PNUMBER1,studentid.getPhone1());
        values.put(STUDENTList_PNAME2,studentid.getName2());
        values.put(STUDENTList_PNUMBER2,studentid.getPhone2());
        db.insert(TABLE_STUDENTList,null,values);
        Toast.makeText(context,"Entered Student",Toast.LENGTH_SHORT).show();
        db.close();
    }
   public void saveNewbook(BookidData bookid, Context context) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID,bookid.getId());
        values.put(Book_NAME, bookid.getName());
        values.put(BOOK_Status, bookid.getStatus());
        // insert
        db.insert(TABLE_NAME,null, values);
       Toast.makeText(context, "Entered book", Toast.LENGTH_SHORT).show();
        db.close();
    }
    public List<BookidData> bookviewlist(String filter) {

        String query;
        if(filter.equals("")){
            query = "SELECT  * FROM " + TABLE_NAME;
        }else{
            //filter results by filter option provided
            query = "SELECT  * FROM " + TABLE_NAME + " ORDER BY "+ filter;
        }

        List<BookidData> BooksLinkedList = new LinkedList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        BookidData books;

        if (cursor.moveToFirst()) {
            do {
                books = new BookidData();

                books.setId(cursor.getString(cursor.getColumnIndex(COLUMN_ID)));
                books.setName(cursor.getString(cursor.getColumnIndex(Book_NAME)));
                books.setStatus(cursor.getString(cursor.getColumnIndex(BOOK_Status)));
                BooksLinkedList.add(books);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return BooksLinkedList;
    }
    public void deletebookRecord(String id,Context context) {
        try {
            SQLiteDatabase db = this.getWritableDatabase();

            db.execSQL("DELETE FROM " + TABLE_NAME + " WHERE bookid='"+id+"'");
            db.close();
        }catch (Exception e){e.printStackTrace();
        }

    }
}
