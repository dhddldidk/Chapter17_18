package kr.or.dgit.it.chapter17_18;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;

public class CustomDialogFragment extends DialogFragment {

    NoticeCustomDialogListener mListener;

    public interface NoticeCustomDialogListener{
        public void onCustomDialogClick(DialogFragment dialogFragment, String res);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try{
            mListener = (NoticeCustomDialogListener) context;
        }catch (ClassCastException e){
            String msg = "must implement NoticeCustomDialogListener";
            throw  new ClassCastException(context.toString()+msg);
        }
    }

    @Override public Dialog onCreateDialog(Bundle savedInstanceState) {
        Bundle bundle = getArguments();
        final LinearLayout layout = (LinearLayout) getActivity().getLayoutInflater().inflate(R.layout. custom_order , null);
        return new AlertDialog.Builder(getActivity())
                .setIcon(R.mipmap. ic_launcher )
                .setTitle(bundle.getString("msg"))
                .setView(layout)
                .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                    @Override public void onClick(DialogInterface dialog, int which) {
                        EditText product = (EditText) layout.findViewById(R.id. product );
                        EditText number = (EditText) layout.findViewById(R.id. number );
                        CheckBox payMethod = (CheckBox) layout.findViewById(R.id. paymethod );
                        StringBuilder sb = new StringBuilder(); sb.append(product.getText() + " ");
                        sb.append(number.getText() + "개 ");
                        sb.append(payMethod.isChecked()?"착불 결제":"");
                        mListener.onCustomDialogClick(CustomDialogFragment.this, sb.toString());
                    }
                })
                .setNegativeButton("취소", new DialogInterface.OnClickListener() {
                    @Override public void onClick(DialogInterface dialog, int which) {
                        mListener.onCustomDialogClick(CustomDialogFragment.this, "주문을 취소했습니다.");
                    }
                })
                .create();
    }
}

