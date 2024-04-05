package edu.mirea.hairloo1x3.sigma.ui.main;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

import edu.mirea.hairloo1x3.sigma.R;
import edu.mirea.hairloo1x3.sigma.data.data_sources.room.entities.UserEntitie;
import edu.mirea.hairloo1x3.sigma.data.repositories.UserRepository;
import edu.mirea.hairloo1x3.sigma.databinding.FragmentMainScreenBinding;

public class MainFragment extends Fragment {
    FragmentMainScreenBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentMainScreenBinding.inflate(inflater);
        binding.navigationMainPage.setItemIconTintList(null);

        return binding.getRoot();

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        NavHostFragment navHostFragment = (NavHostFragment) getChildFragmentManager().findFragmentById(R.id.upper_info);
        if (navHostFragment != null){
            NavController navController = navHostFragment.getNavController();
            BottomNavigationView navigationBar = binding.navigationMainPage;
            //navigationBar.setVisibility(View.GONE);
            NavigationUI.setupWithNavController(navigationBar, navController);
//            navController.addOnDestinationChangedListener(new NavController.OnDestinationChangedListener() {
//                @Override
//                public void onDestinationChanged(@NonNull NavController controller, @NonNull NavDestination destination, @Nullable Bundle arguments) {
//                    if(destination.getId() == R.id.main_list_of_app || destination.getId() == R.id.favourites_of_character || destination.getId() == R.id.stock_search) {
//                        binding.navView.setVisibility(View.VISIBLE);
//                    } else {
//                        binding.navView.setVisibility(View.GONE);
//                    }
//                }
//            });
            navController.addOnDestinationChangedListener(new NavController.OnDestinationChangedListener() {
                @Override
                public void onDestinationChanged(@NonNull NavController navController, @NonNull NavDestination navDestination, @Nullable Bundle bundle) {
                    if(navDestination.getId() == R.id.enterFragment || navDestination.getId() == R.id.registrationFragment || navDestination.getId() == R.id.listTasksFragment || R.id.taskFragment == navDestination.getId()){
                        binding.navigationMainPage.setVisibility(View.GONE);
                        binding.line.setVisibility(View.GONE);
                    }
                    else{
                        binding.navigationMainPage.setVisibility(View.VISIBLE);
                        binding.line.setVisibility(View.VISIBLE);
                    }
                }
            });
        }
    }

//    @Override
//    public void onDestroy() {
//        super.onDestroy();
//        //updateUser();
//    }
//    public void updateUser() {
//        UserEntitie user = userRepository.getUser();
//        Map<String, Object> map = new HashMap<>();
//        map.put("idsFalse", user.getIdsFalse());
//        map.put("idsCompleted", user.getIdsCompleted());
//        databaseReference.child(user.getEmail()).updateChildren(map).addOnCompleteListener(new OnCompleteListener<Void>() {
//            @Override
//            public void onComplete(@NonNull Task<Void> task) {
//                if (task.isSuccessful()) {
//                    Toast.makeText(getContext(), "Данные обновлены", Toast.LENGTH_LONG).show();
//                } else {
//                    Toast.makeText(getContext(), "Данные НЕ обновлены", Toast.LENGTH_LONG).show();
//                }
//            }
//        });
//    }
}
