package com.example.facilitionapp;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;

import com.github.paolorotolo.appintro.AppIntro;
import com.github.paolorotolo.appintro.AppIntroFragment;

public class introactivity1 extends AppIntro {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //setContentView(R.layout.activity_introactivity1);
        //first slide
        addSlide(AppIntroFragment.newInstance("Login", "Feel Free To Login", R.drawable.baseline_lock_white_48dp,
                ContextCompat.getColor(getApplicationContext(),R.color.gradStart)));
        //second slide
        addSlide(AppIntroFragment.newInstance("Any Query?", "Register Complain", R.drawable.baseline_query_builder_white_48dp,
                ContextCompat.getColor(getApplicationContext(),R.color.gradStart)));
            //third slide
        addSlide(AppIntroFragment.newInstance("Congratulations!", "Complain Resolved", R.drawable.baseline_done_white_48dp,
                ContextCompat.getColor(getApplicationContext(),R.color.gradStop)));
    }

    @Override
    public void onDonePressed(Fragment currentFragment) {
        super.onDonePressed(currentFragment);
        Intent intent = new Intent(getApplicationContext(),MainActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void onSkipPressed(Fragment currentFragment) {
        super.onSkipPressed(currentFragment);
        Intent intent = new Intent(getApplicationContext(),MainActivity.class);
        startActivity(intent);
        finish();
    }
}
