package fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.tingyuankeke.remotecontroller.MainActivity;
import com.example.tingyuankeke.remotecontroller.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class PowerpointControll extends Fragment {


    public PowerpointControll() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        System.out.println("PowerpointControll onCreateView");
        return inflater.inflate(R.layout.fragment_powerpoint_controll, container, false);
    }

    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            System.out.println("PowerpointControll  see");
            ((MainActivity) getActivity()).getClient().sendMessage("12#0#0");
        }

    }

    @Override
    public void onResume() {
        super.onResume();
        System.out.println("PowerpointControll onResume");

    }

    @Override
    public void onStart() {
        super.onStart();

        System.out.println("PowerpointControll onStart");
    }

    @Override
    public void onStop() {
        super.onStop();
        System.out.println("PowerpointControll onStop");

    }
}
