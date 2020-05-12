package com.example.dell.jd.login.presenter;

import com.example.dell.jd.base.BasePresenter;
import com.example.dell.jd.login.contract.AffirmContract;
import com.example.dell.jd.login.model.AffirmRegisterModel;

public class AffirmRegisterPresenter extends BasePresenter<AffirmContract.IAffirmView> implements AffirmContract.IAffirmPresenter {

    private AffirmContract.IAffirmMode iAffirmMode;
    public AffirmRegisterPresenter() {

        iAffirmMode = new AffirmRegisterModel();

    }

    @Override
    public void register(String phoneNum, String password, String affirm_password) {

    }
}
