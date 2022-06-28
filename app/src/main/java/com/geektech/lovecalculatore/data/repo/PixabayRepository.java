package com.geektech.lovecalculatore.data.repo;


import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.geektech.lovecalculatore.App;
import com.geektech.lovecalculatore.common.Resource;
import com.geektech.lovecalculatore.data.entity.LoveModel;
import com.geektech.lovecalculatore.data.entity.historymodel.HistoryModel;
import com.geektech.lovecalculatore.data.network.LoveApi;
import com.geektech.lovecalculatore.data.pref.Prefs;
import com.geektech.lovecalculatore.data.room.LoveDao;
import com.geektech.lovecalculatore.ui.fragment.history.adapter.HistoryAdapter;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PixabayRepository {

    public static final String KEY = "fcf01ff652mshaec969ccd6aaceap1b5b91jsna3743dce8599";
    public static final String HOST = "love-calculator.p.rapidapi.com";
    LoveApi api;

    @Inject
    public PixabayRepository(LoveApi api) {
        this.api = api;
    }

    public MutableLiveData<Resource<LoveModel>> getModel(String firstName, String secondName) {
        MutableLiveData<Resource<LoveModel>> liveData = new MutableLiveData<>();
        liveData.setValue(Resource.loading());
        api.calculate(firstName, secondName, KEY, HOST).enqueue(new Callback<LoveModel>() {
            @Override
            public void onResponse(@NonNull Call<LoveModel> call,
                                   @NonNull Response<LoveModel> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        liveData.setValue(Resource.success(response.body()));
                    }
                }
            }

            @Override
            public void onFailure(@NonNull Call<LoveModel> call, @NonNull Throwable t) {
                liveData.setValue(Resource.error(t.getMessage()));
            }
        });
        return liveData;
    }
}