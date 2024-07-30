//이슈1. 그리드뷰()는 Height값 (높이값)이 제멋대로이다. 그리드뷰는 Height(높이) 값을 조정하기가 어려운 면이 있어서 리사이클러 뷰(ListView)를 사용하는 방법을 많이 쓴다.
//이슈2. 메뉴 첫 화면을 함수화하지 않아서 onCreate 함수와 메뉴탭선택 함수 2군데를 함께 수정해줘야 한다.


package com.example.kiosk_v1;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {

    FrameLayout fl;
    TabLayout tb;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tb=findViewById(R.id.kiosk_menu);
        fl=findViewById(R.id.frame);

        //메뉴 첫 화면
        fl.removeAllViews();
        RelativeLayout framebest=(RelativeLayout)getLayoutInflater().inflate(R.layout.frame_best,null);
        GridView gv=framebest.findViewById(R.id.gridView);

        // 데이터(아이템) 내용
        Item[] items = {
                new Item(R.drawable.americano, "아메리카노", "3500"),
                new Item(R.drawable.caffelatte, "카페라떼", "3000"),
                new Item(R.drawable.chocolatte, "초코라떼", "4000"),
                new Item(R.drawable.vanillalatte, "바닐라라떼", "5500"),
                new Item(R.drawable.lemonade, "레몬에이드", "6000"),
                new Item(R.drawable.caffelatte, "카페라떼", "3000"),
                new Item(R.drawable.chocolatte, "초코라떼", "4000"),
                new Item(R.drawable.vanillalatte, "바닐라라떼", "5500"),
                new Item(R.drawable.caffelatte, "카페라떼", "3000"),
                new Item(R.drawable.chocolatte, "초코라떼", "4000"),
        };
        CustomList adapter=new CustomList(MainActivity.this,items);//<=커스텀어댑터를 생성한다.(커스텀어댑터는 아래에서 함수로 정의)
        gv.setAdapter(adapter);
        fl.addView(framebest);

        //메뉴탭 누르면 화면전환
        tb.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

                int position=tab.getPosition();

                if (position==0){

                    //잘못된 코드: addView는 동적으로 뷰를 추가할 때 사용합니다.주로 ViewGroup(예: LinearLayout, RelativeLayout, FrameLayout 등)에 새로운 뷰를 추가할 때 사용합니다.
                    //사용 사례: 간단한 UI를 동적으로 추가할 때, 뷰의 수가 많지 않고 복잡하지 않은 경우
//                    fl.removeAllViews();
//                    RelativeLayout framebest=(RelativeLayout)getLayoutInflater().inflate(R.layout.frame_best,null);
//                    GridView gv=framebest.findViewById(R.id.gridView);
//                    gv.addView(getLayoutInflater().inflate(R.layout.menu_item,null));
//                    fl.addView(framebest);

                    //어댑터를 사용하여 그리드뷰에 데이터(아이템) 표시 해주기
                    //어댑터는 리스트, 그리드 등 데이터 집합을 뷰에 연결하여 표시할 때 사용합니다.
                    // RecyclerView, ListView, GridView와 같은 뷰 그룹에서 데이터를 표시할 때 어댑터를 사용합니다. 어댑터는 데이터를 바탕으로 뷰를 생성하고 관리합니다.

                    fl.removeAllViews();
                    RelativeLayout framebest=(RelativeLayout)getLayoutInflater().inflate(R.layout.frame_best,null);
                    GridView gv=framebest.findViewById(R.id.gridView);

                    // 데이터(아이템) 내용
                    Item[] items = {
                            new Item(R.drawable.americano, "아메리카노", "3500"),
                            new Item(R.drawable.caffelatte, "카페라떼", "3000"),
                            new Item(R.drawable.chocolatte, "초코라떼", "4000"),
                            new Item(R.drawable.vanillalatte, "바닐라라떼", "5500"),
                            new Item(R.drawable.lemonade, "레몬에이드", "6000"),
                            new Item(R.drawable.caffelatte, "카페라떼", "3000"),
                            new Item(R.drawable.chocolatte, "초코라떼", "4000"),
                            new Item(R.drawable.vanillalatte, "바닐라라떼", "5500"),
                            new Item(R.drawable.caffelatte, "카페라떼", "3000"),
                            new Item(R.drawable.chocolatte, "초코라떼", "4000"),

                    };

                    // 어댑터를 사용하여 그리드뷰에 데이터(아이템) 표시 해주기
                    CustomList adapter=new CustomList(MainActivity.this,items);//<=커스텀어댑터를 생성한다.(커스텀어댑터는 아래에서 함수로 정의)
                    gv.setAdapter(adapter);
                    fl.addView(framebest);

                }
                if (position==1){
                    fl.removeAllViews();
                    fl.addView(getLayoutInflater().inflate(R.layout.frame_coffee,null));
                }
                if (position==2){
                    fl.removeAllViews();
                    fl.addView(getLayoutInflater().inflate(R.layout.frame_noncoffee,null));
                }
                if (position==3){
                    fl.removeAllViews();
                    fl.addView(getLayoutInflater().inflate(R.layout.frame_smoothie,null));
                }
                if (position==4){
                    fl.removeAllViews();
                    fl.addView(getLayoutInflater().inflate(R.layout.frame_juice,null));
                }
                if (position==5){
                    fl.removeAllViews();
                    fl.addView(getLayoutInflater().inflate(R.layout.frame_dessert,null));
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    ///ArrayAdapter을 상속할때 <>안에 타입 맞춰주기
    public class CustomList extends ArrayAdapter<Item>{

        private final Activity context;
        private final Item[] items;

        public CustomList(Activity context,Item[] items) {
            super(context, R.layout.menu_item, items);
            this.context=context;
            this.items=items;
        }

        public View getView(int position, View view, ViewGroup parent) {

            LayoutInflater inflater=context.getLayoutInflater();
            View gridView = inflater.inflate(R.layout.menu_item, null,true);

            ImageView imageView = (ImageView)gridView.findViewById(R.id.imageView);
            TextView titleTextView = (TextView)gridView.findViewById(R.id.titleTextView);
            TextView subTextView = (TextView)gridView.findViewById(R.id.subTextView);

            Item currentItem = items[position];
            imageView.setImageResource(currentItem.getImageResId());
            titleTextView.setText(currentItem.getTitle());
            subTextView.setText(currentItem.getSubtitle());

            return gridView;
        }

    }
}

