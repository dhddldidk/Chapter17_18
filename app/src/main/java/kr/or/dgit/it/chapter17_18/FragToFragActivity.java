package kr.or.dgit.it.chapter17_18;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class FragToFragActivity extends AppCompatActivity implements ToolBarFragment.ToolbarListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_frag_to_frag);
    }

    @Override
    public void onButtonClick(int fontSize, String text) {
        ContentFragment cf = (ContentFragment) getSupportFragmentManager().findFragmentById(R.id.contentFragment);
        cf.changeTextProperties(fontSize, text);
    }
}
