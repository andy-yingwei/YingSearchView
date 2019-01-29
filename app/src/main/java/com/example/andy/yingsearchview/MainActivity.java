package com.example.andy.yingsearchview;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.yingsearchviewlibrary.MyAdapter;
import com.example.yingsearchviewlibrary.YingSearchViewByPopupwindow;
import com.example.yingsearchviewlibrary.YingSearchViewByRecyclerView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<String> itemList =  new ArrayList<String>();
    private YingSearchViewByRecyclerView myYingSearchViewByRecyclerView;
    private YingSearchViewByPopupwindow myYingSearchViewByPopupwindow;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        itemList = new ArrayList<String>();
        itemList.add("下拉列表项1");
        itemList.add("下拉列表项2");
        itemList.add("下拉列表项3");

        myYingSearchViewByRecyclerView = (YingSearchViewByRecyclerView) findViewById(R.id.yingSearchViewByRecyclerView);
        myYingSearchViewByPopupwindow=(YingSearchViewByPopupwindow) findViewById(R.id.yingSearchViewByPopupwindow);

        myYingSearchViewByRecyclerView.setRecyclerViewData(itemList);

        myYingSearchViewByPopupwindow.setPopupWindowData(itemList);
        myYingSearchViewByPopupwindow.setTextViewStyle(20, Color.BLACK,true, Color.WHITE);
        itemList.add("djdsjsdkskds");
        myYingSearchViewByPopupwindow.setPopupWindowData(itemList);
    }

}
