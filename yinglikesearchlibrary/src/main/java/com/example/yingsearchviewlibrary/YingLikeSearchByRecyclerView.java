package com.example.yingsearchviewlibrary;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class YingLikeSearchByRecyclerView extends LinearLayout {

    private boolean cutLine;
    private int recyclerViewBackGround;
    private int itemTextSize;


    private View myView;
    private MyAdapter myAdapter;
    private EditText myEditText;
    private RecyclerView myRecyclerView;
    private LinearLayoutManager layoutManager;
    private ArrayList<String> itemList =  new ArrayList<String>();
    int i=4;
    public YingLikeSearchByRecyclerView(Context context) {
        this(context,null);
    }

    public YingLikeSearchByRecyclerView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public YingLikeSearchByRecyclerView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initView();
    }

    private void initView(){
        String infServie = Context.LAYOUT_INFLATER_SERVICE;
        LayoutInflater layoutInflater;
        layoutInflater =  (LayoutInflater) getContext().getSystemService(infServie);
        myView = layoutInflater.inflate(R.layout.search_by_recyclerview, this,true);
        myEditText = (EditText) findViewById(R.id.search_by_recyclerview_editText);
        myEditText.setHint("请输入环号");
        myRecyclerView =(RecyclerView) findViewById(R.id.search_by_recyclerview_recyclerView);
        myEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(layoutManager==null){
                    layoutManager=new LinearLayoutManager(getContext());
                    layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                    myRecyclerView.setLayoutManager(layoutManager);
                    myRecyclerView.setVisibility(VISIBLE);
                    myAdapter = new MyAdapter(itemList);
                    myAdapter.setOnItemClickLitener(new MyAdapter.ViewHolder.OnItemClickListener() {
                        @Override
                        public void onItemClick(View view, TextView textView, int position) {
                            //myEditText.setText(itemList.get(position));
                            myRecyclerView.setVisibility(GONE);
                            myEditText.setText("");
                            layoutManager=null;
                        }
                    });
                    myRecyclerView.setAdapter(myAdapter);
                }else{
                    itemList.add("下拉列表项"+i);
                    i=i+1;
                    myAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });





    }

    //设置下拉列表数据
    public void setRecyclerViewData(ArrayList<String> itemList){
        this.itemList = itemList;
    }


    //取得TextView内容
    public String getSelectItem(){
        return myEditText.getText().toString();
    }


    //设置选项字体大小
    public void setItemTextSize(int itemTextSize){
        this.itemTextSize=itemTextSize;
    }

    public void setTextViewStyle(int textSize,int textColor,boolean cutLine,int recyclerViewBackGround){
        myEditText.setTextSize(textSize);
        myEditText.setTextColor(textColor);
        this.cutLine=cutLine;
        this.recyclerViewBackGround=recyclerViewBackGround;
    }


}

