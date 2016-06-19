package com.easymeasure;

import android.content.Context;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.RecyclerView;
import android.text.format.DateUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.easymeasure.model.Order;
import com.squareup.picasso.Picasso;

import java.util.Date;
import java.util.List;

public class MyItemRecyclerViewAdapter extends RecyclerView.Adapter<MyItemRecyclerViewAdapter.ViewHolder> {

    private final List<Order> mValues;
    private Context mContext;

    public MyItemRecyclerViewAdapter(Context context, List<Order> items) {
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
            Order order = mValues.get(position);
            holder.mItem = order;
            holder.mIdView.setText(mValues.get(position).getClient().getClientName());
            holder.mContentView.setText(order.getClient().getClientGender() + " size : " + order.getClient().getClientDeaultSize());
            Picasso.with(mContext).load(order.getClient().getClientProfilePicture().getUrl()).into(holder.profilePicture);
            holder.btnCompleted.setText(order.getClient().getClientOrderCount() - order.getClient().getClientPendingOrderCount());
            holder.btnPending.setText(order.getClient().getClientPendingOrderCount());
            holder.btnTimeStamp.setText(setTimestamp(order.getClient().getUpdatedAt()));
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

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public final View mView;
        public final TextView mIdView;
        public final TextView mContentView;
        public ImageView profilePicture;
        public Button btnCompleted, btnPending, btnTimeStamp;
        public Order mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mIdView = (TextView) view.findViewById(R.id.client_name);
            mContentView = (TextView) view.findViewById(R.id.size_and_gender);
            profilePicture = (ImageView) view.findViewById(R.id.profile_image);
            btnCompleted = (Button) view.findViewById(R.id.btn_completed);
            btnPending = (Button) view.findViewById(R.id.btn_pending);
            btnTimeStamp = (Button) view.findViewById(R.id.btn_timestamp);

            view.setOnClickListener(this);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mContentView.getText() + "'";
        }

        @Override
        public void onClick(View v) {
            Snackbar.make(v, "You clicked hommie", Snackbar.LENGTH_SHORT).setAction("cancel", new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
        }
    }
}
