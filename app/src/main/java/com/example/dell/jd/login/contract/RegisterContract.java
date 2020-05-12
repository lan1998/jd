package com.example.dell.jd.login.contract;

import com.example.dell.jd.base.BaseView;
import com.example.dell.jd.login.bean.VerifiedBean;
import com.example.dell.jd.net.INetCallBack;

/**
 * 注册获取短信验证码
 */
public class RegisterContract {

        public interface IRegisterView extends BaseView {

            void getVerified(VerifiedBean s);

            void  getLoginResult(String string);

            void checkSmsCodeResult(VerifiedBean verfiedBean);

        }
        public interface IRegisterMode{
            <T> void getVerified(String phoneNum, String type, INetCallBack<T> iNetCallBack);
            <T> void checkSmsCode(String phoneNum, String smsCode, String type, INetCallBack<T> iNetCallBack);
        }
        public interface IRegisterPresenter{
            void getVerified(String string, String type);

            void checkSmsCode(String phoneNum, String smsCode, String type);
        }
}
