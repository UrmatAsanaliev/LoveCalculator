package com.geektech.lovecalculatore;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.geektech.lovecalculatore.databinding.FragmentResultBinding;

public class ResultFragment extends Fragment {

    private FragmentResultBinding binding;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentResultBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initResult();
    }

    private void initResult() {
        if (getArguments() != null){
            String model = getArguments().getString("key");
            binding.txtResult.setText(model);
        }
    }
}