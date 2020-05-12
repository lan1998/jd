package com.example.dell.jd.login.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.dell.jd.R;
import com.example.dell.jd.base.BaseActivity;
import com.example.dell.jd.login.bean.AffirmRegisterBean;
import com.example.dell.jd.login.contract.AffirmContract;
import com.example.dell.jd.login.presenter.AffirmRegisterPresenter;

/**
 * 确认注册
 */
public class AffirmRegisterActivity extends BaseActivity<AffirmRegisterPresenter> implements AffirmContract.IAffirmView {

    private EditText affreg_passward;
    private EditText affreg_affirmpassword;
    private Button arrirm_regbug;
    private String phoneNum;

    @Override
    protected AffirmRegisterPresenter initPresenter() {
        return new AffirmRegisterPresenter();
    }

    @Override
    public void initView() {

        affreg_passward = findViewById(R.id.affreg_passward);
        affreg_affirmpassword = findViewById(R.id.affreg_affirmpassword);
        arrirm_regbug  = findViewById(R.id.arrirm_regbug);
    }

    @Override
    public void initData() {
        Intent intent = getIntent();
        phoneNum = intent.getStringExtra("phoneNum");
    }

    @Override
    public void initLinstener() {
        arrirm_regbug.setOnClickListener(v ->{

            String ed_affreg_passward = affreg_passward.getText().toString().trim();
            String ed_affreg_affirmpassword = affreg_affirmpassword.getText().toString().trim();
            //判断密码长度是否大于6位，两次密码输入是否相同
            if(!TextUtils.isEmpty(ed_affreg_passward) && !TextUtils.isEmpty(ed_affreg_affirmpassword)){
                if(ed_affreg_passward.equals(ed_affreg_affirmpassword) && ed_affreg_passward.length()>6){
                    mPresenter.register(phoneNum,ed_affreg_passward,ed_affreg_affirmpassword);
                }else {
                    Log.e("TAG","密码长度必须大于6位，两次密码输入必须相同");
                }
            }else {
                Toast.makeText(this, "密码不能为空", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getLayoutID() {
        return R.layout.activity_affirm_register;
    }

    @Override
    public void registerResult(AffirmRegisterBean registerBean) {

    }
}