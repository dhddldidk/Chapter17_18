package kr.or.dgit.it.chapter17_18;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class FragmentToActivity extends AppCompatActivity {

    private EditText et;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment_to);
        setTitle(getIntent().getStringExtra("title"));

        et=findViewById(R.id.editStartNum);
    }

    public void btnPushClick(View view) {

        int start = Integer.parseInt(et.getText().toString());
        CounterFragment cf = CounterFragment.newInstance(start);

        getSupportFragmentManager().beginTransaction()
                .add(R.id.frame, cf, "counterFrag")
                .addToBackStack(null)
                .commit();


    }
}
