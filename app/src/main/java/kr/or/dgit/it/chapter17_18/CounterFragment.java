package kr.or.dgit.it.chapter17_18;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class CounterFragment extends Fragment {

    public static CounterFragment newInstance(int start){
        CounterFragment cf = new CounterFragment();
        Bundle args = new Bundle();
        args.putInt("start", start);
        cf.setArguments(args);
        return cf;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_counter,container,false);
        final TextView cntTextView = root.findViewById(R.id.txtCounter);//프래그먼트 안에서만 찾으면 됨.

        if(savedInstanceState!=null){//저장되어 있을 때
            cntTextView.setText(String.valueOf(savedInstanceState.getInt("counter")));//저장된 곳에서 숫자 가져오기
        }

        Bundle args = getArguments();

        if(args!=null){
            int start = args.getInt("start");
            cntTextView.setText(String.valueOf(start));//저장된 곳에서 숫자 가져오기
        }

        Button btnIncrease = root.findViewById(R.id.btnIncrease);
        btnIncrease.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int count = Integer.parseInt(cntTextView.getText().toString());
                cntTextView.setText(String.valueOf(count+1));//클릭할 때마다 숫자가 1씩 증가하도록
            }
        });

        return root;
    }

    //화면을 전환해도 숫자가 그대로 나오도록 데이터 저장하는 함수
    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        final TextView cntTextView = getView().findViewById(R.id.txtCounter);//getView가 root임 fragmemt 뷰.
        int data = Integer.parseInt(cntTextView.getText().toString());
        outState.putInt("counter", data);
    }
}
