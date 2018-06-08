package kr.or.dgit.it.chapter17_18;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;

public class SingleDialogFragment extends DialogFragment {

    NoticeSingleDialogListener mListener;
    int mSelect;

    public interface NoticeSingleDialogListener{
        public void onSingleDialogClick(DialogFragment dialogFragment, String res);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try{
            mListener = (NoticeSingleDialogListener) context;
        }catch (ClassCastException e){
            String msg = "must implement NoticeSingleDialogListener";
            throw new ClassCastException(context.toString()+msg);
        }
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Bundle bundle = getArguments();
        return new AlertDialog.Builder(getActivity())
                .setIcon(R.mipmap.ic_launcher)
                .setTitle(bundle.getString("msg"))
                .setSingleChoiceItems(R.array.fruit, mSelect, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        mSelect = which;
                    }
                })
                .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String[] fruits = getResources().getStringArray(R.array.fruit);
                        mListener.onSingleDialogClick(SingleDialogFragment.this, fruits[mSelect]);
                    }
                })
                .setNegativeButton("취소", null)
                .create();
    }
}
