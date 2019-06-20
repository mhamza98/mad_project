package com.example.facilitionapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class welcome extends AppCompatActivity {

    Handler handler = new Handler();
    Runnable runnable = new Runnable() {
        @Override
        public void run() {

            SharedPreferences sharedPreferences = getSharedPreferences("user_session_detail", Context.MODE_PRIVATE);
            Boolean check = sharedPreferences.getBoolean("sess",false);
            //admin
            SharedPreferences sharedPreferencesadmin = getSharedPreferences("user_session_detail_admin", Context.MODE_PRIVATE);
            Boolean checkadmin = sharedPreferencesadmin.getBoolean("sess-admin",false);
            if (check)
            {
                Intent intent = new Intent(welcome.this,student_home.class);
                startActivity(intent);
                finish();
            }
            else if(checkadmin)
            {
                Intent intent = new Intent(welcome.this,admin_dashboard.class);
                startActivity(intent);
                finish();
            }
            else {
                Intent intent = new Intent(welcome.this, introactivity1.class);
                startActivity(intent);
                finish();
            }
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        handler.postDelayed(runnable,1000);
    }


}
