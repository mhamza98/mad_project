package com.example.facilitionapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.view.inputmethod.ExtractedText;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class suggestion extends AppCompatActivity {

    Button suggestion;
    EditText message1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_suggestion);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(suggestion.this,student_home.class);
                startActivity(intent);
                finish();
            }
        });

        final db_helper mydb = new db_helper(this);
        SharedPreferences sharedPreferences = getSharedPreferences("user_session_detail", Context.MODE_PRIVATE);
        final String userEmail = sharedPreferences.getString("email",null);
        suggestion = findViewById(R.id.bt_suggestion);
        message1 = findViewById(R.id.message_suggestion);
        suggestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Cursor cursor = mydb.getuser();
                String message = message1.getText().toString();
                if (TextUtils.isEmpty(message1.getText())){
                    message1.setError("Add Some Remarks.");
                }
                else{
                    boolean check2 = mydb.insert_suggestion(userEmail,message);
                    if (check2 == true)
                    {
                        Toast.makeText(getApplicationContext(),"Thanks For Your Feedback",Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(suggestion.this,student_home.class);
                        startActivity(intent);
                        finish();
                    }
                    else {

                        Toast.makeText(getApplicationContext(),"Feedback Failed",Toast.LENGTH_SHORT).show();
                    }

            }}
        });

    }

}
