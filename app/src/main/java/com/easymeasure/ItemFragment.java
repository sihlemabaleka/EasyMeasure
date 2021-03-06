package com.easymeasure;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.Toast;

import com.easymeasure.model.Client;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;
import com.parse.SaveCallback;

import java.util.ArrayList;
import java.util.List;

public class ItemFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {

    SwipeRefreshLayout mRefreshLayout;
    RecyclerView recyclerView;
    ImageButton btnRefresh;
    RecyclerView.Adapter adapter;
    List<Client> clients = new ArrayList<Client>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_item_list, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.list);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext()));
        adapter = new MyItemRecyclerViewAdapter(getActivity(), clients);
        mRefreshLayout = (SwipeRefreshLayout) getActivity().findViewById(R.id.swipeRefreshLayout);
        btnRefresh = (ImageButton) getActivity().findViewById(R.id.refresh);
        btnRefresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mRefreshLayout.setRefreshing(true);
                getClientsFromCloud();
            }
        });
        mRefreshLayout.setOnRefreshListener(this);
        mRefreshLayout.setRefreshing(true);
        getClientsFromLocalDatastore();
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        mRefreshLayout.setRefreshing(true);
        getClientsFromLocalDatastore();
    }

    public void getClientsFromLocalDatastore() {
        ParseQuery query = ParseQuery.getQuery(Client.class);
        query.fromLocalDatastore();
        query.findInBackground(new FindCallback() {
            @Override
            public void done(List objects, ParseException e) {
                if (e == null) {
                    if (objects.size() > 0) {
                        clients.addAll(objects);
                        recyclerView.setAdapter(adapter);
                        mRefreshLayout.setRefreshing(false);
                    } else {
                        getClientsFromCloud();
                    }
                } else {
                    getClientsFromCloud();
                }
            }

            @Override
            public void done(Object client, Throwable throwable) {
                if (client == null) {
                    throwable.printStackTrace();
                } else {
                    client.toString();
                }
            }
        });
    }

    public void getClientsFromCloud() {
        ParseQuery<Client> query = ParseQuery.getQuery(Client.class);
        query.addDescendingOrder("created_at");
        query.findInBackground(new FindCallback<Client>() {
            @Override
            public void done(final List<Client> objects, ParseException e) {
                if (e == null) {
                    Client.unpinAllInBackground();
                    Client.pinAllInBackground(objects, new SaveCallback() {
                        @Override
                        public void done(ParseException e) {
                            if (e == null) {
                                mRefreshLayout.setRefreshing(false);
                                recyclerView.setAdapter(new MyItemRecyclerViewAdapter(getActivity(), objects));
                            } else {
                                mRefreshLayout.setRefreshing(false);
                                Toast.makeText(getActivity(), "Couldn't Load Clients", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                } else {
                    mRefreshLayout.setRefreshing(false);
                    Toast.makeText(getActivity(), "No Clients...", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public void onRefresh() {
        getClientsFromCloud();
    }
}
