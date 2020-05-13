package com.example.dell.jd.login.contract;

import com.example.dell.jd.base.BaseView;
import com.example.dell.jd.login.bean.VerifiedBean;
import com.example.dell.jd.net.INetCallBack;

/**
 * 契约类
 * 契约     约定
 */
public class ForgetPWContract {

        public interface IForgetPWView extends BaseView {

            void getVerified(VerifiedBean s);

            void checkSmsCodeResult(VerifiedBean verfiedBean);

        }
        public interface IForgetPWMode{
           <T> void getVerified(String phoneNum, String type, INetCallBack<T> iNetCallBack);

            <T> void checkSmsCode(String phoneNum, String smsCode, String type, INetCallBack<T> iNetCallBack);
        }
        public interface IForgetPWPresenter{
            void getVerified(String string, String type);
            void checkSmsCode(String phoneNum, String smsCode, String type);
        }
}
