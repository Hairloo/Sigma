package edu.mirea.hairloo1x3.sigma.ui.main.task_menu.recyclerviewtasks;

import edu.mirea.hairloo1x3.sigma.data.data_sources.room.entities.TaskEntity;

public interface RecInterface {
    void onItemClick(int position, TaskEntity entity);
}
