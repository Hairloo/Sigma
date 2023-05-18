package edu.mirea.hairloo1x3.sigma.ui.main.task_menu.recyclerviewtasks.task;

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
import androidx.navigation.fragment.NavHostFragment;

import java.util.Arrays;
import java.util.List;

import edu.mirea.hairloo1x3.sigma.R;
import edu.mirea.hairloo1x3.sigma.data.data_sources.room.entities.TaskEntity;
import edu.mirea.hairloo1x3.sigma.data.data_sources.room.entities.UserEntitie;
import edu.mirea.hairloo1x3.sigma.databinding.TaskFragmentBinding;

public class TaskFragment extends Fragment {
    private TaskFragmentViewModel viewModel;
    TaskEntity entity;
    private UserEntitie user;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(this).get(TaskFragmentViewModel.class);
        user = viewModel.getUser();
        Log.d("Array","Completed :" + user.getIdsCompleted().toString());
        Log.d("Array", "Falsed :" + user.getIdsFalse().toString());
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        TaskFragmentBinding binding = TaskFragmentBinding.inflate(inflater);
        entity = TaskFragmentViewModel.entity;
        binding.middleTextTask.setText(entity.getTask_description());
        binding.upperTextTask.setText(entity.getTask_name());
        binding.sourceText.setText(entity.getTask_source());
        List<String> answers = Arrays.asList(entity.getAnswer().split(" "));
        binding.sendAnswer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String answer = binding.answer.getText().toString();
                if(answers.contains(answer) && !user.getIdsCompleted().contains(entity.getId())){
                    int addpoints = 0;
                    if(entity.getTask_dif().equals("H")){
                        addpoints = 1500;
                    }
                    if(entity.getTask_dif().equals("E")){
                        addpoints = 500;
                    }
                    if(entity.getTask_dif().equals("M")){
                        addpoints = 1200;
                    }
                    viewModel.updateUser(user,addpoints, entity.getId(), true);
                    viewModel.updateTask(entity.getId(), "C");
                    Toast.makeText(getContext(), "Правильно", Toast.LENGTH_LONG).show();
                }
                else{
                    Toast.makeText(getContext(), "Неправильно", Toast.LENGTH_LONG).show();
                    viewModel.updateUser(user,0, entity.getId(), false);
                    viewModel.updateTask(entity.getId(), "F");
                }
            }
        });
        binding.profileIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onIconClick();
            }
        });
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
    public void onIconClick(){
        NavHostFragment.findNavController(this).navigate(R.id.action_taskFragment_to_fragmentProfile2);
    }
}
