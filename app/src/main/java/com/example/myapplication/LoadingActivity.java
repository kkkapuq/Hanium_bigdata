package com.example.myapplication;

import android.app.Activity;
import android.content.ContentValues;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;

import org.jetbrains.annotations.Nullable;
import org.w3c.dom.Text;

import Connection.RequestHttpURLConnection;
import androidx.appcompat.app.AppCompatActivity;

public class LoadingActivity extends Activity {

    private TextView recArticle;
    private String baseUrl = "http://ec2-15-165-159-104.ap-northeast-2.compute.amazonaws.com/";

    public class NetworkTask extends AsyncTask<Void, Void, String> {

        private String url;
        private ContentValues values;

        public NetworkTask(String url, ContentValues values) {

            this.url = url;
            this.values = values;
        }

        @Override
        protected String doInBackground(Void... params) {

            String result; // 요청 결과를 저장할 변수.
            RequestHttpURLConnection requestHttpURLConnection = new RequestHttpURLConnection();
            result = requestHttpURLConnection.request(url, values); // 해당 URL로 부터 결과물을 얻어온다.

            return result;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            //doInBackground()로 부터 리턴된 값이 onPostExecute()의 매개변수로 넘어오므로 s를 출력한다.
            recArticle.setText(s);
        }
    }

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
        // 위젯에 대한 참조.
        recArticle = findViewById(R.id.recArticle2);

        // AsyncTask를 통해 HttpURLConnection 수행.
        NetworkTask networkTask = new NetworkTask(baseUrl, null);
        networkTask.execute();

        startLoading();
    }
}
