package edu.mirea.hairloo1x3.sigma.ui.main.profile;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import edu.mirea.hairloo1x3.sigma.MainActivity;
import edu.mirea.hairloo1x3.sigma.R;
import edu.mirea.hairloo1x3.sigma.data.data_sources.room.entities.UserEntitie;
import edu.mirea.hairloo1x3.sigma.data.repositories.UserRepository;
import edu.mirea.hairloo1x3.sigma.databinding.FragmentMainPageBinding;
import edu.mirea.hairloo1x3.sigma.databinding.FragmentProfileBinding;
import edu.mirea.hairloo1x3.sigma.ui.main.mainPage.FragmentMainPageViewModel;

public class FragmentProfile extends Fragment {
    FragmentProfileBinding binding;
    FragmentProfileViewModel viewModel;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(this).get(FragmentProfileViewModel.class);

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentProfileBinding.inflate(inflater);

        //Fragment fragment = getChildFragmentManager().findFragmentById(R.id.visible_stats_or_progress);
        //fragment.getParentFragmentManager();
        replaceFrag(new FragmentProgress());
        binding.progressButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                replaceFrag(new FragmentProgress());
                binding.statsButton.setBackgroundResource(R.drawable.buttons_profile_click);
                binding.statsButton.setTextColor(Color.parseColor("#FFFFFFFF"));
                binding.progressButton.setBackgroundResource(R.drawable.buttons_profile);
                binding.progressButton.setTextColor(Color.parseColor("#003399"));
            }
        });
        binding.statsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                replaceFrag(new FragmentStats());
                binding.progressButton.setBackgroundResource(R.drawable.buttons_profile_click);
                binding.progressButton.setTextColor(Color.parseColor("#FFFFFFFF"));
                binding.statsButton.setBackgroundResource(R.drawable.buttons_profile);
                binding.statsButton.setTextColor(Color.parseColor("#003399"));
            }
        });
        return binding.getRoot();
    }
    private void replaceFrag(Fragment fragment){
        getChildFragmentManager().beginTransaction().replace(R.id.visible_stats_or_progress, fragment).commit();
    }
}
