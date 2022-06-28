package com.geektech.lovecalculatore.ui.fragment.main;

import android.os.Bundle;

import androidx.lifecycle.ViewModelProvider;
import android.widget.Toast;

import com.geektech.lovecalculatore.R;
import com.geektech.lovecalculatore.base.BaseFragment;
import com.geektech.lovecalculatore.data.pref.Prefs;
import com.geektech.lovecalculatore.databinding.FragmentMainBinding;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class MainFragment extends BaseFragment<FragmentMainBinding> {

    private LoveViewModel viewModel;
    @Inject
    Prefs prefs;

    public static final String KEY = "key";
    public static final String FNAME = "fname";
    public static final String SNAME = "sname";

    @Override
    protected FragmentMainBinding bind() {
        return FragmentMainBinding.inflate(getLayoutInflater());
    }

    @Override
    protected void setupUI() {
        viewModel = new ViewModelProvider(requireActivity()).get(LoveViewModel.class);
    }

    @Override
    protected void setupObserver() {
        binding.btnCalculate.setOnClickListener(view -> init());
        initBoard();
        toHistory();
    }

    private void toHistory() {
        binding.imgHistory.setOnClickListener(v -> controller.navigate(R.id.historyFragment));
    }

    private void initBoard() {
        if (!prefs.isShown()){
            toBoard();
            prefs.isShowed();
        }
    }

    private void toBoard() {
        controller.navigate(R.id.boardFragment);
    }


    private void init() {
        String first = binding.edFname.getText().toString().trim();
        String second = binding.edSname.getText().toString().trim();

        viewModel.getModel(first, second).observe(getViewLifecycleOwner(), loveModelResource -> {
            switch (loveModelResource.status) {
                case SUCCESS:
                    Bundle bundle = new Bundle();
                    bundle.putString(FNAME, first);
                    bundle.putString(SNAME, second);
                    bundle.putString(KEY, loveModelResource.data.percentage);
                    controller.navigate(R.id.resultFragment, bundle);
                    break;
                case LOADING:
                    Toast.makeText(getContext(), "Идет вычисление!", Toast.LENGTH_SHORT).show();
                    break;
                case ERROR:
                    Toast.makeText(
                            requireActivity(),
                            loveModelResource.message,
                            Toast.LENGTH_SHORT)
                            .show();
            }
        });

    }
}