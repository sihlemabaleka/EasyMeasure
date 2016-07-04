package com.easymeasure;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.easymeasure.model.BaseClothingMeasurements;
import com.easymeasure.model.Client;
import com.easymeasure.model.ClothesBedding;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;

import java.util.List;

/**
 * Created by geekulcha on 6/28/16.
 */
public class ClientMeasurementDetails extends Fragment {

    TextView mChestLength, mWaistLength, mHipsLength, mShoulderLength, mSleeveLength;


    public static ClientMeasurementDetails newInstance(String objectID) {
        ClientMeasurementDetails fragment = new ClientMeasurementDetails();
        Bundle args = new Bundle();
        args.putString("objectId", objectID);
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.ui_view_measurements, container, false);

        mChestLength = (TextView) v.findViewById(R.id.chest_length);
        mWaistLength = (TextView) v.findViewById(R.id.waist_length);
        mHipsLength = (TextView) v.findViewById(R.id.hips_length);
        mShoulderLength = (TextView) v.findViewById(R.id.shoulder_length);
        mSleeveLength = (TextView) v.findViewById(R.id.sleeve_length);

        setMeasurementValues();
        return v;
    }

    public void setMeasurementValues() {

        ParseQuery user = ParseQuery.getQuery(Client.class);
        user.whereEqualTo("objectId", getArguments().getString("objectId"));

        ParseQuery query = ParseQuery.getQuery(ClothesBedding.class);
        query.whereMatchesQuery("client", user);
        query.findInBackground(new FindCallback() {
            @Override
            public void done(List objects, ParseException e) {
                if (e == null) {
                    mChestLength.setText("" + ((BaseClothingMeasurements) objects.get(0)).getChest());
                    mWaistLength.setText("" + ((BaseClothingMeasurements) objects.get(0)).getWaist());
                    mHipsLength.setText("" + ((BaseClothingMeasurements) objects.get(0)).getHip());
                    mShoulderLength.setText("" + ((BaseClothingMeasurements) objects.get(0)).getShoulder());
                    mSleeveLength.setText("" + ((BaseClothingMeasurements) objects.get(0)).getSleeve());
                } else {
                    e.printStackTrace();
                }
            }

            @Override
            public void done(Object o, Throwable throwable) {

            }
        });
    }
}
