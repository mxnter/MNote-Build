package com.example.mxnter.mnote_build;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class Note_Xq extends AppCompatActivity {

    // private static final CharSequence[] Note.str1 = null;
    EditText e_name, e_content;
    SharedPreferences NoteData;
    int no;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note__xq);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences.Editor DataEdit = NoteData.edit();
                DataEdit.putString("NoteName" + no, e_name.getText().toString());
                DataEdit.putString("NoteContent" + no, e_content.getText().toString());
                // DataEdit.putInt("all", 1);
                DataEdit.commit();
                Snackbar.make(view, "已保存", Snackbar.LENGTH_LONG).setAction("Action", null).show();
                Intent i = new Intent();
                i.setClass(Note_Xq.this, Note.class);
                startActivity(i);
                finish();
                //Toast.makeText(getApplicationContext(), "已保存", 1).show();
            }
        });
 //       Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();

        e_name = (EditText) findViewById(R.id.xq_name);
        e_content = (EditText) findViewById(R.id.xq_content);

        NoteData = getSharedPreferences("NoteData", MODE_PRIVATE);

        // 设置EditText的显示方式为多行文本输入
        e_content.setInputType(InputType.TYPE_TEXT_FLAG_MULTI_LINE);
        // 文本显示的位置在EditText的最上方
        e_content.setGravity(Gravity.TOP);
        // 改变默认的单行模式
        e_content.setSingleLine(false);
        // 水平滚动设置为False
        e_content.setHorizontallyScrolling(false);
        Intent i = getIntent();
        no = i.getIntExtra("no", 1);
        // ("no");
        // =i.getIntExtra("no");
        e_name.setText(Note.str1[no]);
        e_content.setText(Note.str2[no]);
        // i.getStringExtra("x");



    }

    // 改写物理按键 返回键的逻辑
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // TODO Auto-generated method stub
        if (keyCode == KeyEvent.KEYCODE_BACK) {

            Intent i = new Intent();
            i.setClass(Note_Xq.this, Note.class);
            startActivity(i);
            Toast.makeText(getApplicationContext(), "返回将不会保存", 1).show();


            finish();


//
//            SharedPreferences.Editor DataEdit = NoteData.edit();
//            DataEdit.putString("NoteName" + no, e_name.getText().toString());
//            DataEdit.putString("NoteContent" + no, e_content.getText().toString());
//            // DataEdit.putInt("all", 1);
//            DataEdit.commit();
//            Intent i = new Intent();
//            i.setClass(Note_Xq.this, Note.class);
//            startActivity(i);
//            Toast.makeText(getApplicationContext(), "已保存", 1).show();
//            finish();
        }
        return super.onKeyDown(keyCode, event);
    }

}
