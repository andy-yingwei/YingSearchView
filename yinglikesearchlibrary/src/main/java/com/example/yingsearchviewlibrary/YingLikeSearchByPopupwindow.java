package com.example.yingsearchviewlibrary;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import java.util.ArrayList;

public class YingLikeSearchByPopupwindow extends LinearLayout {

    private boolean cutLine;
    private int recyclerViewBackGround;
    private int itemTextSize;

    private ArrayList<String> itemList =  new ArrayList<String>();
    private MyAdapter myAdapter;
    private EditText myEditText;
    private PopupWindow popupWindow = null;
    private RecyclerView recyclerView;
    private View popupwindowView;

    private OnEdittextChangedListener myOnEdittextChangedListener = null;

    public YingLikeSearchByPopupwindow(Context context) {
        this(context,null);
    }

    public YingLikeSearchByPopupwindow(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public YingLikeSearchByPopupwindow(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initView();
    }

    private void initView(){
        String infServie = Context.LAYOUT_INFLATER_SERVICE;
        LayoutInflater layoutInflater;
        layoutInflater =  (LayoutInflater) getContext().getSystemService(infServie);
        View view = layoutInflater.inflate(R.layout.search_by_popupwindow, this,true);
        myEditText= (EditText)findViewById(R.id.search_by_popupwindow_editText);
        myEditText.setHint("请输入环号");
        myEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(popupWindow == null){
                    if(myOnEdittextChangedListener != null){
                        myOnEdittextChangedListener.onEdittextChanged(myEditText.getText().toString().trim());
                    }
                    showPopWindow();
                }
            }
        });

    }

    //打开下拉列表弹窗
    private void showPopWindow() {
        popupwindowView = LayoutInflater.from(getContext()).inflate(R.layout.popupwindow, null);
        recyclerView = (RecyclerView) popupwindowView.findViewById(R.id.popupwindow_recyclerView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setBackgroundColor(recyclerViewBackGround);
        if (cutLine) {
            recyclerView.addItemDecoration(new DividerItemDecoration(popupwindowView.getContext(), DividerItemDecoration.VERTICAL));
        }
        myAdapter = new MyAdapter(itemList);
        recyclerView.setAdapter(myAdapter);
        myAdapter.setOnItemClickLitener(new MyAdapter.ViewHolder.OnItemClickListener() {
            @Override
            public void onItemClick(View view, TextView textView, int position) {
                myEditText.setText(itemList.get(position));
                closePopWindow();
            }
        });

        popupWindow = new PopupWindow();
        popupWindow.setWidth(ViewGroup.LayoutParams.WRAP_CONTENT);
        popupWindow.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        popupWindow.setContentView(popupwindowView);
        popupWindow.setBackgroundDrawable(new ColorDrawable(0x00000000));
        //popupWindow.setFocusable(false);
        popupWindow.setOutsideTouchable(true);
        popupWindow.showAsDropDown(this);
        popupWindow.setTouchInterceptor(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_OUTSIDE && !popupWindow.isFocusable()) {
                    //如果点击了popupWindow外面且焦点不在popupWindow上,不再往下dispatch事件：
                    //myEditText.setText("");
                    closePopWindow();
                    return true;
                }
                //否则default，往下dispatch事件:关掉popupWindow，
                return false;
            }
        });

    }


    //关闭下拉列表弹窗
    private void closePopWindow(){
        popupWindow.dismiss();
        popupWindow = null;
    }

    public interface OnEdittextChangedListener {
        void onEdittextChanged(String text);
    }

    public void setOnEdittextChangedListener(OnEdittextChangedListener onEdittextChangedListener) {
        this.myOnEdittextChangedListener = onEdittextChangedListener;
    }

    //设置下拉列表数据
    public void setPopupWindowData(ArrayList<String> itemList){
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
