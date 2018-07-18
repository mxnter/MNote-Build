package com.example.mxnter.mnote_build;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.WindowManager;
import android.webkit.WebView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;


public class Note_Hi extends AppCompatActivity {

    LinearLayout bl;
    SharedPreferences Start;
    SharedPreferences  Data;
    TextView hy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note__hi);
        hy=findViewById(R.id.textView3);

        //bl = (LinearLayout) findViewById(R.id.);
        Start = getSharedPreferences("NoteStart", MODE_PRIVATE);



        Intent intent = new Intent(Note_Hi.this, Note_Zc.class);
        if (Start.getString("Start", "").toString().equals("ok")) {
            Data = getSharedPreferences("NoteData", MODE_PRIVATE);
            hy.setText("欢迎回来 "+Data.getString("UserName", "").toString());

            intent = new Intent(Note_Hi.this, Note_Dl.class);

        } else {
            intent = new Intent(Note_Hi.this, Note_Zc.class);

        }
        final Intent inten = intent;

        Timer time = new Timer();

        TimerTask tk = new TimerTask() {


            @Override
            public void run() {
                // TODO Auto-generated method stub
                Bundle bundle = new Bundle();
                inten.putExtras(bundle);//将Bundle添加到Intent,也可以在Bundle中添加相应数据传递给下个页面,例如：bundle.putString("abc", "bbb");
                startActivityForResult(inten, 0);// 跳转并要求返回值，0代表请求值(可以随便写)
                //startActivity(intent);
                finish();
            }
        };
        time.schedule(tk, 1000);


    }

    protected void loadurl(WebView view, String url) {
        // TODO Auto-generated method stub

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {


        return true;
    }

}


















