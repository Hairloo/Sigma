package edu.mirea.hairloo1x3.sigma.data.repositories;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

import edu.mirea.hairloo1x3.sigma.data.data_sources.room.dao.TaskDAO;
import edu.mirea.hairloo1x3.sigma.data.data_sources.room.entities.TaskEntity;
import edu.mirea.hairloo1x3.sigma.data.data_sources.room.root.AppDatabase;

public class TasksRepository {
    private TaskDAO mTaskDao ;
    private LiveData<List<TaskEntity>> mAllTasks;

    public TasksRepository(Application application) {
        AppDatabase db = AppDatabase.getDatabase(application);
        mTaskDao = db.taskDAO();
        mAllTasks = mTaskDao.getAllTasks();
    }

    public LiveData<List<TaskEntity>> getAllTasks() {
        return mAllTasks;
    }


    public void insert(TaskEntity word) {
        AppDatabase.databaseWriteExecutor.execute(() -> {
            mTaskDao.insert(word);
        });
    }
}
