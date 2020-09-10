package com.sunyard.mvvmdemo.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableField;
import androidx.lifecycle.MutableLiveData;

import com.sunyard.mvvmdemo.base.BaseViewModel;
import com.sunyard.mvvmdemo.model.LoginModel;

import io.reactivex.disposables.Disposable;

/**
 * @package name：com.sunyard.mvvmdemo
 * @describe
 * @anthor jokerlover
 * @email:shengj.chen@sunyard.com
 * @time 2020/9/10 3:28 PM
 */
public class LoginModelView extends BaseViewModel {
    LoginModel loginModel;
    private  MutableLiveData<String> data ;
   public final ObservableField<String> accountName = new ObservableField<>();
    public final ObservableField<String> accountpwd = new ObservableField<>();


    public LoginModelView(@NonNull Application application) {
        super(application);
        loginModel = new LoginModel();
        accountName.set("csj");
    }


    public MutableLiveData<String> Login() {
        data = new MutableLiveData<>();
        loginModel.getLoginModel(accountName.get(), accountpwd.get(), new LoginModel.LoginCallBack() {
            @Override
            public void loadSuccess() {
                data.setValue("1");
            }

            @Override
            public void loadFailed(String msg) {
                data.setValue(msg);
            }

            @Override
            public void addSubscription(Disposable disposable) {
                addDisposables(disposable);
            }
        });
        return  data;
    }

}
