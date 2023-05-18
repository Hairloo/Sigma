package edu.mirea.hairloo1x3.sigma.ui.main.task_menu.recyclerviewtasks;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import edu.mirea.hairloo1x3.sigma.R;
import edu.mirea.hairloo1x3.sigma.data.data_sources.room.entities.TaskEntity;
import edu.mirea.hairloo1x3.sigma.data.models.Task;


public class ListTasksViewHolder extends RecyclerView.ViewHolder {
    private final TextView name;
    private final TextView status;
    private final TextView dif;
    public static List<TaskEntity> list = new ArrayList<>();
    private ListTasksViewHolder(View itemView, RecInterface recInterface) {
        super(itemView);
        name = itemView.findViewById(R.id.task_name);
        status = itemView.findViewById(R.id.task_status);
        dif = itemView.findViewById(R.id.task_dif);
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(recInterface != null){
                    int pos = getAbsoluteAdapterPosition();
                    //if(pos != RecyclerView.NO_POSITION){
                        recInterface.onItemClick(pos, list.get(pos));
                    //}
                }
            }
        });
    }

    public void bind(TaskEntity taskEntity){
        list.add(taskEntity);
        switch(taskEntity.getTask_status().toUpperCase()){
            case "N":
                this.status.setTextColor(Color.parseColor("#D9D9D9"));
                break;
            case "C":
                this.status.setTextColor(Color.parseColor("#19EC00"));
                break;
            case "F":
                this.status.setTextColor(Color.parseColor("#FF1100"));
                break;
        }
        switch(taskEntity.getTask_dif().toUpperCase()){
            case "E":
                this.dif.setTextColor(Color.parseColor("#19EC00"));
                break;
            case "H":
                this.dif.setTextColor(Color.parseColor("#FF1100"));
                break;
            case "M":
                this.dif.setTextColor(Color.parseColor("#EFBF14"));
                break;
        }
        this.name.setText(taskEntity.getTask_name().toUpperCase());
        this.status.setText(taskEntity.getTask_status());
        this.dif.setText(taskEntity.getTask_dif().toUpperCase());
    }

    static ListTasksViewHolder create(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_item_task, parent, false);
        return new ListTasksViewHolder(view, ListTasksAdapter.recInterface);
    }
}