package com.geektech.lovecalculatore;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavHostController;
import androidx.navigation.fragment.NavHostFragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.geektech.lovecalculatore.databinding.FragmentMainBinding;
import com.geektech.lovecalculatore.network.LoveModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainFragment extends Fragment {

    private FragmentMainBinding binding;
    private NavHostController controller;
    public static String KEY2 = "key";
    public static String FNAME = "fname";
    public static String SNAME = "sname";
    public static final String KEY = "fcf01ff652mshaec969ccd6aaceap1b5b91jsna3743dce8599";
    public static final String HOST = "love-calculator.p.rapidapi.com";

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentMainBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initClickers();
        initController();
    }

    private void initController() {
        NavHostFragment navHostController = (NavHostFragment)
                requireActivity().getSupportFragmentManager()
                        .findFragmentById(R.id.fragmentContainerView);
        assert navHostController != null;
        controller = (NavHostController) navHostController.getNavController();
    }

    private void initClickers() {
        binding.btnCalculate.setOnClickListener(v -> init());
    }

    private void init() {
        String firstName = binding.edFname.getText().toString().trim();
        String secondName = binding.edSname.getText().toString().trim();

        App.loveApi.calculate(firstName, secondName, KEY,HOST).enqueue(
                new Callback<LoveModel>() {
                    @Override
                    public void onResponse(@NonNull Call<LoveModel> call,
                                           @NonNull Response<LoveModel> response) {
                        if (response.isSuccessful()){
                            Bundle bundle = new Bundle();
                            assert response.body() != null;
                            bundle.putString(KEY2, response.body().percentage);
                            bundle.putString(FNAME, response.body().firstName);
                            bundle.putString(SNAME, response.body().secondName);
                            controller.navigate(R.id.resultFragment, bundle);
                        }
                    }

                    @Override
                    public void onFailure(@NonNull Call<LoveModel> call, @NonNull Throwable t) {
                       Log.e("ololo", "onResponse" + t.getMessage());
                    }
                });
    }
}