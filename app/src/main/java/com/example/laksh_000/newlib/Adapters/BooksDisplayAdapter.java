package com.example.laksh_000.newlib.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.laksh_000.newlib.DataFiles.BookidData;
import com.example.laksh_000.newlib.Helpers.MainDatabasehelper;
import com.example.laksh_000.newlib.R;

import java.util.List;

public class BooksDisplayAdapter  extends RecyclerView.Adapter<BooksDisplayAdapter.ViewHolder> {
    private List<BookidData> mPeopleList;
    private Context mContext;
    private RecyclerView mRecyclerV;


    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView bookid;
        public TextView bookname;
        public Button modifybt;
        public View layout;

        public ViewHolder(View v) {
            super(v);
            layout = v;
            bookid = v.findViewById(R.id.bookidDP);
            bookname = v.findViewById(R.id.bookName);
            modifybt=v.findViewById(R.id.Deletebk);
        }
    }

    public void add(int position, BookidData person) {
        mPeopleList.add(position, person);
        notifyItemInserted(position);
    }

    public void remove(int position) {
        mPeopleList.remove(position);
        notifyItemRemoved(position);
    }



    // Provide a suitable constructor (depends on the kind of dataset)
    public BooksDisplayAdapter(List<BookidData> myDataset, Context context, RecyclerView recyclerView) {
        mPeopleList = myDataset;
        mContext = context;
        mRecyclerV = recyclerView;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public BooksDisplayAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                       int viewType) {
        // create a new view
        LayoutInflater inflater = LayoutInflater.from(
                parent.getContext());
        View v =
                inflater.inflate(R.layout.recycle_cell_view, parent, false);
        // set the view's size, margins, paddings and layout parameters
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element

        final BookidData booksnames1 = mPeopleList.get(position);
        holder.bookid.setText("ID: " + booksnames1.getId());
        holder.bookname.setText("Name: " + booksnames1.getName());
       holder.modifybt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                        MainDatabasehelper dbHelper = new MainDatabasehelper(mContext);
                        dbHelper.deletebookRecord(booksnames1.getId(), mContext);
                        mPeopleList.remove(position);
                        mRecyclerV.removeViewAt(position);
                        notifyItemRemoved(position);
                        notifyItemRangeChanged(position, mPeopleList.size());
                        notifyDataSetChanged();
            }
        });


    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mPeopleList.size();
    }

}
