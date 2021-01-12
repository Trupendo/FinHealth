package devetlopers.finhealth;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.SetOptions;

import java.util.HashMap;

public class LoginFragment extends Fragment {

    TextView login;
    CardView email, password;
    Button signInButton, signUpButton;
    float v = 0;
    ProgressDialog progressDialog;

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
//        Button signOutButton = view.findViewById(R.id.signOutButton);
        TextView emailField = view.findViewById(R.id.emailField);
        TextView passwordField = view.findViewById(R.id.passwordField);
        login = view.findViewById(R.id.login);
        email = view.findViewById(R.id.email);
        password = view.findViewById(R.id.password);
        signUpButton = view.findViewById(R.id.signUpButton);
        signInButton = view.findViewById(R.id.signInButton);

        login.setTranslationX(800);
        email.setTranslationX(800);
        password.setTranslationX(800);
        signInButton.setTranslationX(800);
        signUpButton.setTranslationX(800);

        login.setAlpha(v);
        email.setAlpha(v);
        password.setAlpha(v);
        signInButton.setAlpha(v);
        signUpButton.setAlpha(v);

        login.animate().translationX(0).alpha(1).setDuration(600).setStartDelay(100).start();
        email.animate().translationX(0).alpha(1).setDuration(600).setStartDelay(200).start();
        password.animate().translationX(0).alpha(1).setDuration(600).setStartDelay(300).start();
        signInButton.animate().translationX(0).alpha(1).setDuration(600).setStartDelay(400).start();
        signUpButton.animate().translationX(0).alpha(1).setDuration(600).setStartDelay(500).start();

        signUpButton.setOnClickListener(view1 -> {
            Navigation.findNavController(view).navigate(R.id.action_loginFragment_to_registrationFragment);
        });

        signInButton.setOnClickListener(view1 -> {
            String email = String.valueOf(emailField.getText());
            String password = String.valueOf(passwordField.getText());
            if (email.equals("") || password.equals("")) {
                emailField.setError("Povinne pole");
                passwordField.setError("Povinne pole");
                return;
            }
            LoadingLogDialog loadingLogDialog = new LoadingLogDialog(requireContext());
            loadingLogDialog.show();
            auth.signInWithEmailAndPassword(email, password).addOnCompleteListener(runnable -> {
                if (runnable.isSuccessful()) {
                    firebaseFirestore.collection("users").document(auth.getCurrentUser().getUid()).get().addOnCompleteListener(runnable1 -> {
                        if (runnable1.isSuccessful()) {
                            User user = runnable1.getResult().toObject(User.class);
                            SingletonUser user1 = SingletonUser.getInstance();
                            user1.setLoggedUser(user);
                            loadingLogDialog.dismiss();
                            Navigation.findNavController(view).navigate(R.id.action_loginFragment_to_mainFragment);
                        }
                    });
                } else {
                    loadingLogDialog.dismiss();
                    ErrorDialog errorDialog = new ErrorDialog(requireContext());
                    errorDialog.show();
                    errorDialog.showDialog();
                }
            });
        });

        auth.signOut();
//        signOutButton.setOnClickListener(view1 -> {
//            auth.signOut();
//            Toast.makeText(getContext(), "ODHLASENE", Toast.LENGTH_SHORT).show();
//        });
    }


}
