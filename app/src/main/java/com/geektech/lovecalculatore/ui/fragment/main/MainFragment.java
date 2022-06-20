package com.geektech.lovecalculatore.ui.fragment.main;

import android.os.Bundle;

import androidx.lifecycle.ViewModelProvider;
import android.widget.Toast;

import com.geektech.lovecalculatore.R;
import com.geektech.lovecalculatore.base.BaseFragment;
import com.geektech.lovecalculatore.databinding.FragmentMainBinding;


public class MainFragment extends BaseFragment<FragmentMainBinding> {

    private LoveViewModel viewModel;
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