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
        binding.firstTaskMenu.setOnClickListener(v -> {
            NavHostFragment.findNavController(this).navigate(R.id.action_fragmentTaskMenu2_to_listTasksFragment);
        });
        binding.secondTaskMenu.setOnClickListener(v -> {
            NavHostFragment.findNavController(this).navigate(R.id.action_fragmentTaskMenu2_to_listTasksFragment);

        });
        binding.thirdTaskMenu.setOnClickListener(v -> {
            NavHostFragment.findNavController(this).navigate(R.id.action_fragmentTaskMenu2_to_listTasksFragment);

        });
        binding.fourthTaskMenu.setOnClickListener(v -> {
            NavHostFragment.findNavController(this).navigate(R.id.action_fragmentTaskMenu2_to_listTasksFragment);

        });
        binding.fifthTaskMenu.setOnClickListener(v -> {
            NavHostFragment.findNavController(this).navigate(R.id.action_fragmentTaskMenu2_to_listTasksFragment);

        });
        binding.sixthTaskMenu.setOnClickListener(v -> {
            NavHostFragment.findNavController(this).navigate(R.id.action_fragmentTaskMenu2_to_listTasksFragment);

        });
        return binding.getRoot();
    }
}
