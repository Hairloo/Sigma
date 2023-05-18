package edu.mirea.hairloo1x3.sigma.data.repositories;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

import edu.mirea.hairloo1x3.sigma.data.data_sources.room.dao.UserDAO;
import edu.mirea.hairloo1x3.sigma.data.data_sources.room.entities.TaskEntity;
import edu.mirea.hairloo1x3.sigma.data.data_sources.room.entities.UserEntitie;
import edu.mirea.hairloo1x3.sigma.data.data_sources.room.root.AppDatabase;
import edu.mirea.hairloo1x3.sigma.data.data_sources.room.root.UserDatabase;

public class UserRepository {
    private UserDAO mUserDao;
    //private LiveData<List<TaskEntity>> mAlgebraTasks;
    public UserRepository(Application application){
        UserDatabase db = UserDatabase.getDatabase(application.getApplicationContext());
        mUserDao = db.userDAO();
    }
    public boolean isUserExist(){
        return mUserDao.isUserExist();
    }

    public void insert(UserEntitie user){
        UserDatabase.databaseWriteExecutor.execute(() ->{
            mUserDao.insert(user);
        });
    }
    public UserEntitie getUser(){
       return mUserDao.getUser();
    }

    public void updateUser(UserEntitie user){
        mUserDao.updateUser(user);
    }

//    public void insert(TaskEntity task) {
//        AppDatabase.databaseWriteExecutor.execute(() -> {
//            mTaskDao.insert(task);
//        });
//    }

}
