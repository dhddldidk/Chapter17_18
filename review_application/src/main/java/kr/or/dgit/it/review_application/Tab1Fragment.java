package kr.or.dgit.it.review_application;


import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class Tab1Fragment extends Fragment {


    public Tab1Fragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_tab1, container, false);

        //원본
        List<String> list = new ArrayList<>();

        for(int i =0; i<20; i++){
            list.add("item = "+i);
        }

        //Tab1 fragment

        RecyclerView recyclerView = view.findViewById(R.id.recyclerView);

        //레이아웃 매니저
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(new MyAdapter(list));


        //데코레이션 달기
        recyclerView.addItemDecoration(new MyItemDecoration());



        return view;
    }

    private class MyItemDecoration extends RecyclerView.ItemDecoration {
        @Override
        public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
            super.onDrawOver(c, parent, state);
            int width = parent.getWidth();
            int height = parent.getHeight();

            Drawable dr = ResourcesCompat.getDrawable(getResources(), R.drawable.android, null);
            int drWidth = dr.getIntrinsicWidth();
            int drHeight = dr.getIntrinsicHeight();

            int left = width/2-drWidth/2;
            int top = height/2-drHeight/2;
            c.drawBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.android), left,top,null);
        }

        //3개씩 묶기
        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            super.getItemOffsets(outRect, view, parent, state);

            //3으로 나눠야 하니까 가져온 값(index 0 , 1, 2 )에 +1을 해줌
            int index=parent.getChildAdapterPosition(view)+1;
            if(index%3==0){
                outRect.set(20,20,20,60);
            }else{
                outRect.set(20,20,20,60);
            }
            view.setBackgroundColor(0xFFECE9E9);
            ViewCompat.setElevation(view, 20.0f);
        }
    }



    private class MyViewHolder extends RecyclerView.ViewHolder{
        TextView title;

        public MyViewHolder(View itemView){
            super(itemView);
            title = itemView.findViewById(android.R.id.text1);
            title.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(getActivity(), title.getText().toString(), Toast.LENGTH_LONG).show();
                }
            });
        }
    }


    private class MyAdapter extends  RecyclerView.Adapter<MyViewHolder>{

        private List<String> list;

        public MyAdapter(List<String> list) {
            this.list = list;
        }


        @NonNull
        @Override
        public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

            View view = LayoutInflater.from(parent.getContext()).inflate(android.R.layout.simple_list_item_1, parent, false);

            return new MyViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

            String title = list.get(position);
            holder.title.setText(title);
        }

        @Override
        public int getItemCount() {
            return list.size();
        }
    }


}
