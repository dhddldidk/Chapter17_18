package kr.or.dgit.it.chapter17_18;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class WebViewFragmentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view_fragment);
        setTitle(getIntent().getStringExtra("title"));

        WebFragment webFrag = new WebFragment();
        getFragmentManager().beginTransaction()
                .add(R.id.frame, webFrag).commit();
    }
}
