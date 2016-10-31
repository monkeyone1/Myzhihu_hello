package com.hello.myzhihu.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.hello.myzhihu.ContentActivity;
import com.hello.myzhihu.R;
import com.hello.myzhihu.bean.NewsData;
import com.hello.myzhihu.connom.AppNetConfig;
import com.squareup.picasso.Picasso;


import java.util.List;

import me.relex.circleindicator.CircleIndicator;

/**
 * Created by hello on 2016/8/22.
 */
public class NewDataAdapter extends RecyclerView.Adapter<NewDataAdapter.MyViewHolder> {


    public static final int TYPE_HEADER = 0;
    public static final int TYPE_NORMAL = 1;
    private static View headerinflate;
    private static int mHeaderView = -1;
    private Handler mHandler;
    private LayoutInflater mInflater;
    private OnItemClickLitener mOnItemClickLitener;
    private Context mcontext;
    private List<NewsData.StoriesBean> stories = null;
    private List<NewsData.TopStoriesBean> top_stories;

    public NewDataAdapter(Context context, NewsData newsData) {
        if (newsData != null) {
            stories = newsData.getStories();
            top_stories = newsData.getTop_stories();
            mcontext = context;
            mInflater = LayoutInflater.from(context);

        }

    }

    /**
     * 加载更多
     */
    public void addloadMore(final List<NewsData.StoriesBean> data) {
        new Thread() {
            @Override
            public void run() {
                stories.addAll(data);
            }
}.start();

    }

    /**
     * ItemClick的回调接口
     */
    public interface OnItemClickLitener {
        void onItemClick(View view, int position);
    }


    public void setOnItemClickLitener(OnItemClickLitener mOnItemClickLitener) {
        this.mOnItemClickLitener = mOnItemClickLitener;
    }

    /**
     * 设置header头布局
     */
    public void setHeaderview(int view) {
        mHeaderView = view;
    }


    @Override
    public int getItemViewType(int position) {
        if (mHeaderView == -1) return TYPE_NORMAL;
        return position == 0 ? TYPE_HEADER : TYPE_NORMAL;
    }


    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        if (mHeaderView != -1 && viewType == TYPE_HEADER) {
            headerinflate = mInflater.inflate(mHeaderView, parent, false);
            return new MyViewHolder(headerinflate);
        } else {
            View view = mInflater.inflate(R.layout.item_rv, parent, false);

            return new MyViewHolder(view);
        }


    }


    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        if (position == 0 && headerinflate != null&&mHeaderView!=-1) {
            holder.tvTitle.setText(top_stories.get(position).getTitle());
            holder.vpBarner.setAdapter(new PagerAdapter() {
                @Override
                public int getCount() {
                    if (NewDataAdapter.this.top_stories == null){
                        return 0;
                    }else {  return NewDataAdapter.this.top_stories.size();}


                }

                @Override
                public boolean isViewFromObject(View view, Object object) {
                    return view == object;
                }

                @Override
                public Object instantiateItem(ViewGroup container, final int position) {
                    String str = (top_stories.get(position)).getImage();
                    ImageView localImageView = new ImageView(NewDataAdapter.this.mcontext);
                    localImageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
                    Picasso.with(NewDataAdapter.this.mcontext).load(str).into(localImageView);
                    holder.tvTitle.setText(top_stories.get(position).getTitle());
                    container.addView(localImageView);
                    localImageView.setOnClickListener(new View.OnClickListener()
                    {

                        @Override
                        public void onClick(View v) {
                            Intent localIntent = new Intent();
                            localIntent.setClass(NewDataAdapter.this.mcontext, ContentActivity.class);
                            localIntent.putExtra("url", AppNetConfig.BASEURL + top_stories.get(position).getId());
                            NewDataAdapter.this.mcontext.startActivity(localIntent);
                        }
                    });
                    return localImageView;

                }

                @Override
                public void destroyItem(ViewGroup container, int position, Object object) {
                    container.removeView((View) object);
                }
            });

            holder.indicator.setViewPager(holder.vpBarner);

            if (mHandler == null)
            {
                mHandler = new Handler()
                {
                    public void handleMessage(Message paramMessage)
                    {
                        int i = 1 + holder.vpBarner.getCurrentItem();
                        if (i > -1 + NewDataAdapter.this.top_stories.size())
                            i = 0;
                        holder.vpBarner.setCurrentItem(i);
                        mHandler.sendEmptyMessageDelayed(0, 3000L);
                    }
                };
                mHandler.sendEmptyMessageDelayed(0, 3000L);
            }

        } else {

            int pos = position-1;
            String images = stories.get(pos).getImages().get(0);
            String title = stories.get(pos).getTitle();
            Picasso.with(mcontext).load(images).into(holder.ivNewsImage);
            holder.tvNewsTitle.setText(title);
            if (mOnItemClickLitener != null) {
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        mOnItemClickLitener.onItemClick(view, holder.getLayoutPosition());

                    }
                });
            }
        }


    }


    @Override
    public int getItemCount() {
        if (headerinflate == null){
            return stories == null ? 0 : stories.size();
        }else {
            return stories == null ? 0 : stories.size() + 1;
        }


    }


    static class MyViewHolder extends RecyclerView.ViewHolder {

        CircleIndicator indicator;
        TextView tvTitle;
        ViewPager vpBarner;
        TextView tvNewsTitle;
        ImageView ivNewsImage;

        public MyViewHolder(View itemView) {

            super(itemView);
            if (itemView == headerinflate) {
                vpBarner = ((ViewPager) itemView.findViewById(R.id.vp_barner));
                tvTitle = ((TextView) itemView.findViewById(R.id.tv_head_title));
                indicator = (CircleIndicator) itemView.findViewById(R.id.indicator);
                return;
            }
                tvNewsTitle = (TextView) itemView.findViewById(R.id.tv_news_title);
                ivNewsImage = (ImageView) itemView.findViewById(R.id.iv_news_image);
        }
    }


}

