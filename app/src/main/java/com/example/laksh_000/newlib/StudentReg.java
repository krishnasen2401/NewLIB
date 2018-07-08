package com.example.laksh_000.newlib;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.laksh_000.newlib.DataFiles.StudentIDdata;
import com.example.laksh_000.newlib.Helpers.Databasehelper;

import java.util.Calendar;

public class StudentReg extends AppCompatActivity implements AdapterView.OnItemClickListener, AdapterView.OnItemSelectedListener {
    public Databasehelper h1;
    private String genderTX;
    private String classesTX;
    EditText age11;

public void PickDate(View view){
    age11=findViewById(R.id.age);
    final Calendar c = Calendar.getInstance();int mYear = c.get(Calendar.YEAR);
    int mMonth = c.get(Calendar.MONTH);
    int mDay = c.get(Calendar.DAY_OF_MONTH);
   // DatePickerDialog datepickerdialog = new DatePickerDialog(this,AlertDialog.THEME_HOLO_LIGHT,ondatelinsten part,year,month,day);
    DatePickerDialog datePickerDialog = new DatePickerDialog(this, AlertDialog.THEME_HOLO_LIGHT,
            new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year,int monthOfYear, int dayOfMonth) {
                    age11.setText(String.format("%02d-%02d-%04d",dayOfMonth,monthOfYear+1,year));
                    //age11.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);
                }
            }, mYear, mMonth, mDay);
    datePickerDialog.show();
    }
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Log.i("Dropdown",parent.getItemAtPosition(position).toString());
        classesTX=parent.getItemAtPosition(position).toString();
        Log.i("Dropdown",parent.getItemAtPosition(position).toString());
        genderTX=parent.getItemAtPosition(position).toString();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_reg);
        Spinner spinner = findViewById(R.id.classes1);
        Spinner spinner2 = findViewById(R.id.gender);
        spinner.setOnItemSelectedListener(this);
        spinner2.setOnItemSelectedListener(this);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.classeslt, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        adapter=ArrayAdapter.createFromResource(this,
                R.array.genderlt, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner2.setAdapter(adapter);
    }
    public void onItemSelected(AdapterView<?> parent, View view,
                               int pos, long id) {
        classesTX=parent.getItemAtPosition(pos).toString();
        Log.i("Changed Drop",classesTX);
        genderTX=parent.getItemAtPosition(pos).toString();
        Log.i("Changed Drop",genderTX);
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


        String SID1,SName1,SClass1,SParent11,SParent22,gender;
        String age12;
        age12=age11.getText().toString();
        Long SPhone11,SPhone22;
        SID1=SID.getText().toString();
        SName1=SName.getText().toString();
        SClass1=classesTX;
        gender=genderTX;
        SParent11=SParent1.getText().toString();
        SPhone11= Long.valueOf(SPhone1.getText().toString());
        SParent22=SParent2.getText().toString();
        SPhone22= Long.valueOf(SPhone2.getText().toString());
        Log.i("Phone Test",SPhone11.toString());
        h1=new Databasehelper(this);
        StudentIDdata sds=new StudentIDdata(SID1,SName1,age12,gender,SClass1,SParent11,SPhone11,SParent22,SPhone22);
        h1.addNewStudent(sds,this);
    }
}
