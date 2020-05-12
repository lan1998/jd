package com.example.dell.jd.login.presenter;

import com.example.dell.jd.base.BasePresenter;
import com.example.dell.jd.login.bean.VerifiedBean;
import com.example.dell.jd.login.contract.RegisterContract;
import com.example.dell.jd.login.model.RegisterModel;
import com.example.dell.jd.net.INetCallBack;

public class RegisterPresenter extends BasePresenter<RegisterContract.IRegisterView> implements RegisterContract.IRegisterPresenter {

    private RegisterContract.IRegisterMode iRegisterMode;

    public RegisterPresenter() {
        iRegisterMode = new RegisterModel();
    }

    @Override
    public void getVerified(String string, String type) {

        iRegisterMode.getVerified(string, type, new INetCallBack<VerifiedBean>() {
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

        iRegisterMode.checkSmsCode(phoneNum, smsCode, type, new INetCallBack<VerifiedBean>() {
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
