package com.easymeasure;

import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.easymeasure.model.BaseClothingMeasurements;
import com.easymeasure.model.Client;
import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;
import com.parse.SaveCallback;

import java.util.List;

public class EditMeasureMentsFragment extends Fragment {

    ProgressDialog pDialog;
    private TextInputEditText mChestLength, mWaistLength, mHipsLegnth, mShoulderLength, mSleeveLength;
    private LinearLayout lClothingLayout, lBeddingLayout;
    private Button btnProceed, btnDelete;
    private String ChestLength, WaistLength, HipsLegnth, ShoulderLength, SleeveLength;

    private TextWatcher mTextWatcher = new TextWatcher() {

        @Override
        public void onTextChanged(CharSequence s, int start, int before,
                                  int count) {
            // TODO Auto-generated method stub

        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count,
                                      int after) {
            // TODO Auto-generated method stub
        }

        @Override
        public void afterTextChanged(Editable s) {
            // TODO Auto-generated method stub
            checkFieldsForEmptyValues();
        }
    };

    public static EditMeasureMentsFragment newInstance(String objectId, String mChestLength, String mWaistLength, String mHipsLegnth, String mShoulderLength, String mSleeveLength) {
        EditMeasureMentsFragment fragment = new EditMeasureMentsFragment();
        Bundle args = new Bundle();
        args.putString("objectId", objectId);
        args.putString("chest_length", mChestLength);
        args.putString("waist_length", mWaistLength);
        args.putString("hips_length", mHipsLegnth);
        args.putString("shoulder_length", mShoulderLength);
        args.putString("sleeve_length", mSleeveLength);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_edit_measurements, container, false);


        mChestLength = (TextInputEditText) v.findViewById(R.id.chest_length);
        mWaistLength = (TextInputEditText) v.findViewById(R.id.waist_length);
        mHipsLegnth = (TextInputEditText) v.findViewById(R.id.hips_length);
        mShoulderLength = (TextInputEditText) v.findViewById(R.id.shoulder_length);
        mSleeveLength = (TextInputEditText) v.findViewById(R.id.sleeve_length);

        lClothingLayout = (LinearLayout) v.findViewById(R.id.clothing_layout);
        lBeddingLayout = (LinearLayout) v.findViewById(R.id.bedding_layout);

        pDialog = new ProgressDialog(getActivity());
        pDialog.setTitle("Working in the background. Just a moment...");
        pDialog.setCancelable(true);

        mChestLength.addTextChangedListener(mTextWatcher);
        mWaistLength.addTextChangedListener(mTextWatcher);
        mHipsLegnth.addTextChangedListener(mTextWatcher);
        mShoulderLength.addTextChangedListener(mTextWatcher);
        mSleeveLength.addTextChangedListener(mTextWatcher);
        btnProceed = (Button) v.findViewById(R.id.save);
        btnDelete = (Button) v.findViewById(R.id.delete);

        setEditFieldValues();
        btnProceed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveClientClothingOrder();
            }
        });
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pDialog.show();
                ParseQuery<BaseClothingMeasurements> query = ParseQuery.getQuery(BaseClothingMeasurements.class);
                query.getInBackground(getArguments().getString("objectId"), new GetCallback<BaseClothingMeasurements>() {
                    public void done(BaseClothingMeasurements object, ParseException e) {
                        if (e == null) {
                            object.getClient().deleteInBackground();
                            object.deleteInBackground();
                            refreshClients();
                        } else {
                            // something went wrong
                        }
                    }
                });
            }
        });

        lClothingLayout.setVisibility(View.VISIBLE);


        return v;
    }

    protected void checkFieldsForEmptyValues() {
        // TODO Auto-generated method stub
        String text1 = mChestLength.getText().toString().trim();
        String text2 = mWaistLength.getText().toString().trim();
        String text3 = mHipsLegnth.getText().toString().trim();
        String text4 = mShoulderLength.getText().toString().trim();
        String text5 = mSleeveLength.getText().toString().trim();

        if ((TextUtils.isEmpty(text1)) || (TextUtils.isEmpty(text2)) || (TextUtils.isEmpty(text3)) || (TextUtils.isEmpty(text4)) || (TextUtils.isEmpty(text5))) {
            btnProceed.setVisibility(View.GONE);
        } else {
            btnProceed.setVisibility(View.VISIBLE);
        }
    }

    public void setEditFieldValues() {
        mChestLength.setText(getArguments().getString("chest_length"));
        mWaistLength.setText(getArguments().getString("waist_length"));
        mHipsLegnth.setText(getArguments().getString("hips_length"));
        mShoulderLength.setText(getArguments().getString("shoulder_length"));
        mSleeveLength.setText(getArguments().getString("sleeve_length"));
    }

    public void saveClientClothingOrder() {
        pDialog.show();
        ParseQuery<BaseClothingMeasurements> query = ParseQuery.getQuery(BaseClothingMeasurements.class);

        query.getInBackground(getArguments().getString("objectId"), new GetCallback<BaseClothingMeasurements>() {
            public void done(BaseClothingMeasurements object, ParseException e) {
                if (e == null) {
                    // Now let's update it with some new data. In this case, only cheatMode and score
                    // will get sent to the Parse Cloud. playerName hasn't changed.
                    object.setChest(Integer.parseInt(mChestLength.getText().toString().trim()));
                    object.setWaist(Integer.parseInt(mWaistLength.getText().toString().trim()));
                    object.setHip(Integer.parseInt(mHipsLegnth.getText().toString().trim()));
                    object.setShoulder(Integer.parseInt(mShoulderLength.getText().toString().trim()));
                    object.setSleeve(Integer.parseInt(mSleeveLength.getText().toString().trim()));
                    object.saveInBackground();
                    getFragmentManager().popBackStackImmediate();
                    pDialog.dismiss();
                }
            }
        });
    }

    public void refreshClients() {
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
                                pDialog.dismiss();
                                getActivity().finish();
                                startActivity(new Intent(getActivity(), MainActivity.class));
                            } else {
                                Toast.makeText(getActivity(), "Couldn't Delete Client", Toast.LENGTH_SHORT).show();
                                getActivity().finish();
                                startActivity(new Intent(getActivity(), MainActivity.class));
                            }
                        }
                    });
                } else {
                    Toast.makeText(getActivity(), "We experienced a slight error. Please try again later...", Toast.LENGTH_SHORT).show();
                    getActivity().finish();
                    startActivity(new Intent(getActivity(), MainActivity.class));
                }
            }
        });
    }
}
