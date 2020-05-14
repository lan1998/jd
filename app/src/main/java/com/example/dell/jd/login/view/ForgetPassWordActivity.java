package com.example.dell.jd.login.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dell.jd.R;
import com.example.dell.jd.base.BaseActivity;
import com.example.dell.jd.login.bean.VerifiedBean;
import com.example.dell.jd.login.contract.ForgetPWContract;
import com.example.dell.jd.login.presenter.ForgetPWPresenter;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ForgetPassWordActivity extends BaseActivity<ForgetPWPresenter> implements ForgetPWContract.IForgetPWView {

    private String phoneNum;
    private EditText cell_phone_num;//手机号
    private EditText import_verified;//输入验证码
    private TextView verified_get;//获取验证码

    private Button next_but;//下一步
    private String verified_str;


    @Override
    protected ForgetPWPresenter initPresenter() {
        return new ForgetPWPresenter();
    }

    @Override
    public void initView() {

        Intent intent = getIntent();

        phoneNum = intent.getStringExtra("phoneNum");

        cell_phone_num = findViewById(R.id.cell_phone_num);
        import_verified = findViewById(R.id.import_verified);//输入验证码
        verified_get= findViewById(R.id.verified_get);//获取验证码
        next_but= findViewById(R.id.next_but);//下一步



        cell_phone_num.setText(phoneNum);

    }

    @Override
    public void initData() {

    }

    @Override
    public void initLinstener() {

        next_but.setOnClickListener(v -> {

            verified_str = import_verified.getText().toString().trim();

            if(!TextUtils.isEmpty(phoneNum) && !TextUtils.isEmpty(verified_str)){

                mPresenter.checkSmsCode(phoneNum, verified_str,"2");

            }else{
                Toast.makeText(this, "手机号和验证码不能为空", Toast.LENGTH_SHORT).show();
            }


        });


        //发送验证码
        verified_get.setOnClickListener(v -> {
//          判断手机号是否正确
            if(!TextUtils.isEmpty(phoneNum)){
                if (isMobileNO(phoneNum))
                mPresenter.getVerified(phoneNum,"2");
            }else{
                Toast.makeText(this, "手机号不能为空", Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public int getLayoutID() {
        return R.layout.activity_forget_pass_word;
    }

    @Override
    public void getVerified(VerifiedBean bean) {

        if(bean.getCode() ==1) Toast.makeText(this, "验证码发送成功", Toast.LENGTH_SHORT).show();
        else Toast.makeText(this, "验证码发送失败", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void checkSmsCodeResult(VerifiedBean verifiedBean) {

        if(verifiedBean.getCode()==1){
//            验证成功,跳转页面

            Intent it = new Intent(ForgetPassWordActivity.this,AffirmPassWordActivity.class);
//            发送验证码和手机号
            it.putExtra("phoneNum",phoneNum);
            it.putExtra("verified_str",verified_str);
            startActivity(it);
        }
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
