package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_result.*
import org.jsoup.Jsoup
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity() {

    var baseUrl = "http://ec2-13-209-74-171.ap-northeast-2.compute.amazonaws.com/"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val intent = Intent(this, LoadingActivity::class.java)
        startActivity(intent)
        val get_Url = findViewById<EditText>(R.id.edt_Url)
        val btn_search = findViewById<Button>(R.id.btn_search)
        btn_search.setOnClickListener {
            val intent = Intent(applicationContext, ResultActivity::class.java)
            print("test")
            try{
                var doc = Jsoup.connect(baseUrl).get()
                var context = doc.toString()
                newsTitle.text = context
                //url들을 room에 저장
            }catch (e : Exception){
                Log.d("TTT",e.toString())
            }
            startActivity(intent)
        }
    }
}