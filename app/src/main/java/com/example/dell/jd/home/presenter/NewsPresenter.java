package com.example.dell.jd.home.presenter;

import com.example.dell.jd.base.BasePresenter;
import com.example.dell.jd.home.bean.NewsBean;
import com.example.dell.jd.home.contract.NewsFragmentContract;
import com.example.dell.jd.home.contract.RecommendContract;
import com.example.dell.jd.home.model.NewsModel;
import com.example.dell.jd.net.INetCallBack;

public class NewsPresenter extends BasePresenter<NewsFragmentContract.INewsView> implements RecommendContract.IRecommendPresenter {
    private NewsFragmentContract.INewsMode iNewsMode;

    @Override
    public void getColumList() {

    }

    public NewsPresenter() {

        iNewsMode = new NewsModel();

    }

    @Override
    public void getRecommendList(String id) {
        iNewsMode.getRecommendList(id,new INetCallBack<NewsBean>() {
            @Override
            public void onSuccess(NewsBean newsBean) {

                mview.setRecommendList(newsBean);
            }

            @Override
            public void onError(Throwable throwable) {

            }
        });


    }
}
