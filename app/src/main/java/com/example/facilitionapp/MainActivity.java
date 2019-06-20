package com.example.facilitionapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Handler;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText userlogin;
    TextInputEditText userpassword;
    Button login, forgetpass, signup;
    String userEmail;
    Boolean session = false;
    Boolean checkuser = false;
    Boolean session_admin = false;
    Boolean checkuser_admin = false;


    private TextWatcher mtextwatcher = new TextWatcher() {
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
        login = findViewById(R.id.login);
        String user_login = userlogin.getText().toString();
        String user_pass = userpassword.getText().toString();

        if (user_login.equals("") || user_pass.equals("")){
            login.setAlpha(.5f);
            login.setEnabled(false);
        }
        else
        {
            login.setAlpha(.9f);
            login.setEnabled(true);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final db_helper mdb = new db_helper(this);

        userlogin = findViewById(R.id.login_user);
        userpassword = findViewById(R.id.login_password);
        login = findViewById(R.id.login);
        //button query
        //set listners
        userlogin.addTextChangedListener(mtextwatcher);
        userpassword.addTextChangedListener(mtextwatcher);
        //call function
        checkfields();
        login.setOnClickListener(new View.OnClickListener() {
            @Override



           public void onClick(View v) {



                Cursor cursor = mdb.getuser();
                String user_login = userlogin.getText().toString();
                String user_pass = userpassword.getText().toString();
                while (cursor.moveToNext())
                {
                       if (user_login.equals(cursor.getString(4)) && user_pass.equals(cursor.getString(5)))
                       {
                                checkuser = true;
                                userEmail = cursor.getString(4);
//                                Intent intent = new Intent(MainActivity.this, student_home.class);
//                                startActivity(intent);
//                                finish();
                       }
                }

                    if (checkuser){
                        SharedPreferences user_session = getSharedPreferences("user_session_detail",MODE_PRIVATE);
                        SharedPreferences.Editor edit = user_session.edit();
                        edit.putString("email",userEmail);
                        session = true;
                        edit.putBoolean("sess",session);
                        edit.commit();

                        Intent intent = new Intent(MainActivity.this, student_home.class);
                        startActivity(intent);
                        finish();
                    }
                    else if (userlogin.getText().toString().equals("admin") && userpassword.getText().toString().equals("54321"))
                    {
                        checkuser_admin = true;
                        SharedPreferences user_session_admin = getSharedPreferences("user_session_detail_admin",MODE_PRIVATE);
                        SharedPreferences.Editor edit_admin = user_session_admin.edit();
                        edit_admin.putString("email-admin",userEmail);
                        session_admin = true;
                        edit_admin.putBoolean("sess-admin",session_admin);
                        edit_admin.commit();
                    //Toast.makeText(getApplicationContext(),"Admin",Toast.LENGTH_SHORT).show();
                        Intent intent_admin = new Intent(MainActivity.this,admin_dashboard.class);
                        startActivity(intent_admin);
                        finish();
                    }
//                    else if (user_login.isEmpty()){
//                        userlogin.setError("Enter Email");
//                    }
//                    else if (user_pass.isEmpty()){
//                        userpassword.setError("Enter Password");
//                    }
                    else{
                        Toast.makeText(getApplicationContext(),"Invalid Email or Password",Toast.LENGTH_SHORT).show();
                    }
//                else if (TextUtils.isEmpty(userlogin.getText()))
//                 {
//                     userlogin.setError("Enter Email");
//                 }
//                 else if(TextUtils.isEmpty(userpassword.getText()))
//                     {
//                         userpassword.setError("Enter Password");
//                     }
//                     else
//                         {
//                             Toast.makeText(getApplicationContext(),"Enter valid Email or Password",Toast.LENGTH_SHORT).show();
//                         }
                
            }
        });

        forgetpass = findViewById(R.id.login_forgetpass);
        forgetpass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,forgotpass.class);
                startActivity(intent);
            }
        });

        //signup
//        signup = findViewById(R.id.bt_signup_login);
//        signup.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(MainActivity.this,admin_dashboard.class);
//                startActivity(intent);
//            }
//        });
}}
