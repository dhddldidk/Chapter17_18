package kr.or.dgit.it.chapter17_18;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;

import static android.support.v4.os.LocaleListCompat.create;

public class ListDialogFragment extends DialogFragment {

    NoticeListDialogListener mListener;

    public interface NoticeListDialogListener{
        public void onDialogClick(DialogFragment dialogFragment, String res);
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try{
            mListener = (NoticeListDialogListener) context;
        }catch (ClassCastException e){
            throw new ClassCastException(context.toString()+"mush implement NoticeListDialogListener");
        }
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        Bundle bundle = getArguments();
        return new AlertDialog.Builder(getActivity())
                .setIcon(R.mipmap.ic_launcher)
                .setTitle(bundle.getString("msg"))
                .setItems(R.array.fruit, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String[] fruits = getResources().getStringArray(R.array.fruit);
                        mListener.onDialogClick(ListDialogFragment.this, fruits[which]);
                    }
                })
                .setNegativeButton("취소", null)
                .create();
    }
}
