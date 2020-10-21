package com.example.myapplication;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;

import org.jetbrains.annotations.Nullable;

public final class MainActivity extends AppCompatActivity {

    private String baseUrl = "http://ec2-13-209-74-171.ap-northeast-2.compute.amazonaws.com/";

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent intent = new Intent((Context) this, LoadingActivity.class);
        this.startActivity(intent);

        EditText get_Url = findViewById(R.id.edt_Url);
        Button btn_search = findViewById(R.id.btn_search);

        btn_search.setOnClickListener((new OnClickListener() {
            public final void onClick(View it) {
                Intent intent = new Intent(MainActivity.this.getApplicationContext(), ResultActivity.class);
                startActivity(intent);
            }
        }));
    }
}