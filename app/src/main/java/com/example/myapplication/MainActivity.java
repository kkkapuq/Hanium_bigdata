/*
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

*/

package com.example.myapplication;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

import Connection.NetworkStatus;
import androidx.appcompat.app.AppCompatActivity;

import org.jetbrains.annotations.Nullable;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

import android.app.ProgressDialog;
import android.widget.Toast;

public final class MainActivity extends AppCompatActivity {

    private String baseUrl = "http://54.180.81.226/";
    private EditText get_Url;

    ArrayList<HashMap<String, String>> tnews = new ArrayList<HashMap<String, String>>();
    ArrayList<HashMap<String, String>> relevantArticle = new ArrayList<HashMap<String, String>>();
    ArrayList<HashMap<String, String>> timeAnalysis = new ArrayList<HashMap<String, String>>();
    ArrayList<HashMap<String, String>> keywordRank = new ArrayList<HashMap<String, String>>();

    String mJsonString;
    //결과값 넘겨줄 Intent 선언
    Intent resultIntent;


    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent intent = new Intent((Context) this, LoadingActivity.class);
        this.startActivity(intent);

        //결과화면으로 넘겨줄 Intent 생성
        resultIntent = new Intent(MainActivity.this.getApplicationContext(), ResultActivity.class);

        get_Url = (EditText)findViewById(R.id.edt_Url);
        Button btn_search = findViewById(R.id.btn_search);

        btn_search.setOnClickListener((new OnClickListener() {
            public final void onClick(View it) {

                //인터넷 연결 체크
                int status = NetworkStatus.getConnectivityStatus(getApplicationContext());
                if(status == NetworkStatus.TYPE_MOBILE){
                }else if (status == NetworkStatus.TYPE_WIFI){
                }else {
                    Toast.makeText(getApplicationContext(),"인터넷을 먼저 연결해주세요.", Toast.LENGTH_SHORT).show();
                    return;
                }

                if(get_Url.getText() != null)
                {
                    //정상적인 링크
                    //1.Crawling
                    Log.e("DATA", "Correct");


                    //POSTAsyncTask POSTAsyncTask = new POSTAsyncTask();
                    //POSTAsyncTask.execute(baseUrl);

                    GETAsyncTask GETAsyncTask = new GETAsyncTask();
                    GETAsyncTask.execute(baseUrl);

                    IMGAsyncTask IMGAsyncTask = new IMGAsyncTask();
                    IMGAsyncTask.execute(baseUrl);

                    //2.데이터 분석 AsyncTask(TimeSleep)
                    try{
                        Thread.sleep(10000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    //3.분석 DB조회 및 데이터 가져오기 결과페이지 출력
//                    startActivity(resultIntent);
                }
                else{
                    //비정상적인 링크력
                    //알림메세지 후 재입력

                }

            }
        }));

    }

    public class POSTAsyncTask extends AsyncTask<String, Void, String>{

        @Override
        protected String doInBackground(String... strings) {
            String url = strings[0];
            String parameter = "crawling1.php";
            url = url + parameter;
            try{
                String selectLink = "post&link=" + get_Url.getText().toString()+ "";
                //String selectLink = "post&word=" + "사기" + "&score=-3";
                Log.e("selectLink",selectLink);
                Log.e("url",url);
                //String selectLink = "link=https://news.naver.com/main/read.nhn?mode=LSD%26mid=shm%26sid1=102%26oid=032%26aid=0003039099";
                //String selectLink = "word=AAA&score=3";
                URL serverURL = new URL(url);
                HttpURLConnection httpURLConnection = (HttpURLConnection)serverURL.openConnection();
                if(httpURLConnection == null) {
                    Log.e("LOG.TAG" ,"ByPostMethod, Connection is null");
                }
                else
                {
                    Log.e("LOG.TAG" ,"ByPostMethod, Connection is Succesful");
                }

                httpURLConnection.setReadTimeout(25000);
                httpURLConnection.setConnectTimeout(25000);
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoInput(true);
                httpURLConnection.connect();

                OutputStream outputStream = httpURLConnection.getOutputStream();
                outputStream.write(selectLink.getBytes("UTF-8"));
                outputStream.flush();
                outputStream.close();

                InputStream is = null;
                BufferedReader in = null;
                String data = "";

                int response = httpURLConnection.getResponseCode();

                is = httpURLConnection.getInputStream();
                in = new BufferedReader(new InputStreamReader(is), 8 * 1024);
                String line = null;
                StringBuffer buff = new StringBuffer();
                while ( ( line = in.readLine() ) != null )
                {
                    buff.append(line);
                }
                data = buff.toString().trim();

                if (response == HttpURLConnection.HTTP_OK) {
                    Log.e("RECV DATA",data);
                }
                else{
                    Log.e("NOT RECV DATA",data);
                }

                in.close();
                mJsonString = data;

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return "";
        }
    }


    public class GETAsyncTask extends AsyncTask<String, Void, String>{
        ProgressDialog progressDialog;

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            JsonParsing();

            startActivity(resultIntent);
        }


        @Override
        protected String doInBackground(String... strings) {
            String url = strings[0];
            //ip : 15.165.159.104
            String parameter = "getJson.php";
            url = url + parameter;
            try{
                //url을 get으로 넘기면 nid 찾는 과정 필요...
                String selectLink = "get&link=" + get_Url.getText().toString()+ "";
                Log.e("selectLink",selectLink);
                Log.e("url",url);
                //String selectLink = "link=https://news.naver.com/main/read.nhn?mode=LSD%26mid=shm%26sid1=102%26oid=032%26aid=0003039099";
                //String selectLink = "word=AAA&score=3";
                URL serverURL = new URL(url);
                HttpURLConnection httpURLConnection = (HttpURLConnection)serverURL.openConnection();
                if(httpURLConnection == null) {
                    Log.e("LOG.TAG" ,"ByPostMethod, Connection is null");
                }
                else
                {
                    Log.e("LOG.TAG" ,"ByPostMethod, Connection is Succesful");
                }

                httpURLConnection.setReadTimeout(25000);
                httpURLConnection.setConnectTimeout(25000);
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoInput(true);
                httpURLConnection.connect();

                OutputStream outputStream = httpURLConnection.getOutputStream();
                outputStream.write(selectLink.getBytes("UTF-8"));
                outputStream.flush();
                outputStream.close();

                InputStream is = null;
                BufferedReader in = null;
                String data = "";

                int response = httpURLConnection.getResponseCode();

                is = httpURLConnection.getInputStream();
                in = new BufferedReader(new InputStreamReader(is), 8 * 1024);
                String line = null;
                StringBuffer buff = new StringBuffer();
                while ( ( line = in.readLine() ) != null )
                {
                    buff.append(line);
                }
                data = buff.toString().trim();


                if (response == HttpURLConnection.HTTP_OK) {
                    Log.e("RECV DATA",data);
                }
                else{
                    Log.e("NOT RECV DATA",data);
                }
                in.close();
                mJsonString = data;

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }


            return null;
        }
    }

    public class IMGAsyncTask extends AsyncTask<String, Void, String>{

        @Override
        protected String doInBackground(String... strings) {
            String url = strings[0];
            Bitmap bitmap;
            String getUrl = get_Url.getText().toString();
            String[] DATA = getUrl.split("&");
            String imgName = "";
//            for(int i = 1; i<DATA.length; i++)
//            {
//                String[] tmp = DATA[i].split("=");
//
//                if(tmp[0].equals("sid1") || tmp[0].equals("oid") || tmp[0].equals("aid"))
//                {
//                    imgName += tmp[1];
//                }
//            }
            imgName = "0010230003574803" + ".png";
            String parameter = "img/";
            url = url + parameter + imgName;

            try{
                Log.e("url",url);
                URL serverURL = new URL(url);
                HttpURLConnection httpURLConnection = (HttpURLConnection)serverURL.openConnection();
                if(httpURLConnection == null) {
                    Log.e("LOG.TAG" ,"ByPostMethod, Connection is null");
                }
                else
                {
                    Log.e("LOG.TAG" ,"ByPostMethod, Connection is Succesful");
                }

                httpURLConnection.setDoInput(true);
                httpURLConnection.connect();

                InputStream is = httpURLConnection.getInputStream();
                bitmap = BitmapFactory.decodeStream(is);

                resultIntent.putExtra("wordcloud", url);


            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;
        }
    }


    private void JsonParsing(){
        try {
            //tnews, relevantArticle , timeAnalysis, keywordRank
            JSONObject jsonObject = new JSONObject(mJsonString);
            JSONArray tnewsJarray = jsonObject.getJSONArray("tnews");
            JSONArray relevanteArticleJarray = jsonObject.getJSONArray("relevantArticle");
            JSONArray timeAnalysisJarray = jsonObject.getJSONArray("timeAnalysis");
            JSONArray keywordRankJarray = jsonObject.getJSONArray("keywordRank");

            Log.e("Function","JsonParsing");

            for(int i=0;i<tnewsJarray.length();i++){
                //Log.e("RESULT","FF");
                JSONObject item = tnewsJarray.getJSONObject(i);
                //Log.e("RESULT","FF");
                //String nid = item.getString("nid");
                String emotion_like = item.getString("emotion_like");
                String emotion_nice = item.getString("emotion_nice");
                String emotion_sad = item.getString("emotion_sad");
                String emotion_angry = item.getString("emotion_angry");
                String emotion_wantAfter = item.getString("emotion_wantAfter");
                String male = item.getString("male");
                String female = item.getString("female");
                String teen = item.getString("teen");
                String twenty = item.getString("twenty");
                String thirty = item.getString("thirty");
                String fourty = item.getString("fourty");
                String fifty = item.getString("fifty");
                String overSixty = item.getString("overSixty");
                Log.e("JSON2 : ", emotion_like+ ", " + emotion_nice +", "+emotion_sad+", "+
                        emotion_angry +", "+ emotion_wantAfter+", "+male +", "+female +", "+teen +", "+
                        twenty +", "+ male +", "+thirty +", "+ fourty +", "+ fifty+", "+ overSixty);


                HashMap<String,String> hashMap = new HashMap<>();
                //Log.e("HASH","FINISH");

                //hashMap.put("nid", nid);
                hashMap.put("emotion_like", emotion_like);
                hashMap.put("emotion_nice", emotion_nice);
                hashMap.put("emotion_sad", emotion_sad);
                hashMap.put("emotion_angry", emotion_angry);
                hashMap.put("emotion_wantAfter", emotion_wantAfter);
                hashMap.put("male", male);
                hashMap.put("female", female);
                hashMap.put("teen", teen);
                hashMap.put("twenty", twenty);
                hashMap.put("thirty", thirty);
                hashMap.put("fourty", fourty);
                hashMap.put("fifty", fifty);
                hashMap.put("overSixty", overSixty);

                //Log.e("PUT","FINISH");
                tnews.add(hashMap);
            }
            resultIntent.putExtra("tnews", tnews);
            Log.e("ARRAYLIST","tnews FINISH");

            for(int i = 0; i<relevanteArticleJarray.length();i++)
            {
                JSONObject item = relevanteArticleJarray.getJSONObject(i);
                String no = item.getString("no");
                //String nid = item.getString("nid");
                String url = item.getString("url");
                String articleName = item.getString("Name");
                String articleContent = item.getString("contents");
                //String articleImg = item.getString("articleImg");
                Log.e("JSON : ",  no + ", "+ url+ ", " + articleName +
                        articleContent);

                HashMap<String,String> hashMap = new HashMap<>();
                //Log.e("HASH","FINISH");

                hashMap.put("no",no);
                hashMap.put("url", url);
                hashMap.put("articleName", articleName);
                hashMap.put("articleContent", articleContent);
                //hashMap.put("articleImg", articleImg);
                //Log.e("PUT","FINISH");

                relevantArticle.add(hashMap);

            }
            resultIntent.putExtra("relevantArticle", relevantArticle);
            Log.e("ARRAYLIST","relevantArticle FINISH");
            for(int i = 0; i<timeAnalysisJarray.length();i++)
            {
                JSONObject item = timeAnalysisJarray.getJSONObject(i);
                String no = item.getString("no");
                //String nid = item.getString("nid");
                String time = item.getString("time");
                String count = item.getString("count");

                Log.e("JSON : ",  no + ", "+ time+ ", " + count);

                HashMap<String,String> hashMap = new HashMap<>();
                //Log.e("HASH","FINISH");

                hashMap.put("no",no);
                hashMap.put("time", time);
                hashMap.put("count", count);

                //Log.e("PUT","FINISH");
                timeAnalysis.add(hashMap);
            }
            resultIntent.putExtra("timeAnalysis", timeAnalysis);
            Log.e("ARRAYLIST","timeAnalysis FINISH");
            for(int i = 0; i<keywordRankJarray.length();i++)
            {
                JSONObject item = keywordRankJarray.getJSONObject(i);
                String no = item.getString("no");
                //String nid = item.getString("nid");
                String emotionBool = item.getString("emotionBool");
                String rank = item.getString("rank");
                String keyword = item.getString("keyword");
                String count = item.getString("count");

                Log.e("JSON : ",  no + ", "+ rank+ ", " + keyword + ", " + count);

                HashMap<String,String> hashMap = new HashMap<>();
                //Log.e("HASH","FINISH");

                hashMap.put("no",no);
                hashMap.put("rank", rank);
                hashMap.put("keyword", keyword);
                hashMap.put("count", count);

                //Log.e("PUT","FINISH");
                keywordRank.add(hashMap);
            }
            resultIntent.putExtra("keywordRank", keywordRank);
            Log.e("ARRAYLIST","keywordRank FINISH");
            /*
            ListAdapter adapter = new SimpleAdapter(
                    MainActivity.this, mArrayList,
                    new String[]{TAG_ID,TAG_NAME, TAG_ADDRESS},
                    new int[]{R.id.textView_list_id, R.id.textView_list_name, R.id.textView_list_address}
            );
            */
            //mlistView.setAdapter(adapter);

        } catch (JSONException e) {

            Log.d("showResult : ", String.valueOf(e));
        }

    }


}