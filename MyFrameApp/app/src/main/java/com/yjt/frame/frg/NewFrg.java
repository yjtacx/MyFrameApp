package com.yjt.frame.frg;

import android.view.View;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.yjt.frame.R;
import com.yjt.frame.adapter.CommonAdapter;
import com.yjt.frame.adapter.ViewHolder;
import com.yjt.frame.bean.BaseBean;
import com.yjt.frame.bean.NewsBean;
import com.yjt.frame.http.URLS;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by yujiangtao on 16/3/27.
 */
public class NewFrg extends BaseListFrg {


    List<NewsBean.New> datalist=new ArrayList<NewsBean.New>();

    @Override
    protected BaseAdapter getAdapter() {
        return new CommonAdapter<NewsBean.New>(getActivity(),datalist,R.layout.layout_new_item) {
            @Override
            public void convert(ViewHolder holder, NewsBean.New item) {
                holder.setText(R.id.item_tv,item.getText());
            }
        };
    }

    @Override
    protected void initview() {
        List<NewsBean.New> datas = test_getdatas();
        bindDataToAdapter(datas);
    }


    @Override
    protected int getLayoutId() {
        return R.layout.frg_news;
    }

    @Override
    protected boolean hasTitleBar() {
        return false;
    }


    @Override
    public void doError(int requestcode, Exception e) {

    }



    @Override
    public void doSuccess(int requestcode, BaseBean bean, String data) {
        NewsBean nb = (NewsBean) bean;
        List<NewsBean.New> datas = nb.getNews();
        bindDataToAdapter(datas);
    }

    @Override
    public Class<?> getParserClass(int requestcode) {
        return NewsBean.class;
    }

    @Override
    public String getUrlSuffix(int requestcode) {
        return URLS.URL_GET_NEWS;
    }

    @Override
    public Map<String, String> getParmas(int requestcode) {
        Map<String,String> map=new HashMap<String,String>();
        map.put("param1","param1");
        map.put("param2","param2");
        return map;
    }

    private List<NewsBean.New>  test_getdatas(){
        List<NewsBean.New> datas =new ArrayList<NewsBean.New>();
        for(int i=0;i<10;i++){
            NewsBean.New n=new NewsBean.New();
            n.setText("测试数据"+i);
            datas.add(n);
        }
        return datas;
    }

    private void bindDataToAdapter(List<NewsBean.New> datas){
        if(datalist.size()>0)datalist.clear();
        datalist.addAll(datas);
        mAdapter.notifyDataSetChanged();
    }
}
