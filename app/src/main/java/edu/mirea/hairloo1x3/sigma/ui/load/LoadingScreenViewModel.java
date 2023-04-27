package edu.mirea.hairloo1x3.sigma.ui.load;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

public class LoadingScreenViewModel extends AndroidViewModel {
    private MutableLiveData<Boolean> liveData = new MutableLiveData();
    public LoadingScreenViewModel(@NonNull Application application) {
        super(application);
    }

    public LiveData<Boolean> getLiveData() {
        return liveData;
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
}
