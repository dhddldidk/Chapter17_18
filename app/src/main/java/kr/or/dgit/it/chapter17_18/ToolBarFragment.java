package kr.or.dgit.it.chapter17_18;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Toast;

public class ToolBarFragment extends Fragment implements SeekBar.OnSeekBarChangeListener{

    private ToolbarListener activityCallback;
    private int seekValue;



    public interface  ToolbarListener{
        void onButtonClick(int fontSize, String next);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try{
            activityCallback = (ToolbarListener) context;

        }catch (ClassCastException e){
            throw new ClassCastException(context.toString()+"must implement ToolBarListener");
        }

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_toolbar, container, false);
        final EditText edit = root.findViewById(R.id.editText);
        SeekBar seekBar = root.findViewById(R.id.seekBar);
        seekBar.setOnSeekBarChangeListener(this);
        Button btnChange = root.findViewById(R.id.btnChange);
        btnChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                activityCallback.onButtonClick(seekValue, edit.getText().toString());
            }
        });

        return root;
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        seekValue = progress;
        activityCallback.onButtonClick(seekValue, null);
        Toast.makeText(getActivity(), seekValue+"", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }
}
