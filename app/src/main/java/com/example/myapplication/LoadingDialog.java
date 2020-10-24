package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

public class LoadingDialog extends AppCompatActivity {
    public void progressON() {
        LoadingApplication.getInstance().progressON(this, null);
    }

    public void progressON(String message) {
        LoadingApplication.getInstance().progressON(this, message);
    }

    public void progressOFF() {
        LoadingApplication.getInstance().progressOFF();
    }
}
