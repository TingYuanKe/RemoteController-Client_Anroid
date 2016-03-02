package com.example.tingyuankeke.remotecontroller;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Handler;
import android.text.InputType;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;

import java.net.Socket;


public class MainActivity extends Activity {

    ClientSocket client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //消除標題列
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        //消除狀態列
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        //建立一個POP OUT視窗要求使用者輸入IP Address
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("請輸入Server IP位置");

        // 設定開始畫面Input
        final EditText input = new EditText(this);
        input.setInputType(InputType.TYPE_CLASS_PHONE);
        builder.setView(input);
        //設定預設IP
        input.setText("192.168.");

        // 建立開始畫面Connect Button
        builder.setPositiveButton("Connect", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //建立與client端地連線
                connectClient(input.getText().toString());
            }
        });

        builder.show();

        //設定所有Button點擊事件
        ImageButton btnLeft = (ImageButton) findViewById(R.id.btn_LeftClick);
        Button btnRight = (Button) findViewById(R.id.btn_rightClick);
        Button btnScrollup=(Button) findViewById(R.id.btn_scrollup);
        Button btnScrolldown=(Button) findViewById(R.id.btn_scrolldown);
        ImageButton btnKeyboard=(ImageButton) findViewById(R.id.btn_Keyboard);
        //滾輪往上
        btnScrollup.setOnTouchListener(new RepeatListener(400, 50, new OnClickListener() {
            @Override
            public void onClick(View view) {
                client.sendMessage(("4# m0#150#"));
                System.out.println("一直往上~~~");
            }
        }));
        //滾輪往下
        //RepeatListener為繼承於OnClickListener的自刻類別
        //用於實現點擊長按的連續點擊功能
        //RepeatListener(長按判定時間(ms),重複執行間隔（ms,）,new OnClickListener(){.....})
        btnScrolldown.setOnTouchListener(new RepeatListener(400, 50, new OnClickListener() {
            @Override
            public void onClick(View view) {
                client.sendMessage(("4#0#-150#"));
                System.out.println("一直往下~~~");
            }
        }));
        btnLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                client.sendMessage(("2#0#0#"));
                System.out.println("左鍵點擊");
            }
        });
        btnRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                client.sendMessage(("3#0#0#"));
                System.out.println("右鍵點擊");
            }
        });
        btnKeyboard.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Intent i=new Intent();
                        startActivity(i.setClass(MainActivity.this, KeyboardControll.class));
                        finish();
                    }
                }, 3);
            }
        });
    }



    @Override
    protected void onStart() {
        super.onStart();

    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    private void connectClient(String ip) {
        //新增一個ClientSocket為client
        client = new ClientSocket(ip, 8221);
        //將client連線設定為背景執行
        client.execute();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }



    public boolean onTouchEvent(MotionEvent event) {
        int x = (int) event.getX();
        int y = (int) event.getY();

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN://點下螢幕
                client.sendMessage(("0#" + x + "#" + y + "#"));
                break;
            case MotionEvent.ACTION_MOVE://螢幕滑動
                    client.sendMessage(("1#" + x + "#" + y + "#"));
                    System.out.println("MOVE~~~~");
                break;
            case MotionEvent.ACTION_UP://左鍵點集
                if(event.getEventTime()-event.getDownTime()<200) {//若在一定的時間內點擊又放開就出發左鍵
                    client.sendMessage(("2#" + x + "#" + y + "#"));
                    System.out.println("左鍵");
                }
                break;
        }


        return false;
    }


}
