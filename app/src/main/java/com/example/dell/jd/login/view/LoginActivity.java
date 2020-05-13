package com.example.dell.jd.login.view;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dell.jd.R;
import com.example.dell.jd.base.BaseActivity;
import com.example.dell.jd.base.BasePresenter;
import com.example.dell.jd.login.bean.VerifiedBean;
import com.example.dell.jd.login.contract.LoginContract;
import com.example.dell.jd.login.presenter.LoginPresenter;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LoginActivity extends BaseActivity<LoginPresenter> implements LoginContract.ILoginView {

    /**
     * 手机号
     */
    private EditText phone_num;
    /**
     * 验证码
     */
    private EditText verified;
    /**
     * 获取验证码
     */
    private TextView send_verified_bug;
    /**
     * 登录
     */
    private Button login;
    /**
     * 立即注册
     */
    private TextView register_but;
    /**
     * 密码登录
     */
    private TextView password_login;

    private   String edit_phone_num;
    private String edit_sms_code;

    @Override
    protected LoginPresenter initPresenter() {
        return new LoginPresenter();
    }

    @Override
    public void initView() {

        phone_num =  findViewById(R.id.phone_num);
        verified =  findViewById(R.id.verified);
        send_verified_bug =  findViewById(R.id.send_verified_bug);
        login =  findViewById(R.id.login_but);
        register_but =  findViewById(R.id.register_but);
        password_login =  findViewById(R.id.password_login);

    }

    @Override
    public void initData() {

    }

    @Override
    public void initLinstener() {

        //发送验证码
        send_verified_bug.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //判断手机号是否为空，判断手机号是否正确
                String phonenum = phone_num.getText().toString();
                if (!TextUtils.isEmpty(phonenum) && isMobileNO(phonenum)) {
                  //发送验证码
                    mPresenter.getVerified(phonenum, "4");
                } else Toast.makeText(LoginActivity.this, "请输入正确得手机号", Toast.LENGTH_SHORT).show();
            }
        });

        //登录
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edit_phone_num = phone_num.getText().toString();
                edit_sms_code = verified.getText().toString();
                if (!TextUtils.isEmpty(edit_phone_num) && isMobileNO(edit_phone_num)) {
                    if (!TextUtils.isEmpty(edit_sms_code)) {
                        //用正则表达式判断验证码是否都是数字并且是6位
                        Pattern pattern = Pattern.compile("\\d{6}");
                        boolean matches = pattern.matcher(edit_sms_code).matches();
                        if (matches) {
                            //判断手机号，和验证码是否正确，如果正确，调用登录接口
                            //提交服务器进行判读
                            Log.e("TAG", edit_phone_num + "验证码值：" + edit_sms_code);

                            mPresenter.checkSmsCode(edit_phone_num, edit_sms_code, "4");
                            //服务器给我们下发得验证码短信，接收     手机号给他，验证码也给他，
                            //                    ture
                            //如果不正确，提示用户
                        } else
                            Toast.makeText(LoginActivity.this, "验证码输入错误", Toast.LENGTH_SHORT).show();
                    } else Toast.makeText(LoginActivity.this, "请输入验证码", Toast.LENGTH_SHORT).show();
                } else Toast.makeText(LoginActivity.this, "请输入正确得手机号", Toast.LENGTH_SHORT).show();
            }
        });
    }
    @Override
    public int getLayoutID() {
        return R.layout.activity_login;
    }

    //    发送验证码返回
    @Override
    public void getVerified(VerifiedBean s) {
        if(s.getCode() ==1){
            Toast.makeText(this, "成功", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this, "错误", Toast.LENGTH_SHORT).show();
        }
    }
    //    验证码是否正确返回
    @Override
    public void checkSmsCodeResult(VerifiedBean verfiedBean) {

        if(verfiedBean.getCode() ==1){
            mPresenter.login(edit_phone_num,edit_sms_code);
        }else Toast.makeText(this, "验证码输入错误", Toast.LENGTH_SHORT).show();
    }

    public void startRegister(View view) {
//        Intent intent = new Intent(this,RegisterMSMCodeActivity.class);
//        startActivity(intent);
    }

    @Override
    public void getLoginResult(String string) {

    }

    /**
     * 验证手机号码
     * @param mobiles
     * @return
     */

    public static boolean isMobileNO(String mobiles){
        boolean flag = false;
        try{
            Pattern p = Pattern.compile("^((13[0-9])|(15[^4,\\D])|(18[0,5-9])|(17[0-9]))\\d{8}$");
            Matcher m = p.matcher(mobiles);
            flag = m.matches();
        }catch(Exception e){
            Log.e("TAG","手机号错误"+e.getMessage());
            flag = false;
        }
        return flag;
    }
}
