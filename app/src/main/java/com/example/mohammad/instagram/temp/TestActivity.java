package com.example.mohammad.instagram.temp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mohammad.instagram.R;

public class TestActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private View imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        LinearLayout linearLayout = findViewById(R.id.linear_lo);
        imageView = findViewById(R.id.my_image);


//        mRecyclerView = findViewById(R.id.my_recycler_view);
//
//        // drawable_edittext_username this setting to improve performance if you know that changes
//        // in content do not change the layout size of the RecyclerView
//        mRecyclerView.setHasFixedSize(true);
//
//        // drawable_edittext_username a linear layout manager
//        mLayoutManager = new LinearLayoutManager(this);
//        mRecyclerView.setLayoutManager(mLayoutManager);
//
//        // specify an adapter (see also next example)
//        String[] myDataset = new String[]{
//                "ahmad","mahdi","mahdi","mahdi","mahdi","mahdi",
//                "mahdi","mohammad","mohammad","mohammad","mohammad",
//                "mohammad","mohammad","mohammad","mohammad","mohammad",
//                "hasan",
//                "hosein",
//                "ahmad","mohammad","mohammad","mohammad",
//                "mahdi",
//                "mohammad",
//                "hasan","mohammad","mohammad","mohammad","mohammad","mohammad",
//                "hosein","ahmad",
//                "mahdi",
//                "mohammad","hasan","hasan","hasan","hasan","hasan","hasan","hasan","hasan",
//                "hasan",
//                "hosein","ahmad",
//                "mahdi",
//                "mohammad",
//                "hasan","hasan","hasan","hasan","hasan","hasan",
//                "hosein","ahmad",
//                "mahdi",
//                "mohammad",
//                "hasan",
//                "hosein","ahmad",
//                "mahdi",
//                "mohammad",
//                "hasan",
//                "hosein"
//        };
//        mAdapter = new MyAdapter(myDataset);
//        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    protected void onResume() {
        super.onResume();

        ScreenUtility screenUtility = new ScreenUtility(this);
        int utilDpWidth = (int) screenUtility.getDpWidth();
        int utilPxWidth = (int) screenUtility.getRealPxWidth();
        int realWidth = imageView.getLayoutParams().width;


        final String text = "Real width: " + realWidth +
                "\nUtil DP width: " + utilDpWidth +
                "\nUtil PX width: " + utilPxWidth;
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast toast = Toast.makeText(getApplicationContext(), text, Toast.LENGTH_LONG);
                toast.setGravity(Gravity.CENTER, 0, 0);
                toast.show();
            }
        });
    }
}

class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    private String[] mDataset;

    // Provide a suitable constructor (depends on the kind of dataset)
    public MyAdapter(String[] myDataset) {
        mDataset = myDataset;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public MyAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent,
                                                     int viewType) {
        // create a new view
        TextView v = (TextView) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.my_text_view, parent, false);
        MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        holder.mTextView.setText(mDataset[position]);

    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.length;
    }

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class MyViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView mTextView;

        public MyViewHolder(TextView v) {
            super(v);
            mTextView = v;
        }
    }
}
