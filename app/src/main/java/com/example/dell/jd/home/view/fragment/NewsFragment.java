package com.example.dell.jd.home.view.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.dell.jd.R;
import com.example.dell.jd.base.BaseFragment;
import com.example.dell.jd.home.adapter.NewsBannerAdapter;
import com.example.dell.jd.home.adapter.NewsRecAdapter;
import com.example.dell.jd.home.bean.NewsBean;
import com.example.dell.jd.home.contract.NewsFragmentContract;
import com.example.dell.jd.home.presenter.NewsPresenter;
import com.example.dell.jd.home.view.Banner_Indicator;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 * A simple {@link Fragment} subclass.
 */
public class NewsFragment extends BaseFragment<NewsPresenter> implements NewsFragmentContract.INewsView {

    private  String tabID;
    private RecyclerView rec;
    private NewsRecAdapter adapter;
    private ViewPager banner_viewPager;
    private List<View> banner_views = new ArrayList<>();
    private Banner_Indicator banner_indicator;
    private int viewpage_Current_Pos = 0;
    int current_banner_item;

    public NewsFragment(String tabID) {
        this.tabID = tabID;
    }

    @Override
    protected NewsPresenter initPresenter() {
        return new NewsPresenter();
    }

    @Override
    protected void initLinstener() {

    }

    @Override
    protected void initData() {

        mPresenter.getRecommendList(tabID);

    }

    @Override
    protected void initView(View view) {

        banner_viewPager = view.findViewById(R.id.banner_viewpager);
        banner_indicator =view.findViewById(R.id.banner_indicator);
        rec = view.findViewById(R.id.rec_news);

        rec.setLayoutManager(new LinearLayoutManager(getActivity()));
         ArrayList<NewsBean.ResultData> resultData = new ArrayList<>();
        adapter = new NewsRecAdapter(resultData);
        rec.setAdapter(adapter);
        //添加分割线
        rec.addItemDecoration(new DividerItemDecoration(getActivity(),DividerItemDecoration.VERTICAL));
    }

    @Override
    public int getLayoutID() {
        return R.layout.fragment_news;
    }

    @Override
    public void setRecommendList(NewsBean newsBean) { ;

        ArrayList<NewsBean.ResultData> resultData = new ArrayList<>();

        //跑马灯数据
        StringBuffer taxts=new StringBuffer();
        List<NewsBean.DataBean.FlashListBean> flash_list = newsBean.getData().getFlash_list();
        for (int i = 0; i < flash_list.size(); i++) {
            taxts.append(flash_list.get(i).getTheme()+"");
        }

        for (int i = 0; i < newsBean.getData().getArticle_list().size(); i++) {
            NewsBean.DataBean.ArticleListBean articleListBean = newsBean.getData().getArticle_list().get(i);
            NewsBean.ResultData artical = new NewsBean.ResultData();
            if(articleListBean.getView_type() == 2){
                artical.type= NewsBean.ResultData.TYPE_TE_XIE;
            }else if (articleListBean.getView_type() == 4){
                artical.type= NewsBean.ResultData.TYPE_VIDEO;
            }else {
                artical.type= NewsBean.ResultData.TYPE_LIST;
            }
            artical.data=articleListBean;
            resultData.add(artical);
        }
        adapter.addData(resultData);

        initBanner(newsBean);
    }

    private void initBanner(final NewsBean newsBean){

        for (int i = 0; i <newsBean.getData().getBanner_list().size(); i++) {
            current_banner_item = i;
            View ban_view = LayoutInflater.from(getContext()).inflate(R.layout.news_banner_item,null,false);
            TextView bannerContent = ban_view.findViewById(R.id.banner_content);
            ImageView bannerImage =  ban_view.findViewById(R.id.banner_image);
            bannerContent.setText(newsBean.getData().getBanner_list().get(i).getDescription());
            Glide.with(getContext()).load(newsBean.getData().getBanner_list().get(i).getImage_url()).into(bannerImage);
            banner_views.add(ban_view);
            ban_view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(getContext(), "点击了"+current_banner_item+"个view", Toast.LENGTH_SHORT).show();
                }
            });
        };

        NewsBannerAdapter bannerAdapter = new NewsBannerAdapter(banner_views);
        banner_viewPager.setAdapter(bannerAdapter);

//        设置图片数量，总数
        banner_indicator.setBannerImageSize(newsBean.getData().getBanner_list().size());
//        设置当前轮播图位置，默认0
        banner_indicator.setCurrentBannerItem(0);

//        viewPage监听
        banner_viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                current_banner_item = position;
                banner_indicator.setCurrentBannerItem(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
//        倒计时
        Timer timer = new Timer();
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                viewpage_Current_Pos+=1;
//                Log.e("TAG","当前位置"+viewpage_Current_Pos%(newsBean.getData().getBanner_list().size()));

                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        banner_viewPager.setCurrentItem(viewpage_Current_Pos%(newsBean.getData().getBanner_list().size()));
                    }
                });
            }
        };
        timer.schedule(timerTask,2000,2000);
    }


}
