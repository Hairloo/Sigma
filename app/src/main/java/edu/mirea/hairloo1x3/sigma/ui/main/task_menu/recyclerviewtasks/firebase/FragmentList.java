//package edu.mirea.hairloo1x3.sigma.ui.main.task_menu.recyclerviewtasks.firebase;
//
//import android.os.Bundle;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//
//import androidx.annotation.NonNull;
//import androidx.annotation.Nullable;
//import androidx.fragment.app.Fragment;
//import androidx.lifecycle.ViewModelProvider;
//import androidx.navigation.fragment.NavHostFragment;
//import androidx.recyclerview.widget.LinearLayoutManager;
//import androidx.recyclerview.widget.RecyclerView;
//
//import com.firebase.ui.database.FirebaseRecyclerOptions;
//import com.google.firebase.database.DatabaseReference;
//import com.google.firebase.database.FirebaseDatabase;
//import com.google.firebase.firestore.DocumentReference;
//import com.google.firebase.firestore.FirebaseFirestore;
//
//import edu.mirea.hairloo1x3.sigma.R;
//import edu.mirea.hairloo1x3.sigma.data.models.Task;
//import edu.mirea.hairloo1x3.sigma.databinding.ListTasksFragmentBinding;
//import edu.mirea.hairloo1x3.sigma.ui.main.task_menu.recyclerviewtasks.RecInterface;
//
//public class FragmentList extends Fragment implements RecInterface {
//    private ListTasksFragmentBinding binding;
//    private TestListTasksAdapter adapter;
//
//    @Override
//    public void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        //firestore = FirebaseFirestore.getInstance();
//        //viewModel = new ViewModelProvider(this).get(ListTasksFragmentViewModel.class);
//        //QuerySnapshot q = firestore.collection("Tasks").get();
//        //firebaseDatabase = FirebaseDatabase.getInstance();
//        //databaseReference = firebaseDatabase.getReference("Tasks");
//
//    }
//
//    @Nullable
//    @Override
//    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        binding = ListTasksFragmentBinding.inflate(inflater);
//        RecyclerView recyclerView = binding.listTasks;
//        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
//
//        FirebaseRecyclerOptions<Model> options = new FirebaseRecyclerOptions.Builder<Model>().setQuery(FirebaseDatabase.getInstance().getReference().child("Tasks").child("Algebra"), Model.class).build();
//
//        adapter = new TestListTasksAdapter(options);
//        recyclerView.setAdapter(adapter);
//
//
////        viewModel.getAllTasks().observe(getViewLifecycleOwner(), words -> {
////            // Update the cached copy of the words in the adapter.
////            adapter.submitList(words);
////        });
//        return binding.getRoot();
//    }
//
//    @Override
//    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
//        super.onViewCreated(view, savedInstanceState);
//
//    }
//
//    @Override
//    public void onStart() {
//        super.onStart();
//        adapter.startListening();
//
//    }
//
//    @Override
//    public void onStop() {
//        super.onStop();
//        adapter.stopListening();
//    }
//
//    @Override
//    public void onItemClick(int position) {
////        binding.firstTaskMenu.setOnClickListener(v -> {
////            NavHostFragment.findNavController(this).navigate(R.id.action_fragmentTaskMenu2_to_listTasksFragment);
////        });
//        NavHostFragment.findNavController(this).navigate(R.id.action_listTasksFragment_to_taskFragment);
//    }
//}
