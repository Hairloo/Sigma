package edu.mirea.hairloo1x3.sigma.ui.main.task_menu.recyclerviewtasks;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

import edu.mirea.hairloo1x3.sigma.data.data_sources.room.entities.TaskEntity;
import edu.mirea.hairloo1x3.sigma.data.repositories.TasksRepository;

public class ListTasksFragmentViewModel extends AndroidViewModel {
    private TasksRepository mRepository;
    private final LiveData<List<TaskEntity>> mAllTasks;

    public ListTasksFragmentViewModel(Application application) {
        super(application);
        mRepository = new TasksRepository(application);
        mAllTasks = mRepository.getAllTasks();
    }

    LiveData<List<TaskEntity>> getAllTasks() {
        return mAllTasks;
    }

    void insert(TaskEntity task) {
        mRepository.insert(task);
    }

}
