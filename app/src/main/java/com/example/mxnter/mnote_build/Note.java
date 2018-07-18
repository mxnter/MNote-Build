package com.example.mxnter.mnote_build;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnLongClickListener;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;
import android.support.v4.app.NavUtils;


public class Note extends AppCompatActivity {




    ListView l;
    int all;
    SharedPreferences NoteData;
    public static  String str1[], str2[];
    ImageView i_gy,i_xj;
    //FloatingActionButton FAB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);
        //Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
      //  setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent();
                 i.setClass(Note.this, Note_Xj.class);
                startActivity(i);
                finish();

            }
        });
/**
 *
 *                 Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG).setAction("Action", null).show();
 *
 * */


        i_gy=(ImageView)findViewById(R.id.gy);
       // i_xj=(ImageView)findViewById(R.id.imageView2);

        l = (ListView) findViewById(R.id.listView1);


        //读取数据
        NoteData = getSharedPreferences("NoteData", MODE_PRIVATE);
        all = NoteData.getInt("all", -1);
        // no=Note.getInt("no", -1);

        if (all == -1) {
            // Toast.makeText(getApplicationContext(), "对不起读取错误，请新建一个",
            // 1).show();
            SharedPreferences.Editor DataEdit = NoteData.edit();
            DataEdit.putString("NoteName0", "欢迎使用M'Note");
            DataEdit.putString("NoteContent0", "本程序由  Mxnter Group 制造");
            DataEdit.putString("NoteName1", "新建");
            DataEdit.putString("NoteContent1", "点击右下角加号");
            DataEdit.putString("NoteName2", "修改");
            DataEdit.putString("NoteContent2", "单击修改");
            DataEdit.putString("NoteName3", "删除");
            DataEdit.putString("NoteContent3", "长按删除");
            DataEdit.putInt("all", 4);
            DataEdit.commit();
            all = NoteData.getInt("all", -1);
        }
        str1 = new String[all];
        str2 = new String[all];

        for (int i = 0; i < all; i++) {
            // no[i]=i;

            str1[i] = NoteData.getString("NoteName" + i, null);
            str2[i] = NoteData.getString("NoteContent" + i, null);

        }

        //显示数据
        List<Map<String, Object>> data = new ArrayList<Map<String, Object>>();
        for (int i = 0; i < str1.length; i++) {// str1.length
            Map<String, Object> m = new HashMap<String, Object>();
            m.put("name", str1[i]);
            m.put("talk", str2[i]);
            // m.put("tx", tx[i]);
            data.add(m);
        }
        // ArrayAdapter<String> aa=new ArrayAdapter<String>(MainActivity.this,
        // android.R.layout.simple_list_item_multiple_choice, str) ;
        // l.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        SimpleAdapter sa = new SimpleAdapter(this, data, R.layout.note_ys,new String[] { "name", "talk" }, new int[] { R.id.textView1,R.id.textView2 });
        l.setOnItemClickListener(new OnItemClickListener() {

            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
                                    long arg3) {
                // TODO Auto-generated method stub
                Intent i = new Intent();
                i.setClass(Note.this, Note_Xq.class);
                i.putExtra("no", arg2);
                //i.putExtra("t", str2[arg2]);
                // i.putExtra("x", tx[arg2]);
                startActivity(i);
                finish();
            }

        });
        l.setOnItemLongClickListener(new OnItemLongClickListener() {
            public boolean onItemLongClick(AdapterView<?> arg0, View arg1,final int arg2, long arg3) {
                // TODO Auto-generated method stub
                AlertDialog.Builder d= new AlertDialog.Builder(Note.this);
                d.setTitle("删除提醒");
                d.setMessage("您将删除标题为　"+str1[arg2]+"　的笔记");
                d.setPositiveButton("确认", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface arg0, int arg1) {
                        // TODO Auto-generated method stub


                        for(int i=arg2;i<all-1;i++){
                            SharedPreferences.Editor DataEdit = NoteData.edit();
                            DataEdit.putString("NoteName"+i, str1[i+1]);
                            DataEdit.putString("NoteContent"+i, str2[i+1]);
                            DataEdit.commit();



                        }

                        SharedPreferences.Editor DataEdit = NoteData.edit();
                        DataEdit.remove("NoteName"+(all-1));
                        DataEdit.remove("NoteContent"+(all-1));
                        DataEdit.putInt("all", all-1);
                        DataEdit.commit();
                        Toast.makeText(getApplicationContext(), "删除成功", 1).show();
                        Intent i = new Intent();
                        i.setClass(Note.this, Note.class);
                        startActivity(i);
                        finish();

                    }
                });
                d.setNegativeButton("取消", new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface arg0, int arg1) {
                        // TODO Auto-generated method stub

                    }
                });
                d.show();

                return true;
            }
        });




        l.setAdapter(sa);



        i_gy.setOnClickListener(new View.OnClickListener() {

            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                Intent i = new Intent();
                i.setClass(Note.this, Note_Gy.class);
                startActivity(i);

            }
        });

//        i_xj.setOnClickListener(new View.OnClickListener() {
//
//            public void onClick(View arg0) {
//                // TODO Auto-generated method stub
//                Intent i = new Intent();
//               // i.setClass(Note.this, Note_xj.class);
//                startActivity(i);
//                finish();
//
//            }
//        });



    }

    @Override
    protected void onDestroy() {
        // TODO Auto-generated method stub

        super.onDestroy();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // getMenuInflater().inflate(R.menu.activity_main, menu);
        //menu.add(0, 1, 0, "重置账号");
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // TODO Auto-generated method stub
//		if (item.getItemId() == 1) {
//
//			finish();
//
//		}

        return super.onOptionsItemSelected(item);
    }

}













