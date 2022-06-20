package com.geektech.lovecalculatore;

import android.app.Application;

import com.geektech.lovecalculatore.data.network.LoveApi;
import com.geektech.lovecalculatore.data.network.RetrofitService;

public class App extends Application {

    public static LoveApi loveApi;

    @Override
    public void onCreate() {
        super.onCreate();
        RetrofitService retrofitService = new RetrofitService();
        loveApi = retrofitService.loveApi;
    }
}
