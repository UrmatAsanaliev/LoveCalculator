package com.geektech.lovecalculatore.ui.fragment.result;

import static com.geektech.lovecalculatore.ui.fragment.main.MainFragment.FNAME;
import static com.geektech.lovecalculatore.ui.fragment.main.MainFragment.KEY;
import static com.geektech.lovecalculatore.ui.fragment.main.MainFragment.SNAME;

import android.os.Bundle;

import androidx.recyclerview.widget.RecyclerView;

import com.geektech.lovecalculatore.R;
import com.geektech.lovecalculatore.base.BaseFragment;
import com.geektech.lovecalculatore.data.entity.historymodel.HistoryModel;
import com.geektech.lovecalculatore.data.room.LoveDao;
import com.geektech.lovecalculatore.databinding.FragmentResultBinding;
import com.geektech.lovecalculatore.ui.fragment.history.adapter.HistoryAdapter;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
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

           binding.imgHistory.setOnClickListener(v -> {
              Bundle bundle = new Bundle();
              bundle.putString("res", result);
              bundle.putString("f", fName);
              bundle.putString("s", sName);

              controller.navigate(R.id.historyFragment, bundle);
         });
       }
    }
}