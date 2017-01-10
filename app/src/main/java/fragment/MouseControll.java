package fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.tingyuankeke.remotecontroller.Global;
import com.example.tingyuankeke.remotecontroller.MainActivity;
import com.example.tingyuankeke.remotecontroller.R;
import com.example.tingyuankeke.remotecontroller.RepeatListener;

/**
 * A simple {@link Fragment} subclass.
 */
public class MouseControll extends Fragment {
    Button btnLeft;
    Button btnRight;
    Button btnScrollup;
    Button btnScrolldown;
    TextView txvpad;

    public MouseControll() {
        // Required fempty public constructor
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            if (((MainActivity) getActivity()).go == true) {
                System.out.println("MouseControll  see");
                ((MainActivity) getActivity()).getClient().sendMessage("10#0#0");
            }

        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_mouse_controll, container, false);

        view.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {

                return true;
            }
        });

        return inflater.inflate(R.layout.fragment_mouse_controll, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        btnLeft = (Button) getView().findViewById(R.id.btn_leftClick);
        btnRight = (Button) getView().findViewById(R.id.btn_rightClick);
        btnScrollup = (Button) getView().findViewById(R.id.btn_scrollup);
        btnScrolldown = (Button) getView().findViewById(R.id.btn_scrolldown);
        txvpad = (TextView) getView().findViewById(R.id.txv_mousePad);
    }

    @Override

    public void onResume() {
        super.onResume();
        //mode 0
        //設定所有Button點擊事件
        //滾輪往上


        btnScrollup.setOnTouchListener(new RepeatListener(400, 100, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String ss = "4#0#" + String.valueOf(Global.up_quantum) + "#";
                ((MainActivity) getActivity()).getClient().sendMessage(ss);
            }
        }));

        /*滾輪往下
        //RepeatListener為繼承於OnClickListener的自刻類別
        //用於實現點擊長按的連續點擊功能
        //RepeatListene間(長按判定時間(ms),重複執行間隔（ms,）,new OnClickListener(){.....})
        */
        btnScrolldown.setOnTouchListener(new RepeatListener(400, 100, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String ss = "4#0#" + String.valueOf(-Global.down_quantum) + "#";
                ((MainActivity) getActivity()).getClient().sendMessage(ss);
            }
        }));
        btnLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity) getActivity()).getClient().sendMessage(("2#0#0#"));
            }
        });
        btnRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity) getActivity()).getClient().sendMessage(("3#0#0#"));
            }
        });
        txvpad.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int x = (int) (event.getX() / 2) * Global.x_quantum;
                int y = (int) (event.getY() / 2) * Global.y_quantum;
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN://點下螢幕
                        ((MainActivity) getActivity()).getClient().sendMessage(("0#" + x + "#" + y + "#"));
                        break;
                    case MotionEvent.ACTION_MOVE://螢幕滑動
                        ((MainActivity) getActivity()).getClient().sendMessage(("1#" + x + "#" + y + "#"));
                        break;
                    case MotionEvent.ACTION_UP://左鍵點集
                        if (event.getEventTime() - event.getDownTime() < 200) {//若在一定的時間內點擊又放開就出發左鍵
                            ((MainActivity) getActivity()).getClient().sendMessage(("2#" + x + "#" + y + "#"));
                        }
                        break;
                }

                return true;
            }
        });

    }


}

