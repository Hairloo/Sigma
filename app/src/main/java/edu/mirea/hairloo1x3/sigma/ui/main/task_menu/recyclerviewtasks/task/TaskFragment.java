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
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import edu.mirea.hairloo1x3.sigma.R;
import edu.mirea.hairloo1x3.sigma.data.data_sources.room.entities.TaskEntity;
import edu.mirea.hairloo1x3.sigma.data.data_sources.room.entities.UserEntitie;
import edu.mirea.hairloo1x3.sigma.databinding.TaskFragmentBinding;

public class TaskFragment extends Fragment {
    private TaskFragmentViewModel viewModel;
    TaskFragmentBinding binding;
    TaskEntity entity;
    private List<Integer> idsFalse;
    private List<Integer> idsCompleted;
    private UserEntitie user;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(this).get(TaskFragmentViewModel.class);
        user = viewModel.getUser2();
        Log.d("Array","Completed :" + idsCompleted);
        Log.d("Array", "Falsed :" + idsFalse);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = TaskFragmentBinding.inflate(inflater);
        entity = TaskFragmentViewModel.entity;
        viewModel.getUser().observe(getViewLifecycleOwner(), new Observer<UserEntitie>() {
            @Override
            public void onChanged(UserEntitie userEntitie) {
                Log.d("User", "ChangeInTask");
                user = userEntitie;
                viewModel.setUser(user);
                bind();
            }
        });
//        viewModel.getIdsFalse().observe(getViewLifecycleOwner(), new Observer<List<Integer>>() {
//            @Override
//            public void onChanged(List<Integer> integers) {
//                if(idsFalse != null){
//                    idsFalse.clear();
//                    idsFalse.addAll(integers);
//                }
//                else{
//                    idsFalse = new ArrayList<>(integers);
//                }
//
//            }
//        });
//        viewModel.getIdsCompleted().observe(getViewLifecycleOwner(), new Observer<List<Integer>>() {
//            @Override
//            public void onChanged(List<Integer> integers) {
//                if(idsCompleted != null){
//                    idsCompleted.clear();
//                    idsCompleted.addAll(integers);
//                }
//                else{
//                    idsCompleted = new ArrayList<>(integers);
//                }
//
//            }
//        });
        binding.middleTextTask.setText(entity.getTask_description());
        binding.upperTextTask.setText(entity.getTask_name());
        binding.sourceText.setText(entity.getTask_source());
        List<String> answers = Arrays.asList(entity.getAnswer().split(" "));
        binding.sendAnswer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String answer = binding.answer.getText().toString();
                if(answers.contains(answer)){
                    Toast.makeText(getContext(), "Правильно", Toast.LENGTH_LONG).show();
                    if(idsCompleted.contains(entity.getId())){
                        return;
                    }
                    if(idsFalse.contains(entity.getId())){
                        user.delFalse(entity.getId());
                    }
                    user.addCompleted(entity.getId());
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
                }
                else{
                    Toast.makeText(getContext(), "Неправильно", Toast.LENGTH_LONG).show();
                    if(!idsCompleted.contains(entity.getId()) && !idsFalse.contains(entity.getId())){
                        user.addFalse(entity.getId());
                        viewModel.updateUser(user,0, entity.getId(), false);
                        viewModel.updateTask(entity.getId(), "F");
                    }
                }
                viewModel.updateUser();
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
    private void bind(){
        idsCompleted = user.getIdsCompleted();
        idsFalse = user.getIdsFalse();
        Log.d("Array","Completed :" + idsCompleted);
        Log.d("Array", "Falsed :" + idsFalse);
        binding.profileIcon.setImageResource(viewModel.iconToRet(viewModel.retSetText()[2]));
    }

}
