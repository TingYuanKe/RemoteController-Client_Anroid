package fragment;


import android.content.Context;
import com.example.tingyuankeke.remotecontroller.Global;
import android.content.DialogInterface;
import android.hardware.input.InputManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.view.inputmethod.InputMethodManager;
import com.example.tingyuankeke.remotecontroller.MainActivity;
import com.example.tingyuankeke.remotecontroller.R;
import com.example.tingyuankeke.remotecontroller.RepeatListener;

/**
 * A simple {@link Fragment} subclass.
 */
public class KeyboardControll extends Fragment {

    private EditText txv_input;
    private Button btn_send;
    private String str;
    private InputManager imm;
    public KeyboardControll() {
        // Required empty public constructor
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            System.out.println("KeybdControll  see");
            ((MainActivity) getActivity()).getClient().sendMessage("11#0#0");
            //show keyboard when focus textview
            InputMethodManager imm=(InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY);
        }
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        txv_input = (EditText) getView().findViewById(R.id.edittext);
        txv_input.requestFocus();
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
                ((MainActivity) getActivity()).getClient().sendMessage(s);
                txv_input.setText(null);
                return false;
            }
        });
        txv_input.setOnKeyListener(new TextView.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                //You can identify which key pressed buy checking keyCode value with KeyEvent.KEYCODE_
//                    switch (keyCode) {
//                        case KeyEvent.KEYCODE_DEL:
//                            ((MainActivity) getActivity()).getClient().sendMessage("2#");
//                            break;
//                        case KeyEvent.KEYCODE_BUTTON_A:
//                            ((MainActivity) getActivity()).getClient().sendMessage("a#");
//                }
                if (keyCode == KeyEvent.KEYCODE_DEL) {
                    ((MainActivity) getActivity()).getClient().sendMessage("2#");
                }
                return false;
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
