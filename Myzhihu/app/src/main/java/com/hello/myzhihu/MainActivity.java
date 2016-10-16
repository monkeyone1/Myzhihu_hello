package com.hello.myzhihu;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.google.gson.Gson;
import com.hello.myzhihu.adapter.NewDataAdapter;
import com.hello.myzhihu.bean.NewsData;
import com.hello.myzhihu.bean.NewsHot;
import com.hello.myzhihu.connom.AppNetConfig;
import com.hello.myzhihu.utils.utils;
import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.squareup.picasso.Picasso;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.builder.GetBuilder;
import com.zhy.http.okhttp.callback.StringCallback;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;

public class MainActivity extends AppCompatActivity {


    private static final String TAG = "Test";
    //    @BindView(R.id.rv_news)
//    RecyclerView rvNews;
    @BindView(R.id.rv_news)
    XRecyclerView rvNews;
    @BindView(R.id.pb_load)
    ProgressBar pbLoad;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.sw_relayout)
    SwipeRefreshLayout swRelayout;
    //  //  @BindView(R.id.vp_barner)
//    ViewPager vpBarner;
    private String date;
    private NewsData newsData;
    private NewDataAdapter newDataAdapter;
    private View headerview;
    private NewsHot newsHot;
    private ViewPager viewPager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        getServiceData(AppNetConfig.NEWURL);
        //getHotData();

        initView();


    }

    private void initView() {
        headerview = View.inflate(this, R.layout.head_item, null);
        //  headerview = mInflater.inflate(R.layout.head_item,rvNews, false);
        viewPager = (ViewPager) headerview.findViewById(R.id.vp_barner);
        toolbar.setTitle("知乎日报");
        toolbar.setSubtitle("属于我自己的知乎");
        toolbar.setTitleTextColor(Color.parseColor("#FFFFFF"));
        toolbar.setSubtitleTextColor(Color.parseColor("#FFFFFF"));
        setSupportActionBar(toolbar);
        swRelayout.setColorSchemeResources(android.R.color.holo_blue_dark,android.R.color.holo_blue_light);
        swRelayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getServiceData(AppNetConfig.NEWURL);
                //getHotData();
            }
        });
        rvNews.setPullRefreshEnabled(false);
        rvNews.setLoadingMoreProgressStyle(ProgressStyle.Pacman);

//        rvNews.setLoadingListener(new XRecyclerView.LoadingListener()
//        {
//            public void onLoadMore()
//            {
//                if (newsData.getDate() == null)
//                {
//                    String str = newsData.getDate();
//                    getServiceData(AppNetConfig.BEFORE+ str);
//                }
//            }
//
//            public void onRefresh()
//            {
//            }
//        });
        /**
         * 解决swipeRefreshLayout和Recyclerview 滑动冲突
         *
         * */
        rvNews.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                int topRowVerticalPosition =
                        (rvNews == null || rvNews.getChildCount() == 0) ? 0 : rvNews.getChildAt(0).getTop();
                swRelayout.setEnabled(topRowVerticalPosition >= 0);
            }
        });
    }

    private void loadMore() {
        (OkHttpUtils.get()
                .url(AppNetConfig.BEFORE + date))
                .build()
                .execute(new StringCallback() {
                    public void onError(Call paramCall, Exception paramException, int paramInt) {
                    }

                    public void onResponse(String response, int paramInt) {
                        parseBeforeJson(response);
                    }
                });
    }

    private void parseBeforeJson(String response) {
        NewsData localNewsData = new Gson().fromJson(response, NewsData.class);
        date = localNewsData.getDate();
        System.out.println("localNewsData.getStories()+" + localNewsData.getStories());
        newDataAdapter.addloadMore(localNewsData.getStories());
        newDataAdapter.notifyDataSetChanged();
        rvNews.loadMoreComplete();
    }


    /**
     * 获取说有数据
     */
    public void getServiceData(String url) {
        OkHttpUtils
                .get()
                .url(url)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        utils.ShowToast(MainActivity.this, "网络请求失败");
                        pbLoad.setVisibility(View.GONE);
                        swRelayout.setRefreshing(false);

                    }

                    @Override
                    public void onResponse(String response, int id) {
                        progressData(response);
                        //  String response1 = response;
                        pbLoad.setVisibility(View.GONE);
                        swRelayout.setRefreshing(false);


                    }


                });


    }

    private void progressData(String response) {
        // System.out.println(response);
        Gson gson = new Gson();

        newsData = gson.fromJson(response, NewsData.class);
        date = newsData.getDate();
        newDataAdapter = new NewDataAdapter(this, this.newsData);
        newDataAdapter.setHeaderview(R.layout.head_item);
        rvNews.setAdapter(newDataAdapter);
        LinearLayoutManager nearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rvNews.setLayoutManager(nearLayoutManager);
        rvNews.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {

            }

            @Override
            public void onLoadMore() {
                loadMore();

            }
        });


        newDataAdapter.setOnItemClickLitener(new NewDataAdapter.OnItemClickLitener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent = new Intent(MainActivity.this, ContentActivity.class);
                int id = MainActivity.this.newsData.getStories().get(position - 1).getId();
                intent.putExtra("url", AppNetConfig.BASEURL + id);
                // Log.d(TAG, "onItemClick: " + AppNetConfig.BASEURL + id);
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();
        switch (itemId) {
            case R.id.action_settings:
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    /*
    *
    * 获取热门信息
    * */
//    public void getHotData() {
//        OkHttpUtils.get()
//                .url(AppNetConfig.HOT)
//                .build()
//                .execute(new StringCallback() {
//                    @Override
//                    public void onError(Call call, Exception e, int id) {
//                        utils.ShowToast(MainActivity.this, "网络请求失败");
//                    }
//
//                    @Override
//                    public void onResponse(String response, int id) {
////                        Log.d(TAG, "onResponse: " + response);
//                        System.out.println("+++++++++++" + response);
//                        progresshot(response);
//                    }
//                });
//
//    }

//    private void progresshot(String response) {
//        Gson gson = new Gson();
//        newsHot = gson.fromJson(response, NewsHot.class);
//        if (newsHot != null) {
//
////            topNewsAdapter = new TopNewsAdapter();
////            viewPager.setAdapter(topNewsAdapter);
//
//        }
//
//    }

//    private class TopNewsAdapter extends PagerAdapter {
//
//
//        @Override
//        public int getCount() {
//            int count = 5;
//            return count;
//        }
//
//        @Override
//        public boolean isViewFromObject(View view, Object object) {
//            return view == object;
//        }
//
//        @Override
//        public Object instantiateItem(ViewGroup container, int position) {
//            String thumbnail = newsHot.getRecent().get(position).getThumbnail();
//            System.out.println("++++++++++++++++++++" + thumbnail);
//            Log.e(TAG, "instantiateItem: " + thumbnail);
//            ImageView view = new ImageView(MainActivity.this);
//
//            Picasso.with(MainActivity.this).load(thumbnail).into(view);
//            //Picasso.with(MainActivity.this).load(thumbnail).into(view);
//            view.setScaleType(ImageView.ScaleType.FIT_XY);
//            container.addView(view);
//            return view;
//        }
//
//        @Override
//        public void destroyItem(ViewGroup container, int position, Object object) {
//            container.removeView((View) object);
//        }
//    }
}
