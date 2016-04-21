package fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.tingyuankeke.remotecontroller.MainActivity;
import com.example.tingyuankeke.remotecontroller.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class KeyboardControll extends Fragment {

    private EditText txv_input;
    private Button btn_send;
    private String str;

    public KeyboardControll() {
        // Required empty public constructor
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            System.out.println("KeybdControll  see");
            ((MainActivity) getActivity()).getClient().sendMessage("11#0#0");
        }
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        txv_input = (EditText) getView().findViewById(R.id.edittext);
        btn_send = (Button) getView().findViewById(R.id.btn_send);
    }

    @Override
    public void onResume() {
        super.onResume();
        txv_input.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                str = txv_input.getText().toString();
                String s = ("0#" + str);
                System.out.println(s);
                ((MainActivity) getActivity()).getClient().sendMessage(s);
                txv_input.setText(null);
                return false;
            }
        });
        btn_send.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v) {

            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_keyboard_controll, container, false);
    }


}
