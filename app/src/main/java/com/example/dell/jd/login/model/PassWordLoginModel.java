package com.example.dell.jd.login.model;

import android.util.Log;

import com.example.dell.jd.login.contract.PassWordLoginContract;
import com.example.dell.jd.net.INetCallBack;
import com.example.dell.jd.net.NetWorkFactory;
import com.example.dell.jd.net.ParamsUtils;
import com.example.dell.jd.net.api.URLConstants;

import java.util.HashMap;

public class PassWordLoginModel implements PassWordLoginContract.IPassWordLoginMode {
    @Override
    public <T> void passWordLogin(String userName, String password, INetCallBack<T> iNetCallBack) {



        HashMap<String, String> commonParams = ParamsUtils.getCommonParams();
        commonParams.put("username",userName);
        commonParams.put("password",password);

        for (String key: commonParams.keySet()) {
            Log.e("TAG","key="+key+",values="+commonParams.get(key));
        }
        NetWorkFactory.getInstance().getNetWork().post(URLConstants.PHONEPAWORD_LOGIN,commonParams,iNetCallBack);



    }
}
