package edu.mirea.hairloo1x3.sigma.ui.main.task_menu.recyclerviewtasks;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;

import edu.mirea.hairloo1x3.sigma.data.data_sources.room.entities.TaskEntity;


public class ListTasksAdapter extends ListAdapter<TaskEntity, ListTasksViewHolder> {
    public ListTasksAdapter(@NonNull DiffUtil.ItemCallback<TaskEntity> diffCallback) {
        super(diffCallback);
    }

    @Override
    public ListTasksViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return ListTasksViewHolder.create(parent);
    }

    @Override
    public void onBindViewHolder(ListTasksViewHolder holder, int position) {
        TaskEntity current = getItem(position);
        holder.bind(current.getTaskName(), current.getTaskStatus(), current.getTaskDif());
    }

    static class WordDiff extends DiffUtil.ItemCallback<TaskEntity> {

        @Override
        public boolean areItemsTheSame(@NonNull TaskEntity oldItem, @NonNull TaskEntity newItem) {
            return oldItem == newItem;
        }

        @Override
        public boolean areContentsTheSame(@NonNull TaskEntity oldItem, @NonNull TaskEntity newItem) {
            return oldItem.getTaskName().equals(newItem.getTaskName()) && oldItem.getTaskDif().equals(newItem.getTaskDif()) && oldItem.getTaskStatus().equals(newItem.getTaskStatus()) ;
        }
    }
    public interface  Listener{
        void onClick(TaskEntity entity);
    }
}
