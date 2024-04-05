package edu.mirea.hairloo1x3.sigma.ui.registration;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import edu.mirea.hairloo1x3.sigma.R;
import edu.mirea.hairloo1x3.sigma.data.data_sources.room.entities.UserEntitie;
import edu.mirea.hairloo1x3.sigma.data.models.User;
import edu.mirea.hairloo1x3.sigma.databinding.FragmentRegistrationBinding;

public class RegistrationFragment extends Fragment {
    RegistrationViewModel viewModel;
    FragmentRegistrationBinding binding;
    private FirebaseAuth firebaseAuth;
    private FirebaseFirestore firebaseFirestore;
    private DatabaseReference databaseReference;
    private UserEntitie user;
    DocumentReference documentReference;
    //private boolean isSwap = false;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(this).get(RegistrationViewModel.class);
        firebaseAuth = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference("Users");
        user = viewModel.getUser();
        //databaseReference = FirebaseDatabase.getInstance().getReference("Users");
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentRegistrationBinding.inflate(inflater);
        binding.letsGoButton.setOnClickListener(v1 ->{
            //NavHostFragment.findNavController(this).navigate(R.id.action_registrationFragment_to_mainFragment);

            String email = binding.emailReg.getText().toString();
            String login = binding.nicknameReg.getText().toString();
            String password = binding.passReg.getText().toString();

                if(!TextUtils.isEmpty(login) && !TextUtils.isEmpty(password) && !TextUtils.isEmpty(email)){
                    firebaseAuth.createUserWithEmailAndPassword(email, password).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                        @Override
                        public void onSuccess(AuthResult authResult) {
                            user.setLogin(login);
                            user.setEmail(email);
                            user.setPassword(password);
                            int a = (int)(Math.random() * (Integer.MAX_VALUE - 1));
                            user.setId(a);
                            viewModel.updateUser(user);
                            Toast.makeText(getActivity().getApplicationContext(), "Регистрация прошла успешно", Toast.LENGTH_SHORT).show();
                            //firebaseFirestore.collection("Users").document(FirebaseAuth.getInstance().getUid()).set(user);
                            databaseReference.child(Integer.toString(user.getId())).setValue(user);
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(getActivity().getApplicationContext(), "Пользователь с данной почтой существует", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
                else{
                    Toast.makeText(getActivity().getApplicationContext(), "Введите данные регистрации корректно", Toast.LENGTH_SHORT).show();
                }


        });

        return binding.getRoot();
    }
    private void nav_control(){
       // NavHostFragment.findNavController(this).navigate(R.id.action_registrationFragment_to_mainFragment);

    }


}
