package com.example.tingyuankeke.remotecontroller;
import android.os.Debug;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import java.io.DataOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import fragment.KeyboardControll;
import fragment.MouseControll;
import fragment.PowerpointControll;
import fragment.SystemControll;

public class MainActivity extends AppCompatActivity implements OnClickListener {

    public boolean go = false;
    Timer timer = new Timer(true);
    private DataOutputStream streamOut = null;


    private ClientSocket client = new ClientSocket("", 0);
    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private ImageButton btn_setting;
    private int[] tabIcons = {
            R.drawable.ic_tab_mouse,
            R.drawable.ic_tab_keyboard,
            R.drawable.ic_tab_ppt,
            R.drawable.ic_tab_pc,
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        setupView();
        setupTabIcons();
        ConnectCheck();

    }


    @Override
    protected void onResume() {
        super.onResume();
    }

//    private void TCP_Heartbeat() {
//        if (getClient().Heartbeat()) {
//            System.out.println("All is well~~");
//        } else {
//            if (go) {
//                getClient().stop();
//                go = false;
//                this.runOnUiThread(new Runnable() {
//                    public void run() {
//                        final AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
//                        builder.setCancelable(false);
//                        builder.setTitle("哎呀~好像沒有連上喔\"");
//                        builder.setPositiveButton("Try", new DialogInterface.OnClickListener() {
//                            @Override
//                            public void onClick(DialogInterface dialog, int which) {
//                                go = false;
//                                ConnectCheck();
//                            }
//                        });
//                        builder.show();
//                    }
//                });
//            }
//        }
//    }

    @Override
    protected void onStop() {
        super.onStop();

    }

    public void ConnectCheck() {
        //建立一個POP OUT視窗要求使用者輸入IP Address
        final AlertDialog.Builder builder = new AlertDialog.Builder(this,R.style.AlertDialogCustom);
        builder.setCancelable(false);
        builder.setTitle("請輸入Server IP位置");

        // 設定開始畫面Input
        final EditText input = new EditText(this);
        input.setInputType(InputType.TYPE_CLASS_PHONE);
        input.setTextColor(getResources().getColor(R.color.white));
        input.setText("192.168.");
        // 建立開始畫面Connect Button
        this.runOnUiThread(new Runnable() {
            public void run() {
                builder.setView(input)
                        .setPositiveButton("Connect", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                //建立與client端地連線
                                connectClient(input.getText().toString());
                                if (go == false) //returns true if internet available
                                {
                                    Toast.makeText(MainActivity.this, "唉呦  好像沒有連上喔", Toast.LENGTH_LONG).show();
                                    ConnectCheck();
                                } else {
                                    Toast.makeText(MainActivity.this, "連上啦!", Toast.LENGTH_LONG).show();
                                    timer.schedule(new TimerTask() {
                                        @Override
                                        public void run() {
                                            new Thread(new Runnable() {

                                                @Override
                                                public void run() {
                                                    if (go) {
                                                    }
                                                }
                                            }).start();
                                        }

                                    }, 1000, 2000);
                                }
                            }
                        })
                        .create().setOnShowListener(new DialogInterface.OnShowListener() {
                    @Override
                    public void onShow(DialogInterface dialog) {
                    }
                });
                builder.show().getWindow().setLayout(1000,600);
            }
        });
    }

    public ClientSocket getClient() {
        return client;
    }

    public void setClient(ClientSocket client) {
        this.client = client;
    }

    private void setupView(){
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle("MouseControll");
        btn_setting = (ImageButton) findViewById(R.id.btn_setting);
        btn_setting.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Setting.class);
                startActivity(intent);
            }
        });
        viewPager = (NonSwipeableViewPager) findViewById(R.id.view_Changer);
//        viewPager.setOnTouchListener((View.OnTouchListener) this);
        setupViewPager(viewPager);
        tabLayout = (TabLayout) findViewById(R.id.tab_Change);
        tabLayout.setupWithViewPager(viewPager);

    }

    private void setupTabIcons() {
        tabLayout.getTabAt(0).setIcon(tabIcons[0]);
        tabLayout.getTabAt(1).setIcon(tabIcons[1]);
        tabLayout.getTabAt(2).setIcon(tabIcons[2]);
        tabLayout.getTabAt(3).setIcon(tabIcons[3]);

        tabLayout.getTabAt(0).setText("Mouse");
        tabLayout.getTabAt(1).setText("Keybd");
        tabLayout.getTabAt(2).setText("PPT");
        tabLayout.getTabAt(3).setText("System");
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        viewPager.setOffscreenPageLimit(4);
        adapter.addFragment(new MouseControll(), "Mouse");
        adapter.addFragment(new KeyboardControll(), "Key");
        adapter.addFragment(new PowerpointControll(), "PPT");
        adapter.addFragment(new SystemControll(), "System");
        viewPager.setAdapter(adapter);

    }

    private void connectClient(String ip) {
        //新增一個ClientSocket為client
        setClient(new ClientSocket(ip, 8221));
        //將client連  線設定為背景執行
        getClient().execute();
        getClient().sendMessage(("0"));
        go = true;

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public void onClick(View v) {

    }

    public class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }


}
