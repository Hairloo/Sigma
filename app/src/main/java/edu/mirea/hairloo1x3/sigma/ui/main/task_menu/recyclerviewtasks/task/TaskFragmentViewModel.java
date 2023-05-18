package edu.mirea.hairloo1x3.sigma.ui.main.task_menu.recyclerviewtasks.task;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import edu.mirea.hairloo1x3.sigma.data.data_sources.room.entities.TaskEntity;
import edu.mirea.hairloo1x3.sigma.data.data_sources.room.entities.UserEntitie;
import edu.mirea.hairloo1x3.sigma.data.models.User;
import edu.mirea.hairloo1x3.sigma.data.repositories.TasksRepository;
import edu.mirea.hairloo1x3.sigma.data.repositories.UserRepository;

public class TaskFragmentViewModel extends AndroidViewModel {
    public static TaskEntity entity;
    private TasksRepository tasksRepository;
    private UserRepository userRepository;
    private UserEntitie user;
    public TaskFragmentViewModel(@NonNull Application application){
        super(application);
        userRepository = new UserRepository(application);
        tasksRepository =  new TasksRepository(application);
        user = userRepository.getUser();
    }
    public UserEntitie getUser(){
        return user;
    }

    public void updateUser(UserEntitie user, int points, int idTask, boolean isCompleted){

        if(isCompleted){
            user.setPoints(user.getPoints() + points);
            if(user.getIdsFalse().contains(idTask)){
                user.delFalse(idTask);
            }
            if(!user.getIdsCompleted().contains(idTask)){
                user.addCompleted(idTask);
            }

        }
        else{
            if(!user.getIdsCompleted().contains(idTask) && !user.getIdsFalse().contains(idTask)){
                user.addFalse(idTask);
            }

        }
        userRepository.updateUser(user);
    }

    public void updateTask(int id, String status){
        tasksRepository.updateTaskById(id, status);
    }


}