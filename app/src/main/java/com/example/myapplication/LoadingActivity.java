package com.example.myapplication;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;

import org.jetbrains.annotations.Nullable;

public class LoadingActivity extends Activity {
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading);
        startLoading();
    }

    private final void startLoading() {
        Handler handler = new Handler();
        handler.postDelayed((Runnable)(new Runnable() {
            public final void run() {
                LoadingActivity.this.finish();
            }
        }), 2000L);
    }
}
