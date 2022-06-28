package com.geektech.lovecalculatore.ui.fragment.board.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.geektech.lovecalculatore.R;

public class BoardAdapter extends RecyclerView.Adapter<BoardAdapter.ViewHolder> {

    private final int[] img = {
            R.drawable.img_2,
            R.drawable.img_3,
            R.drawable.img_4,
            R.drawable.img_5
    };

    private final  String[]  title = {
            "Have a good time",
            "Cherishing love",
            "Have a breakup?",
            ""
    };

    private final String[] desc = {
            "You should take the time to help those who need you",
            "It is now no longer possible for you to cherish love",
            "We have made the correction for you don't worry" +
                    "Maybe someone is waiting for you!",
            "It's Funs and Mary more"
    };


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_vp,
                parent,
                false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.onBind(img[position], title[position], desc[position]);
    }

    @Override
    public int getItemCount() {
        return img.length;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        private final TextView title, desc;
        private final ImageView img;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.vp_text);
            desc = itemView.findViewById(R.id.vp_text2);
            img = itemView.findViewById(R.id.vp_img);
        }

        public void onBind(int img, String title, String desc) {
            this.title.setText(title);
            this.desc.setText(desc);
            this.img.setImageResource(img);
        }
    }
}
