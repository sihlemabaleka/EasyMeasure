package com.easymeasure;

import android.app.Fragment;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.easymeasure.model.Client;
import com.parse.ParseQuery;

public class CreateClient extends Fragment implements View.OnClickListener {

    private ProgressDialog pDialog;
    private String[] ageSizes;
    private TextInputEditText mClientName;
    private ArrayAdapter<String> adapter;
    private Button btnSave;
    private Spinner mGenderSpinner, mAgeSpinner;
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

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.ui_client_create, container, false);
        btnSave = (Button) v.findViewById(R.id.proceed);
        mClientName = (TextInputEditText) v.findViewById(R.id.name);
        mGenderSpinner = (Spinner) v.findViewById(R.id.gender_spinner);
        mAgeSpinner = (Spinner) v.findViewById(R.id.size_spinner);
        mGenderSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        ageSizes = getResources().getStringArray(R.array.adult_size);
                        break;
                    case 1:
                        ageSizes = getResources().getStringArray(R.array.adult_size);
                        break;
                    case 2:
                        ageSizes = getResources().getStringArray(R.array.kids_size);
                        break;
                    case 3:
                        ageSizes = getResources().getStringArray(R.array.kids_size);
                        break;
                    default:
                        ageSizes = getResources().getStringArray(R.array.kids_size);
                        break;
                }
                adapter = new ArrayAdapter<String>(getActivity(),
                        android.R.layout.simple_spinner_item, ageSizes);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                mAgeSpinner.setAdapter(adapter);
                mAgeSpinner.setVisibility(View.VISIBLE);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        ageSizes = getResources().getStringArray(R.array.adult_size);
        adapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_spinner_item, ageSizes);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mAgeSpinner.setAdapter(adapter);
        mAgeSpinner.setVisibility(View.VISIBLE);

        mClientName.addTextChangedListener(mTextWatcher);
        checkFieldsForEmptyValues();

        btnSave.setOnClickListener(this);

        return v;
    }

    protected void checkFieldsForEmptyValues() {
        // TODO Auto-generated method stub
        String text1 = mClientName.getText().toString().trim();
        if (!text1.isEmpty()) {
            mGenderSpinner.setVisibility(View.VISIBLE);
            if ((mGenderSpinner.getVisibility() == View.VISIBLE) || (mAgeSpinner.getVisibility() == View.VISIBLE)) {
                btnSave.setEnabled(true);
            } else {
                btnSave.setEnabled(false);
            }
        } else {
            btnSave.setEnabled(false);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.proceed:
                new checkClientAvailability().execute(mClientName.getText().toString());
                break;
            case R.id.cancel:
                getActivity().finish();
                break;
        }
    }

    class checkClientAvailability extends AsyncTask<String, Void, String> {

        @Override
        protected void onPreExecute() {
            pDialog = new ProgressDialog(getActivity());
            pDialog.setMessage("Checking if client exists. Just a moment...");
            pDialog.show();

        }

        @Override
        protected String doInBackground(String... param) {
            ParseQuery query = ParseQuery.getQuery(Client.class);
            query.whereEqualTo("client_name", param);
            try {
                Client client = (Client) query.getFirst();
                if (client != null) {
                    return client.getObjectId();
                } else {
                    return null;
                }
            } catch (Exception ex) {
                ex.printStackTrace();
                return null;
            }
        }

        @Override
        protected void onPostExecute(String objectId) {
            super.onPostExecute(objectId);
            pDialog.dismiss();
            if (objectId != null) {
                getFragmentManager().beginTransaction().replace(R.id.container, ClientMeasurementDetails.newInstance(objectId)).addToBackStack("").commit();
            } else {
                getFragmentManager().beginTransaction().replace(R.id.container, AddInformationFragment.newInstance(mClientName.getText().toString().trim(), mGenderSpinner.getSelectedItem().toString().trim(), mAgeSpinner.getSelectedItem().toString().trim())).addToBackStack("create").commit();
            }
        }
    }
}
