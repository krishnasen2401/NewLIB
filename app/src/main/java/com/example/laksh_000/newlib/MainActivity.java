package com.example.laksh_000.newlib;

import android.Manifest;


import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;
import com.ajts.androidmads.library.SQLiteToExcel;
import com.example.laksh_000.newlib.Helpers.BookRecord;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.drive.Drive;
import com.google.android.gms.drive.DriveClient;
import com.google.android.gms.drive.DriveContents;
import com.google.android.gms.drive.DriveFolder;
import com.google.android.gms.drive.DriveResourceClient;
import com.google.android.gms.drive.MetadataChangeSet;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;


public class MainActivity extends AppCompatActivity{
    private static final int REQUEST_CODE_SIGN_IN = 0;
    private DriveClient mDriveClient;
    private DriveResourceClient mDriveResourceClient;
    protected GoogleSignInClient mGoogleSignInClient;
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

                }
                return;
            }

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
    public void signIn() {
        mGoogleSignInClient= buildGoogleSignInClient();
        startActivityForResult(mGoogleSignInClient.getSignInIntent(), REQUEST_CODE_SIGN_IN);
    }
    public GoogleSignInClient buildGoogleSignInClient() {
        GoogleSignInOptions signInOptions =
                new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                        .requestScopes(Drive.SCOPE_APPFOLDER)
                        .requestScopes(Drive.SCOPE_FILE)
                        .build();
        return GoogleSignIn.getClient(this, signInOptions);
    }
    protected void onActivityResult(final int requestCode, final int resultCode, final Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case REQUEST_CODE_SIGN_IN:
                Log.i("account", "Sign in request code");
                // Called after user is signed in.
                if (resultCode == RESULT_OK) {
                    Log.i("account", "Signed in successfully.");
                    // Use the last signed in account here since it already have a Drive scope.
                    mDriveClient = Drive.getDriveClient(this, GoogleSignIn.getLastSignedInAccount(this));
                    // Build a drive resource client.
                    mDriveResourceClient =
                            Drive.getDriveResourceClient(this, GoogleSignIn.getLastSignedInAccount(this));
                    // Start backup.
                    createFileInAppFolder();
                }
                break;
        }
    }
    public void Gdrivebackup(View v) throws IOException {
        BookRecord db=new BookRecord(this);
        db.backupDB(this);
        signIn();
    }
    private void createFileInAppFolder() {
        final String packageName = this.getPackageName();
        String DB_FILEPATH = "/data/data/" + packageName + "/databases/booksmain";
        String inFileName = DB_FILEPATH;
        File dbFile = new File(inFileName);

        final Task<DriveFolder> appFolderTask = mDriveResourceClient.getAppFolder();
        final Task<DriveContents> createContentsTask = mDriveResourceClient.createContents();
        Tasks.whenAll(appFolderTask, createContentsTask)
                .continueWithTask(task -> {
                    DriveFolder parent = appFolderTask.getResult();
                    DriveContents contents = createContentsTask.getResult();
                    //OutputStream outputStream = contents.getOutputStream();
                   // try (Writer writer = new OutputStreamWriter(outputStream)) {
                     //   writer.write("Hello World!");
                    //}
                    FileInputStream fis = new FileInputStream(dbFile);
                    OutputStream output = contents.getOutputStream();
                    // transfer bytes from the inputfile to the outputfile
                    byte[] buffer = new byte[1024];
                    int length;
                    while ((length = fis.read(buffer)) > 0) {
                        output.write(buffer, 0, length);
                    }
                    // Close the streams
                    output.flush();
                    output.close();
                    fis.close();
                    MetadataChangeSet changeSet = new MetadataChangeSet.Builder()
                            .setTitle("School")
                            //.setMimeType("application/octet-stream")
                            .setStarred(true)
                            .build();
                    return mDriveResourceClient.createFile(parent, changeSet, contents);
                })
                .addOnSuccessListener(this,
                        driveFile -> {
                    Log.i("status creation","created");
                            //finish(); intent will close here
                        })
                .addOnFailureListener(this, e -> {
                    Log.e("file", "Unable to create file", e);
                    //finish();
                });
    }

}
