package edu.mirea.hairloo1x3.sigma.ui.main.task_menu;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import edu.mirea.hairloo1x3.sigma.R;
import edu.mirea.hairloo1x3.sigma.data.data_sources.room.entities.UserEntitie;
import edu.mirea.hairloo1x3.sigma.databinding.FragmentProfileBinding;
import edu.mirea.hairloo1x3.sigma.databinding.FragmentTaskMenuBinding;
import edu.mirea.hairloo1x3.sigma.ui.main.profile.FragmentProfileViewModel;
import edu.mirea.hairloo1x3.sigma.ui.main.task_menu.recyclerviewtasks.ListTasksFragment;
import edu.mirea.hairloo1x3.sigma.ui.main.task_menu.recyclerviewtasks.ListTasksFragmentViewModel;

public class FragmentTaskMenu extends Fragment {
    FragmentTaskMenuBinding binding;
    FragmentTaskMenuViewModel viewModel;
    private UserEntitie user;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(this).get(FragmentTaskMenuViewModel.class);
        user = viewModel.getUser2();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        binding = FragmentTaskMenuBinding.inflate(inflater);
        binding.profileIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onIconClick();
            }
        });
        binding.firstTaskMenu.setOnClickListener(v -> {
            ListTasksFragmentViewModel.razdel = "Algebra";
            NavHostFragment.findNavController(this).navigate(R.id.action_fragmentTaskMenu2_to_listTasksFragment);
        });
        binding.secondTaskMenu.setOnClickListener(v -> {
            ListTasksFragmentViewModel.razdel = "Matan";
            NavHostFragment.findNavController(this).navigate(R.id.action_fragmentTaskMenu2_to_listTasksFragment);

        });
        binding.thirdTaskMenu.setOnClickListener(v -> {
            ListTasksFragmentViewModel.razdel = "Stats";
            NavHostFragment.findNavController(this).navigate(R.id.action_fragmentTaskMenu2_to_listTasksFragment);

        });
        binding.fourthTaskMenu.setOnClickListener(v -> {
            ListTasksFragmentViewModel.razdel = "Logic";
            NavHostFragment.findNavController(this).navigate(R.id.action_fragmentTaskMenu2_to_listTasksFragment);

        });
        binding.fifthTaskMenu.setOnClickListener(v -> {
            ListTasksFragmentViewModel.razdel = "Geometry";
            NavHostFragment.findNavController(this).navigate(R.id.action_fragmentTaskMenu2_to_listTasksFragment);

        });
        binding.sixthTaskMenu.setOnClickListener(v -> {
            ListTasksFragmentViewModel.razdel = "Combination";
            NavHostFragment.findNavController(this).navigate(R.id.action_fragmentTaskMenu2_to_listTasksFragment);

        });
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel.getUser().observe(getViewLifecycleOwner(), new Observer<UserEntitie>() {
            @Override
            public void onChanged(UserEntitie userEntitie) {
                user = userEntitie;
                viewModel.setUser(userEntitie);
                bind();
            }
        });
    }

    public void onIconClick(){
        NavHostFragment.findNavController(this).navigate(R.id.action_fragmentTaskMenu2_to_fragmentProfile2);
    }
    private void bind(){
        binding.profileIcon.setImageResource(viewModel.iconToRet(viewModel.retSetText()[2]));
    }
}
