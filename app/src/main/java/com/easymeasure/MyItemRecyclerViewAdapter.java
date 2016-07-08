package com.easymeasure;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.text.format.DateUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.easymeasure.model.Client;

import java.util.Date;
import java.util.List;

public class MyItemRecyclerViewAdapter extends RecyclerView.Adapter<MyItemRecyclerViewAdapter.ViewHolder> {

    private final List<Client> mValues;
    private Activity mContext;

    public MyItemRecyclerViewAdapter(Activity context, List<Client> items) {
        this.mValues = items;
        this.mContext = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.main_recyclerview_item_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        try {
            Client client = mValues.get(position);
            holder.mItem = client;
            holder.mIdView.setText(client.getClientName());
            holder.mContentView.setText(client.getClientGender().toString());
            holder.mSizeView.setText(client.getClientDeaultSize().toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    private CharSequence setTimestamp(Date createdAt) {
        // TODO Auto-generated method stub
        return DateUtils.getRelativeTimeSpanString(createdAt.getTime(),
                System.currentTimeMillis(), DateUtils.SECOND_IN_MILLIS);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView mIdView, mContentView, mSizeView;
        public Client mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mIdView = (TextView) view.findViewById(R.id.client_name);
            mContentView = (TextView) view.findViewById(R.id.gender);
            mSizeView = (TextView) view.findViewById(R.id.size);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mContext.getFragmentManager().beginTransaction().replace(R.id.container, ClientMeasurementDetails.newInstance(mItem.getObjectId())).addToBackStack("").commit();
                }
            });
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mContentView.getText() + "'";
        }

    }
}
