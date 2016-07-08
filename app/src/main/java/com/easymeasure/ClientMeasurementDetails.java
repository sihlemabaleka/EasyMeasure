package com.easymeasure;

import android.app.Fragment;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.easymeasure.model.BaseClothingMeasurements;
import com.easymeasure.model.Client;
import com.parse.ParseQuery;

public class ClientMeasurementDetails extends Fragment {

    TextView mChestLength, mWaistLength, mHipsLength, mShoulderLength, mSleeveLength;
    ProgressDialog pDialog;

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
        if (savedInstanceState == null)
            new getMeasurementValues().execute();
        return v;
    }

    class getMeasurementValues extends AsyncTask<Void, Void, BaseClothingMeasurements> {
        @Override
        protected void onPreExecute() {
            pDialog = new ProgressDialog(getActivity());
            pDialog.setTitle("");
            pDialog.setMessage("Just A Moment...");
            pDialog.show();
        }

        @Override
        protected BaseClothingMeasurements doInBackground(Void... params) {
            try {
                ParseQuery<Client> user = ParseQuery.getQuery(Client.class);
                user.whereEqualTo("objectId", getArguments().getString("objectId"));

                ParseQuery<BaseClothingMeasurements> query = ParseQuery.getQuery(BaseClothingMeasurements.class);
                query.whereMatchesQuery("client", user);
                return query.getFirst();
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }

        @Override
        protected void onPostExecute(BaseClothingMeasurements baseClothingMeasurements) {
            super.onPostExecute(baseClothingMeasurements);
            pDialog.dismiss();
            if (baseClothingMeasurements != null) {
                mChestLength.setText(baseClothingMeasurements.getChest() + " cm");
                mWaistLength.setText(baseClothingMeasurements.getWaist() + " cm");
                mHipsLength.setText(baseClothingMeasurements.getHip() + " cm");
                mShoulderLength.setText(baseClothingMeasurements.getShoulder() + " cm");
                mSleeveLength.setText(baseClothingMeasurements.getSleeve() + " cm");
            } else {
                Toast.makeText(getActivity(), "An error occured. Please try again later", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
