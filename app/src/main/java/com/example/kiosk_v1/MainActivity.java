//이슈1. 그리드뷰()는 Height값 (높이값)이 제멋대로이다. 그리드뷰는 Height(높이) 값을 조정하기가 어려운 면이 있어서 리사이클러 뷰(ListView)를 사용하는 방법을 많이 쓴다.
//이슈2. 메뉴 첫 화면을 함수화하지 않아서 onCreate 함수와 메뉴탭선택 함수 2군데를 함께 수정해줘야 한다.


package com.example.kiosk_v1;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
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

    private TextView txt1;
    private TextView txt2;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tb=findViewById(R.id.kiosk_menu);
        fl=findViewById(R.id.frame);
        fl2=findViewById(R.id.frame_below);
        fl3=findViewById(R.id.frame_last);
        txt1=findViewById(R.id.total_orders_num);
        txt2=findViewById(R.id.total_payment_amount);

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
                //addToCart(clickedItem);->showOption 메서드 내에 내포시킴
                //클릭된 메뉴 아이템의 옵션창을 띄어주는 로직(하단에 메서드 정의)
                showOption(clickedItem);
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
                            //클릭된 메뉴 아이템 정보를 장바구니에 추가하는 로직(하단에 메서드 정의)
                            //addToCart(clickedItem);->showOption 메서드 내에 내포시킴
                            //클릭된 메뉴 아이템의 옵션창을 띄어주는 로직(하단에 메서드 정의)
                            showOption(clickedItem);

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
                            //클릭된 메뉴 아이템 정보를 장바구니에 추가하는 로직(하단에 메서드 정의)
                            //addToCart(clickedItem);->showOption 메서드 내에 내포시킴
                            //클릭된 메뉴 아이템의 옵션창을 띄어주는 로직(하단에 메서드 정의)
                            showOption(clickedItem);
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

    //선택한 옵션 모두 나열하기
    public String printOptionSet(String[][] optionSet){
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < optionSet.length; i++) {
            for (int j = 0; j < optionSet[i].length; j++) {
                if (optionSet[i][j]==null) continue;
                result.append(optionSet[i][j]);
                result.append(" ");
            }
        }

        // 마지막 공백 제거
        if (result.length() > 0) {
            result.setLength(result.length() - 1);
        }

        return result.toString();
    }

    private void addToCart(String title, String subtitle,String[][] optionSet) {


        String options= printOptionSet(optionSet);

        //장바구니에 클릭한 아이템 담기
        TableRow tableRow = (TableRow) getLayoutInflater().inflate(R.layout.order_item, null, false);

        TextView itemOrdered = tableRow.findViewById(R.id.itemOrdered);
        Button btnDecItem = tableRow.findViewById(R.id.btnDecItem);
        TextView itemCnt = tableRow.findViewById(R.id.itemCnt);
        Button btnIncItem = tableRow.findViewById(R.id.btnIncItem);
        TextView priceOrdered = tableRow.findViewById(R.id.priceOrdered);
        Button btnCancelItem = tableRow.findViewById(R.id.btnCancelItem);
        TextView optioins_recording=tableRow.findViewById(R.id.optioins_recording);

        // 수량을 1로 초기화합니다
        itemCnt.setText("1");

        itemOrdered.setText(title);
        priceOrdered.setText(subtitle);
        optioins_recording.setText(options);

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
                calculateTotalPayment();
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
                    calculateTotalPayment();
                }
            }
        });

        btnCancelItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fl2.removeView(tableRow);
                calculateTotalPayment();
            }
        });
        fl2.addView(tableRow);////////////////////////////////테이블 레이아웃에 테이블로우 inflate 담기

        calculateTotalPayment();
    }

    private void calculateTotalPayment(){
        int itemCount = 0;
        int totalPrice = 0;

        for (int i = 0; i < fl2.getChildCount(); i++) {
            TableRow row = (TableRow) fl2.getChildAt(i);
            TextView countTextView = row.findViewById(R.id.itemCnt);
            TextView priceTextView = row.findViewById(R.id.priceOrdered);
            int count = Integer.parseInt(countTextView.getText().toString());
            int price = Integer.parseInt(priceTextView.getText().toString());
            itemCount += count;
            totalPrice += price;
        }
        txt1.setText("총 " + itemCount + "개 결제");
        txt2.setText(totalPrice + "원");
    }


    private void showOption(Item clickedItem) {

        // 아이템의 제목과 부제목을 가져옵니다 where? Item 클래스의 인스턴스에 있는 메서드(getTitle, getSubtitle)로 가져옴
        // 옵션창 내에서 활용하기
        int imageResId = clickedItem.getImageResId();
        String title = clickedItem.getTitle();
        String subtitle = clickedItem.getSubtitle();

        // 옵션 다이얼로그를 생성하고 레이아웃을 설정합니다
        final Dialog optionDialog=new Dialog(this);

        // 다이얼로그 내의 레이아웃을 가져옵니다
        ConstraintLayout optionLayout = (ConstraintLayout)getLayoutInflater().inflate(R.layout.option_frame,null);

        // 레이아웃 내의 위젯을 가져옵니다
        ImageView imageView = optionLayout.findViewById(R.id.imageView2);
        TextView titleTextView = optionLayout.findViewById(R.id.textView2);
        TextView subtitleTextView = optionLayout.findViewById(R.id.textView3);

        // 위젯에 데이터를 설정합니다
        imageView.setImageResource(imageResId);
        titleTextView.setText(title);
        subtitleTextView.setText(subtitle+"원");

        //HOT/ICE 옵션 추가하기(메뉴편집기에서 옵션배치 및 옵션정보를 넣어주어 자바에선 불러오기만)
        TextView option1_text = optionLayout.findViewById(R.id.option1_text);
        TextView option1_num = optionLayout.findViewById(R.id.option1_num);
        LinearLayout option1=(LinearLayout) optionLayout.findViewById(R.id.option1);

        TextView option2_text = optionLayout.findViewById(R.id.option2_text);
        TextView option2_num = optionLayout.findViewById(R.id.option2_num);
        LinearLayout option2=(LinearLayout)option2_text.getParent();

        TextView option3_text = optionLayout.findViewById(R.id.option3_text);
        TextView option3_num = optionLayout.findViewById(R.id.option3_num);
        LinearLayout option3=(LinearLayout)option3_text.getParent();

        TextView option4_text = optionLayout.findViewById(R.id.option4_text);
        TextView option4_num = optionLayout.findViewById(R.id.option4_num);
        LinearLayout option4=(LinearLayout)option4_text.getParent();

        TextView option5_text = optionLayout.findViewById(R.id.option5_text);
        TextView option5_num = optionLayout.findViewById(R.id.option5_num);
        LinearLayout option5=(LinearLayout)option5_text.getParent();

        // 옵션 정보를 저장할 배열
        final String[][] optionSet=new String[10][2];
        final boolean[] isOption1Active = {false};
        //ㄴ익명 내부 클래스 내에서 사용할 변수는 final로 선언할 수 없다. 이를 해결하기 위해 배열(참조 자체를 변경하지 않고 값을 변경할 수 있는 구조)을 사용할 수 있습니다.

        option1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isOption1Active[0] = !isOption1Active[0];

                if (isOption1Active[0]) {
                    // 버튼이 활성화된 경우
                    optionSet[0][0] = option1_text.getText().toString();
                    optionSet[0][1] = option1_num.getText().toString();

                // 테두리 색 변경
                GradientDrawable border = new GradientDrawable();
                border.setColor(Color.TRANSPARENT); // 배경색을 투명으로 설정
                border.setStroke(5, Color.BLUE); // 테두리 두께와 색상 설정
                option1.setBackground(border);
                } else {
                    // 버튼이 비활성화된 경우
                    // recording 배열의 값을 빈 문자열로 설정
                    optionSet[0][0] = "";
                    optionSet[0][1] = "";

                    // 테두리 색 변경 (비활성화된 경우)
                    GradientDrawable border = new GradientDrawable();
                    border.setColor(Color.TRANSPARENT); // 배경색을 투명으로 설정
                    border.setStroke(5, Color.GRAY); // 테두리 두께와 색상 설정 (비활성화 색상으로 변경)
                    option1.setBackground(border);
                }
            }
        });

        option2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isOption1Active[0] = !isOption1Active[0];

                if (isOption1Active[0]) {
                    // 버튼이 활성화된 경우
                    optionSet[1][0] = option2_text.getText().toString();
                    optionSet[1][1] = option2_num.getText().toString();

                    // 테두리 색 변경
                    GradientDrawable border = new GradientDrawable();
                    border.setColor(Color.TRANSPARENT); // 배경색을 투명으로 설정
                    border.setStroke(5, Color.BLUE); // 테두리 두께와 색상 설정
                    option2.setBackground(border);
                } else {
                    // 버튼이 비활성화된 경우
                    // recording 배열의 값을 빈 문자열로 설정
                    optionSet[1][0] = "";
                    optionSet[1][1] = "";

                    // 테두리 색 변경 (비활성화된 경우)
                    GradientDrawable border = new GradientDrawable();
                    border.setColor(Color.TRANSPARENT); // 배경색을 투명으로 설정
                    border.setStroke(5, Color.GRAY); // 테두리 두께와 색상 설정 (비활성화 색상으로 변경)
                    option2.setBackground(border);
                }
            }
        });

        option3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isOption1Active[0] = !isOption1Active[0];

                if (isOption1Active[0]) {
                    // 버튼이 활성화된 경우
                    optionSet[2][0] = option3_text.getText().toString();
                    optionSet[2][1] = option3_num.getText().toString();

                    // 테두리 색 변경
                    GradientDrawable border = new GradientDrawable();
                    border.setColor(Color.TRANSPARENT); // 배경색을 투명으로 설정
                    border.setStroke(5, Color.BLUE); // 테두리 두께와 색상 설정
                    option3.setBackground(border);
                } else {
                    // 버튼이 비활성화된 경우
                    // recording 배열의 값을 빈 문자열로 설정
                    optionSet[2][0] = "";
                    optionSet[2][1] = "";

                    // 테두리 색 변경 (비활성화된 경우)
                    GradientDrawable border = new GradientDrawable();
                    border.setColor(Color.TRANSPARENT); // 배경색을 투명으로 설정
                    border.setStroke(5, Color.GRAY); // 테두리 두께와 색상 설정 (비활성화 색상으로 변경)
                    option3.setBackground(border);
                }
            }
        });

        option4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isOption1Active[0] = !isOption1Active[0];

                if (isOption1Active[0]) {
                    // 버튼이 활성화된 경우
                    optionSet[3][0] = option4_text.getText().toString();
                    optionSet[3][1] = option4_num.getText().toString();

                    // 테두리 색 변경
                    GradientDrawable border = new GradientDrawable();
                    border.setColor(Color.TRANSPARENT); // 배경색을 투명으로 설정
                    border.setStroke(5, Color.BLUE); // 테두리 두께와 색상 설정
                    option4.setBackground(border);
                } else {
                    // 버튼이 비활성화된 경우
                    // recording 배열의 값을 빈 문자열로 설정
                    optionSet[3][0] = "";
                    optionSet[3][1] = "";

                    // 테두리 색 변경 (비활성화된 경우)
                    GradientDrawable border = new GradientDrawable();
                    border.setColor(Color.TRANSPARENT); // 배경색을 투명으로 설정
                    border.setStroke(5, Color.GRAY); // 테두리 두께와 색상 설정 (비활성화 색상으로 변경)
                    option4.setBackground(border);
                }
            }
        });

        option5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isOption1Active[0] = !isOption1Active[0];

                if (isOption1Active[0]) {
                    // 버튼이 활성화된 경우
                    optionSet[4][0] = option5_text.getText().toString();
                    optionSet[4][1] = option5_num.getText().toString();

                    // 테두리 색 변경
                    GradientDrawable border = new GradientDrawable();
                    border.setColor(Color.TRANSPARENT); // 배경색을 투명으로 설정
                    border.setStroke(5, Color.BLUE); // 테두리 두께와 색상 설정
                    option5.setBackground(border);
                } else {
                    // 버튼이 비활성화된 경우
                    // recording 배열의 값을 빈 문자열로 설정
                    optionSet[4][0] = "";
                    optionSet[4][1] = "";

                    // 테두리 색 변경 (비활성화된 경우)
                    GradientDrawable border = new GradientDrawable();
                    border.setColor(Color.TRANSPARENT); // 배경색을 투명으로 설정
                    border.setStroke(5, Color.GRAY); // 테두리 두께와 색상 설정 (비활성화 색상으로 변경)
                    option5.setBackground(border);
                }
            }
        });

        //선택완료 버튼 위젯 가져오기
        Button closebutton=optionLayout.findViewById(R.id.button);
        //선택완료 버튼 이벤트
        closebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addToCart(title,subtitle,optionSet);
                optionDialog.dismiss();
            }
        });

        optionDialog.setContentView(optionLayout);
        optionDialog.show();

    }

}

