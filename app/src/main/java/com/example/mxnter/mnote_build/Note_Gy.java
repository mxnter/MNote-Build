package com.example.mxnter.mnote_build;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class Note_Gy extends AppCompatActivity {


    public static final int SHOW_RESPONSE = 0;
    String strbb="0.0.0.1";
    String strgy="M’Note 是一款加密的个人笔记的安卓APP";
    WebView tz;
    TextView gy;
    //int ail=0;
    //String strbb;
    String lsv="0.0.0.1";
    SharedPreferences Start, Data;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note__gy);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        gy=findViewById(R.id.gy);


        Start = getSharedPreferences("NoteStart", MODE_PRIVATE);
        Data = getSharedPreferences("NoteData", MODE_PRIVATE);

        getSupportActionBar().setTitle("欢迎回来 "+Data.getString("UserName", "").toString());






        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(strbb.equals(lsv)){
                    Snackbar.make(view, "软件已为最新版", Snackbar.LENGTH_LONG).setAction("Action", null).show();
                    //Toast.makeText(getApplicationContext(), "软件已为最新版", Toast.LENGTH_SHORT).show();

                }else{

                    AlertDialog.Builder b= new AlertDialog.Builder(Note_Gy.this);
                    b.setTitle("更新");
                    b.setMessage("已发现最新版："+strbb);


                    b.setPositiveButton("确认", new DialogInterface.OnClickListener() {

                        public void onClick(DialogInterface arg0, int arg1) {
                            // TODO Auto-generated method stub
                          //  Intent intent = new Intent();
                            //intent.setAction("android.intent.action.VIEW");
                           // Toast.makeText(getApplicationContext(), "我们将跳转到下载页面", Toast.LENGTH_SHORT).show();
                           // Uri content_url = Uri.parse("https://loswkl.win/lnotexz");    //http://a.loswkl.xyz/lapp/LStudy.ap
                          //  intent.setData(content_url);
                          //  startActivity(intent);


                        }
                    });
                    b.setNegativeButton("取消", new DialogInterface.OnClickListener() {

                        public void onClick(DialogInterface arg0, int arg1) {
                            // TODO Auto-generated method stub

                        }
                    });
                    b.show();
                }

            }
        });

        //检测关于信息-支线获取
        new Thread(new Runnable() {

            public void run() {
                try
                {
                    URL url = new URL("https://mxnter.github.io/information/MNote/information/index.html");
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    conn.setDoInput(true);
                    //conn.setDefaultUseCaches(false);//禁用缓存
                    conn.setConnectTimeout(10000);
                    conn.setRequestMethod("GET");
                    conn.setRequestProperty("accept", "*/*");
                    String location = conn.getRequestProperty("location");
                    int resCode = conn.getResponseCode();
                    conn.connect();
                    InputStream stream = conn.getInputStream();
                    byte[] data=new byte[102400];
                    int length=stream.read(data);
                    String str=new String(data,0,length);
                    //在子线程中将Message对象发出去
                    Message message = new Message();
                    message.obj = str.toString();
                    handler_gy.sendMessage(message);
                    conn.disconnect();
                    System.out.println(str);
                    stream.close();
                }
                catch(Exception ee)
                {
                    //Toast.makeText(getApplicationContext(), "网络连接超时", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent();
                    i.setClass(Note_Gy.this, Note.class);
                    startActivity(i);
                    Toast.makeText(getApplicationContext(), "网络连接超时", Toast.LENGTH_SHORT).show();
                    finish();
                    //System.out.print("ee:"+ee.getMessage());
                }
            }
        }).start();//这个start()方法不要忘记了

        //检测更新-支线获取
        new Thread(new Runnable() {

            public void run() {
                try
                {
                    URL url = new URL("https://mxnter.github.io/information/MNote/versions/index.html");
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    conn.setDoInput(true);
                    //conn.setDefaultUseCaches(false);//禁用缓存
                    conn.setConnectTimeout(10000);
                    conn.setRequestMethod("GET");
                    conn.setRequestProperty("accept", "*/*");
                    String location = conn.getRequestProperty("location");
                    int resCode = conn.getResponseCode();
                    conn.connect();
                    InputStream stream = conn.getInputStream();
                    byte[] data=new byte[102400];
                    int length=stream.read(data);
                    String str=new String(data,0,length);
                    //在子线程中将Message对象发出去
                    Message message = new Message();
                    message.obj = str.toString();
                    handler.sendMessage(message);
                    conn.disconnect();
                    System.out.println(str);
                    stream.close();
                }
                catch(Exception ee)
                {
                    //Toast.makeText(getApplicationContext(), "网络连接超时", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent();
                    i.setClass(Note_Gy.this, Note.class);
                    startActivity(i);
                    Toast.makeText(getApplicationContext(), "网络连接超时", Toast.LENGTH_SHORT).show();
                    finish();

                    //System.out.print("ee:"+ee.getMessage());
                }
            }
        }).start();//这个start()方法不要忘记了















    }

    //检测更新-获取支线信息
    private Handler handler_gy = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case SHOW_RESPONSE:
                    String response = (String) msg.obj;
                    strgy=response;
                    gy.setText(strgy);
                    break;

                default: Toast.makeText(getApplicationContext(), "网络连接超时", Toast.LENGTH_SHORT).show();
                    break;
            }
        }

    };


    //检测更新-获取支线信息
    private Handler handler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case SHOW_RESPONSE:
                    String response = (String) msg.obj;
                    strbb=response;
                    break;

                default: Toast.makeText(getApplicationContext(), "网络连接超时", Toast.LENGTH_SHORT).show();
                    break;
            }
        }

    };



}
