package kr.or.dgit.it.chapter17_18;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class MultiDescFragment extends Fragment {

    int mNowIndex;

    //activity에서 fragment로 전달
    public static MultiDescFragment newInstance(int selectedIndex){
        MultiDescFragment mdf = new MultiDescFragment();
        mdf.mNowIndex = selectedIndex;
        return mdf;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        String[] planetDesc = getResources().getStringArray(R.array.planet_desc);

        View root = inflater.inflate(R.layout.fragment_multi_desc, container, false);
        TextView tv = root.findViewById(R.id.plant_desc);
        tv.setText(planetDesc[mNowIndex]);
        return root;
    }
}
