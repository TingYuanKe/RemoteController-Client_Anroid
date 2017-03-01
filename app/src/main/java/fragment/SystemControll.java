package fragment;


import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.SeekBar;

import com.example.tingyuankeke.remotecontroller.MainActivity;
import com.example.tingyuankeke.remotecontroller.R;
import com.example.tingyuankeke.remotecontroller.RepeatListener;

/**
 * A simple {@link Fragment} subclass.
 */
public class SystemControll extends Fragment {

    private static boolean volumeOn;
    private ImageButton btn_increase, btn_decrease, btn_mute,
            btn_desktop, btn_sleep, btn_shutdown;
    private SeekBar skb_volume;
    public SystemControll() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        System.out.println("SystemControll onCreateView");
        return inflater.inflate(R.layout.fragment_system_controll, container, false);
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            System.out.println("SystemControll  see");
            ((MainActivity) getActivity()).getClient().sendMessage("13#0#0");
        }
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        btn_decrease = (ImageButton) getView().findViewById(R.id.imb_decrease);
        btn_increase = (ImageButton) getView().findViewById(R.id.imb_increase);
        btn_mute = (ImageButton) getView().findViewById(R.id.imb_mute);
        btn_desktop = (ImageButton) getView().findViewById(R.id.imb_desktop);
        btn_sleep = (ImageButton) getView().findViewById(R.id.imb_sleep);
        btn_shutdown = (ImageButton) getView().findViewById(R.id.imb_shutdown);
        skb_volume = (SeekBar) getView().findViewById(R.id.skb_volume);

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        System.out.println("SystemControll onDestroyView");
    }

    @Override
    public void onResume() {
        super.onResume();
        System.out.println("SystemControll onResume");
        btn_decrease.setImageResource(R.drawable.ic_tab_decrease);
        btn_increase.setImageResource(R.drawable.ic_tab_increase);
        btn_mute.setImageResource(R.drawable.ic_tab_mute1);
        btn_desktop.setImageResource(R.drawable.ic_tab_desktop);
        btn_sleep.setImageResource(R.drawable.ic_tab_sleep);
        btn_shutdown.setImageResource(R.drawable.ic_tab_shutdown);
        volumeOn = true;

        btn_decrease.setOnTouchListener(new RepeatListener(400, 50, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity) getActivity()).getClient().sendMessage("1#0#0");

            }
        }));

        btn_increase.setOnTouchListener(new RepeatListener(400, 50, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity) getActivity()).getClient().sendMessage("2#0#0");

            }
        }));

        btn_mute.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (volumeOn) {
                    ((MainActivity) getActivity()).getClient().sendMessage("0#0#0");
                    btn_mute.setImageResource(R.drawable.ic_tab_mute2);
                    volumeOn = false;
                } else {
                    ((MainActivity) getActivity()).getClient().sendMessage("0#0#0");
                    btn_mute.setImageResource(R.drawable.ic_tab_mute1);
                    volumeOn = true;
                }
            }
        });
        btn_desktop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity) getActivity()).getClient().sendMessage("3#0#0");
            }
        });
        btn_sleep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity) getActivity()).getClient().sendMessage("4#0#0");
            }
        });
        btn_shutdown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder shutdown = new AlertDialog.Builder(getActivity());
                shutdown.setMessage("Are you sure to SHUTDOWN PC?")
                        .setTitle("WARNING!")
                        .setPositiveButton("YES", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        })
                        .setNegativeButton("NO",null)
                        .show();
            }
        });
    }

    @Override
    public void onStop() {
        super.onStop();
        System.out.println("SystemControll onStop");

    }
}
