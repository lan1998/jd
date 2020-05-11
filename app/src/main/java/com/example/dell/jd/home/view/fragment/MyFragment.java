package com.example.dell.jd.home.view.fragment;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.dell.jd.R;
import com.example.dell.jd.base.BaseFragment;
import com.example.dell.jd.base.BasePresenter;
import com.example.dell.jd.login.view.LoginActivity;

public class MyFragment extends BaseFragment {

    private ImageView img_my;
    private LinearLayout my_integral;
    private LinearLayout my_collect;
    private LinearLayout my_information;
    private LinearLayout site_my;
    private Button login_my;

    @Override
    protected void initView(View view) {
        img_my = view.findViewById(R.id.img_my);
        my_integral = view.findViewById(R.id.my_integral);
        my_collect = view.findViewById(R.id.my_collect);
        my_information = view.findViewById(R.id.my_information);
        site_my = view.findViewById(R.id.site_my);
        login_my = view.findViewById(R.id.login_my);

//        img_my.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(getActivity(), LoginActivity.class);
//                startActivity(intent);
//            }
//        });
    }

    @Override
    public int getLayoutID() {
        return R.layout.my_fragment;
    }
    @Override
    protected BasePresenter initPresenter() {
        return null;
    }

    @Override
    protected void initLinstener() {

    }

    @Override
    protected void initData() {

    }

}
