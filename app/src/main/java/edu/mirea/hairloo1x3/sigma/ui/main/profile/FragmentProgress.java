package edu.mirea.hairloo1x3.sigma.ui.main.profile;

import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import java.util.List;

import edu.mirea.hairloo1x3.sigma.R;
import edu.mirea.hairloo1x3.sigma.data.data_sources.room.entities.UserEntitie;
import edu.mirea.hairloo1x3.sigma.databinding.FragmentProfileBinding;
import edu.mirea.hairloo1x3.sigma.databinding.FragmentProgressBinding;

public class FragmentProgress extends Fragment {
    FragmentProgressBinding binding;
    FragmentProgressViewModel viewModel;
    private UserEntitie user;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(this).get(FragmentProgressViewModel.class);
        user = viewModel.getUser();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentProgressBinding.inflate(inflater);
        List<String[]> allLevels = viewModel.getAll();
        String [] a = viewModel.retSetText();
        binding.progress.setText(a[0] + "/" +  a[1]);
        binding.progressIcon12.setImageResource(viewModel.iconToRet(a[2]));
        binding.progressIcon22.setImageResource(viewModel.iconToRet(a[3]));
        binding.progress1text.setText(a[2]);
        binding.progress2text.setText(a[3]);
        binding.progressBar.setMax(Integer.parseInt(a[1]));
        int curProgress = Integer.parseInt(a[0]);
        ObjectAnimator.ofInt(binding.progressBar, "progress", curProgress).setDuration(1000).start();
        binding.progressText1.setText(allLevels.get(0)[0] + " - " + allLevels.get(0)[1]);
        binding.progressIcon1.setImageResource(R.drawable.student1);
        binding.progressText2.setText(allLevels.get(1)[0] + " - " + allLevels.get(1)[1]);
        binding.progressIcon2.setImageResource(R.drawable.prepod1);
        binding.progressText3.setText(allLevels.get(2)[0] + " - " + allLevels.get(2)[1]);
        binding.progressIcon3.setImageResource(R.drawable.gausss1);
        binding.progressText4.setText(allLevels.get(3)[0] + " - " + allLevels.get(3)[1]);
        binding.progressIcon4.setImageResource(R.drawable.einsteinger1);
        binding.progressText5.setText(allLevels.get(4)[0] + " - " + allLevels.get(4)[1]);
        binding.progressIcon5.setImageResource(R.drawable.pifagorus1);
        binding.progressText6.setText(allLevels.get(5)[0] + " - " + allLevels.get(5)[1]);
        binding.progressIcon6.setImageResource(R.drawable.newtone1);
        return binding.getRoot();
    }
}
