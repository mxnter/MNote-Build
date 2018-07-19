package com.example.mxnter.mnote_build;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;

public class Note_Zl extends AppCompatActivity {

    EditText e_name,e_password,e_phone;
    SharedPreferences Start, Data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note__zl);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        e_name=findViewById(R.id.zl_name);
        e_password=findViewById(R.id.zl_pasword);
        e_phone=findViewById(R.id.zl_phone);
        Start = getSharedPreferences("NoteStart", MODE_PRIVATE);
        Data = getSharedPreferences("NoteData", MODE_PRIVATE);

        e_name.setText(Data.getString("UserName",""));
        //e_password.setText(Data.getString("Password",""));
        e_phone.setText(Data.getString("telephone",""));


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(e_password.getText().toString().equals(Data.getString("Password",""))&&e_phone.getText().length()==11){

                    SharedPreferences.Editor DataEdit = Data.edit();
                    DataEdit.putString("UserName", e_name.getText().toString());
                    DataEdit.putString("telephone", e_phone.getText().toString());
                    DataEdit.commit();

                    Snackbar.make(view, "修改成功", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                    Intent i = new Intent();
                    i.setClass(Note_Zl.this, Note_Hi.class);
                    startActivity(i);
                    finish();





                }else{
                    Snackbar.make(view, "修改失败请检测您输入的内容！", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();

                }



            }
        });
    }
}
