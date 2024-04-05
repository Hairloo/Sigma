package edu.mirea.hairloo1x3.sigma.ui.main.task_menu.recyclerviewtasks;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.List;

import edu.mirea.hairloo1x3.sigma.R;
import edu.mirea.hairloo1x3.sigma.data.data_sources.room.dao.TaskDAO;
import edu.mirea.hairloo1x3.sigma.data.data_sources.room.entities.TaskEntity;
import edu.mirea.hairloo1x3.sigma.data.data_sources.room.entities.UserEntitie;
import edu.mirea.hairloo1x3.sigma.data.models.Task;
import edu.mirea.hairloo1x3.sigma.data.repositories.TasksRepository;
import edu.mirea.hairloo1x3.sigma.databinding.ListTasksFragmentBinding;
import edu.mirea.hairloo1x3.sigma.ui.main.task_menu.recyclerviewtasks.task.TaskFragmentViewModel;

public class ListTasksFragment extends Fragment implements RecInterface{
    private List<TaskEntity> list;
    private ListTasksFragmentBinding binding;
    private ListTasksFragmentViewModel viewModel;
    private ListTasksAdapter adapter;
    private UserEntitie user;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    String razdel;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        databaseReference = FirebaseDatabase.getInstance().getReference("Tasks").child("FirstTask");
        viewModel = new ViewModelProvider(this).get(ListTasksFragmentViewModel.class);
        user = viewModel.getUser2();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = ListTasksFragmentBinding.inflate(inflater);
        viewModel.getUser().observe(getViewLifecycleOwner(), new Observer<UserEntitie>() {
            @Override
            public void onChanged(UserEntitie userEntitie) {
                user = userEntitie;
                viewModel.setUser(user);
                bind();
            }
        });
        binding.profileIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onIconClick();
            }
        });
        switch (ListTasksFragmentViewModel.razdel){
            case "Algebra":
                binding.toSwapLogo.setBackgroundResource(R.drawable.mathematic_b);
                break;
            case "Stats":
                binding.toSwapLogo.setBackgroundResource(R.drawable.stats_b);
                break;
            case "Matan":
                binding.toSwapLogo.setBackgroundResource(R.drawable.function_b);
                break;
            case "Geometry":
                binding.toSwapLogo.setBackgroundResource(R.drawable.geometry_b);
                break;
            case "Combination":
                binding.toSwapLogo.setBackgroundResource(R.drawable.dice_b);
                break;
            case "Logic":
                binding.toSwapLogo.setBackgroundResource(R.drawable.logical_thinking_b);
                break;

        }
        RecyclerView recyclerView = binding.listTasks;
        adapter = new ListTasksAdapter (new ListTasksAdapter.WordDiff(), this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
        //readDataFromDB();

        //Update from firebase

        switch (ListTasksFragmentViewModel.razdel){
            case "Algebra":
                viewModel.getAlgebraTasks().observe(getViewLifecycleOwner(), words -> {
                    // Update the cached copy of the words in the adapter.
                    adapter.submitList(words);
                    setSortList();
                });
                break;
            case "Matan":
                viewModel.getMatanTasks().observe(getViewLifecycleOwner(), words -> {
                    // Update the cached copy of the words in the adapter.
                    adapter.submitList(words);
                    setSortList();
                });
                break;
            case "Stats":
                viewModel.getStatsTasks().observe(getViewLifecycleOwner(), words -> {
                    // Update the cached copy of the words in the adapter.
                    adapter.submitList(words);
                    setSortList();
                });
                break;
            case "Logic":
                viewModel.getLogicTasks().observe(getViewLifecycleOwner(), words -> {
                    // Update the cached copy of the words in the adapter.
                    adapter.submitList(words);
                    setSortList();
                });
                break;
            case "Geometry":
                viewModel.getGeometryTasks().observe(getViewLifecycleOwner(), words -> {
                    // Update the cached copy of the words in the adapter.
                    adapter.submitList(words);
                    setSortList();
                });
                break;
            case  "Combination":
                viewModel.getCombinationTasks().observe(getViewLifecycleOwner(), words -> {
                    // Update the cached copy of the words in the adapter.
                    adapter.submitList(words);
                    setSortList();
                });
                break;
        }

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    @Override
    public void onItemClick(int position, TaskEntity entity) {
//        binding.firstTaskMenu.setOnClickListener(v -> {
//            NavHostFragment.findNavController(this).navigate(R.id.action_fragmentTaskMenu2_to_listTasksFragment);
//        });
        Log.d("myLogs",  entity.getTask_name() +" " +  Integer.toString(position));
        TaskFragmentViewModel.entity = entity;
        NavHostFragment.findNavController(this).navigate(R.id.action_listTasksFragment_to_taskFragment);
    }

    public void onIconClick(){
        NavHostFragment.findNavController(this).navigate(R.id.action_listTasksFragment_to_fragmentProfile2);
    }

    private void readDataFromDB(){
        Log.d("myLogs", "присваиваем обработчик кнопкам");
        ValueEventListener valueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                //if(list.size() > 0) list.clear();
                for(DataSnapshot ds : snapshot.getChildren()){
                    TaskEntity task = ds.getValue(TaskEntity.class);
                    Log.d("myLogs",  Integer.toString(task.getId()));
                    Log.d("Finds",  viewModel.findTaskById(task.getId()) == null ? "yes" : "no");
                    //list.add(task);
                    if(viewModel.findTaskById(task.getId()) == null){
                        viewModel.insert(task);
                    }
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        };
        databaseReference.addValueEventListener(valueEventListener);
    }

    private void setSortList(){
        if(ListTasksViewHolder.list.size() > 0) ListTasksViewHolder.list.clear();
        ListTasksViewHolder.list.addAll(adapter.getCurrentList());
    }
    private void setSortList2(List<TaskEntity> list){
        if(ListTasksViewHolder.list.size() > 0) ListTasksViewHolder.list.clear();
        ListTasksViewHolder.list.addAll(list);
    }
    private void bind(){
        binding.profileIcon.setImageResource(viewModel.iconToRet(viewModel.retSetText()[2]));
    }
}
