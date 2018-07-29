package com.example.laksh_000.newlib;

import android.Manifest;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;
import com.ajts.androidmads.library.SQLiteToExcel;

public class MainActivity extends AppCompatActivity{

    public void AddBooknew(View view){
        Intent intent=new Intent(this,AddBook.class);
        startActivity(intent);
    }
    public void newbookRec(View view){
        Intent intent=new Intent(this,BookRecordAdd.class);
        startActivity(intent);
    }

public void PermissionRequest1(){
    Log.i("Permission","checking");
    // Here, thisActivity is the current activity
    if (ContextCompat.checkSelfPermission(this,
            Manifest.permission.WRITE_EXTERNAL_STORAGE)
            != PackageManager.PERMISSION_GRANTED) {
        ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},200);
        // Permission is not granted
        // Should we show an explanation?
        if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
            // Show an explanation to the user *asynchronously* -- don't block
            // this thread waiting for the user's response! After the user
            // sees the explanation, try again to request the permission.
        } else {

            // No explanation needed; request the permission
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 200);

            // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
            // app-defined int constant. The callback method gets the
            // result of the request.
        }
    } else {
        Log.i("Permission","gotit");
        // Permission has already been granted
    }
}
    @Override
    public void onRequestPermissionsResult(int requestCode,
      String permissions[], int[] grantResults) {
        switch (requestCode) {
            case 200: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // permission was granted, yay! Do the
                    // contacts-related task you need to do.
                } else {
                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                }
                return;
            }

            // other 'case' lines to check for other
            // permissions this app might request.
        }
    }

public void ToXls(View view){
    if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
            != PackageManager.PERMISSION_GRANTED) {
        ///create a intent to go to app setting page to grant it
    }else{
    try{
        Log.i("Permission","gotit");
        SQLiteToExcel sqliteToExcel = new SQLiteToExcel(this, "booksmain");
        sqliteToExcel. exportAllTables ("books.xls", new SQLiteToExcel.ExportListener() {
            @Override
            public void onStart() {
            }
            @Override
            public void onCompleted(String filePath) {
                Toast.makeText(MainActivity.this, "Create file at "+filePath, Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onError(Exception e) {
            }
        });
    }catch (Exception e){
        e.printStackTrace();
        Toast.makeText(this, "failed", Toast.LENGTH_SHORT).show();
    }
}}
public void DisplayBookList(View view){
    Intent intent=new Intent(this,Display.class);
    startActivity(intent);
}
    public void AddStudent(View view){
        Intent intent=new Intent(this,StudentReg.class);
        startActivity(intent);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        PermissionRequest1();

    }
    public void DisplayStudent1(View v){
        Intent intent=new Intent(this,StudentListDisplayXml.class);
        startActivity(intent);
    }

}
