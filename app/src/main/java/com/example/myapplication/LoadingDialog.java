package com.example.myapplication;

import android.app.Dialog;
import android.content.Context;
import android.view.Window;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class LoadingDialog extends Dialog {
    public LoadingDialog(Context context){
        super(context);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog_loading);
    }
}
