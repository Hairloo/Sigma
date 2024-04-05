package edu.mirea.hairloo1x3.sigma.ui.main.profile;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;

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
    private UserEntitie user;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(this).get(FragmentProfileViewModel.class);
        user = viewModel.getUser2();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentProfileBinding.inflate(inflater);

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel.getUser().observe(getViewLifecycleOwner(), new Observer<UserEntitie>() {
            @Override
            public void onChanged(UserEntitie userEntitie) {
                Log.d("User", "ChangeProfile");
                user = userEntitie;
                Log.d("User",  userEntitie.getPoints() + " " + userEntitie.getEmail() + " "  + userEntitie.getPassword() + " " + userEntitie.getId());

                viewModel.setUser(userEntitie);
                bind();
            }
        });

        binding.enter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                moveTo(R.id.action_fragmentProfile2_to_enterFragment);
            }
        });
        binding.registration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                moveTo(R.id.action_fragmentProfile2_to_registrationFragment);
            }
        });
        binding.out.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                user.setPassword(null);
                user.setEmail(null);
                user.setLogin(null);
                viewModel.updateUser(user);
                Toast.makeText(getContext(), "Вы вышли успешно", Toast.LENGTH_LONG).show();

            }
        });

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
    }

    private void replaceFrag(Fragment fragment){
        getChildFragmentManager().beginTransaction().replace(R.id.visible_stats_or_progress, fragment).commit();
    }
    private void moveTo(int moveId){
        NavHostFragment.findNavController(this).navigate(moveId);
    }
    private  void bind(){
        Log.d("User", "Quit");
        if(user.getLogin() != null){
            Log.d("User", "Login");

            binding.profileNick.setText(user.getLogin());
            binding.profileNick.setVisibility(View.VISIBLE);
        }
        else{
            Log.d("User", "No login");
            binding.profileNick.setVisibility(View.GONE);
        }
        if(user.getEmail() == null){
            Log.d("User", "No email");
            binding.out.setVisibility(View.GONE);
            binding.enter.setVisibility(View.VISIBLE);
            binding.registration.setVisibility(View.VISIBLE);
        }
        else{
            binding.out.setVisibility(View.VISIBLE);
            Log.d("User", "Email");
            binding.enter.setVisibility(View.GONE);
            binding.registration.setVisibility(View.GONE);
        }
        String [] a = viewModel.retSetText();
        binding.profileIm.setImageResource(viewModel.iconToRet(a[2]));
    }
}
