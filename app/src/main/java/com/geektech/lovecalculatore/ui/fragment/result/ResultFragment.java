package com.geektech.lovecalculatore.ui.fragment.result;

import static com.geektech.lovecalculatore.ui.fragment.main.MainFragment.FNAME;
import static com.geektech.lovecalculatore.ui.fragment.main.MainFragment.KEY;
import static com.geektech.lovecalculatore.ui.fragment.main.MainFragment.SNAME;

import com.geektech.lovecalculatore.base.BaseFragment;
import com.geektech.lovecalculatore.databinding.FragmentResultBinding;

public class ResultFragment extends BaseFragment<FragmentResultBinding> {

    @Override
    protected FragmentResultBinding bind() {
        return FragmentResultBinding.inflate(getLayoutInflater());
    }

    @Override
    protected void setupUI() {
        initResult();
    }

    private void initResult() {
       if(getArguments() != null){
           String result = getArguments().getString(KEY);
           String fName = getArguments().getString(FNAME);
           String sName = getArguments().getString(SNAME);

           binding.txtResult.setText(result);
           binding.txtFname.setText(fName);
           binding.txtSname.setText(sName);
       }
    }
}