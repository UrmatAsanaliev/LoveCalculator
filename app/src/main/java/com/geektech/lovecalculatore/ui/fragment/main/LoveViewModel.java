package com.geektech.lovecalculatore.ui.fragment.main;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.geektech.lovecalculatore.common.Resource;
import com.geektech.lovecalculatore.data.entity.LoveModel;
import com.geektech.lovecalculatore.data.entity.historymodel.HistoryModel;
import com.geektech.lovecalculatore.data.pref.Prefs;
import com.geektech.lovecalculatore.data.repo.PixabayRepository;
import com.geektech.lovecalculatore.data.room.LoveDao;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class LoveViewModel extends ViewModel {

    PixabayRepository repository;

    @Inject
    public LoveViewModel(PixabayRepository repository) {
        this.repository = repository;
    }

    public LiveData<Resource<LoveModel>> getModel(String firstName, String secondName) {
        return repository.getModel(firstName, secondName);
    }
}
