package com.example.facilitionapp;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class change_password extends AppCompatActivity {


    Button b1;
    TextInputEditText currentpass,newpass,retypepass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Intent intent = new Intent(change_password.this,student_home.class);
               startActivity(intent);
               finish();
            }
        });

        b1 = findViewById(R.id.bt_changepass);
        currentpass = findViewById(R.id.current_password);
        newpass = findViewById(R.id.new_password);
        retypepass = findViewById(R.id.retype_pass);
        final db_helper mydb = new db_helper(this);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor cursor = mydb.getuser();
                String current_pass = currentpass.getText().toString();
                String new_pass = newpass.getText().toString();
                String retype_pass = retypepass.getText().toString();
                if (TextUtils.isEmpty(currentpass.getText())) {
                    currentpass.setError("Current Pass Is Empty");
                } else if (TextUtils.isEmpty(newpass.getText())  || new_pass.length() < 8) {
                    newpass.setError("New Pass Is Empty  or At least 8 characters");
                } else if (!retype_pass.matches(new_pass)) {
                    retypepass.setError("Password does not match");
                }
                else
                {
                    while (cursor.moveToNext())
                    {
                        if (current_pass.equals(cursor.getString(5))){
//                            cursor.getString(5).equals(new_pass);
//                            cursor.getString(5).equals(retype_pass);
                            Toast.makeText(getApplicationContext(),"Password Successfully Changed",Toast.LENGTH_SHORT).show();
                        }
                        else
                        {
                            currentpass.setError("Password Does Not matched");
                        }
                    }
//                    Toast.makeText(getApplicationContext(), "Changed Password: Successfuly", Toast.LENGTH_SHORT).show();
//                Intent intent = new Intent(change_password.this, student_home.class);
//                startActivity(intent);
//                finish();
            }}});

    }
}
