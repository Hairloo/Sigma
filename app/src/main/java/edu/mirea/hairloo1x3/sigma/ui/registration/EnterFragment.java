package edu.mirea.hairloo1x3.sigma.ui.registration;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import edu.mirea.hairloo1x3.sigma.R;
import edu.mirea.hairloo1x3.sigma.data.data_sources.room.entities.TaskEntity;
import edu.mirea.hairloo1x3.sigma.data.data_sources.room.entities.UserEntitie;
import edu.mirea.hairloo1x3.sigma.data.models.User;
import edu.mirea.hairloo1x3.sigma.databinding.FragmentEnterBinding;

public class EnterFragment extends Fragment {
    FragmentEnterBinding binding;
    EnterFragmentViewModel viewModel;
    private DatabaseReference databaseReference;
    private FirebaseAuth auth;
    private UserEntitie user;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        auth = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference("Users");
        viewModel = new ViewModelProvider(this).get(EnterFragmentViewModel.class);
        user = viewModel.getUser();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentEnterBinding.inflate(inflater);
        binding.letsGoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!TextUtils.isEmpty(binding.nicknameReg.getText().toString()) && !TextUtils.isEmpty(binding.passReg.getText().toString())){
                    auth.signInWithEmailAndPassword(binding.nicknameReg.getText().toString(), binding.passReg.getText().toString()).addOnCompleteListener( new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                readDataFromDB(binding.nicknameReg.getText().toString(), binding.passReg.getText().toString());
                                Toast.makeText(getContext(), "Вы вошли успешно", Toast.LENGTH_LONG).show();
                            }
                            else{
                                Toast.makeText(getContext(), "Вы не вошли", Toast.LENGTH_LONG).show();
                            }

                        }
                    });
                }
                else{
                    //
                }
            }
        });
        return binding.getRoot();
    }

    private void readDataFromDB(String login, String password){
        Log.d("myLogs", "присваиваем обработчик кнопкам");
        ValueEventListener valueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                //if(list.size() > 0) list.clear();
                for(DataSnapshot ds : snapshot.getChildren()){
                    UserEntitie task = ds.getValue(UserEntitie.class);
                    //Log.d("myLogs",  Integer.toString(task.getId()));
                    Log.d("User",  task.getPoints() + " " + task.getEmail() + " "  + task.getPassword() + " " + task.getId());
                    //list.add(task);
                    user.setEmail(task.getEmail());
                    user.setPassword(task.getPassword());
                    user.setLogin(task.getLogin());
                    user.setIdsCompleted(task.getIdsCompleted());
                    user.setIdsFalse(task.getIdsFalse());
                    user.setPoints(task.getPoints());
                    user.setId(task.getId());
                    if(task.getPassword().equals(password) && task.getEmail().equals(login)){
                        viewModel.deleteUser();
                        viewModel.insertUser(task);
                    }
                    if(task.getIdsCompleted() != null){
                        for(int id : task.getIdsCompleted()){
                            //TaskEntity task1 = viewModel.getTaskById(i);
                            //task1.setTask_status("C");
                            viewModel.updateTask(id, "C");
                        }
                    }
                    if(task.getIdsFalse() != null){
                        for(int id : task.getIdsFalse()){
                            viewModel.updateTask(id, "F");
                        }
                    }
                    Log.d("User", "Update User" +  task.getPoints() + " " + task.getEmail() + " "  + task.getPassword() + " " + task.getId());


                }
                //adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        };
        databaseReference.addValueEventListener(valueEventListener);
    }
}
