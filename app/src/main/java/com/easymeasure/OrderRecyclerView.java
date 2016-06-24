package com.easymeasure;

import android.content.Context;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.RecyclerView;
import android.text.format.DateUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.easymeasure.model.Order;

import java.util.Date;
import java.util.List;

public class OrderRecyclerView extends RecyclerView.Adapter<OrderRecyclerView.ViewHolder> {

    private final List<Order> mValues;
    private Context mContext;

    public OrderRecyclerView(Context context, List<Order> items) {
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
        public final TextView mIdView, mContentView, mSizeView;
        public Button btnCompleted, btnPending, btnTimeStamp;
        public Order mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mIdView = (TextView) view.findViewById(R.id.client_name);
            mContentView = (TextView) view.findViewById(R.id.gender);
            mSizeView = (TextView) view.findViewById(R.id.size);
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
