package com.sunyard.mvvmdemo.model;

import com.sunyard.mvvmdemo.RetrofitManager;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * @package name：com.sunyard.nbysb
 * @describe
 * @anthor jokerlover
 * @email:shengj.chen@sunyard.com
 * @time 2020/9/10 2:00 PM
 */
public class LoginModel {

    public void getLoginModel(String accountName, String pwd, LoginCallBack loginCallBack) {

        RetrofitManager.getInstance().loginUser(accountName, pwd).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<LoginBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        loginCallBack.addSubscription(d);
                    }

                    @Override
                    public void onNext(LoginBean loginBean) {
                        if (loginBean != null) {
                            int errorCode = loginBean.getErrorCode();
                            if (errorCode == 0) {
                                loginCallBack.loadSuccess();
                            } else {
                                loginCallBack.loadFailed(loginBean.getErrorMsg());
                            }
                        } else {
                            loginCallBack.loadFailed("登入失败");
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        loginCallBack.loadFailed(e.getLocalizedMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

   public interface LoginCallBack {
        void loadSuccess();

        void loadFailed(String msg);

        void addSubscription(Disposable disposable);

    }


}
