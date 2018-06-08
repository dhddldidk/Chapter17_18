package kr.or.dgit.it.chapter17_18;

import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class SimpleDialogFragmentActivity extends AppCompatActivity
        implements QuestionDialogFragment.NoticeDialogListener,
                    ListDialogFragment.NoticeListDialogListener,
                    SingleDialogFragment.NoticeSingleDialogListener,
                    MultipleDialogFragment.NoticeMultipleDialogListener,
                    CustomDialogFragment.NoticeCustomDialogListener{

    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple_dialog_fragment);
        setTitle(getIntent().getStringExtra("title"));

        tv = findViewById(R.id.tvResult);
    }

    public void btnQDialClick(View view) {
        QuestionDialogFragment questionDialogFragment = new QuestionDialogFragment();
        Bundle bundle = new Bundle();
        bundle.putString("title", "질문");
        bundle.putString("msg", "어떤 연산을 하시겠습니까?");
        bundle.putString("Q1", "덧셈");
        bundle.putString("Q2", "곱셈");
        bundle.putInt("a", 3);
        bundle.putInt("b", 4);

        questionDialogFragment.setArguments(bundle);
        questionDialogFragment.show(getSupportFragmentManager(),"Dialog");

    }

    public void btnListDialClick(View view) {
        ListDialogFragment listDialogFragment = new ListDialogFragment();
        Bundle bundle = new Bundle();
        bundle.putString("msg","과일을 선택하세요.");

        listDialogFragment.setArguments(bundle);
        listDialogFragment.show(getSupportFragmentManager(),"Dialog");
    }

    public void btnSingleClick(View view) {
        SingleDialogFragment singleDialogFragment = new SingleDialogFragment();
        Bundle bundle = new Bundle();
        bundle.putString("msg", "마음에 드는 과일을 선택하세요.");

        singleDialogFragment.setArguments(bundle);
        singleDialogFragment.show(getSupportFragmentManager(), "Dialog");
    }

    public void btnMultiClick(View view) {
        MultipleDialogFragment multipleDialogFragment = new MultipleDialogFragment();
        Bundle bundle = new Bundle();
        bundle.putString("msg", "마음에 드는 과일을 선택하세요.");

        multipleDialogFragment.setArguments(bundle);
        multipleDialogFragment.show(getSupportFragmentManager(),"Dialog");
    }

    public void btnCustomClick(View view) {
        CustomDialogFragment customDialogFragment = new CustomDialogFragment();
        Bundle bundle = new Bundle();
        bundle.putString("msg", "주문정보가 입력되었습니다.");
        customDialogFragment.setArguments(bundle);
        customDialogFragment.show(getSupportFragmentManager(),"Dialog");
    }

    @Override
    public void onDialogClick(DialogFragment dialogFragment, int res) {
        tv.setText("연산 결과 = "+res);
        Toast.makeText(this, "연산을 완료하였습니다.", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onDialogClick(DialogFragment dialogFragment, String res) {
        tv.setText("선택 결과 = "+res);
        Toast.makeText(this, "마음에 드는 과일을 선택하였습니다.", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onSingleDialogClick(DialogFragment dialogFragment, String res) {
        tv.setText("선택 결과 = "+res);
        Toast.makeText(this, "라디오 버튼으로 마음에 드는 과일을 선택하였습니다.", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onMultipleDialogClick(DialogFragment dialogFragment, String res) {
        tv.setText("선택 결과 = "+res);
        Toast.makeText(this, "콤보박스로 마음에 드는 과일을 선택하였습니다.", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onCustomDialogClick(DialogFragment dialogFragment, String res) {
        tv.setText("선택 결과 = "+res);
        Toast.makeText(this, "콤보박스로 마음에 드는 과일을 선택하였습니다.", Toast.LENGTH_LONG).show();
    }
}
