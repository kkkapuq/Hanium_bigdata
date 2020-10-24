package com.example.myapplication;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;

import org.jetbrains.annotations.Nullable;

public final class MainActivity extends LoadingDialog {

    private String baseUrl = "http://ec2-15-165-159-104.ap-northeast-2.compute.amazonaws.com/";

    private void startProgress() {

        progressON("기사 정보를 수집중입니다...");

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                progressOFF();
            }
        }, 3500);

    }

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent intent = new Intent((Context) this, LoadingActivity.class);
        this.startActivity(intent);

        EditText get_Url = findViewById(R.id.edt_Url);
        Button btn_search = findViewById(R.id.btn_search);

        btn_search.setOnClickListener((new OnClickListener() {
            public final void onClick(View it) {
                //startProgress();

                Intent intent = new Intent(MainActivity.this.getApplicationContext(), ResultActivity.class);
                startActivity(intent);
            }
        }));
    }
}