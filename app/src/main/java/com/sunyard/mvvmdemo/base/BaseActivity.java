package com.sunyard.mvvmdemo.base;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProvider;

import com.sunyard.mvvmdemo.utils.ClassUtil;

/**
 * @package name：com.sunyard.mvvmdemo
 * @describe
 * @anthor jokerlover
 * @email:shengj.chen@sunyard.com
 * @time 2020/9/10 3:38 PM
 */
public abstract class BaseActivity<DB extends ViewDataBinding, VM extends BaseViewModel> extends FragmentActivity {


    protected DB mBinding;
    protected VM mViewModel;


    //获取当前activity布局文件,并初始化我们的binding
    protected abstract int getContentViewId();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(getContentViewId());
        mBinding = DataBindingUtil.setContentView(this, getContentViewId());
          //给binding加上感知生命周期，AppCompatActivity就是lifeOwner
        mBinding.setLifecycleOwner(this);
        //创建我们的ViewModel。
        createViewModel();
        initData();
    }

    public void createViewModel() {
        Class<VM> viewModel = ClassUtil.getViewModel(this);
        mViewModel = new ViewModelProvider(this).get(viewModel);
        //mViewModel = new MyViewModel();

    }


    public abstract void initData();
}
