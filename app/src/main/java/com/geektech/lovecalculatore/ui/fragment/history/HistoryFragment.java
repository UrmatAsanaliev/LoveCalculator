package com.geektech.lovecalculatore.ui.fragment.history;

import android.annotation.SuppressLint;

import androidx.lifecycle.ViewModelProvider;

import com.geektech.lovecalculatore.R;
import com.geektech.lovecalculatore.base.BaseFragment;
import com.geektech.lovecalculatore.data.entity.historymodel.HistoryModel;
import com.geektech.lovecalculatore.data.room.LoveDao;
import com.geektech.lovecalculatore.databinding.FragmentHistoryBinding;
import com.geektech.lovecalculatore.ui.fragment.history.adapter.HistoryAdapter;
import com.geektech.lovecalculatore.ui.fragment.main.LoveViewModel;


import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class HistoryFragment extends BaseFragment<FragmentHistoryBinding> {

    @Inject
    LoveDao loveDao;
    LoveViewModel viewModel;
    private final HistoryAdapter adapter = new HistoryAdapter();

    @Override
    protected FragmentHistoryBinding bind() {
        return FragmentHistoryBinding.inflate(getLayoutInflater());
    }

    @Override
    protected void setupUI() {
        viewModel = new ViewModelProvider(requireActivity()).get(LoveViewModel.class);
        initResult();
    }

    @Override
    protected void setupObserver() {
        super.setupObserver();
        toMenu();
    }

    @SuppressLint("NotifyDataSetChanged")
    private void toMenu() {
        binding.txtMenu.setOnClickListener(v -> controller.navigate(R.id.mainFragment));
    }

    private void initResult() {
        if (getArguments() != null){
            String result = getArguments().getString("res");
            String fname = getArguments().getString("f");
            String sname = getArguments().getString("s");
            loveDao.addLove(new HistoryModel(fname, sname, result));
            adapter.addItem(loveDao.getAllLoves());
            binding.rvList.setAdapter(adapter);
        }
    }
}