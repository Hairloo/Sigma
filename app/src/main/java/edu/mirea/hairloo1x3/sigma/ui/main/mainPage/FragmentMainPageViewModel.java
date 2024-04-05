package edu.mirea.hairloo1x3.sigma.ui.main.mainPage;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import edu.mirea.hairloo1x3.sigma.R;
import edu.mirea.hairloo1x3.sigma.data.data_sources.room.entities.UserEntitie;
import edu.mirea.hairloo1x3.sigma.data.repositories.UserRepository;

public class FragmentMainPageViewModel extends AndroidViewModel {
    private UserRepository userRepository;
    private UserEntitie user;
    private List<String[]> map = new ArrayList<>();
    String[] setText;
    public FragmentMainPageViewModel(@NonNull Application application){
        super(application);
        userRepository = new UserRepository(application);
        map.add(new String[]{"Студент", "0"});
        map.add(new String[]{"Препод", "1000"});
        map.add(new String[]{"Гаусс", "5000"});
        map.add(new String[]{"Эйнштейн", "10000"});
        map.add(new String[]{"Пифагор", "20000"});
        map.add(new String[]{"Ньютон", "30000"});
        levels();

    }

    public LiveData<UserEntitie> getUser() {
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
        String [] returnStrings = new String[4];
        int points = user.getPoints();
        Log.d("Array", Arrays.toString(returnStrings));
        for(int i = 0; i < map.size(); i++){
            if(points >= Integer.parseInt(map.get(i)[1])){
                returnStrings[2] = map.get(i)[0];
                returnStrings[0] = Integer.toString(points);
                returnStrings[3] = map.get(i + 1)[0];
                returnStrings[1] = map.get(i + 1)[1];
            }
        }
        Log.d("Array", Arrays.toString(returnStrings));
        return returnStrings;
    }
    public String[] retSetText(){
        return setText;
    }
    public int iconToRet(String str){
        switch(str){
            case "Студент":
                return R.drawable.student;
            case "Препод":
                return R.drawable.prepod;
            case "Гаусс":
                return R.drawable.gausss;
            case "Эйнштейн":
                return R.drawable.einsteinger;
            case "Пифагор":
                return R.drawable.pifagorus;
            case "Ньютон":
                return R.drawable.newtone;

        }
        return 0;
    }

    public void setUser(UserEntitie user) {
        this.user = user;
        setText = toSetText();
    }
    public UserEntitie getUser2(){
        return userRepository.getUser();
    }
}
