package edu.mirea.hairloo1x3.sigma.ui.registration;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import edu.mirea.hairloo1x3.sigma.data.data_sources.room.entities.UserEntitie;
import edu.mirea.hairloo1x3.sigma.data.repositories.UserRepository;

public class RegistrationViewModel extends AndroidViewModel {
    private UserEntitie user;
    private UserRepository userRepository;
    private MutableLiveData<Boolean> liveData = new MutableLiveData<>();
    public RegistrationViewModel(@NonNull Application application){
        super(application);
        userRepository = new UserRepository(application);
    }
    public LiveData<Boolean> getLiveData(){
        return liveData;
    }

    public UserEntitie getUser() {
        return userRepository.getUser();
    }
    public void updateUser(UserEntitie user){
        userRepository.deleteUser();
        userRepository.insert(user);
    }
}
