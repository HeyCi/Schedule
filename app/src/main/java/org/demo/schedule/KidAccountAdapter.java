package org.demo.schedule;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class KidAccountAdapter extends RecyclerView.Adapter<KidAccountAdapter.ViewHolder> {

    private ArrayList<User> kidList;

    public KidAccountAdapter(ArrayList<User> kidList) {
        this.kidList = kidList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup vg, int viewType) {
        View v = LayoutInflater.from(vg.getContext()).inflate(R.layout.row_kid_account, vg, false);
        return new KidAccountAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        User user = kidList.get(position);
        // holder.txt_tache.setText(user.getNom_task());
    }

    @Override
    public int getItemCount() {
        return kidList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        View layout_row;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            layout_row = itemView.findViewById(R.id.layout_row);
        }
    }

}