package edu.mirea.hairloo1x3.sigma.ui.main.mainPage;

import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import edu.mirea.hairloo1x3.sigma.R;
import edu.mirea.hairloo1x3.sigma.data.data_sources.room.entities.UserEntitie;
import edu.mirea.hairloo1x3.sigma.data.repositories.UserRepository;
import edu.mirea.hairloo1x3.sigma.databinding.FragmentMainPageBinding;

public class FragmentMainPage extends Fragment  {
    FragmentMainPageBinding binding;
    FragmentMainPageViewModel viewModel;
    FirebaseDatabase firebaseDatabase;
    private UserEntitie user;
    List<Citates> citates;
    DatabaseReference databaseReference;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(this).get(FragmentMainPageViewModel.class);
        databaseReference = FirebaseDatabase.getInstance().getReference("Tasks").child("Citates");
        citates = new ArrayList<>();
        user = viewModel.getUser();
        Log.d("User", " " + user.getPoints());
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        String [] a = viewModel.retSetText();
        binding.progressTextMain.setText(a[0] + "/" +  a[1]);
        binding.progress1text.setText(a[2]);
        binding.progress2text.setText(a[3]);
//        binding.dailyTask.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                onCardClick();
//            }
//        });
        binding.profileIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onIconClick();
            }
        });
        binding.progressBar.setMax(Integer.parseInt(a[1]));
        int curProgress = Integer.parseInt(a[0]);
        ObjectAnimator.ofInt(binding.progressBar, "progress", curProgress).setDuration(1000).start();
        getCitates();
        //Log.d("myLogs", " " + citates.size());
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentMainPageBinding.inflate(inflater);

        return binding.getRoot();
    }

    private void getCitates(){
        ValueEventListener valueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                int random = (int)(Math.random() * 2);
                Log.d("myLogs", "Im here");
                if (!citates.isEmpty())citates.clear();
                for(DataSnapshot ds : snapshot.getChildren()){
                    Citates citate = ds.getValue(Citates.class);
                    assert citate != null;
                    citates.add(citate);
                    Log.d("myLogs", " " + citates.size());
                }
                binding.citate.setText(citates.get(random).description);
                binding.author.setText(citates.get(random).author);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        };
        databaseReference.addValueEventListener(valueEventListener);
    }

    public void onIconClick(){
        NavHostFragment.findNavController(this).navigate(R.id.action_fragmentMainPage2_to_fragmentProfile2);
    }
    public void onCardClick(){
        NavHostFragment.findNavController(this).navigate(R.id.action_fragmentMainPage2_to_taskFragment);
    }

}
