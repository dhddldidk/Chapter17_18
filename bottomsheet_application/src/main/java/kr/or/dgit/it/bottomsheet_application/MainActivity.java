package kr.or.dgit.it.bottomsheet_application;

import android.os.Bundle;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.BottomSheetDialog;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private CoordinatorLayout coordinatorLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        coordinatorLayout = findViewById(R.id.coordinator);

        View bottomSheet=coordinatorLayout.findViewById(R.id.bottom_sheet);
        BottomSheetBehavior.from(bottomSheet);
    }

    public void mBtnBottomSheetClick(View view) {
        List<DataVO> list=getDatas();

        RecyclerViewAdapter adapter=new RecyclerViewAdapter(list);
        View modelSheet=getLayoutInflater().inflate(R.layout.modal_sheet, null);
        RecyclerView recyclerView=modelSheet.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        BottomSheetDialog modalBottomSheet=new BottomSheetDialog(this);
        modalBottomSheet.setContentView(modelSheet);
        modalBottomSheet.show();
    }

    private List<DataVO> getDatas() {
        List<DataVO> list=new ArrayList<>();
        DataVO vo=new DataVO();
        vo.title="Keep";
        vo.image= ResourcesCompat.getDrawable(getResources(), R.drawable.ic_lab4_1, null);
        list.add(vo);

        vo=new DataVO();
        vo.title="Inbox";
        vo.image= ResourcesCompat.getDrawable(getResources(), R.drawable.ic_lab4_2, null);
        list.add(vo);

        vo=new DataVO();
        vo.title="Messanger";
        vo.image= ResourcesCompat.getDrawable(getResources(), R.drawable.ic_lab4_3, null);
        list.add(vo);

        vo=new DataVO();
        vo.title="Google+";
        vo.image= ResourcesCompat.getDrawable(getResources(), R.drawable.ic_lab4_4, null);
        list.add(vo);

        return list;
    }

    private class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ItemHolder> {
        private List<DataVO> list;

        public RecyclerViewAdapter(List<DataVO> list) {
            this.list = list;
        }

        @Override
        public ItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View root = LayoutInflater.from(parent.getContext()).inflate(R.layout.lab4_sheet_row, parent, false);
            return new ItemHolder(root);
        }

        @Override
        public void onBindViewHolder(ItemHolder holder, int position) {
            DataVO vo=list.get(position);
            holder.textView.setText(vo.title);
            holder.imageView.setImageDrawable(vo.image);
        }

        @Override
        public int getItemCount() {
            return list.size();
        }

        class ItemHolder extends RecyclerView.ViewHolder{
            TextView textView;
            ImageView imageView;

            public ItemHolder(View root) {
                super(root);
                imageView = itemView.findViewById(R.id.lab4_sheet_row_imageView);
                textView = itemView.findViewById(R.id.lab4_sheet_row_textView);
            }
        }
    }
}
