//이슈1. 그리드뷰()는 Height값 (높이값)이 제멋대로이다. 그리드뷰는 Height(높이) 값을 조정하기가 어려운 면이 있어서 리사이클러 뷰(ListView)를 사용하는 방법을 많이 쓴다.
//이슈2. 메뉴 첫 화면을 함수화하지 않아서 onCreate 함수와 메뉴탭선택 함수 2군데를 함께 수정해줘야 한다.


package com.example.kiosk_v1;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    FrameLayout fl;
    TabLayout tb;
    TableLayout fl2;
    LinearLayout fl3;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tb=findViewById(R.id.kiosk_menu);
        fl=findViewById(R.id.frame);
        fl2=findViewById(R.id.frame_below);
        fl3=findViewById(R.id.frame_last);

        //메뉴 첫 화면
        fl.removeAllViews();
        ConstraintLayout layout=(ConstraintLayout)getLayoutInflater().inflate(R.layout.frame_best,null);
        GridView gv=layout.findViewById(R.id.gridView);

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
        fl.addView(layout);

        gv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Item clickedItem = (Item) parent.getItemAtPosition(position);
                //클릭된 메뉴 아이템 정보를 장바구니에 추가하는 로직(하단에 메서드 정의)
                addToCart(clickedItem);
            }
        });

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
                    ConstraintLayout layout=(ConstraintLayout)getLayoutInflater().inflate(R.layout.frame_best,null);
                    GridView gv=layout.findViewById(R.id.gridView);

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
                    fl.addView(layout);

                    gv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            Item clickedItem = (Item) parent.getItemAtPosition(position);
                            addToCart(clickedItem);

//                            TableRow tableRow = (TableRow) getLayoutInflater().inflate(R.layout.order_item, null, false);
//
//                            TextView itemOrdered = tableRow.findViewById(R.id.itemOrdered);
//                            Button btnDecItem = tableRow.findViewById(R.id.btnDecItem);
//                            TextView itemCnt = tableRow.findViewById(R.id.itemCnt);
//                            Button btnIncItem = tableRow.findViewById(R.id.btnIncItem);
//                            TextView priceOrdered = tableRow.findViewById(R.id.priceOrdered);
//                            Button btnCancelItem = tableRow.findViewById(R.id.btnCancelItem);
//
//                            fl2.addView(tableRow);

                        }
                    });
                }
                if (position==1){
                    fl.removeAllViews();
                    ConstraintLayout layout=(ConstraintLayout)getLayoutInflater().inflate(R.layout.frame_coffee,null);
                    GridView gv=layout.findViewById(R.id.gridView);

                    Item[] items = {
                            new Item(R.drawable.americano, "아메리카노", "3500"),
                            new Item(R.drawable.caffelatte, "카페라떼", "3000"),
                            new Item(R.drawable.vanillalatte, "바닐라라떼", "5500"),
                            new Item(R.drawable.caffelatte, "카페라떼", "3000"),
                            new Item(R.drawable.caramelmacchiato, "카라멜마끼아또", "4000"),
                    };

                    CustomList adapter=new CustomList(MainActivity.this,items);
                    gv.setAdapter(adapter);
                    fl.addView(layout);

                    gv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            Item clickedItem = (Item) parent.getItemAtPosition(position);
                            addToCart(clickedItem);
                        }
                    });
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

    //장바구니 전체 삭제
    public void all_delete_onclick(View view) {
        fl2.removeAllViews();
        TextView totalnum= fl3.findViewById(R.id.total_orders_num);
        totalnum.setText("총 0개 결제");
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

    private void addToCart(Item clickedItem) {
        // 장바구니에 추가하는 로직을 여기에 작성합니다.
        // 아이템의 제목과 부제목을 가져옵니다 where? Item 클래스의 인스턴스에 있는 메서드(getTitle, getSubtitle)로 가져옴
        String title = clickedItem.getTitle();
        String subtitle = clickedItem.getSubtitle();

        // 제목과 부제목을 포함한 Toast 메시지를 표시합니다
        Toast.makeText(MainActivity.this, "Clicked: " + title + " , " + subtitle, Toast.LENGTH_SHORT).show();

        //장바구니에 클릭한 아이템 담기
        TableRow tableRow = (TableRow) getLayoutInflater().inflate(R.layout.order_item, null, false);

        TextView itemOrdered = tableRow.findViewById(R.id.itemOrdered);
        Button btnDecItem = tableRow.findViewById(R.id.btnDecItem);
        TextView itemCnt = tableRow.findViewById(R.id.itemCnt);
        Button btnIncItem = tableRow.findViewById(R.id.btnIncItem);
        TextView priceOrdered = tableRow.findViewById(R.id.priceOrdered);
        Button btnCancelItem = tableRow.findViewById(R.id.btnCancelItem);

        // 수량을 1로 초기화합니다
        itemCnt.setText("1");
        itemOrdered.setText(title);
        priceOrdered.setText(subtitle);

        // 수량 증가 버튼 클릭 리스너 설정
        btnIncItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 현재 수량을 가져와서 증가시킵니다
                int count = Integer.parseInt(itemCnt.getText().toString());
                int nPrice=Integer.parseInt(priceOrdered.getText().toString());
                int uintPrice=nPrice/count;
                count++;
                nPrice=uintPrice*count;

                itemCnt.setText(""+count);
                priceOrdered.setText(String.valueOf(nPrice));
            }
        });

        // 수량 감소 버튼 클릭 리스너 설정
        btnDecItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 현재 수량을 가져와서 감소시킵니다 (수량이 1보다 작은 경우 감소하지 않음)
                int count = Integer.parseInt(itemCnt.getText().toString());
                int nPrice=Integer.parseInt(priceOrdered.getText().toString());
                int uintPrice=nPrice/count;
                count--;
                nPrice=uintPrice*count;

                if (count >= 1) {
                    itemCnt.setText(""+count);
                    priceOrdered.setText(String.valueOf(nPrice));
                }
            }
        });

        btnCancelItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fl2.removeView(tableRow);
            }
        });
        fl2.addView(tableRow);////////////////////////////////테이블 레이아웃에 테이블로우 inflate 담기

        int sum=0;
        int childCount=fl2.getChildCount();

        for(int i=0;i<fl2.getChildCount();i++) { //getChildCount()=> 뷰 그룹내에 있는 자식 뷰의 개수를 반환
            TableRow tr = (TableRow)fl2.getChildAt(i); //getChildAt(int index) 메서드는 ViewGroup 클래스에 속한 메서드로, 특정 인덱스에 위치한 자식 뷰를 반환합니다.
            TextView tv = tr.findViewById(R.id.priceOrdered);
            sum+= Integer.parseInt(tv.getText().toString());
        }

        TextView totalnum= fl3.findViewById(R.id.total_orders_num);
        totalnum.setText("총"+String.valueOf(childCount)+"개 결제");

        TextView totalamount=fl3.findViewById(R.id.total_payment_amount);
        totalamount.setText(String.valueOf(sum)+"원");///////////초반에 아이템 클릭하는것만 합계가 되고 플러스 버튼 눌러준 값은 미반영됨, 수정 필요****
    }
}

