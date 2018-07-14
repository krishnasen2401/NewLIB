package com.example.laksh_000.newlib;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class StudentFullDisplay extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_studen_display_full);
        Bundle bundle = getIntent().getExtras();
        ImageView v1=findViewById(R.id.imageView);
        if(bundle.getString("gender").equals("Male"))
            v1.setImageResource(R.drawable.boy);
        else
            v1.setImageResource(R.drawable.girl);

        TextView id1 = findViewById(R.id.StidDis);
        id1.setText(bundle.getString("id"));
        TextView name = findViewById(R.id.StNameDis);
        name.setText(bundle.getString("name"));
        TextView age = findViewById(R.id.Stuage);
        age.setText(bundle.getString("age"));
        TextView classes = findViewById(R.id.Stclassdis);
        classes.setText(bundle.getString("class"));
        TextView pnam1 = findViewById(R.id.Studp1);
        pnam1.setText(bundle.getString("pname1"));
        TextView pnum1 = findViewById(R.id.Stupn1);
        final String pnum11 = bundle.getString("pnum1");
        pnum1.setText(pnum11);
        TextView pnam2 = findViewById(R.id.Stup2);
        pnam2.setText(bundle.getString("pname2"));
        TextView pnum2 = findViewById(R.id.Stupn2);
        String pnum22 = bundle.getString("pnum1");
        pnum2.setText(pnum22);
        pnum1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent callIntent = new Intent(Intent.ACTION_DIAL);//ACTION_CALL requries permission for dial not required and also permission check
               // if (ActivityCompat.checkSelfPermission(StudentFullDisplay.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                  //  return;
                //}
                callIntent.setData(Uri.parse("tel:"+pnum11));
                startActivity(callIntent);
            }
        });
    }
}
