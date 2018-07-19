package com.example.mxnter.mnote_build;

import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Random;

public class Note_Zc extends AppCompatActivity {
    EditText e_yhm, e_mm, e_qrmm, e_sjh;
    Button b_zc, b_cz;
    String yhm, mm, qrmm;
    SharedPreferences Start, Data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note__zc);
        Start = getSharedPreferences("NoteStart", MODE_PRIVATE);
        Data = getSharedPreferences("NoteData", MODE_PRIVATE);


        e_yhm = (EditText) findViewById(R.id.editText1);
        e_mm = (EditText) findViewById(R.id.editText2);
        e_qrmm = (EditText) findViewById(R.id.editText3);
        e_sjh = (EditText) findViewById(R.id.editText4);
        b_zc = (Button) findViewById(R.id.button1);
        b_cz = (Button) findViewById(R.id.button2);


        b_zc.setOnClickListener(new View.OnClickListener() {

            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                if (e_sjh.getText().toString().equals("")) {
                    Toast.makeText(Note_Zc.this, "请输入手机号", 1).show();
                } else if (!e_sjh.getText().toString().matches("[0-9]{1,}")) {
                    Toast.makeText(Note_Zc.this, "请输入数字的手机号", 1).show();
                } else if (e_sjh.getText().toString().length() != 11) {
                    Toast.makeText(Note_Zc.this, "请输入11位手机号", 1).show();
                } else if (e_yhm.getText().toString().equals("")) {
                    Toast.makeText(Note_Zc.this, "请输入用户名", 1).show();
                } else if (e_mm.getText().toString().equals("") && e_qrmm.getText().toString().equals("")) {
                    Toast.makeText(Note_Zc.this, "请输入密码", 1).show();
                } else if (e_mm.getText().toString().equals(e_qrmm.getText().toString())) {

                    SharedPreferences.Editor DataEdit = Data.edit();
                    DataEdit.putString("UserName", e_yhm.getText().toString());
                    DataEdit.putString("Password", e_mm.getText().toString());




                    //生成重置代码
                    Random r1 = new Random();
                    String x = r1.nextInt() + "";
                    Toast.makeText(Note_Zc.this, "即将向您输入的手机号发送一条重置密码(运营商收取一条短信费用)", 1).show();
                    //发送重置代码
                    SmsManager smss = SmsManager.getDefault();
                   // smss.sendTextMessage(e_sjh.getText().toString(), null, "重置代码为：" + x + " 请妥善保管【L'Note】", null, null);
                    Toast.makeText(Note_Zc.this, "重置代码为：" + x + " 请妥善保管【M'Note】", 1).show();
                    SharedPreferences.Editor StartEdit = Start.edit();
                    StartEdit.putString("Start", "ok");
                    DataEdit.putString("telephone", e_sjh.getText().toString());
                    StartEdit.putString("Reset", x);
                    StartEdit.commit();
                    DataEdit.commit();
                    Toast.makeText(Note_Zc.this, "注册成功", 1).show();
                    Intent i = new Intent();
                    i.setClass(Note_Zc.this, Note_Hi.class);
                    startActivity(i);
                    finish();
                } else {
                    Toast.makeText(Note_Zc.this, "密码和确认密码不一致", 1).show();
                }


            }
        });

        b_cz.setOnClickListener(new View.OnClickListener() {

            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                e_yhm.setText("");
                e_mm.setText("");
                e_qrmm.setText("");


            }
        });


    }

}