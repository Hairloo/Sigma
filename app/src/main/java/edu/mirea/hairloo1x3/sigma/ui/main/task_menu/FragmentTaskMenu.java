package edu.mirea.hairloo1x3.sigma.ui.main.task_menu;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import edu.mirea.hairloo1x3.sigma.R;
import edu.mirea.hairloo1x3.sigma.databinding.FragmentProfileBinding;
import edu.mirea.hairloo1x3.sigma.databinding.FragmentTaskMenuBinding;
import edu.mirea.hairloo1x3.sigma.ui.main.profile.FragmentProfileViewModel;
import edu.mirea.hairloo1x3.sigma.ui.main.task_menu.recyclerviewtasks.ListTasksFragment;
import edu.mirea.hairloo1x3.sigma.ui.main.task_menu.recyclerviewtasks.ListTasksFragmentViewModel;

public class FragmentTaskMenu extends Fragment {
    FragmentTaskMenuBinding binding;
    FragmentTaskMenuViewModel viewModel;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(this).get(FragmentTaskMenuViewModel.class);
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
    public void onIconClick(){
        NavHostFragment.findNavController(this).navigate(R.id.action_fragmentTaskMenu2_to_fragmentProfile2);
    }
}
