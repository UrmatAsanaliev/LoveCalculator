package com.geektech.lovecalculatore.ui.fragment.main;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.geektech.lovecalculatore.common.Resource;
import com.geektech.lovecalculatore.data.entity.LoveModel;
import com.geektech.lovecalculatore.data.repo.PixabayRepository;

public class LoveViewModel extends ViewModel {

    PixabayRepository repository = new PixabayRepository();

    public LiveData<Resource<LoveModel>> getModel(String firstName, String secondName) {
        return repository.getModel(firstName, secondName);
    }
}
