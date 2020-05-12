package com.example.dell.jd.login.contract;

import com.example.dell.jd.base.BaseView;
import com.example.dell.jd.login.bean.AffirmRegisterBean;
import com.example.dell.jd.net.INetCallBack;

/**
 * 注册获取短信验证码
 */
public class AffirmContract {

        public interface IAffirmView extends BaseView {

            void registerResult(AffirmRegisterBean registerBean);
        }
        public interface IAffirmMode{
            <T> void register(String phoneNum, String password, String affirm_password, INetCallBack<T> iNetCallBack);
        }
        public interface IAffirmPresenter{
            void register(String phoneNum, String password, String affirm_password);

        }
}
