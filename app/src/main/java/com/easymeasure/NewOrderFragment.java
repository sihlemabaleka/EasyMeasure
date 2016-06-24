package com.easymeasure;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.easymeasure.model.Client;
import com.easymeasure.model.Order;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;

import java.util.List;

public class NewOrderFragment extends Fragment {

    private Button btnNewOrder;

    public static NewOrderFragment newInstance(String objectId) {
        NewOrderFragment fragment = new NewOrderFragment();
        Bundle args = new Bundle();
        args.putString("objectId", objectId);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.client_details, container, false);
        final RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        ParseQuery clientQuery = ParseQuery.getQuery(Client.class);
        clientQuery.whereEqualTo("objectId", getArguments().getString("objectId"));
        ParseQuery<Order> query = ParseQuery.getQuery(Order.class);
        query.whereMatchesQuery("client", clientQuery);
        query.addAscendingOrder("created_at");
        query.findInBackground(new FindCallback<Order>() {
            @Override
            public void done(List<Order> objects, ParseException e) {
                if (e == null) {
                    recyclerView.setAdapter(new OrderRecyclerView(getActivity(), objects));
                }
            }
        });

        btnNewOrder = (Button) view.findViewById(R.id.new_order);
        btnNewOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


        return view;
    }
}
