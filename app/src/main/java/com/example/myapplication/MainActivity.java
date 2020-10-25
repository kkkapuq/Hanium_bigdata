package com.example.myapplication;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import org.jetbrains.annotations.Nullable;



public final class MainActivity extends LoadingDialog {

    private DrawerLayout drawerLayout;
    private View drawerView;
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

    DrawerLayout.DrawerListener listener = new DrawerLayout.DrawerListener() {
        @Override
        public void onDrawerSlide(@NonNull View drawerView, float slideOffset) {
        }

        @Override
        public void onDrawerOpened(@NonNull View drawerView) {
        }

        @Override
        public void onDrawerClosed(@NonNull View drawerView) {
        }

        @Override
        public void onDrawerStateChanged(int newState) {
        }
    };

    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent intent = new Intent((Context) this, LoadingActivity.class);
        this.startActivity(intent);

        drawerLayout = findViewById(R.id.drawer_layout);
        drawerView = findViewById(R.id.drawerView);
        ImageView img_menu= findViewById(R.id.img_menu);
        drawerLayout.addDrawerListener(listener);
        img_menu.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(drawerView);
            }
        });


        ProgressDialog ayncDialog = new ProgressDialog(MainActivity.this);
        EditText get_Url = findViewById(R.id.edt_Url);
        Button btn_search = findViewById(R.id.btn_search);

        btn_search.setOnClickListener((new OnClickListener() {
            public final void onClick(View it) {
                //startProgress();
                CheckTypesTask task = new CheckTypesTask();
                task.execute();

                Intent intent = new Intent(MainActivity.this.getApplicationContext(), ResultActivity.class);
                startActivity(intent);
            }
        }));
    }

    private class CheckTypesTask extends AsyncTask<Void, Void, Void>{
        ProgressDialog asyncDialog = new ProgressDialog(MainActivity.this);

        @Override
        protected void onPreExecute(){
            asyncDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            asyncDialog.setMessage("결과 화면을 구성중입니다...");

            asyncDialog.show();
            super.onPreExecute();
        }
        @Override
        protected Void doInBackground(Void... voids) {
            try{
                //여기에 통신관련 소스 넣고 콜백으로 받아야됨
                for(int i = 0; i < 5; i++){
                    Thread.sleep(2000);
                }
            } catch (InterruptedException e){
                e.printStackTrace();
            }
            return null;
        }
        @Override
        protected void onPostExecute(Void result){

            asyncDialog.dismiss();
            super.onPostExecute(result);
        }
    }
}