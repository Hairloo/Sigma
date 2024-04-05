package edu.mirea.hairloo1x3.sigma.ui.main.profile;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import edu.mirea.hairloo1x3.sigma.databinding.FragmentStatsBinding;

public class FragmentStats extends Fragment {
    FragmentStatsBinding binding;
    //FragmentProfileViewModel viewModel;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentStatsBinding.inflate(inflater);
        return binding.getRoot();
    }
}