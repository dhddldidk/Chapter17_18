package kr.or.dgit.it.chapter17_18;

import android.content.res.Configuration;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MultiDescAcrtivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);


        if(getResources().getConfiguration().orientation== Configuration.ORIENTATION_LANDSCAPE){
            finish();
            return;
        }
        //메인화면에서 선택된 인덱스를 프래그먼트에 넘겨줌
        int index = getIntent().getExtras().getInt("selectedIndex");
        MultiDescFragment df = MultiDescFragment.newInstance(index);

        getSupportFragmentManager().beginTransaction().add(android.R.id.content, df).commit();
    }
}
