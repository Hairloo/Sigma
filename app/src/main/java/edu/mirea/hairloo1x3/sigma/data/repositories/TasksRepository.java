package edu.mirea.hairloo1x3.sigma.data.repositories;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.LiveData;

import java.util.List;

import edu.mirea.hairloo1x3.sigma.data.data_sources.room.dao.TaskDAO;
import edu.mirea.hairloo1x3.sigma.data.data_sources.room.entities.TaskEntity;
import edu.mirea.hairloo1x3.sigma.data.data_sources.room.root.AppDatabase;

public class TasksRepository {
    private TaskDAO mTaskDao ;
    private LiveData<List<TaskEntity>> mAllTasks;
    private LiveData<List<TaskEntity>> mAlgebraTasks;
    private LiveData<List<TaskEntity>> mMatanTasks;
    private LiveData<List<TaskEntity>> mLogicTasks;
    private LiveData<List<TaskEntity>> mGeometryTasks;
    private LiveData<List<TaskEntity>> mStatsTasks;
    private LiveData<List<TaskEntity>> mCombinationTasks;

    public TasksRepository(Application application) {
        AppDatabase db = AppDatabase.getDatabase(application.getApplicationContext());
        mTaskDao = db.taskDAO();
        mAllTasks = mTaskDao.getAllTasks();
        mAlgebraTasks = mTaskDao.getAlgebraTasks();
        mCombinationTasks = mTaskDao.getCombinationTasks();
        mStatsTasks = mTaskDao.getStatsTasks();
        mLogicTasks = mTaskDao.getLogicTasks();
        mGeometryTasks = mTaskDao.getGeometryTasks();
        mMatanTasks = mTaskDao.getMatanTasks();
    }
    public LiveData<List<TaskEntity>> getAlgebraTasks() {
        return mAlgebraTasks;
    }
    public LiveData<List<TaskEntity>> getStatsTasks() {
        return mStatsTasks;
    }
    public LiveData<List<TaskEntity>> getMatanTasks() {
        return mMatanTasks;
    }
    public LiveData<List<TaskEntity>> getLogicTasks() {
        return mLogicTasks;
    }
    public LiveData<List<TaskEntity>> getGeometryTasks() {
        return mGeometryTasks;
    }
    public LiveData<List<TaskEntity>> getCombinationTasks() {
        return mCombinationTasks;
    }

    public LiveData<List<TaskEntity>> getAllTasks() {
        return mAllTasks;
    }


    public void insert(TaskEntity task) {
        AppDatabase.databaseWriteExecutor.execute(() -> {
                mTaskDao.insert(task);
        });
    }

    public void deleteAll() {
        AppDatabase.databaseWriteExecutor.execute(() -> {
            mTaskDao.deleteAll();
        });
    }

    public TaskEntity findTaskById(int id){
        return mTaskDao.findTaskById(id);
    }

    public void updateTaskById(int id, String status){
        new Thread(new Runnable() {
            @Override
            public void run() {
                TaskEntity task = mTaskDao.findTaskById(id);
                task.setTask_status(status);
                mTaskDao.updateTaskById(task);
            }
        }).start();
    }



}
