package com.easymeasure;

import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Spinner;

import com.easymeasure.model.BaseClothingMeasurements;
import com.easymeasure.model.Client;
import com.easymeasure.model.Linen;
import com.parse.ParseException;
import com.parse.SaveCallback;

public class AddInformationFragment extends Fragment {

    private TextInputEditText mChestLength, mWaistLength, mHipsLegnth, mShoulderLength, mSleeveLength;
    private Spinner sOrderType, sBeddingType, sBeddingSize;
    private LinearLayout lClothingLayout, lBeddingLayout;
    private Button btnProceed;
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

    public static AddInformationFragment newInstance(String mName, String mGender, String mSize) {
        AddInformationFragment fragment = new AddInformationFragment();
        Bundle args = new Bundle();
        args.putString("name", mName);
        args.putString("gender", mGender);
        args.putString("size", mSize);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_add_information, container, false);

        sOrderType = (Spinner) v.findViewById(R.id.order_type);

        mChestLength = (TextInputEditText) v.findViewById(R.id.chest_length);
        mWaistLength = (TextInputEditText) v.findViewById(R.id.waist_length);
        mHipsLegnth = (TextInputEditText) v.findViewById(R.id.hips_length);
        mShoulderLength = (TextInputEditText) v.findViewById(R.id.shoulder_length);
        mSleeveLength = (TextInputEditText) v.findViewById(R.id.sleeve_length);

        sBeddingType = (Spinner) v.findViewById(R.id.bedding_type);
        sBeddingSize = (Spinner) v.findViewById(R.id.bedding_size);

        lClothingLayout = (LinearLayout) v.findViewById(R.id.clothing_layout);
        lBeddingLayout = (LinearLayout) v.findViewById(R.id.bedding_layout);

        mChestLength.addTextChangedListener(mTextWatcher);
        mWaistLength.addTextChangedListener(mTextWatcher);
        mHipsLegnth.addTextChangedListener(mTextWatcher);
        mShoulderLength.addTextChangedListener(mTextWatcher);
        mSleeveLength.addTextChangedListener(mTextWatcher);
        btnProceed = (Button) v.findViewById(R.id.proceed);

        checkFieldsForEmptyValues();

        btnProceed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (sOrderType.getSelectedItemPosition()){
                    case 0:
                            saveClientBeddingOrder();
                        break;
                    case 1:
                            saveClientClothingOrder();
                        break;
                }
            }
        });

        sOrderType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        lClothingLayout.setVisibility(View.GONE);
                        lBeddingLayout.setVisibility(View.VISIBLE);
                        break;
                    case 1:
                        lClothingLayout.setVisibility(View.VISIBLE);
                        lBeddingLayout.setVisibility(View.GONE);
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        sBeddingSize.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                btnProceed.setVisibility(View.VISIBLE);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                btnProceed.setVisibility(View.GONE);
            }
        });

        sBeddingType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                btnProceed.setVisibility(View.VISIBLE);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                btnProceed.setVisibility(View.GONE);
            }
        });


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

    public void saveClientClothingOrder() {
        //Create Client
        Client client = new Client();
        client.setClientName(getArguments().getString("name"));
        client.setClientGender(getArguments().getString("gender"));
        client.setClientSize(getArguments().toString());

        //Set Base Measurements
        final BaseClothingMeasurements measurements = new BaseClothingMeasurements();
        measurements.setChest(Integer.valueOf(mChestLength.getText().toString().trim()));
        measurements.setWaist(Integer.valueOf(mWaistLength.getText().toString().trim()));
        measurements.setShoulder(Integer.valueOf(mShoulderLength.getText().toString().trim()));
        measurements.setSleeve(Integer.valueOf(mSleeveLength.getText().toString().trim()));
        measurements.setHip(Integer.valueOf(mHipsLegnth.getText().toString().trim()));
        measurements.setClient(client);
        measurements.setClothing(true);
        measurements.saveEventually(new SaveCallback() {
            @Override
            public void done(ParseException e) {
                if (e == null) {
                    getActivity().finish();
                }
            }
        });
    }

    public void saveClientBeddingOrder(){
        Client client = new Client();
        client.setClientName(getArguments().getString("name"));
        client.setClientGender(getArguments().getString("gender"));
        client.setClientSize(getArguments().getString("size"));

        Linen linen = new Linen();
        linen.setClient(client);
        linen.setType(sBeddingType.getSelectedItem().toString().trim());
        linen.setSize();
        linen.setClothing(false);
        linen.saveEventually(new SaveCallback() {
            @Override
            public void done(ParseException e) {
                if(e == null){
                    getActivity().finish();
                }
            }
        });
    }
}
