package edu.mirea.hairloo1x3.sigma.ui.registration;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import edu.mirea.hairloo1x3.sigma.data.data_sources.room.entities.TaskEntity;
import edu.mirea.hairloo1x3.sigma.data.data_sources.room.entities.UserEntitie;
import edu.mirea.hairloo1x3.sigma.data.models.User;
import edu.mirea.hairloo1x3.sigma.data.repositories.TasksRepository;
import edu.mirea.hairloo1x3.sigma.data.repositories.UserRepository;



public class EnterFragmentViewModel extends AndroidViewModel {
    private UserRepository userRepository;
    private TasksRepository tasksRepository;
    private UserEntitie user;
    public EnterFragmentViewModel(@NonNull Application app){
        super(app);
        userRepository = new UserRepository(app);
        tasksRepository = new TasksRepository(app);
    }
    public void updateUser(UserEntitie user){
        userRepository.updateUser(user);
    }
    public UserEntitie getUser(){
        return userRepository.getUser();
    }
    public void updateTask(int id, String status){
        tasksRepository.updateTaskById(id, status);
    }
    public void deleteUser(){
        userRepository.deleteUser();
    }
    public void insertUser(UserEntitie user){
        userRepository.insert(user);
    }


}
