package com.example.tingyuankeke.remotecontroller;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.SeekBar;
import android.widget.TextView;

public class Setting extends AppCompatActivity {
    private SeekBar seekBar_updown;
    private SeekBar seekBar_xy;
    private TextView txv_xy, txv_updown;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity_setting);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        seekBar_updown = (SeekBar) findViewById(R.id.seekBar5_UPDOWN);
        seekBar_xy = (SeekBar) findViewById(R.id.seekBar_XY);
        txv_updown = (TextView) findViewById(R.id.txv_updown);
        txv_xy = (TextView) findViewById(R.id.txv_xy);

        seekBar_xy.setProgress(Global.xy_seekbar_index);
        txv_xy.setText(seekBar_xy.getProgress() + "/" + seekBar_xy.getMax());
        seekBar_updown.setProgress(Global.up_down_seekbar_index);
        txv_updown.setText(seekBar_updown.getProgress() + "/" + seekBar_updown.getMax());

        seekBar_xy.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                switch (seekBar.getProgress()) {
                    case 0:
                        Global.x_quantum = 1;
                        Global.y_quantum = 1;
                        break;
                    case 1:
                        Global.x_quantum = 2;
                        Global.y_quantum = 2;
                        break;
                    case 2:
                        Global.x_quantum = 4;
                        Global.y_quantum = 4;
                        break;
                }

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                txv_xy.setText(seekBar.getProgress() + "/" + seekBar.getMax());
                Global.xy_seekbar_index = seekBar.getProgress();
            }
        });
        seekBar_updown.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                switch (seekBar.getProgress()) {
                    case 0:
                        Global.up_quantum = 90;
                        Global.down_quantum = 90;
                        break;
                    case 1:
                        Global.up_quantum = 120;
                        Global.down_quantum = 120;
                        break;
                    case 2:
                        Global.up_quantum = 150;
                        Global.down_quantum = 150;
                        break;
                    case 3:
                        Global.up_quantum = 180;
                        Global.down_quantum = 180;
                        break;
                    case 4:
                        Global.up_quantum = 210;
                        Global.down_quantum = 210;
                        break;
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                txv_updown.setText(seekBar.getProgress() + "/" + seekBar.getMax());
                Global.up_down_seekbar_index = seekBar.getProgress();
            }
        });

    }

}
