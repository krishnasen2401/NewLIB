package com.example.laksh_000.newlib.Adapters;

import android.content.Context;
import android.content.Intent;
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
                intent.putExtra("Dataid",studentnames1.getId());
                mContext1.startActivity(intent);
                Log.i("Click Status","Long CLicked at"+studentnames1.getId());
                return true;
            }

        });
        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v) {
                Log.i("Click Status","Short CLicked at"+studentnames1.getId());
            }
        });

    }
    public int getItemCount(){return  mlists.size();}
}
