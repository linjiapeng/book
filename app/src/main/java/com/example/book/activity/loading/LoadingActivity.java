package com.example.book.activity.loading;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.WindowManager;

import com.example.book.R;

import com.example.book.activity.home.HomeActivity;
import com.example.book.activity.home.LoginActivity;

public class LoadingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading);

       getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN);


        Handler mHandler=new Handler();

        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {

                startActivity(new Intent(LoadingActivity.this, LoginActivity.class));
                finish();
            }
        },3000);

    }
}
