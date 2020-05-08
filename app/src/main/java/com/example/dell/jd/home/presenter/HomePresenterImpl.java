package com.example.dell.jd.home.presenter;

import com.example.dell.jd.base.BasePresenter;
import com.example.dell.jd.home.bean.User;
import com.example.dell.jd.home.contract.HomeContract;
import com.example.dell.jd.home.model.HomeModelImpl;
import com.example.dell.jd.home.view.HomeActivity;
import com.example.dell.jd.net.INetCallBack;

public class HomePresenterImpl extends BasePresenter<HomeActivity> implements HomeContract.IHomePresenter {

    private HomeContract.IHomeMode  iHomeMode;
//    private HomeContract.IHomeView iHomeView;

    public HomePresenterImpl() {
        iHomeMode = new HomeModelImpl(this);
    }
    @Override
    public void callHomeBannview(String string) {
//        P层中拿到数据
        mview.setBannView(string);
    }
    @Override
    public void getBannerView() {
        iHomeMode.getHomeBannview(new INetCallBack<User>() {
            @Override
            public void onSuccess(User user) {

            }

            @Override
            public void onError(Throwable throwable) {

            }
        });
    }

    @Override
    public void getHomeTabList() {

    }

   /* @Override
    public void disAttachView() {
        iHomeMode = null;
        iHomeView = null;
    }*/
}
