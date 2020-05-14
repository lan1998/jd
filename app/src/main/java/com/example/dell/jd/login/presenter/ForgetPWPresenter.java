package com.example.dell.jd.login.presenter;

import com.example.dell.jd.base.BasePresenter;
import com.example.dell.jd.login.bean.VerifiedBean;
import com.example.dell.jd.login.contract.ForgetPWContract;
import com.example.dell.jd.login.model.ForgetPWModel;
import com.example.dell.jd.net.INetCallBack;

public class ForgetPWPresenter extends BasePresenter<ForgetPWContract.IForgetPWView> implements ForgetPWContract.IForgetPWPresenter {

    private ForgetPWContract.IForgetPWMode iForgetPWMode;

    public ForgetPWPresenter() {
        iForgetPWMode = new ForgetPWModel();
    }

    @Override
    public void getVerified(String string, String type) {

        iForgetPWMode.getVerified(string, type, new INetCallBack<VerifiedBean>() {
            @Override
            public void onSuccess(VerifiedBean verifiedBean) {
                mview.getVerified(verifiedBean);
            }
            @Override
            public void onError(Throwable throwable) {
            }
        });

    }

    @Override
    public void checkSmsCode(String phoneNum, String smsCode, String type) {

        iForgetPWMode.checkSmsCode(phoneNum, smsCode, type, new INetCallBack<VerifiedBean>() {
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
