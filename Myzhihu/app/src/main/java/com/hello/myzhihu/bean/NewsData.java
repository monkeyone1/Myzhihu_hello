package com.hello.myzhihu.bean;

import java.util.List;

/**
 * Created by hello on 2016/8/22.
 */
public class NewsData {

    /**
     * date : 20160822
     * stories : [{"ga_prefix":"082212","id":8711098,"images":["http://pic3.zhimg.com/f82d0efa981047cfedc3e4f70479585e.jpg"],"title":"大误 · 生活在电脑模拟的宇宙","type":0},{"ga_prefix":"082211","id":8712471,"images":["http://pic2.zhimg.com/fb55d3d65311259a2afe1017b69d9a75.jpg"],"title":"《三体》之后，一个中国女作家拿到了雨果奖","type":0},{"ga_prefix":"082210","id":8712038,"images":["http://pic1.zhimg.com/8d46f6f23de2c64b25ba1478afa5bd40.jpg"],"title":"能用到心理学专业技能的地方，可远不止心理咨询室而已","type":0},{"ga_prefix":"082209","id":8705761,"images":["http://pic1.zhimg.com/6655986bcc9c96af39f16fa4a80ae2c0.jpg"],"title":"前列腺高潮是什么意思？","type":0},{"ga_prefix":"082208","id":8693948,"images":["http://pic3.zhimg.com/9f4b0ef7f7a4b91c3a4e769f1ab6c0f6.jpg"],"title":"运营分析数据的十个经典方法，朴实却能解决问题","type":0},{"ga_prefix":"082207","id":8712078,"images":["http://pic1.zhimg.com/92731ff7453fbccfb8f54f84af8c9ab8.jpg"],"title":"电蚊拍噼啪一声，在它们的世界里发生了什么？","type":0},{"ga_prefix":"082207","id":8711533,"images":["http://pic4.zhimg.com/ae21b3f493483f06049f6a3017aa23bf.jpg"],"multipic":true,"title":"看完这篇混凝土受力的原理，希望你对此再无疑惑","type":0},{"ga_prefix":"082207","id":8702565,"images":["http://pic1.zhimg.com/8e9b25906a6ca7f91dda4efcfbd641a4.jpg"],"title":"数据会说谎，这里有一些真实的例子","type":0},{"ga_prefix":"082207","id":8711851,"images":["http://pic2.zhimg.com/ed60da14b98c4eac9864bdaf0c52f971.jpg"],"title":"读读日报 24 小时热门 TOP 5 · 林丹是真的老了吗？","type":0},{"ga_prefix":"082206","id":8705919,"images":["http://pic4.zhimg.com/284687abbb2bbcf6c5cb5115c931b2e3.jpg"],"title":"瞎扯 · 如何正确地吐槽","type":0}]
     * top_stories : [{"ga_prefix":"082211","id":8712471,"image":"http://pic1.zhimg.com/b1656e9f7ba6f2084cf78c2d91f75b50.jpg","title":"《三体》之后，一个中国女作家拿到了雨果奖","type":0},{"ga_prefix":"082208","id":8693948,"image":"http://pic3.zhimg.com/5dab24541438fbd30612be507bfd5cfe.jpg","title":"运营分析数据的十个经典方法，朴实却能解决问题","type":0},{"ga_prefix":"082207","id":8711851,"image":"http://pic3.zhimg.com/cd12cc6e2813c27e1cef6da2a023dbe2.jpg","title":"读读日报 24 小时热门 TOP 5 · 林丹是真的老了吗？","type":0},{"ga_prefix":"082207","id":8702565,"image":"http://pic1.zhimg.com/132a31971ba038dde3e4e6c7a38603c0.jpg","title":"数据会说谎，这里有一些真实的例子","type":0},{"ga_prefix":"082121","id":8709324,"image":"http://pic2.zhimg.com/c60492bf43ed956940f050dcce671689.jpg","title":"整点儿奥运 · 再夸一次女排，夸完才能看男篮决赛","type":0}]
     */

    private String date;
    /**
     * ga_prefix : 082212
     * id : 8711098
     * images : ["http://pic3.zhimg.com/f82d0efa981047cfedc3e4f70479585e.jpg"]
     * title : 大误 · 生活在电脑模拟的宇宙
     * type : 0
     */

    private List<StoriesBean> stories;
    /**
     * ga_prefix : 082211
     * id : 8712471
     * image : http://pic1.zhimg.com/b1656e9f7ba6f2084cf78c2d91f75b50.jpg
     * title : 《三体》之后，一个中国女作家拿到了雨果奖
     * type : 0
     */

    private List<TopStoriesBean> top_stories;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public List<StoriesBean> getStories() {
        return stories;
    }

    public void setStories(List<StoriesBean> stories) {
        this.stories = stories;
    }

    public List<TopStoriesBean> getTop_stories() {
        return top_stories;
    }

    public void setTop_stories(List<TopStoriesBean> top_stories) {
        this.top_stories = top_stories;
    }

    public static class StoriesBean {
        private String ga_prefix;
        private int id;
        private String title;
        private int type;
        private List<String> images;

        public String getGa_prefix() {
            return ga_prefix;
        }

        public void setGa_prefix(String ga_prefix) {
            this.ga_prefix = ga_prefix;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public List<String> getImages() {
            return images;
        }

        public void setImages(List<String> images) {
            this.images = images;
        }
    }

    public static class TopStoriesBean {
        private String ga_prefix;
        private int id;
        private String image;
        private String title;
        private int type;

        public String getGa_prefix() {
            return ga_prefix;
        }

        public void setGa_prefix(String ga_prefix) {
            this.ga_prefix = ga_prefix;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }
    }
}
