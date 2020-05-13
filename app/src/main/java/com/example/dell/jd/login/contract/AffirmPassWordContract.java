package com.example.dell.jd.login.contract;

import com.example.dell.jd.base.BaseView;
import com.example.dell.jd.login.bean.VerifiedBean;
import com.example.dell.jd.net.INetCallBack;

public class AffirmPassWordContract {


    public interface IAffirmPaswView extends BaseView {

        void registerResult(VerifiedBean bean);
    }
    public interface IAffirmPaswMode{
        <T> void forgetPasw(String mobile, String sms_code, String password, INetCallBack<T> iNetCallBack);
    }
    public interface IAffirmPaswPresenter{
        void forgetPasw(String mobile, String sms_code, String password);

    }
}
