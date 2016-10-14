package com.hello.myzhihu.bean;

import java.util.List;

/**
 * Created by hello on 2016/8/29.
 */
public class NewsHot {

    /**
     * news_id : 8730411
     * url : http://news-at.zhihu.com/api/2/news/8730411
     * thumbnail : http://pic2.zhimg.com/41feab44ccc151f6c7d1b8ec83485b71.jpg
     * title : 美剧里尸体上的缝合线，内行人能看出好多门道
     */

    private List<RecentBean> recent;

    public List<RecentBean> getRecent() {
        return recent;
    }

    public void setRecent(List<RecentBean> recent) {
        this.recent = recent;
    }

    public static class RecentBean {
        private int news_id;
        private String url;
        private String thumbnail;
        private String title;

        public int getNews_id() {
            return news_id;
        }

        public void setNews_id(int news_id) {
            this.news_id = news_id;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getThumbnail() {
            return thumbnail;
        }

        public void setThumbnail(String thumbnail) {
            this.thumbnail = thumbnail;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }
    }
}
