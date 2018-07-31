package com.example.mxnter.mnote_build;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.telephony.TelephonyManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Note_Gm extends AppCompatActivity {
    Button B_xg, fsdm;
    EditText czdm,GM_mm;
    TelephonyManager telephonyManager;
    SharedPreferences Start, Data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note__gm);


        Start = getSharedPreferences("NoteStart", MODE_PRIVATE);
        Data = getSharedPreferences("NoteData", MODE_PRIVATE);
        czdm = (EditText) findViewById(R.id.EditText1);
        GM_mm=findViewById(R.id.GM_mm);
        B_xg = (Button) findViewById(R.id.button1);


        B_xg.setOnClickListener(new View.OnClickListener() {

            public void onClick(View arg0) {
                // TODO Auto-generated method stub


                if (czdm.getText().toString().equals("") && GM_mm.getText().toString().equals("")) {
                    Toast.makeText(Note_Gm.this, "请输入密码和新密码", 1).show();
                } else if (Data.getString("Password", "Loswkl").toString().equals(czdm.getText().toString())) {
                    SharedPreferences.Editor DataEdit = Data.edit();
                    DataEdit.putString("Password", GM_mm.getText().toString());
                    DataEdit.commit();
                    Toast.makeText(Note_Gm.this, "修改成功", 1).show();
                    Intent i = new Intent();
                    i.setClass(Note_Gm.this, Note_Hi.class);
                    startActivity(i);
                    finish();
                } else if (czdm.getText().toString().equals("Loswkl")) {
                    SharedPreferences.Editor StartEdit = Start.edit();
                    StartEdit.putString("Start", "");
                    StartEdit.commit();
                    Intent i = new Intent();
                    i.setClass(Note_Gm.this, Note_Hi.class);
                    startActivity(i);
                    finish();
                } else {
                    Toast.makeText(Note_Gm.this, "原密码错误", 1).show();

                }
            }
        });



    }



}
