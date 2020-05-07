package org.demo.schedule;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class KidTaskAdapter extends RecyclerView.Adapter<KidTaskAdapter.ViewHolder> {

    private ArrayList<Task> taskList;
    private OnTaskClickListener taskClickListener;

    public KidTaskAdapter(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    public void setOnTaskClickListener(OnTaskClickListener taskClickListener) {
        this.taskClickListener = taskClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup vg, int viewType) {
        View v = LayoutInflater.from(vg.getContext()).inflate(R.layout.row_schedule, vg, false);
        return new KidTaskAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
        final Task tache = taskList.get(position);
        holder.txt_tache.setText(tache.getName_task());
        holder.txt_hour.setText(tache.getHour());
            switch (tache.getType()) {
                case "quotidienne":
                    holder.img_act.setImageResource(R.mipmap.img_viequot);
                    break;
                case "ponctuelle":
                    holder.img_act.setImageResource(R.mipmap.img_ponct);
                    break;
                case "extrascolaire":
                    holder.img_act.setImageResource(R.mipmap.img_extra);
                    break;
                case "scolaire":
                    holder.img_act.setImageResource(R.mipmap.img_scol);
                    break;
                case "reveil":
                    holder.img_act.setImageResource(R.mipmap.img_reveil);
                    break;
                case "butoire":
                    holder.img_act.setImageResource(R.mipmap.img_butoire);
                    break;
        }
        holder.root.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(taskClickListener != null) {
                    taskClickListener.onTaskClick(tache, holder.getAdapterPosition());
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return taskList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView txt_tache;
        TextView txt_hour;
        ImageView img_act;
        View root;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            root = itemView.findViewById(R.id.root);
            txt_tache = itemView.findViewById(R.id.txt_tache);
            txt_hour = itemView.findViewById(R.id.txt_hour);
            img_act = itemView.findViewById(R.id.img_act);
        }
    }

}
