package com.example.dell.jd.login.contract;

import com.example.dell.jd.base.BaseView;
import com.example.dell.jd.login.bean.VerifiedBean;
import com.example.dell.jd.net.INetCallBack;

public class LoginContract {

        public interface ILoginView extends BaseView {

            void getVerified(VerifiedBean s);

            void  getLoginResult(String string);

            void checkSmsCodeResult(VerifiedBean verifiedBean);

        }
        public interface ILoginMode{
           <T> void getVerified(String phoneNum, String type, INetCallBack<T> iNetCallBack);

            <T> void login(String mobile, String smsCode, INetCallBack<T> iNetCallBack);
            <T> void checkSmsCode(String phoneNum, String smsCode, String type, INetCallBack<T> iNetCallBack);
        }
        public interface ILoginPresenter{
            void getVerified(String string, String type);
            void login(String mobile, String smsCode);
            void checkSmsCode(String phoneNum, String smsCode, String type);
        }
}
