package edu.mirea.hairloo1x3.sigma.ui.load;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

import edu.mirea.hairloo1x3.sigma.data.data_sources.room.entities.UserEntitie;
import edu.mirea.hairloo1x3.sigma.data.repositories.UserRepository;

public class LoadingScreenViewModel extends AndroidViewModel {
    private MutableLiveData<Boolean> liveData = new MutableLiveData();
    private UserRepository userRepository;

    public LoadingScreenViewModel(@NonNull Application application) {
        super(application);
        userRepository = new UserRepository(application);


    }

    public LiveData<Boolean> getLiveData() {
        return liveData;
    }

    public void load() {
        Log.d("User", userRepository.isUserExist() ? "Exist" : "Not Exist");
        if(!userRepository.isUserExist()){
            userRepository.insert(new UserEntitie(1));
        }
        new Thread(() -> {
            try {
                Thread.sleep(2000);
                liveData.postValue(true);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }


}
