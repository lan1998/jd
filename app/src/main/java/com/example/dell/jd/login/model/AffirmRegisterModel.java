package com.example.dell.jd.login.model;

import com.example.dell.jd.login.contract.AffirmContract;
import com.example.dell.jd.net.INetCallBack;

public class AffirmRegisterModel implements AffirmContract.IAffirmMode {
    @Override
    public <T> void register(String phoneNum, String password, String affirm_password, INetCallBack<T> iNetCallBack) {

    }
}
