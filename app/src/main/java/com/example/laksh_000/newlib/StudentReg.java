package com.example.laksh_000.newlib;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.laksh_000.newlib.DataFiles.StudentIDdata;
import com.example.laksh_000.newlib.Helpers.Databasehelper;

public class StudentReg extends AppCompatActivity implements AdapterView.OnItemClickListener, AdapterView.OnItemSelectedListener {
    public Databasehelper h1;

    private String classesTX;
    @Override

    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Log.i("Dropdown",parent.getItemAtPosition(position).toString());
        classesTX=parent.getItemAtPosition(position).toString();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_reg);
        Spinner spinner = (Spinner) findViewById(R.id.spinner1);
        spinner.setOnItemSelectedListener(this);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.classes, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
    }
    public void onItemSelected(AdapterView<?> parent, View view,
                               int pos, long id) {
        classesTX=parent.getItemAtPosition(pos).toString();
        Log.i("Changed Drop",classesTX);
    }
    public void onNothingSelected(AdapterView<?> parent) {
        ///classesTX=parent.getItemAtPosition(0).toString();
        //Log.i("Nothing Drop",classesTX);
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    public void SubmitStudent(View view){
        EditText SID=findViewById(R.id.studentIDREG);
        EditText SName=findViewById(R.id.studentNameREG);
        EditText SParent1=findViewById(R.id.StudentP1);
        EditText SPhone1=findViewById(R.id.StudentPH1);
        EditText SParent2=findViewById(R.id.StudentP2);
        EditText SPhone2=findViewById(R.id.StudentPH2);
        String SID1,SName1,SClass1,SParent11,SParent22;
        Long SPhone11,SPhone22;
        SID1=SID.getText().toString();
        SName1=SName.getText().toString();
        SClass1=classesTX;
        SParent11=SParent1.getText().toString();
        SPhone11= Long.valueOf(SPhone1.getText().toString());
        SParent22=SParent2.getText().toString();
        SPhone22= Long.valueOf(SPhone2.getText().toString());
        Log.i("Phone Test",SPhone11.toString());
        h1=new Databasehelper(this);
        StudentIDdata sds=new StudentIDdata(SID1,SName1,SClass1,SParent11,SPhone11,SParent22,SPhone22);
        h1.addNewStudent(sds,this);
    }
}
