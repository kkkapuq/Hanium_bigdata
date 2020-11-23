package com.hanium.moamoa;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

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

import Connection.NetworkStatus;

public final class MainActivity extends AppCompatActivity {

    private String baseUrl = "http://54.180.81.226/";
    private EditText get_Url;

    ArrayList<HashMap<String, String>> tnews = new ArrayList<HashMap<String, String>>();
    ArrayList<HashMap<String, String>> relevantArticle = new ArrayList<HashMap<String, String>>();
    ArrayList<HashMap<String, String>> timeAnalysis = new ArrayList<HashMap<String, String>>();
    ArrayList<HashMap<String, String>> keywordRank = new ArrayList<HashMap<String, String>>();
    ArrayList<HashMap<String, String>> emotionAnalysis = new ArrayList<HashMap<String, String>>();
    ArrayList<HashMap<String, String>> emotionComments = new ArrayList<HashMap<String, String>>();

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
        final String newsUrl = get_Url.getText().toString();

        //로딩창 객체 생성
        final LoadingDialog loadingDialog = new LoadingDialog(this);
        //로딩창을 투명하게
        loadingDialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));

//        getnewsUrl();

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
                //021/0002450344/110  = 16
                //1100210002450344
                //로딩창 띄워주기
//                CheckTypesTask task = new CheckTypesTask();
//                task.execute();
                loadingDialog.show();


                if(get_Url.getText() != null)
                {
                    //정상적인 링크
                    //1.Crawling
                    Log.e("DATA", "Correct");

//                    GetNewsInfoTask GetNewsInfoTask = new GetNewsInfoTask();
//                    GetNewsInfoTask.execute(baseUrl);

                    POSTAsyncTask POSTAsyncTask = new POSTAsyncTask();
                    POSTAsyncTask.execute(baseUrl);

                    IMGAsyncTask IMGAsyncTask = new IMGAsyncTask();
                    IMGAsyncTask.execute(baseUrl);

                    GETAsyncTask GETAsyncTask = new GETAsyncTask();
                    GETAsyncTask.execute(baseUrl);

                    //2.데이터 분석 AsyncTask(TimeSleep)
                    try{
                        Thread.sleep(1000);
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

//    //로딩창 구현
//    private class CheckTypesTask extends AsyncTask<Void, Void, Void>{
//        ProgressDialog asyncDialog = new ProgressDialog(MainActivity.this);
//
//        @Override
//        protected void onPreExecute(){
//            super.onPreExecute();
//            asyncDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
//            asyncDialog.setMessage("결과 화면을 구성중입니다...");
//
//            asyncDialog.show();
//        }
//        @Override
//        protected Void doInBackground(Void... voids) {
//            for(int i = 0 ; i < 4 ; i++) {
//                try {
//                    //여기에 통신관련 소스 넣고 콜백으로 받아야됨
//                    Thread.sleep(500);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
//            return null;
//        }
//        @Override
//        protected void onPostExecute(Void result){
//
//            asyncDialog.dismiss();
//            super.onPostExecute(result);
//        }
//    }

//    private class GetNewsInfoTask extends AsyncTask<String, Void, String> {
//        @Override
//        protected String doInBackground(String... strings) {
//            HashMap<String, String> hashMap = new HashMap<String, String>();
//
//            String url = get_Url.getText().toString();
//
//            try {
//                Document document = Jsoup.connect(url).get();
//                Elements elements = document.select("div.article_info h3");
//                String newsTitle = elements.text();
//                hashMap.put("newsTitle", newsTitle);
//                tnews.add(hashMap);
//
////                elements = document.select("div#spiLayer > div > ul > li");
////
////                for(Element i : elements){
////
////                }
////                hashMap.put("emotion_like", elements.text());
////
////                elements = document.select("div#cbox_module div div ul li div div");
////                hashMap.put("male", elements.text());
////
////                elements = document.select(".lotto_area .winner_money #lottoNo1SuAmount");
////                hashMap.put("winGameMoney", elements.text());
//
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//            return null;
//        }
//
//        @Override
//        protected void onPostExecute(String s) {
//            super.onPostExecute(s);
//
//
//        }
//    }


    public class POSTAsyncTask extends AsyncTask<String, Void, String>{

        @Override
        protected String doInBackground(String... strings) {
            String url = strings[0];
            String parameter = "crawling1.php";
            url = url + parameter;      //ip. . . . /crawling1.php/?link="12345"&imgName="abc"
            String getUrl = get_Url.getText().toString();

            String imgName = "";

            String firstUrl = getUrl.split("//")[1].split("[.]")[0];
            if(firstUrl.equals("n"))
            {
                String[] DATA = getUrl.split("/");
                String oid = DATA[DATA.length-2];
                String[] DATA2 = DATA[DATA.length-1].split("[?]");
                String aid = DATA2[0];
                String sid = DATA2[1].split("=")[1];
                imgName = sid + oid + aid;

            }
            else{
                String[] DATA = getUrl.split("&");
                for(int i = 1; i<DATA.length; i++)
                {
                    String[] tmp = DATA[i].split("=");

                    if(tmp[0].equals("sid1") || tmp[0].equals("oid") || tmp[0].equals("aid"))
                    {
                        imgName += tmp[1];
                    }
                }
            }


            try{
                String selectLink = "post&link=" + get_Url.getText().toString()+ "&imgName=" + imgName;
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

                httpURLConnection.setReadTimeout(300000);
                httpURLConnection.setConnectTimeout(300000);
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
            JsonParsing();

            startActivity(resultIntent);
            super.onPostExecute(s);
        }

        @Override
        protected String doInBackground(String... strings) {
            String url = strings[0];
            //ip : 15.165.159.104
            String parameter = "getJson1.php";
            url = url + parameter;
            try{
                //url을 get으로 넘기면 nid 찾는 과정 필요...
                String selectLink = "?link=" + get_Url.getText().toString()+ "";
                Log.e("selectLink",selectLink);
                Log.e("url",url);
                //String selectLink = "link=https://news.naver.com/main/read.nhn?mode=LSD%26mid=shm%26sid1=102%26oid=032%26aid=0003039099";
                //String selectLink = "word=AAA&score=3";
                url = url + selectLink;
                URL serverURL = new URL(url);
                HttpURLConnection httpURLConnection = (HttpURLConnection)serverURL.openConnection();
                if(httpURLConnection == null) {
                    Log.e("LOG.TAG" ,"ByPostMethod, Connection is null");
                }
                else
                {
                    Log.e("LOG.TAG" ,"ByPostMethod, Connection is Succesful");
                }
                //httpURLConnection.setRequestMethod("GET");
                httpURLConnection.setReadTimeout(25000);
                httpURLConnection.setConnectTimeout(25000);
                httpURLConnection.setUseCaches(false);

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
            String[] temp = new String[4];
            String getUrl = get_Url.getText().toString();

            String imgName = "";
            String firstUrl = getUrl.split("//")[1].split("[.]")[0];
            if(firstUrl.equals("n"))
            {
                String[] DATA = getUrl.split("/");
                String oid = DATA[DATA.length-2];
                String[] DATA2 = DATA[DATA.length-1].split("[?]");
                String aid = DATA2[0];
                String sid = DATA2[1].split("=")[1];
                imgName = sid + oid + aid;

            }
            else{
                String[] DATA = getUrl.split("&");
                for(int i = 1; i<DATA.length; i++)
                {
                    String[] tmp = DATA[i].split("=");

                    if(tmp[0].equals("sid1") || tmp[0].equals("oid") || tmp[0].equals("aid"))
                    {
                        imgName += tmp[1];
                    }
                }
            }
            imgName = "1"+imgName;; //+".png";

            for(int i = 0; i < temp.length; i++){
                String url = strings[0];
                Bitmap bitmap;

//            imgName = "0010230003574803" + ".png";

                String parameter = "img/";
                switch (i){
                    case 0:
                        temp[i] = url + parameter + imgName + ".png";
                        resultIntent.putExtra("wordcloud", temp[i]);
                        break;
                    default:
                        temp[i] = url + parameter + imgName + "_" + Integer.toString(i) + ".jpg";
//                        HashMap<String, String> map = new HashMap<String, String>();
//                        map.put("simArticleImg"+Integer.toString(i), temp[i]);
//                        relevantArticle.add(i,map);
                        resultIntent.putExtra("simArticleImg"+Integer.toString(i), temp[i]);
                        break;

                }

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


                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
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
            JSONArray emotionAnalysisJarray = jsonObject.getJSONArray("news");
            JSONArray emotionCommentsJarray = jsonObject.getJSONArray("emotionComments");

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

            //관련기사 분석
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

            //time analysis
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


            //감정 분석
            for(int i = 0; i<emotionAnalysisJarray.length();i++)
            {
                JSONObject item = emotionAnalysisJarray.getJSONObject(i);
                String neutral = item.getString("neutral");
                String positive = item.getString("positive");
                String negative = item.getString("negative");
                String title = item.getString("title");

                Log.e("JSON : ",  neutral + ", "+ positive+ ", " + negative + ", " + title);

                HashMap<String,String> hashMap = new HashMap<>();
                //Log.e("HASH","FINISH");

                hashMap.put("neutral",neutral);
                hashMap.put("positive", positive);
                hashMap.put("negative",negative);
                hashMap.put("title",title);
                //Log.e("PUT","FINISH");

                emotionAnalysis.add(hashMap);
            }
            resultIntent.putExtra("emotionAnalysis", emotionAnalysis);
            Log.e("ARRAYLIST","emotionAnalysis FINISH");

            //키워드 분석
            for(int i = 0; i<keywordRankJarray.length();i++)
            {
                JSONObject item = keywordRankJarray.getJSONObject(i);
                String no = item.getString("no");
                //String nid = item.getString("nid");
                String emotionBool = item.getString("emotionBool");
                String rank = item.getString("rank");
                String keyword = item.getString("keyword");
                String count = item.getString("count");

                Log.e("JSON : ",  no + ", "+ rank+ "," + emotionBool + ", " + keyword + ", " + count);

                HashMap<String,String> hashMap = new HashMap<>();
                //Log.e("HASH","FINISH");

                hashMap.put("no",no);
                hashMap.put("emotionBool", emotionBool);
                hashMap.put("rank", rank);
                hashMap.put("keyword", keyword);
                hashMap.put("count", count);

                //Log.e("PUT","FINISH");
                keywordRank.add(hashMap);
            }
            resultIntent.putExtra("keywordRank", keywordRank);
            Log.e("ARRAYLIST","keywordRank FINISH");


            //긍부정 댓글
            for(int i = 0; i<emotionCommentsJarray.length();i++)
            {
                JSONObject item = emotionCommentsJarray.getJSONObject(i);
                String no = item.getString("no");
                String emotionBool = item.getString("emotionBool");
                String comments = item.getString("comments");
                String sympathyCount = item.getString("sympathyCount");
                String antipathyCount = item.getString("antipathyCount");
                String replyAllCount = item.getString("replyAllCount");
                String usrName = item.getString("usrName");

                Log.e("JSON : ",  no + ", "+  emotionBool + ", " + comments);

                HashMap<String,String> hashMap = new HashMap<>();
                //Log.e("HASH","FINISH");

                hashMap.put("no",no);
                hashMap.put("emotionBool", emotionBool);
                hashMap.put("comments", comments);
                hashMap.put("sympathyCount", sympathyCount);
                hashMap.put("antipathyCount", antipathyCount);
                hashMap.put("replyAllCount", replyAllCount);
                hashMap.put("usrName", usrName);


                //Log.e("PUT","FINISH");
                emotionComments.add(hashMap);
            }
            resultIntent.putExtra("emotionComments", emotionComments);
            Log.e("ARRAYLIST","emotionComments FINISH");
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