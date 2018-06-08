package kr.or.dgit.it.review_application;


import android.database.Cursor;
import android.graphics.Rect;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import kr.or.dgit.it.review_application.dao.PhoneDbDao;
import kr.or.dgit.it.review_application.dto.HeaderItem;
import kr.or.dgit.it.review_application.dto.ItemVO;
import kr.or.dgit.it.review_application.dto.PhoneInfo;


/**
 * A simple {@link Fragment} subclass.
 */
public class Tab3Fragment extends Fragment {

    final String TODAY = "2017-07-01";
    final String YESTERDAY = "2017-06-30";

    private RecyclerView recyclerView;
    private List<ItemVO> list;
    private PhoneDbDao dao;

    private static final String TAG = "Tab3Fragment";

    public Tab3Fragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dao = new PhoneDbDao(getContext());
        dao.open();

        Cursor todayCursor = dao.selectItemAll("date=?", new String[]{TODAY});
        Log.d(TAG, "onCreateView: todayCursor "+todayCursor.toString());
        Cursor yesterdayCursor = dao.selectItemAll("date=?", new String[]{YESTERDAY});
        Cursor beforeCursor = dao.selectItemAll("date !=? and date !=?", new String[]{TODAY, YESTERDAY});


        list = new ArrayList<>();

        if (todayCursor.getCount() != 0) {
            HeaderItem item = new HeaderItem();
            item.setHeaderTitle("오늘");
            list.add(item);
            while (todayCursor.moveToNext()) {
                PhoneInfo dataItem = new PhoneInfo(todayCursor.getString(1), todayCursor.getString(2));
                Log.d(TAG, "onCreateView: dataItem = "+dataItem.toString() );
                list.add(dataItem);
            }
        }
        if (yesterdayCursor.getCount() != 0) {
            HeaderItem item = new HeaderItem();
            item.setHeaderTitle("어제");
            list.add(item);
            while (yesterdayCursor.moveToNext()) {
                PhoneInfo dataItem = new PhoneInfo(yesterdayCursor.getString(1), yesterdayCursor.getString(2));
                list.add(dataItem);
            }
        }
        if (beforeCursor.getCount() != 0) {
            HeaderItem item = new HeaderItem();
            item.setHeaderTitle("이전");
            list.add(item);
            while (beforeCursor.moveToNext()) {
                PhoneInfo dataItem = new PhoneInfo(beforeCursor.getString(1), beforeCursor.getString(2));
                list.add(dataItem);
            }
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        RecyclerView view = (RecyclerView) inflater.inflate(R.layout.fragment_tab3, container, false);

        view.setLayoutManager(new LinearLayoutManager(getContext()));
        view.setAdapter(new MyAdapter(list));
        view.addItemDecoration(new MyDecoration());

        return view;
    }

    private class MyAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
        private final List<ItemVO> list;
        public MyAdapter(List<ItemVO> list){
            this.list=list;
        }

        @Override
        public int getItemViewType(int position) {
            return list.get(position).getType();
        }

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            if(viewType==ItemVO.TYPE_HEADER){
                View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.mission3_item_header, parent, false);
                return new HeaderViewHolder(view);
            }else {
                View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.mission3_item_data, parent, false);
                return new DataViewHolder(view);
            }
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            ItemVO itemVO=list.get(position);
            if(itemVO.getType()==ItemVO.TYPE_HEADER){
                HeaderViewHolder viewHolder=(HeaderViewHolder)holder;
                HeaderItem headerItem=(HeaderItem)itemVO;
                viewHolder.headerView.setText(headerItem.getHeaderTitle());
            }
            else {
                DataViewHolder viewHolder=(DataViewHolder)holder;
                PhoneInfo dataItem=(PhoneInfo)itemVO;
                viewHolder.nameView.setText(dataItem.getName());
                viewHolder.dateView.setText(dataItem.getDate());

                int count = position % 5;
                if(count == 0){
                    ((GradientDrawable)viewHolder.personView.getBackground()).setColor(0xff009688);
                }else if(count==1){
                    ((GradientDrawable)viewHolder.personView.getBackground()).setColor(0xff4285f4);
                }else if(count==2){
                    ((GradientDrawable)viewHolder.personView.getBackground()).setColor(0xff039be5);
                }else if(count==3){
                    ((GradientDrawable)viewHolder.personView.getBackground()).setColor(0xff9c27b0);
                }else if(count==4){
                    ((GradientDrawable)viewHolder.personView.getBackground()).setColor(0xff0097a7);
                }
            }
        }

        @Override
        public int getItemCount() {
            return list.size();
        }

        private class DataViewHolder extends RecyclerView.ViewHolder {
            public TextView nameView;
            public TextView dateView;
            public ImageView personView;

            public DataViewHolder(View itemView){
                super(itemView);
                nameView=itemView.findViewById(R.id.mission3_item_name);
                dateView=itemView.findViewById(R.id.mission3_item_date);
                personView=itemView.findViewById(R.id.mission3__item_person);
            }
        }

        private class HeaderViewHolder extends RecyclerView.ViewHolder {
            public TextView headerView;
            public HeaderViewHolder(View itemView){
                super(itemView);
                headerView=itemView.findViewById(R.id.mission3_item_header);
            }
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        dao.close();
    }

    private class MyDecoration extends RecyclerView.ItemDecoration {
        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            super.getItemOffsets(outRect, view, parent, state);
            int index=parent.getChildAdapterPosition(view);
            ItemVO itemVO=list.get(index);
            if(itemVO.getType()==ItemVO.TYPE_DATA){
                view.setBackgroundColor(0xFFFFFFFF);
                ViewCompat.setElevation(view, 10.0f);
            }
            outRect.set(20, 10, 20, 10);
        }
    }
}
