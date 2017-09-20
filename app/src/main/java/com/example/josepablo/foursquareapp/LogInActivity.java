package com.example.josepablo.foursquareapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class LogInActivity extends AppCompatActivity {

    private EditText tfAccount;
    private EditText tfPassword;

    private Button bLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        bLogin = (Button) findViewById(R.id.bLogin);

        tfAccount = (EditText) findViewById(R.id.tfAccount);
        tfPassword = (EditText) findViewById(R.id.tfPassword);

        bLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FSLogin(tfAccount.getText().toString(), tfPassword.getText().toString());
            }
        });
    }

    private void FSLogin(String accnt, String pass) {
        // TODO: 1 WebAuth to FourSquare

        // If token was received, proceed to next Activity
        Intent intent = new Intent(this, LocationActivity.class);

        startActivity(intent);

    }
}
