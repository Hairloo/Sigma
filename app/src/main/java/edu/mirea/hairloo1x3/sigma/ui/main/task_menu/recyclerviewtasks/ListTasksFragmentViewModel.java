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
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import edu.mirea.hairloo1x3.sigma.R;
import edu.mirea.hairloo1x3.sigma.data.data_sources.room.entities.TaskEntity;
import edu.mirea.hairloo1x3.sigma.data.data_sources.room.entities.UserEntitie;
import edu.mirea.hairloo1x3.sigma.data.repositories.TasksRepository;
import edu.mirea.hairloo1x3.sigma.data.repositories.UserRepository;

public class ListTasksFragmentViewModel extends AndroidViewModel {
    private UserRepository userRepository;
    private TasksRepository mRepository;
    private UserEntitie user;
    public static String razdel;
    private List<String[]> map = new ArrayList<>();
    String [] setText;
    private final LiveData<List<TaskEntity>> mAlgebraTasks;
    private final LiveData<List<TaskEntity>> mMatanTasks;
    private final LiveData<List<TaskEntity>> mStatsTasks;
    private final LiveData<List<TaskEntity>> mLogicTasks;
    private final LiveData<List<TaskEntity>> mGeometryTasks;
    private final LiveData<List<TaskEntity>> mCombinationTasks;
    private final LiveData<List<TaskEntity>> mAllTasks;

    public ListTasksFragmentViewModel(Application application) {
        super(application);
        userRepository = new UserRepository(application);
        mRepository = new TasksRepository(application);
        mAllTasks = mRepository.getAllTasks();
        mAlgebraTasks = mRepository.getAlgebraTasks();
        mMatanTasks = mRepository.getMatanTasks();
        mStatsTasks = mRepository.getStatsTasks();
        mGeometryTasks = mRepository.getGeometryTasks();
        mLogicTasks = mRepository.getLogicTasks();
        mCombinationTasks = mRepository.getCombinationTasks();
        map.add(new String[]{"Студент", "0"});
        map.add(new String[]{"Препод", "1000"});
        map.add(new String[]{"Гаусс", "5000"});
        map.add(new String[]{"Эйнштейн", "10000"});
        map.add(new String[]{"Пифагор", "20000"});
        map.add(new String[]{"Ньютон", "30000"});
        levels();
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

    public LiveData<UserEntitie> getUser(){
        return userRepository.getUser2();
    }
    private void levels(){
        map.sort(new Comparator<String[]>() {
            @Override
            public int compare(String[] o1, String[] o2) {
                return Integer.parseInt(o1[1]) - Integer.parseInt(o2[1]);
            }
        });
    }
    private String [] toSetText(){
        String[] returnStrings = new String[4];
        int points = user.getPoints();
        Log.d("Array", Arrays.toString(returnStrings));
        for(int i = 0; i < map.size(); i++){
            if(points >= Integer.parseInt(map.get(i)[1])){
                returnStrings[2] = map.get(i)[0];
                returnStrings[0] = map.get(i)[1];
                returnStrings[3] = map.get(i + 1)[0];
                returnStrings[1] = map.get(i + 1)[1];
            }
        }
        Log.d("Array", Arrays.toString(returnStrings));
        return returnStrings;
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
    public int iconToRet(String str){
        switch(str){
            case "Студент":
                return R.drawable.student1;
            case "Препод":
                return R.drawable.prepod1;
            case "Гаусс":
                return R.drawable.gausss1;
            case "Эйнштейн":
                return R.drawable.einsteinger1;
            case "Пифагор":
                return R.drawable.pifagorus1;
            case "Ньютон":
                return R.drawable.newtone1;

        }
        return 0;
    }
    public String[] retSetText(){
        return setText;
    }

    public void setUser(UserEntitie user){
        this.user = user;
        setText = toSetText();
    }
    public void updateUser(UserEntitie user){
        userRepository.updateUser(user);
    }
    public UserEntitie getUser2(){
        return userRepository.getUser();
    }

}
