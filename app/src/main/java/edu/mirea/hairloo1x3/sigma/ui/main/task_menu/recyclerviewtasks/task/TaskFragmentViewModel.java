package edu.mirea.hairloo1x3.sigma.ui.main.task_menu.recyclerviewtasks.task;

import android.app.Application;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import edu.mirea.hairloo1x3.sigma.R;
import edu.mirea.hairloo1x3.sigma.data.data_sources.room.entities.TaskEntity;
import edu.mirea.hairloo1x3.sigma.data.data_sources.room.entities.UserEntitie;
import edu.mirea.hairloo1x3.sigma.data.models.User;
import edu.mirea.hairloo1x3.sigma.data.repositories.TasksRepository;
import edu.mirea.hairloo1x3.sigma.data.repositories.UserRepository;

public class TaskFragmentViewModel extends AndroidViewModel {
    public static TaskEntity entity;
    private TasksRepository tasksRepository;
    private UserRepository userRepository;
    private DatabaseReference databaseReference;
    private UserEntitie user;
    private List<String[]> map = new ArrayList<>();
    String [] setText;
    public TaskFragmentViewModel(@NonNull Application application){
        super(application);
        userRepository = new UserRepository(application);
        tasksRepository =  new TasksRepository(application);
        databaseReference = FirebaseDatabase.getInstance().getReference("Users");
        //user = userRepository.getUser();
        map.add(new String[]{"Студент", "0"});
        map.add(new String[]{"Препод", "1000"});
        map.add(new String[]{"Гаусс", "5000"});
        map.add(new String[]{"Эйнштейн", "10000"});
        map.add(new String[]{"Пифагор", "20000"});
        map.add(new String[]{"Ньютон", "30000"});
        levels();
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

    public void updateUser(UserEntitie user, int points, int idTask, boolean isCompleted){

        if(isCompleted){
            user.setPoints(user.getPoints() + points);

        }
        userRepository.updateUser(user);
    }

    public void updateTask(int id, String status){
        tasksRepository.updateTaskById(id, status);
    }
    public void setUser(UserEntitie user){
        this.user = user;
        setText = toSetText();
    }
    public UserEntitie getUser2(){
        return userRepository.getUser();
    }
    public void updateUser() {
        UserEntitie user = userRepository.getUser();
        Map<String, Object> map = new HashMap<>();
        map.put("idsFalse", user.getIdsFalse());
        map.put("idsCompleted", user.getIdsCompleted());
        map.put("points", user.getPoints());
        if(user.getEmail() != null){
            databaseReference.child(Integer.toString(user.getId())).updateChildren(map).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if (task.isSuccessful()) {
                        Log.d("User", "Данные обновлены");
                        //Toast.makeText(getContext(), "Данные обновлены", Toast.LENGTH_LONG).show();
                    } else {
                        Log.d("User", "Данные НЕ обновлены");
                        //Toast.makeText(getContext(), "Данные НЕ обновлены", Toast.LENGTH_LONG).show();
                    }
                }
            });
        }

    }


}