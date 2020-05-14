package com.example.dell.jd.login.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.dell.jd.R;
import com.example.dell.jd.base.BaseActivity;
import com.example.dell.jd.login.bean.VerifiedBean;
import com.example.dell.jd.login.contract.AffirmPassWordContract;
import com.example.dell.jd.login.presenter.AffirmPaswPresenter;

public class AffirmPassWordActivity  extends BaseActivity<AffirmPaswPresenter> implements AffirmPassWordContract.IAffirmPaswView {
    //密码
    private EditText cell_password;
    //确认密码
    private EditText affirm_psw;
    //完成
    private Button update_but;
    private String phoneNum;
    private String verified_str;

    @Override
    protected AffirmPaswPresenter initPresenter() {
        return new AffirmPaswPresenter();
    }

    @Override
    public void initView() {


        Intent intent = getIntent();
        phoneNum = intent.getStringExtra("phoneNum");
        verified_str = intent.getStringExtra("verified_str");

        cell_password = findViewById(R.id.cell_password);
        affirm_psw = findViewById(R.id.affirm_psw);
        update_but = findViewById(R.id.update_but);
    }

    @Override
    public void initData() {
        Intent intent = getIntent();

    }

    @Override
    public void initLinstener() {
        update_but.setOnClickListener(v -> {
            String pasw_str = cell_password.getText().toString().trim();
            String apw = affirm_psw.getText().toString().trim();

            if(!TextUtils.isEmpty(pasw_str) && !TextUtils.isEmpty(apw)){
//                当前页面只有验证码，没有手机号和密码，需要上个页面传递过来
                if(pasw_str.equals(apw)){
                    mPresenter.forgetPasw(phoneNum,verified_str,pasw_str);
                }


            }else Toast.makeText(this, "密码不能为空", Toast.LENGTH_SHORT).show();


        });
    }

    @Override
    public int getLayoutID() {
        return R.layout.activity_affirm_pass_word;
    }

    @Override
    public void registerResult(VerifiedBean bean) {
        //判断短信验证码是否正确，密码是否修改成功
        if(bean.getCode()==1){
            Intent it = new Intent(AffirmPassWordActivity.this,LoginActivity.class);
            startActivity(it);
        }else {
            Toast.makeText(this, bean.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
}
