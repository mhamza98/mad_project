package com.example.facilitionapp;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class signup_student extends AppCompatActivity {

    TextInputEditText fname, lname, phone, email, newpassword, retypepass;
    Button signup;

    private TextWatcher textWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            checkfields();
        }
    };

     void checkfields(){
     signup = findViewById(R.id.bt_signup);
         String first_name = fname.getText().toString();
         String last_name = lname.getText().toString();
         String contact = phone.getText().toString();
         String e_mail = email.getText().toString();
         String new_pass = newpassword.getText().toString();
         String retype_pass = retypepass.getText().toString();

         if (first_name.equals("") || last_name.equals("") || contact.equals("") || e_mail.equals("") || new_pass.equals("") || retype_pass.equals("")){
             signup.setAlpha(.5f);
             signup.setEnabled(false);
         }
         else {
             signup.setAlpha(.9f);
             signup.setEnabled(true);
         }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_student);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(signup_student.this,admin_dashboard.class);
                startActivity(intent);
                finish();
            }
        });

        fname = findViewById(R.id.signup_firstname);
        lname = findViewById(R.id.signup_lastname);
        phone = findViewById(R.id.signup_phone);
        email = findViewById(R.id.signup_email);
        newpassword = findViewById(R.id.signup_newpass);
        retypepass = findViewById(R.id.signup_retypewpass);
        signup = findViewById(R.id.bt_signup);
        //set listners
        fname.addTextChangedListener(textWatcher);
        lname.addTextChangedListener(textWatcher);
        phone.addTextChangedListener(textWatcher);
        email.addTextChangedListener(textWatcher);
        newpassword.addTextChangedListener(textWatcher);
        retypepass.addTextChangedListener(textWatcher);
        //call function
        checkfields();
        final db_helper mydb = new db_helper(this);

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String first_name = fname.getText().toString();
                String last_name = lname.getText().toString();
                String contact = phone.getText().toString();
                String e_mail = email.getText().toString();
                String new_pass = newpassword.getText().toString();
                String retype_pass = retypepass.getText().toString();
                String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
                Cursor cursor = mydb.getuser();

                    if (first_name.length() < 3){
                        fname.setError("First name must be at least 3 characters");
                    }
                else if (last_name.length() < 3){
                    lname.setError("Last name must be at least 3 characters");
                }
                    else if (contact.length() != 11){
                        phone.setError("Enter Valid Number");
                    }
                    else if (!e_mail.matches(emailPattern)) {
                        email.setError("Enter Valid Email");
                    }
                    else if (new_pass.length() < 8){
                        newpassword.setError("Password must be at least 8 characters");
                    }
                    else if (!retype_pass.matches(new_pass)){
                        retypepass.setError("Password does not match");
                    }
                    else{

                        while (cursor.moveToNext()){
                            if (e_mail.equals(cursor.getString(4))){
                                email.setError("Email Already Exists");
                            }
                        }
                    boolean check = mydb.insert_data(first_name,last_name,contact,e_mail,new_pass,retype_pass);
                    if (check)
                    {
                        Toast.makeText(getApplicationContext(),"Insertion Success",Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(signup_student.this,admin_dashboard.class);
                        startActivity(intent);
                        finish();
                    }
                    else {

                        Toast.makeText(getApplicationContext(),"Insertion Failed",Toast.LENGTH_SHORT).show();
                    }

            }}
        });

    }
    //function

    public boolean checkparam(String first_name,String last_name,String contact,String e_mail,String new_pass,String retype_pass)
    {
        if (first_name.isEmpty() && last_name.isEmpty() && contact.isEmpty() && e_mail.isEmpty() && new_pass.isEmpty() && retype_pass.isEmpty() )
        {
            return false;
        }
        else
        {

            return true;
        }
    }

}
