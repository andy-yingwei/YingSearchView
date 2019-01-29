package com.example.andy.yingsearchview;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.yingsearchviewlibrary.YingLikeSearchByPopupwindow;
import com.example.yingsearchviewlibrary.YingLikeSearchByRecyclerView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<String> itemList =  new ArrayList<String>();
    private YingLikeSearchByRecyclerView myYingLikeSearchByRecyclerView;
    private YingLikeSearchByPopupwindow myYingLikeSearchByPopupwindow;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        itemList = new ArrayList<String>();
        itemList.add("下拉列表项1");
        itemList.add("下拉列表项2");
        itemList.add("下拉列表项3");

        myYingLikeSearchByRecyclerView = (YingLikeSearchByRecyclerView) findViewById(R.id.yingSearchViewByRecyclerView);
        myYingLikeSearchByPopupwindow =(YingLikeSearchByPopupwindow) findViewById(R.id.yingSearchViewByPopupwindow);

        myYingLikeSearchByRecyclerView.setRecyclerViewData(itemList);

        myYingLikeSearchByPopupwindow.setPopupWindowData(itemList);
        myYingLikeSearchByPopupwindow.setTextViewStyle(20, Color.BLACK,true, Color.WHITE);
        itemList.add("djdsjsdkskds");
        myYingLikeSearchByPopupwindow.setPopupWindowData(itemList);
    }

}
