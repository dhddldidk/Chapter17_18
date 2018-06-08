package kr.or.dgit.it.chapter17_18;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class ContentFragment extends Fragment {

    private TextView textView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_content, container, false);
        textView = root.findViewById(R.id.textView);


        return root;
    }

    public void changeTextProperties(int fontSize, String text){
        textView.setTextSize(fontSize);

        if(text !=null){
            textView.setText(text);
        }
    }
}
