package com.geektech.lovecalculatore.ui.fragment.board;

import com.geektech.lovecalculatore.R;
import com.geektech.lovecalculatore.base.BaseFragment;
import com.geektech.lovecalculatore.databinding.FragmentBoardBinding;
import com.geektech.lovecalculatore.databinding.FragmentMainBinding;
import com.geektech.lovecalculatore.ui.fragment.board.adapter.BoardAdapter;

public class BoardFragment extends BaseFragment<FragmentBoardBinding> {

    @Override
    protected FragmentBoardBinding bind() {
        return FragmentBoardBinding.inflate(getLayoutInflater());
    }

    @Override
    protected void setupUI() {
    }

    @Override
    protected void setupObserver() {
        super.setupObserver();
        initClicker();
        initAdapter();
    }

    private void initClicker() {
        binding.btnBoard.setOnClickListener(v ->
                navigateUp());
    }

    public void navigateUp(){
        controller.navigateUp();
    }

    private void initAdapter() {
        BoardAdapter adapter = new BoardAdapter();
        binding.vpBoard.setAdapter(adapter);
    }


}