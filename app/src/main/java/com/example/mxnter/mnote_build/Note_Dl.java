package com.example.mxnter.mnote_build;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class Note_Dl extends AppCompatActivity {
    EditText e1, e2;
    Button b1, b2;
    SharedPreferences Data, Start;
    CheckBox c1, c2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note__dl);
        e1 = (EditText) findViewById(R.id.editText1);
        e2 = (EditText) findViewById(R.id.editText2);
        b1 = (Button) findViewById(R.id.button1);
        b2 = (Button) findViewById(R.id.button2);

        Start = getSharedPreferences("NoteStart", MODE_PRIVATE);
        Data = getSharedPreferences("NoteData", MODE_PRIVATE);
        e1.setText(Data.getString("UserName", ""));

        b1.setOnClickListener(new View.OnClickListener() {

            public void onClick(View arg0) {
                // TODO Auto-generated method stub

                if (e1.getText().toString().equals("")) {
                    Toast.makeText(Note_Dl.this, "请输入用户名", 1).show();
                } else if (e2.getText().toString().equals("")) {
                    Toast.makeText(Note_Dl.this, "请输入密码", 1).show();

                } else if (e2.getText().toString().equals(Data.getString("Password", ""))) {
                    Toast.makeText(Note_Dl.this, "登陆成功", 1).show();
                    Intent i = new Intent();
                    i.setClass(Note_Dl.this, Note.class);
                    startActivity(i);
                    finish();

                } else {
                    Toast.makeText(Note_Dl.this, "用户名或密码错误", 1).show();
                }

            }
        });


        b2.setOnClickListener(new View.OnClickListener() {

            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                finish();
            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // getMenuInflater().inflate(R.menu.activity_main, menu);
        menu.add(0, 1, 0, "重置账号");
        // menu.add(0,2,0,"返回");

        //menu.add(0,4,0,"检查更新");
        //menu.add(0,3,0,"退出");
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // TODO Auto-generated method stub
        if (item.getItemId() == 1) {


            Intent i = new Intent();
            i.setClass(Note_Dl.this, Note_Cz.class);
            startActivity(i);
            finish();


//    		// View text=
//
//    		 AlertDialog.Builder b= new AlertDialog.Builder(Note_Dl.this);
//
//				b.setTitle(Start.getString("Reset", "").toString());
//
//
//
//
//
//				b.setPositiveButton("确认", new DialogInterface.OnClickListener() {
//
//					public void onClick(DialogInterface arg0, int arg1) {
//						// TODO Auto-generated method stub
//						if(Start.getString("Reset", "").toString().equals("1")){
//
//
//		    	        }else{
//
//
//		    	        }
//		    			 SharedPreferences.Editor StartEdit=Start.edit();
//		    		 	StartEdit.putString("Start", "");
//						StartEdit.commit();
//						Intent i=new Intent();
//						i.setClass(Note_Dl.this, Hi.class);
//						startActivity(i);
//						finish();
//					}
//				});
//				b.show();
//


        }


        return super.onOptionsItemSelected(item);
    }


}