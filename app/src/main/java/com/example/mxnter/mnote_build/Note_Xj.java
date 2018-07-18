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

public class Note_Xj extends AppCompatActivity {


    // private static final CharSequence[] Note.str1 = null;
    TextView t1, t2;
    ImageView back, bc;
    EditText e_name, e_content;
    SharedPreferences NoteData;
    int no;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note__xj);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                SharedPreferences.Editor DataEdit = NoteData.edit();
                DataEdit.putString("NoteName" + (no), e_name.getText().toString());
                DataEdit.putString("NoteContent" + (no), e_content.getText().toString());
                DataEdit.putInt("all", no+1);
                DataEdit.commit();
                Toast.makeText(getApplicationContext(), "已保存", 1).show();
                Snackbar.make(view, "已保存", Snackbar.LENGTH_LONG).setAction("Action", null).show();

                Intent i = new Intent();
                i.setClass(Note_Xj.this, Note.class);
                startActivity(i);
                finish();
            }
        });


        e_name = (EditText) findViewById(R.id.xj_name);
        e_content = (EditText) findViewById(R.id.xj_content);


        // 设置EditText的显示方式为多行文本输入
        e_content.setInputType(InputType.TYPE_TEXT_FLAG_MULTI_LINE);
        // 文本显示的位置在EditText的最上方
        e_content.setGravity(Gravity.TOP);
        // 改变默认的单行模式
        e_content.setSingleLine(false);
        // 水平滚动设置为False
        e_content.setHorizontallyScrolling(false);



        NoteData = getSharedPreferences("NoteData", MODE_PRIVATE);
        no = NoteData.getInt("all", -1);

        if (no == -1) {
            // Toast.makeText(getApplicationContext(), "对不起读取错误，请新建一个",
            // 1).show();
            no=0;
            Toast.makeText(getApplicationContext(), "对不起，出现错误，我们已重新开始新建", 1).show();

        }






    }

    // 改写物理按键 返回键的逻辑
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // TODO Auto-generated method stub
        if (keyCode == KeyEvent.KEYCODE_BACK) {
//			SharedPreferences.Editor DataEdit = NoteData.edit();
//			DataEdit.putString("NoteName" + no, e_name.getText().toString());
//			DataEdit.putString("NoteContent" + no, e_content.getText().toString());
//			// DataEdit.putInt("all", 1);
//			DataEdit.commit();
//			Intent i = new Intent();
//			i.setClass(Note_xj.this, Note.class);
//			startActivity(i);
            Toast.makeText(getApplicationContext(), "未新建", 1).show();
            Intent i = new Intent();
            i.setClass(Note_Xj.this, Note.class);
            startActivity(i);
            finish();
        }
        return super.onKeyDown(keyCode, event);
    }

}
