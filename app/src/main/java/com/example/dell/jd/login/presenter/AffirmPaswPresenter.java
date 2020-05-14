package com.example.dell.jd.login.presenter;

import com.example.dell.jd.base.BasePresenter;
import com.example.dell.jd.login.bean.VerifiedBean;
import com.example.dell.jd.login.contract.AffirmPassWordContract;
import com.example.dell.jd.login.model.AffirmPaswMode;
import com.example.dell.jd.net.INetCallBack;

public class AffirmPaswPresenter extends BasePresenter<AffirmPassWordContract.IAffirmPaswView> implements AffirmPassWordContract.IAffirmPaswPresenter {

    private AffirmPassWordContract.IAffirmPaswMode iAffirmPaswMode;

    public AffirmPaswPresenter() {

        iAffirmPaswMode = new AffirmPaswMode();

    }

    @Override
    public void forgetPasw(String phoneNum, String sms, String password) {

        iAffirmPaswMode.forgetPasw(phoneNum, sms, password, new INetCallBack<VerifiedBean>() {
            @Override
            public void onSuccess(VerifiedBean bean) {

                mview.registerResult(bean);
            }

            @Override
            public void onError(Throwable throwable) {

            }
        });

    }
}
