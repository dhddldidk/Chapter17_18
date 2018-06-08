package kr.or.dgit.it.chapter17_18;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.ListFragment;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MultiListFragment extends ListFragment {
    private String[] planets;
    private int mLastIndex;
    private boolean mMultiPane;//화면이 가로냐 세로냐 판단


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        planets = getResources().getStringArray(R.array.planet);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(), R.array.planet, android.R.layout.simple_list_item_activated_1);
        setListAdapter(adapter);

        getListView().setChoiceMode(ListView.CHOICE_MODE_SINGLE);

        if(savedInstanceState != null){//화면 옆으로 돌리면 그때 선택한 값을 저장하기
            mLastIndex = savedInstanceState.getInt("lastIndex");

        }

        View descPanel = getActivity().findViewById(R.id.planet_desc);
        if(descPanel != null && descPanel.getVisibility()==View.VISIBLE){// 가로라는 말+보이고 있다면
            mMultiPane = true;
            onListItemClick(getListView(), null, mLastIndex, 0);
        }
    }

    //위에 lastIndex랑 밑에 lastIndex랑 같은거여야 함
    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("lastIndex", mLastIndex);
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        mLastIndex = position;
        getListView().setItemChecked(position,true);


        if(mMultiPane){//가로
            FragmentManager fm = getFragmentManager();
            MultiDescFragment mdf = (MultiDescFragment) fm.findFragmentById(R.id.plant_desc);

            if(mdf == null || mdf.mNowIndex != position){//생성되지 않고, 넘어온 포지션과 다르면 (화면에 겹치는거 에러 처리)
                mdf = MultiDescFragment.newInstance(position);
                fm.beginTransaction().replace(R.id.planet_desc, mdf).commit();
            }
        }else{//세로모드
            //desc startActivity 구현-세로모드일 경우
            Intent intent = new Intent(getActivity(), MultiDescAcrtivity.class);
            intent.putExtra("selectedIndex", position);//selectedIndex 값이 전달됨
            startActivity(intent);
        }

    }
}
