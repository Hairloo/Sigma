package edu.mirea.hairloo1x3.sigma.ui.main.task_menu.recyclerviewtasks;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;

import java.util.ArrayList;
import java.util.Comparator;
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

    public List<TaskEntity> sortByType()
    {
        List<TaskEntity> sortedList = new ArrayList<>(getCurrentList());
        //sortedList.sort(Comparator.comparing(TaskEntity::getType));
        sortedList.sort(new Comparator<TaskEntity>() {
            @Override
            public int compare(TaskEntity o1, TaskEntity o2) {
                if(o1.getTask_status().equals("C")){
                    if(o2.getTask_status().equals("C")){
                        return 0;
                    }
                    else{
                        return 1;
                    }
                }
                else if(o1.getTask_status().equals("N")){
                    if(o2.getTask_status().equals("N")){
                        return 0;
                    }
                    else if(o2.getTask_status().equals("C")){
                        return -1;
                    }
                    else{
                        return 1;
                    }
                }
                else{
                    if(o2.getTask_status().equals("F")){
                        return 0;
                    }
                    else{
                        return -1;
                    }
                }
            }
        });
        List<TaskEntity> displayOrderList = new ArrayList<>(getCurrentList());
        //notifyDataSetChanged();
        for (int i = 0; i < sortedList.size(); ++i)
        {
            int toPos = sortedList.indexOf(displayOrderList.get(i));
            notifyItemMoved(i, toPos);
            listMoveTo(displayOrderList, i, toPos);

        }
        return displayOrderList;
    }

    private void listMoveTo(List<TaskEntity> list, int fromPos, int toPos)
    {
        TaskEntity fromValue = list.get(fromPos);
        int delta = fromPos < toPos ? 1 : -1;
        for (int i = fromPos; i != toPos; i += delta) {
            list.set(i, list.get(i + delta));
        }
        list.set(toPos, fromValue);
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
