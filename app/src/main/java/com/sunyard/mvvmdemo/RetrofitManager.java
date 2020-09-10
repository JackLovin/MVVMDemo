package com.sunyard.mvvmdemo;

import com.sunyard.mvvmdemo.model.LoginBean;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class RetrofitManager {

    private static final String TAG = "RetrofitManager";
    private final ApiService mApiService;
    private static final String BASE_URL = "https://www.wanandroid.com/";

    private static final int READ_TIMEOUT = 6000;
    private static final int WRITE_TIMEOUT = 6000;
    private static final int CONNECT_TIMEOUT = 6000;



    private RetrofitManager() {

        final Retrofit retrofit = getRetrofit();
        mApiService = retrofit.create(ApiService.class);
    }

    private Retrofit getRetrofit() {
        Retrofit.Builder builder = new Retrofit.Builder();
        builder.baseUrl(BASE_URL)
                .client(getInstanceOkHttpClient())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create()) ;

        return builder.build();
    }

    public static RetrofitManager getInstance() {
        if (retrofitManager == null) {
            synchronized (RetrofitManager.class) {
                if (retrofitManager == null) {
                    retrofitManager = new RetrofitManager();
                }
            }
        }
        return retrofitManager;
    }

    private static volatile RetrofitManager retrofitManager = null;
    private static volatile OkHttpClient okHttpClient = null;

    public static OkHttpClient getInstanceOkHttpClient() {
        if (okHttpClient == null) {
            synchronized (RetrofitManager.class) {
                if (okHttpClient == null) {
                    okHttpClient = new OkHttpClient.Builder()
                            .connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS)
                            .writeTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS)
                            .readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
                            .addInterceptor(new HttpLoggingInterceptor()
                                    .setLevel(HttpLoggingInterceptor.Level.BODY)
                            ).build();
                }
            }
        }
        return okHttpClient;
    }


    public Observable<LoginBean> loginUser(String name, String password) {
        return mApiService.login(name,password);

    }

}
