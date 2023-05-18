package edu.mirea.hairloo1x3.sigma.ui.main.task_menu.recyclerviewtasks;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import edu.mirea.hairloo1x3.sigma.data.data_sources.room.entities.TaskEntity;
import edu.mirea.hairloo1x3.sigma.data.repositories.TasksRepository;

public class ListTasksFragmentViewModel extends AndroidViewModel {

    private TasksRepository mRepository;
    public static String razdel;
    private final LiveData<List<TaskEntity>> mAlgebraTasks;
    private final LiveData<List<TaskEntity>> mMatanTasks;
    private final LiveData<List<TaskEntity>> mStatsTasks;
    private final LiveData<List<TaskEntity>> mLogicTasks;
    private final LiveData<List<TaskEntity>> mGeometryTasks;
    private final LiveData<List<TaskEntity>> mCombinationTasks;
    private final LiveData<List<TaskEntity>> mAllTasks;

    public ListTasksFragmentViewModel(Application application) {
        super(application);

        mRepository = new TasksRepository(application);
        mAllTasks = mRepository.getAllTasks();
        mAlgebraTasks = mRepository.getAlgebraTasks();
        mMatanTasks = mRepository.getMatanTasks();
        mStatsTasks = mRepository.getStatsTasks();
        mGeometryTasks = mRepository.getGeometryTasks();
        mLogicTasks = mRepository.getLogicTasks();
        mCombinationTasks = mRepository.getCombinationTasks();
    }

    LiveData<List<TaskEntity>> getAllTasks() {
        return mAllTasks;
    }
    LiveData<List<TaskEntity>> getStatsTasks() {
        return mStatsTasks;
    }
    LiveData<List<TaskEntity>> getLogicTasks() {
        return mLogicTasks;
    }
    LiveData<List<TaskEntity>> getMatanTasks(){
        return mMatanTasks;
    }
    LiveData<List<TaskEntity>> getCombinationTasks() {
        return mCombinationTasks;
    }
    LiveData<List<TaskEntity>> getAlgebraTasks() {
        return mAlgebraTasks;
    }
    LiveData<List<TaskEntity>> getGeometryTasks() {
        return mGeometryTasks;
    }


    void insert(TaskEntity task) {
        mRepository.insert(task);
    }
    void deleteAll() {
        mRepository.deleteAll();
    }
    void updateTaskById(int id, String status){
        mRepository.updateTaskById(id, status);
    }
    TaskEntity findTaskById(int id){
        return mRepository.findTaskById(id);
    }


}
