package com.easymeasure;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class CreateActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);
        if (savedInstanceState == null) {
            getFragmentManager().beginTransaction().add(R.id.container, new CreateClient()).commit();

        }
    }
}
