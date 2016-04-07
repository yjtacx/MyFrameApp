package com.yjt.frame.frg;

import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import com.yjt.frame.R;

/**
 * Created by yujiangtao on 16/3/27.
 */
public abstract class BaseListFrg extends BaseWebFrg {

    protected abstract BaseAdapter getAdapter();

    /**
     * 初始化完listview后的操作
     */
    protected abstract void initview();

    protected ListView mlistview = null;

    protected BaseAdapter mAdapter=null;

    protected void onitemclick(View view,int position){

    }

    @Override
    protected void initView() {
        mlistview = (ListView) findViewById(R.id.listview);
        mAdapter = getAdapter();

        //添加footerview
//        mlistview.addFooterView();
        mlistview.setAdapter(mAdapter);
        initview();
        initListViewListener();
    }

    private void initListViewListener(){
        /**
         * 设置Item点击事件
         */
        mlistview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                onitemclick(view,position);
            }
        });
        /**
         * 设置滑动到最后加载更多事件
         */
        mlistview.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {

            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                //判断是否滚到最后一行
            }
        });
    }



}
