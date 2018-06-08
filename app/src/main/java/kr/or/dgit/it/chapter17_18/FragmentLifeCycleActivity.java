package kr.or.dgit.it.chapter17_18;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class FragmentLifeCycleActivity extends AppCompatActivity {

    private static final String TAG = "Fragment_Life_Cycle";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "Activity - onCreate()");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment_life_cycle);
        setTitle(getIntent().getStringExtra("title"));
    }

    @Override
    protected void onStart() {
        Log.d(TAG, "Activity - onStart()");
        super.onStart();
    }

    @Override
    protected void onResume() {
        Log.d(TAG, "Activity - onResume()");
        super.onResume();
    }

    @Override
    protected void onPause() {
        Log.d(TAG, "Activity - onPause()");
        super.onPause();
    }

    @Override
    protected void onStop() {
        Log.d(TAG, "Activity - onStop()");
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        Log.d(TAG, "Activity - onDestroy()");
        super.onDestroy();
    }


    //프래그먼트 생명주기
    public static class CounterFragment extends Fragment{
        @Override
        public void onAttach(Context context) {
            Log.d(TAG, "\t\tFragment - onAttach()");
            super.onAttach(context);
        }

        @Override
        public void onCreate(@Nullable Bundle savedInstanceState) {
            Log.d(TAG, "\t\tFragment - onCreate()");
            super.onCreate(savedInstanceState);
        }

        //화면을 전환해도 숫자가 그대로 나오도록 데이터 저장하는 함수
        @Override
        public void onSaveInstanceState(@NonNull Bundle outState) {
            super.onSaveInstanceState(outState);
            final TextView cntTextView = getView().findViewById(R.id.txtCounter);//getView가 root임 fragmemt 뷰.
            int data = Integer.parseInt(cntTextView.getText().toString());
            outState.putInt("counter", data);
        }

        @Nullable
        @Override
        public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            Log.d(TAG, "\t\tFragment - onCreateView()");
            View root = inflater.inflate(R.layout.fragment_counter,container,false);
            final TextView cntTextView = root.findViewById(R.id.txtCounter);//프래그먼트 안에서만 찾으면 됨.

            if(savedInstanceState!=null){//저장되어 있을 때
                cntTextView.setText(String.valueOf(savedInstanceState.getInt("counter")));//저장된 곳에서 숫자 가져오기
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

        @Override
        public void onActivityCreated(@Nullable Bundle savedInstanceState) {
            Log.d(TAG, "\t\tFragment - onActivityCreated()");
            super.onActivityCreated(savedInstanceState);
        }

        @Override
        public void onStart() {
            Log.d(TAG, "\t\tFragment - onStart()");
            super.onStart();
        }

        @Override
        public void onResume() {
            Log.d(TAG, "\t\tFragment - onResume()");
            super.onResume();
        }

        @Override
        public void onPause() {
            Log.d(TAG, "\t\tFragment - onPause()");
            super.onPause();
        }

        @Override
        public void onStop() {
            Log.d(TAG, "\t\tFragment - onStop()");
            super.onStop();
        }

        @Override
        public void onDestroyView() {
            Log.d(TAG, "\t\tFragment - onDestroyView()");
            super.onDestroyView();
        }

        @Override
        public void onDestroy() {
            Log.d(TAG, "\t\tFragment - onDestroy()");
            super.onDestroy();
        }

        @Override
        public void onDetach() {
            Log.d(TAG, "\t\tFragment - onDetach()");
            super.onDetach();
        }
    }
}
