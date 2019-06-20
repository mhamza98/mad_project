package com.example.facilitionapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class confirmation_code extends AppCompatActivity {

    Button verify;
    EditText confirm_code;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmation_code);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(confirmation_code.this,forgotpass.class);
                startActivity(intent);
                finish();
            }
        });

        confirm_code = findViewById(R.id.confirmation_code);
        verify = findViewById(R.id.bt_verifycode);

        verify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(confirm_code.getText()))
                {
                    confirm_code.setError("Enter Code");
                }
                else
                {
                    Intent intent_changepass = new Intent(confirmation_code.this,change_password.class);
                    startActivity(intent_changepass);
                    finish();
                }
            }
        });

    }

}
