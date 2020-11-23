
package com.hanium.moamoa;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;

public class LoadingActivity extends Activity {

    private final void startLoading() {
        Handler handler = new Handler();
        handler.postDelayed((Runnable)(new Runnable() {
            public final void run() {
                LoadingActivity.this.finish();
            }
        }), 2000L);
    }


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading);

        startLoading();
    }
}

