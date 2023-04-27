package edu.mirea.hairloo1x3.sigma.ui.main.task_menu.recyclerviewtasks;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import edu.mirea.hairloo1x3.sigma.R;
import edu.mirea.hairloo1x3.sigma.data.data_sources.room.entities.TaskEntity;


public class ListTasksViewHolder extends RecyclerView.ViewHolder {
    private final TextView name;
    private final TextView status;
    private final TextView dif;

    private ListTasksViewHolder(View itemView) {
        super(itemView);
        name = itemView.findViewById(R.id.task_name);
        status = itemView.findViewById(R.id.task_status);
        dif = itemView.findViewById(R.id.task_dif);
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                
            }
        });
    }

    public void bind(String name, String status, String dif) {
        this.name.setText(name);
        this.status.setText(status);
        this.dif.setText(dif);

    }

    static ListTasksViewHolder create(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_item_task, parent, false);
        return new ListTasksViewHolder(view);
    }
}