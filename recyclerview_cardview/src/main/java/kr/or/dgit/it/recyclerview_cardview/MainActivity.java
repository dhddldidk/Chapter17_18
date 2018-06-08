package kr.or.dgit.it.recyclerview_cardview;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
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
import android.widget.Toast;




public class MainActivity extends AppCompatActivity {

    private ActionBarDrawerToggle toggle;
    private DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayShowTitleEnabled(true);
        drawerLayout = findViewById(R.id.main_drawer);

        toggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.drawer_open, R.string.drawer_close);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.main_drawer_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {


                int id= item.getItemId();
                if(id==R.id.menu_drawer_home){
                    showToast("NavigationDrawer...Home");
                }else if(id==R.id.menu_drawer_message){
                    showToast("NavigationDrawer...Message");
                }else if(id==R.id.menu_drawer_add){
                    showToast("NavigationDrawer...Add");
                }

                drawerLayout.closeDrawer(GravityCompat.START);
                return true;
            }
        });


        //원본
        Item[] items = {
                new Item("Chapter One", "Item one Details", R.drawable.android_image_1),
                new Item("Chapter Two", "Item two Details", R.drawable.android_image_2),
                new Item("Chapter Three", "Item three Details", R.drawable.android_image_3),
                new Item("Chapter Four", "Item four Details", R.drawable.android_image_4),
                new Item("Chapter Five", "Item five Details", R.drawable.android_image_5),
                new Item("Chapter Six", "Item six Details", R.drawable.android_image_6),
                new Item("Chapter Seven", "Item seven Details", R.drawable.android_image_7),
                new Item("Chapter Eight", "Item eight Details", R.drawable.android_image_8),
        };



        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        //RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);


        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(layoutManager);

        recyclerView.setAdapter(new RecyclerAdapter(items));

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    private void showToast (String msg){
        Toast toast = Toast.makeText(this, msg, Toast.LENGTH_LONG);
        toast.show();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId()==android.R.id.home){
            drawerLayout.openDrawer(GravityCompat.START);
            return true;
        }
        return  super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    private class RecyclerAdapter extends RecyclerView.Adapter {
        Item[] items;

        public RecyclerAdapter(Item[] items) {
            this.items = items;
        }



        /*

        배열로 써도 되나 Item 클래스로 만들어서
        써봄


        private String[] titles = {
                "Chapter One", "Chapter Two",
                "Chapter Three", "Chapter Four",
                "Chapter Five", "Chapter Six",
                "Chapter Seven", "Chapter Eight",
        };


        private String[] details = {
                "Item one details",  "Item two details", "Item three details",
                "Item four details", "Item five details", "Item six details",
                "Item seven details","Item eight details"
        };

        private int[] images = {
                R.drawable.android_image_1,
                R.drawable.android_image_2,
                R.drawable.android_image_3,
                R.drawable.android_image_4,
                R.drawable.android_image_5,
                R.drawable.android_image_6,
                R.drawable.android_image_7,
                R.drawable.android_image_8
        };*/


        @NonNull
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_layout, parent, false);
            ViewHolder viewHolder = new ViewHolder(view);
            return viewHolder;
        }

        @Override
        public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
            ViewHolder viewHolder = (ViewHolder) holder;

            Item item = items[position];
            viewHolder.itemTitle.setText(item.title);//
            viewHolder.itemDetail.setText(item.detail);
            viewHolder.itemImages.setImageResource(item.img);

        }

        @Override
        public int getItemCount() {
            return items.length;
        }

        class ViewHolder extends RecyclerView.ViewHolder{



            ImageView itemImages;
            TextView itemTitle;
            TextView itemDetail;

            public ViewHolder(View itemView) {// 카드뷰가 넘어옴
                super(itemView);

                itemImages = itemView.findViewById(R.id.item_image);
                itemTitle = itemView.findViewById(R.id.item_title);
                itemDetail = itemView.findViewById(R.id.item_detail);

                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int position = getAdapterPosition();//어뎁터에서 선택한 포지션이 넘어옴
                        //선택한 index값이 넘어오기 때문에 index에 +1을 해주면 1,2,3,4,5 순서로 나옴
                        Snackbar.make(v, "Click selected on Item"+(position+1), Snackbar.LENGTH_LONG)
                                .setAction("Action", null).show();
                    }
                });
            }
        }

    }
}
