package kr.or.dgit.it.chapter17_18;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;

import java.util.ArrayList;

public class MultipleDialogFragment extends DialogFragment {

    NoticeMultipleDialogListener mListener;
    ArrayList<Integer> mSelectedLists = new ArrayList<>();

    public interface  NoticeMultipleDialogListener{
        public void onMultipleDialogClick(DialogFragment dialogFragment, String res);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try{
            mListener = (NoticeMultipleDialogListener) context;
        }catch (ClassCastException e){
            String msg = "must implement NoticeMultipleDialogListener";
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
                    .setMultiChoiceItems(R.array.fruit, null, new DialogInterface.OnMultiChoiceClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                            if(isChecked){
                                mSelectedLists.add(which);
                            }else if(mSelectedLists.contains(which)){
                                mSelectedLists.remove(which);
                            }
                        }
                    })
                .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String[] fruits = getResources().getStringArray(R.array.fruit);
                        StringBuilder sb = new StringBuilder();
                        for(int idx : mSelectedLists){
                            sb.append(fruits[idx]+",");
                        }
                        sb.delete(sb.length()-3, sb.length()-3);
                        mListener.onMultipleDialogClick(MultipleDialogFragment.this, sb.toString());
                    }
                })
                .setNegativeButton("취소", null)
                .create();
    }
}
