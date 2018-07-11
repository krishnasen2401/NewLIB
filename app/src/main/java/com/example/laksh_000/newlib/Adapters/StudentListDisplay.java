package com.example.laksh_000.newlib.Adapters;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.laksh_000.newlib.DataFiles.StudentIDdata;
import com.example.laksh_000.newlib.R;
import com.example.laksh_000.newlib.StudentFullDisplay;

import java.util.List;

public class StudentListDisplay extends RecyclerView.Adapter<StudentListDisplay.ViewHolder>{
    private List<StudentIDdata> mlists;
    private Context mContext1;
    private RecyclerView mRecyclerV1;
    public class ViewHolder extends RecyclerView.ViewHolder{
    public TextView Studentid,classname,Studentname;
    public View layout;
        public ViewHolder(View v) {
            super(v);
            layout=v;
            Studentid=v.findViewById(R.id.StudentIDList);
            classname=v.findViewById(R.id.ClassIdList);
            Studentname=v.findViewById(R.id.StudentNameList);
        }
    }
   public void add(int position, StudentIDdata ListData){
        mlists.add(position,ListData);
        notifyItemInserted(position);
   }
    public StudentListDisplay(List<StudentIDdata> myDataset, Context context, RecyclerView recyclerView) {
        mlists = myDataset;
        mContext1 = context;
        mRecyclerV1 = recyclerView;
    }
    public StudentListDisplay.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                             int viewType){
        LayoutInflater inflater = LayoutInflater.from(
                parent.getContext());
        View v =inflater.inflate(R.layout.recycle_cell_studentall, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }
    public void onBindViewHolder(ViewHolder holder, final int position){
        final StudentIDdata studentnames1=mlists.get(position);
        holder.Studentid.setText(studentnames1.getId());
        holder.Studentname.setText(studentnames1.getName());
        holder.classname.setText(studentnames1.getSClass());
        holder.layout.setOnLongClickListener(new View.OnLongClickListener() {

            @Override
            public boolean onLongClick(View v) {
                Intent intent=new Intent(mContext1,StudentFullDisplay.class);
                Bundle b = new Bundle();
                b.putString("id",studentnames1.getId());
                b.putString("name",studentnames1.getName());
                b.putString("age",studentnames1.getAge());
                b.putString("gender",studentnames1.getGender());
                b.putString("class",studentnames1.getSClass());
                b.putString("pname1",studentnames1.getName1());
                b.putString("pnum1", String.valueOf(studentnames1.getPhone1()));
                b.putString("pname2",studentnames1.getName2());
                b.putString("pnum2", String.valueOf(studentnames1.getPhone2()));
                intent.putExtras(b);
                mContext1.startActivity(intent);

                return true;
            }

        });
        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v) {
               /* String Datatester=studentnames1.getId()+":"+studentnames1.getName()+":"+studentnames1.getAge()+":"+studentnames1.getGender()+":"+studentnames1.getSClass()+":"+studentnames1.getName1()+":"+studentnames1.getPhone1()+":"+studentnames1.getName2()+":"+studentnames1.getPhone2();
                Log.i("id",studentnames1.getId());
                Log.i("name",studentnames1.getName());
                Log.i("age",studentnames1.getAge());
                Log.i("gender",studentnames1.getGender());
                Log.i("class", studentnames1.getSClass());
                Log.i("name1",studentnames1.getName1());
                Log.i("Phone1", String.valueOf(studentnames1.getPhone1()));
                Log.i("name2",studentnames1.getName2());
                Log.i("phone2", String.valueOf(studentnames1.getPhone2()));*/
            }
        });

    }
    public int getItemCount(){return  mlists.size();}
}
