package com.geektech.lovecalculatore.ui.fragment.history.adapter;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.geektech.lovecalculatore.data.entity.historymodel.HistoryModel;
import com.geektech.lovecalculatore.data.room.LoveDao;
import com.geektech.lovecalculatore.data.room.LoveDataBase;
import com.geektech.lovecalculatore.databinding.ItemHistoryBinding;

import java.util.ArrayList;
import java.util.List;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.ViewHolder> {

    private List<HistoryModel> list = new ArrayList<>();

    @SuppressLint("NotifyDataSetChanged")
    public void addItem(List<HistoryModel> list){
        this.list.clear();
        this.list.addAll(list);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemHistoryBinding binding = ItemHistoryBinding.inflate(LayoutInflater.from(parent.getContext()),
                parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.binding.txtMe.setText(list.get(position).getFirstName());
        holder.binding.txtYou.setText(list.get(position).getSecondName());
        holder.binding.txtResultHistory.setText(list.get(position).getResult());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        private final ItemHistoryBinding binding;
        public ViewHolder(ItemHistoryBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
