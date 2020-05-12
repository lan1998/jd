package com.example.dell.jd.login.presenter;

import android.util.Log;

import com.example.dell.jd.base.BasePresenter;
import com.example.dell.jd.login.bean.VerifiedBean;
import com.example.dell.jd.login.contract.LoginContract;
import com.example.dell.jd.login.model.LoginModel;
import com.example.dell.jd.net.INetCallBack;

import java.io.IOException;

import okhttp3.ResponseBody;

public class LoginPresenter extends BasePresenter<LoginContract.ILoginView> implements LoginContract.ILoginPresenter {

    LoginContract.ILoginMode iLoginMode;

    public LoginPresenter() {
        iLoginMode = new LoginModel();
    }

    @Override
    public void getVerified(String phoneNum,String type) {


        iLoginMode.getVerified(phoneNum, type, new INetCallBack<VerifiedBean>() {
            @Override
            public void onSuccess(VerifiedBean s) {


                mview.getVerified(s);

            }

            @Override
            public void onError(Throwable throwable) {

            }
        });

    }

    @Override
    public void login(String mobile, String smsCode) {
        iLoginMode.login(mobile, smsCode, new INetCallBack<ResponseBody>() {
            @Override
            public void onSuccess(ResponseBody responseBody) {

                try {
                    Log.e("TAG","登录成功返回值："+responseBody.string());
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onError(Throwable throwable) {

            }
        });
    }


    @Override
    public void checkSmsCode(String phoneNum, String smsCode, String type) {

        Log.e("TAG",phoneNum+"验证p层码值："+smsCode);

        iLoginMode.checkSmsCode(phoneNum, smsCode, type, new INetCallBack<VerifiedBean>() {
            @Override
            public void onSuccess(VerifiedBean verifiedBean) {

                mview.checkSmsCodeResult(verifiedBean);

            }

            @Override
            public void onError(Throwable throwable) {

            }
        });
    }
}
