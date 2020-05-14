package com.example.dell.jd.login.model;

import android.util.Log;

import com.example.dell.jd.login.contract.AffirmPassWordContract;
import com.example.dell.jd.net.INetCallBack;
import com.example.dell.jd.net.NetWorkFactory;
import com.example.dell.jd.net.ParamsUtils;
import com.example.dell.jd.net.api.URLConstants;

import java.util.HashMap;

public class AffirmPaswMode implements AffirmPassWordContract.IAffirmPaswMode {
    @Override
    public <T> void forgetPasw(String mobile, String sms_code, String password, INetCallBack<T> iNetCallBack) {


        HashMap<String, String> commonParams = ParamsUtils.getCommonParams();
        commonParams.put("mobile",mobile);
        commonParams.put("password",password);
        commonParams.put("sms_code",sms_code);

        for (String key: commonParams.keySet()) {
            Log.e("TAG","key="+key+",values="+commonParams.get(key));
        }
        NetWorkFactory.getInstance().getNetWork().post(URLConstants.FORGET_PW,commonParams,iNetCallBack);




    }
}
