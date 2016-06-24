package com.easymeasure;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.easymeasure.model.Client;
import com.easymeasure.model.Order;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;

import java.util.List;

/**
 * Created by geekulcha on 6/24/16.
 */
public class ClientOrderDetails extends Activity {

    Intent intent;
    Button btnNewOrder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.client_details);

        intent = getIntent();
        intent.getExtras();
        final RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        ParseQuery clientQuery = ParseQuery.getQuery(Client.class);
        clientQuery.whereEqualTo("objectId", intent.getStringExtra("objectId"));
        ParseQuery<Order> query = ParseQuery.getQuery(Order.class);
        query.whereMatchesQuery("client", clientQuery);
        query.addAscendingOrder("created_at");
        query.findInBackground(new FindCallback<Order>() {
            @Override
            public void done(List<Order> objects, ParseException e) {
                if (e == null) {
                    recyclerView.setAdapter(new OrderRecyclerView(getApplicationContext(), objects));
                }
            }
        });

        btnNewOrder = (Button) findViewById(R.id.new_order);
        btnNewOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


    }
}
