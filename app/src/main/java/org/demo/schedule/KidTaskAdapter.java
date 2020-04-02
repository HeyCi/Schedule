package org.demo.schedule;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class KidTaskAdapter extends RecyclerView.Adapter<KidTaskAdapter.ViewHolder> {

    private ArrayList<Task> taskList;

    public KidTaskAdapter(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup vg, int viewType) {
        View v = LayoutInflater.from(vg.getContext()).inflate(R.layout.row_schedule, vg, false);
        return new KidTaskAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Task tache = taskList.get(position);
        holder.txt_tache.setText(tache.getName_task());
        //holder.txt_hour.setText(tache.getHour().toString());
    }

    @Override
    public int getItemCount() {
        return taskList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView txt_tache;
        TextView txt_hour;
        View root;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            root = itemView.findViewById(R.id.root);
            txt_tache = itemView.findViewById(R.id.txt_tache);
            txt_hour = itemView.findViewById(R.id.txt_hour);
        }
    }

}
