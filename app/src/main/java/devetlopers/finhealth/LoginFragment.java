package devetlopers.finhealth;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.SetOptions;

import java.util.HashMap;


/**
 * A simple {@link Fragment} subclass.
 */
public class LoginFragment extends Fragment {

    public LoginFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        FirebaseAuth auth = FirebaseAuth.getInstance();
        FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();
        Button signUpButton = view.findViewById(R.id.signUpButton);
        Button signInButton = view.findViewById(R.id.signInButton);
//        Button signOutButton = view.findViewById(R.id.signOutButton);
        TextView emailField = view.findViewById(R.id.emailField);
        TextView passwordField = view.findViewById(R.id.passwordField);


        signUpButton.setOnClickListener(view1 -> {
            Navigation.findNavController(view).navigate(R.id.action_loginFragment_to_registrationFragment);
        });

        signInButton.setOnClickListener(view1 -> {
            String email = String.valueOf(emailField.getText());
            String password = String.valueOf(passwordField.getText());
            if (email.equals("") || password.equals("")) {
                emailField.setError("Povinne more!");
                passwordField.setError("Povinne more!");
                return;
            }
            auth.signInWithEmailAndPassword(email, password).addOnCompleteListener(runnable -> {
                if (runnable.isSuccessful()) {
                    firebaseFirestore.collection("users").document(auth.getCurrentUser().getUid()).get().addOnCompleteListener(runnable1 -> {
                        if (runnable1.isSuccessful()) {
                            User user = runnable1.getResult().toObject(User.class);
                            SingletonUser user1 = SingletonUser.getInstance();
                            user1.setLoggedUser(user);
                            //TOTO SU VECI OD VECI - nevsimaj si
                            Bundle dataToPass = new Bundle();
                            dataToPass.putString("Name", "Matejkooo");
                            Navigation.findNavController(view).navigate(R.id.action_loginFragment_to_mainFragment, dataToPass);
                            //PO TADE
                        } else
                            Toast.makeText(getContext(), "Could not fetch data", Toast.LENGTH_SHORT).show();
                    });
                } else
                    Toast.makeText(getContext(), "Something went wrong", Toast.LENGTH_SHORT).show();
            });
        });

        auth.signOut();
//        signOutButton.setOnClickListener(view1 -> {
//            auth.signOut();
//            Toast.makeText(getContext(), "ODHLASENE", Toast.LENGTH_SHORT).show();
//        });
    }


}
