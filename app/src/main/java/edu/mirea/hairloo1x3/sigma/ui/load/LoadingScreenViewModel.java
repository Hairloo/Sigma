package edu.mirea.hairloo1x3.sigma.ui.load;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import edu.mirea.hairloo1x3.sigma.data.data_sources.room.entities.TaskEntity;
import edu.mirea.hairloo1x3.sigma.data.data_sources.room.entities.UserEntitie;
import edu.mirea.hairloo1x3.sigma.data.repositories.TasksRepository;
import edu.mirea.hairloo1x3.sigma.data.repositories.UserRepository;

public class LoadingScreenViewModel extends AndroidViewModel {
    private MutableLiveData<Boolean> liveData = new MutableLiveData();
    private UserRepository userRepository;
    private DatabaseReference databaseReference;
    private TasksRepository mRepository;
    public LoadingScreenViewModel(@NonNull Application application) {
        super(application);
        userRepository = new UserRepository(application);
        databaseReference = FirebaseDatabase.getInstance().getReference("Tasks").child("FirstTask");
        mRepository = new TasksRepository(application);
    }

    public LiveData<Boolean> getLiveData() {
        return liveData;
    }

    public void readDataFromDB(){
        Log.d("myLogs", "присваиваем обработчик кнопкам");
        ValueEventListener valueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                //if(list.size() > 0) list.clear();
                for(DataSnapshot ds : snapshot.getChildren()){
                    TaskEntity task = ds.getValue(TaskEntity.class);
                    Log.d("myLogs",  Integer.toString(task.getId()));
                    Log.d("Finds",  findTaskById(task.getId()) == null ? "yes" : "no");
                    //list.add(task);
                    if(findTaskById(task.getId()) == null){
                        insert(task);
                    }
                }
                //adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        };
        databaseReference.addValueEventListener(valueEventListener);
    }
    public  void user(){
        Log.d("User", userRepository.isUserExist() ? "Exist" : "Not Exist");
        if(!userRepository.isUserExist()){
            int a = (int)(Math.random() * (Integer.MAX_VALUE - 1));
            Log.d("User", "new id " + a);
            userRepository.insert(new UserEntitie(a));
        }
    }
    public void load() {

        new Thread(() -> {
            try {
                Thread.sleep(2000);
                liveData.postValue(true);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
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
