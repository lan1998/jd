package com.example.dell.jd.home.contract;

import com.example.dell.jd.base.BaseView;
import com.example.dell.jd.home.bean.NewsBean;
import com.example.dell.jd.net.INetCallBack;

public class NewsFragmentContract {


    public interface INewsView extends BaseView {
        void  setRecommendList(NewsBean newsBean);
    }
    public interface INewsMode{
        <T> void getRecommendList(String tabID, INetCallBack<T> iNetCallBack);
    }
    public interface INewsPresenter{
        void getRecommend(String string);
    }
}
