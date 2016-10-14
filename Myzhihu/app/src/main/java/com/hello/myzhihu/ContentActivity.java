package com.hello.myzhihu;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ImageView;

import com.google.gson.Gson;
import com.hello.myzhihu.bean.NewsContent;
import com.hello.myzhihu.utils.utils;
import com.squareup.picasso.Picasso;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;

public class ContentActivity extends AppCompatActivity {

    private static final String TAG = "CONTENT";
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.wv_news_content)
    WebView wvNewsContent;
    //    @BindView(R.id.progressBar)
//    ProgressBar progressBar;
    @BindView(R.id.iv_content_image)
    ImageView ivContentImage;
    @BindView(R.id.collapsing_toolbar_layout)
    CollapsingToolbarLayout collapsingToolbarLayout;
    @BindView(R.id.coordinatorLayout)
    CoordinatorLayout coordinatorLayout;
    //    @BindView(R.id.tv_content_title)
//    TextView tvContentTitle;
    private NewsContent newsContent;

    /**
     * body : HTML 格式的新闻
     * image-source : 图片的内容提供方。为了避免被起诉非法使用图片，在显示图片时最好附上其版权信息。
     * title : 新闻标题
     * image : 获得的图片同 最新消息 获得的图片分辨率不同。这里获得的是在文章浏览界面中使用的大图。
     * share_url : 供在线查看内容与分享至 SNS 用的 URL
     * js : 供手机端的 WebView(UIWebView) 使用
     * recommenders : 这篇文章的推荐者
     * ga_prefix : 供 Google Analytics 使用
     * section : 栏目的信息
     * thumbnail : 栏目的缩略图
     * id : 该栏目的 id
     * name : 该栏目的名称
     * type : 新闻的类型
     * id : 新闻的 id
     * css : 供手机端的 WebView(UIWebView) 使用
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content);
        ButterKnife.bind(this);
        initData();
        initView();

    }

    private void initData() {
        String url = getIntent().getStringExtra("url");
        Log.d(TAG, "initView: " + url);
        if (!TextUtils.isEmpty(url)) {
            OkHttpUtils
                    .get()
                    .url(url)
                    .build()
                    .execute(new StringCallback() {
                        @Override
                        public void onError(Call call, Exception e, int id) {
                            utils.ShowToast(ContentActivity.this, "出现错误");
                        }

                        @Override
                        public void onResponse(String response, int id) {
                            progressData(response);

                        }
                    });
        }


    }

    private void progressData(String response) {
        Gson gson = new Gson();
        newsContent = gson.fromJson(response, NewsContent.class);
        initcontent();
    }


    private void initcontent() {
        String title = newsContent.getTitle();
        String image = newsContent.getImage();
        String body = newsContent.getBody();
//        tvContentTitle.setText(title);
        collapsingToolbarLayout.setTitle(title);
        collapsingToolbarLayout.setVisibility(View.VISIBLE);



        Picasso.with(this).load(image).into(ivContentImage);
        WebSettings settings = wvNewsContent.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        // 开启DOM storage API 功能
       settings.setDomStorageEnabled(true);

        // settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NARROW_COLUMNS);
        String css = "<link rel=\"stylesheet\" href=\"file:///android_asset/news_qa.auto.css\" type=\"text/css\">";
        String html = "<html><head>" + css + "</head><body>" + body + "</body></html>";
        wvNewsContent.loadDataWithBaseURL("x-data://base", html, "text/html", "utf-8", null);

    }


    private void initView() {
        toolbar.setTitle("知乎日报");
        toolbar.setTitleTextColor(Color.parseColor("#FFFFFF"));
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
                finish();

            }
        });

        //       if (!TextUtils.isEmpty(url)) {
        //          wvNewsContent.loadUrl(url);
//            WebSettings settings = wvNewsContent.getSettings();
//            settings.setJavaScriptEnabled(true);
//
//            wvNewsContent.setWebViewClient(new WebViewClient() {
//                @Override//开始加载网页
//                public void onPageStarted(WebView view, String url, Bitmap favicon) {
//                    super.onPageStarted(view, url, favicon);
//                    Log.d(TAG, "onPageStarted: 开始加载网页了");
//                    progressBar.setVisibility(View.VISIBLE);
//                }
//
//                @Override//网页加载结束
//                public void onPageFinished(WebView view, String url) {
//                    super.onPageFinished(view, url);
//                    Log.d(TAG, "onPageStarted: 网页加载结束");
//                    progressBar.setVisibility(View.GONE);
//
//                }
//
//            });
//
//        } else {
//            utils.ShowToast(this, "信息为空");
        //  }

    }
}
