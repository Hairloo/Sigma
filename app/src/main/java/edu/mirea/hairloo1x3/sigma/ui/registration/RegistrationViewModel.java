package edu.mirea.hairloo1x3.sigma.ui.registration;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

public class RegistrationViewModel extends AndroidViewModel {
    private MutableLiveData<Boolean> liveData = new MutableLiveData<>();
    public RegistrationViewModel(@NonNull Application application){
        super(application);
    }
    public LiveData<Boolean> getLiveData(){
        return liveData;
    }
}
