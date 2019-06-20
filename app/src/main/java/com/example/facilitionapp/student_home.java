package com.example.facilitionapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

public class student_home extends AppCompatActivity


        implements NavigationView.OnNavigationItemSelectedListener {

    final db_helper mdb = new db_helper(this);
    TextView usermail, username;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        //session

        SharedPreferences sharedPreferences = getSharedPreferences("user_session_detail", Context.MODE_PRIVATE);
        String userEmail = sharedPreferences.getString("email",null);
        //
        final db_helper mdb = new db_helper(this);

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        displaymenu(R.id.homes);
        //
        View headerView = navigationView.getHeaderView(0);
        usermail = headerView.findViewById(R.id.useremails);
        username = headerView.findViewById(R.id.username);
        //String user_mail = mdb.getuser().getColumnName(4);
        //usermail.setText(user_mail);

        Cursor cursor = mdb.getuser();

        while (cursor.moveToNext())
        {
            if (userEmail.equals(cursor.getString(4)))
            {
                usermail.setText(userEmail);
                username.setText(cursor.getString(1) +  cursor.getString(2));
            }
        }

    }

    //exit function
    private static long back_pressed;
    @Override
    public void onBackPressed()
    {
        if (back_pressed + 2000 > System.currentTimeMillis()) super.onBackPressed();
        else Toast.makeText(getBaseContext(), "Press once again to exit!", Toast.LENGTH_SHORT).show();
        back_pressed = System.currentTimeMillis();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.student_home, menu);
        //Toast.makeText(getApplicationContext(),"abcv",Toast.LENGTH_SHORT).show();
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Intent intent_toggle_notify = new Intent(student_home.this,notification.class);
        startActivity(intent_toggle_notify);

            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void displaymenu(int id){
        Fragment fragment = null;

        switch (id){
            case R.id.homes:
//                Intent intent_home = new Intent(student_home.this, mainhome_student.class);
//                startActivity(intent_home);
                fragment = new home();
                break;
            case R.id.notification:
                Intent intent_notification = new Intent(student_home.this,notification.class);
                startActivity(intent_notification);
                break;
            case R.id.suggestion:
                Intent intent_suggestion = new Intent(student_home.this,suggestion.class);
                startActivity(intent_suggestion);
                break;
            case R.id.complaint_status:
                Intent intent_complainstatus = new Intent(student_home.this,complain_status.class);
                startActivity(intent_complainstatus);
                break;
            case R.id.change_password:
                Intent intent_pass = new Intent(student_home.this,change_password.class);
                startActivity(intent_pass);
                break;
            case R.id.logout:

                SharedPreferences sharedPreferences = getSharedPreferences("user_session_detail", Context.MODE_PRIVATE);
                SharedPreferences.Editor edit = sharedPreferences.edit();
                edit.putBoolean("sess",false);
                edit.apply();

                Intent intent = new Intent(student_home.this,MainActivity.class);
                startActivity(intent);
                finish();
                break;
        }
        if (fragment!=null){
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.content_main, fragment);
            ft.commit();

        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        displaymenu(id);
        return true;
    }
}
