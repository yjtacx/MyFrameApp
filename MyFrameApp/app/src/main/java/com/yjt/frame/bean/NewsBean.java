package com.yjt.frame.bean;

import java.util.List;

/**
 * Created by yujiangtao on 16/3/27.
 */
public class NewsBean extends BaseBean {
    private List<New> news=null;

    public List<New> getNews() {
        return news;
    }

    public void setNews(List<New> news) {
        this.news = news;
    }

    public static class New {

        private String text;

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }

    }
}
