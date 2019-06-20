package com.example.facilitionapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;

public class faqs extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faqs);

        WebView webView = new WebView(this);
        setContentView(webView);
        webView.loadUrl("http://www.jinnah.edu/policies-rules/");
    }
}
