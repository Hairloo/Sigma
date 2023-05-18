package edu.mirea.hairloo1x3.sigma.ui.main.task_menu.recyclerviewtasks;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;

import java.util.ArrayList;
import java.util.List;

import edu.mirea.hairloo1x3.sigma.data.data_sources.room.entities.TaskEntity;
import edu.mirea.hairloo1x3.sigma.data.models.Task;


public class ListTasksAdapter extends ListAdapter<TaskEntity, ListTasksViewHolder> {
    protected static RecInterface recInterface;
    //private List<TaskEntity> list = new ArrayList<>();
    public ListTasksAdapter(@NonNull DiffUtil.ItemCallback<TaskEntity> diffCallback, RecInterface recInterface) {
        super(diffCallback);
        this.recInterface = recInterface;
    }

    @Override
    public ListTasksViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return ListTasksViewHolder.create(parent);
    }

    @Override
    public void onBindViewHolder(ListTasksViewHolder holder, int position) {
        TaskEntity current = getItem(position);
        //list.add(current);
        holder.bind(current);
    }

    static class WordDiff extends DiffUtil.ItemCallback<TaskEntity> {

        @Override
        public boolean areItemsTheSame(@NonNull TaskEntity oldItem, @NonNull TaskEntity newItem) {
            return oldItem == newItem;
        }

        @Override
        public boolean areContentsTheSame(@NonNull TaskEntity oldItem, @NonNull TaskEntity newItem) {
            return oldItem.getTask_name().equals(newItem.getTask_name()) && oldItem.getTask_dif().equals(newItem.getTask_dif()) && oldItem.getTask_status().equals(newItem.getTask_status());
        }
    }
}
