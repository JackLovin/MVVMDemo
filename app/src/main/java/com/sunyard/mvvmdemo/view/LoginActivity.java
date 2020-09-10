package com.sunyard.mvvmdemo.view;

import android.view.View;
import android.widget.Toast;

import com.sunyard.mvvmdemo.R;
import com.sunyard.mvvmdemo.base.BaseActivity;
import com.sunyard.mvvmdemo.databinding.ActivityMainBinding;
import com.sunyard.mvvmdemo.viewmodel.LoginModelView;

public class LoginActivity extends BaseActivity<ActivityMainBinding, LoginModelView> {

    @Override
    protected int getContentViewId() {
        return R.layout.activity_main;
    }

    @Override
    public void initData() {
        mBinding.setViewModel(mViewModel);
    }
    public  void onClick(View view){

        mViewModel.Login().observe(this, this::loadSuccess);

    }

    /**
     * 注册或登录成功
     */
    public void loadSuccess(String msg) {

        if (msg.equals("1")) {

            Toast.makeText(this, "登入成功", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "登入失败", Toast.LENGTH_SHORT).show();


        }
    }
}