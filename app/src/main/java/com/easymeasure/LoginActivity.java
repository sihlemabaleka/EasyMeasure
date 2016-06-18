package com.easymeasure;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.digits.sdk.android.AuthCallback;
import com.digits.sdk.android.DigitsAuthButton;
import com.digits.sdk.android.DigitsException;
import com.digits.sdk.android.DigitsSession;
import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;

/**
 * A login screen that offers login via phone number.
 */
public class LoginActivity extends AppCompatActivity {

    DigitsAuthButton authButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        authButton = (DigitsAuthButton) findViewById(R.id.view);
        authButton.setCallback(new AuthCallback() {
            @Override
            public void success(DigitsSession session, String phoneNumber) {
                ParseUser.becomeInBackground(session.getAuthToken().toString(), new LogInCallback() {
                    @Override
                    public void done(ParseUser user, ParseException e) {
                        if (e == null) {
                            startActivity(new Intent(LoginActivity.this, MainActivity.class));
                            finish();
                        } else {
                            e.printStackTrace();
                        }
                    }
                });
            }

            @Override
            public void failure(DigitsException error) {
                error.printStackTrace();
            }
        });
    }
}

