package devetlopers.finhealth;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.SetOptions;

import java.util.HashMap;

public class RegistrationFragment extends Fragment {

    Button registerButton;
    Button loginButton;
    CardView name;
    CardView surname;
    CardView email;
    CardView password;
    float v = 0;

    public RegistrationFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_registration, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        TextView emailField = view.findViewById(R.id.emailFieldReg);
        TextView passwordField = view.findViewById(R.id.passwordFieldReg);
        TextView nameField = view.findViewById(R.id.nameFieldReg);
        TextView surnameField = view.findViewById(R.id.surnameFieldReg);
        name = view.findViewById(R.id.name);
        surname = view.findViewById(R.id.surname);
        email = view.findViewById(R.id.email);
        password = view.findViewById(R.id.password);
        registerButton = view.findViewById(R.id.registerButton);
        loginButton = view.findViewById(R.id.loginButton);

        name.setTranslationX(800);
        surname.setTranslationX(800);
        email.setTranslationX(800);
        password.setTranslationX(800);
        registerButton.setTranslationX(800);
        loginButton.setTranslationX(800);

        name.setAlpha(v);
        surname.setAlpha(v);
        email.setAlpha(v);
        password.setAlpha(v);
        registerButton.setAlpha(v);
        loginButton.setAlpha(v);

        name.animate().translationX(0).alpha(1).setDuration(600).setStartDelay(100).start();
        surname.animate().translationX(0).alpha(1).setDuration(600).setStartDelay(200).start();
        email.animate().translationX(0).alpha(1).setDuration(600).setStartDelay(300).start();
        password.animate().translationX(0).alpha(1).setDuration(600).setStartDelay(400).start();
        registerButton.animate().translationX(0).alpha(1).setDuration(600).setStartDelay(500).start();
        loginButton.animate().translationX(0).alpha(1).setDuration(600).setStartDelay(600).start();

        FirebaseAuth auth = FirebaseAuth.getInstance();
        FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();

        registerButton.setOnClickListener(view1 -> {
            String email = String.valueOf(emailField.getText());
            String password = String.valueOf(passwordField.getText());
            String name = String.valueOf(nameField.getText());
            String surname = String.valueOf(surnameField.getText());
            if (email.equals("") || password.equals("") || name.equals("") || surname.equals("")) {
                emailField.setError("Povinne pole");
                passwordField.setError("Povinne pol!");
                nameField.setError("Povinne pole");
                surnameField.setError("Povinne pole");
                return;
            }
            LoadingRegDialog loadingRegDialog = new LoadingRegDialog(requireContext());
            loadingRegDialog.show();
            auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(runnable -> {
                if (runnable.isSuccessful()) {
                    HashMap<String, Object> data = new HashMap<>();
                    data.put("name", name);
                    data.put("surname", surname);
                    data.put("zostatok", 0);
                    firebaseFirestore.collection("users").document(auth.getCurrentUser().getUid()).set(data, SetOptions.merge()).addOnCompleteListener(runnable1 -> {
                    });
                    firebaseFirestore.collection("users").document(auth.getCurrentUser().getUid()).get().addOnCompleteListener(runnable1 -> {
                        if (runnable1.isSuccessful()) {
                            User user = runnable1.getResult().toObject(User.class);
                            SingletonUser user1 = SingletonUser.getInstance();
                            user1.setLoggedUser(user);
                            loadingRegDialog.dismiss();
                            Navigation.findNavController(view).navigate(R.id.action_registrationFragment_to_addExpensesFragment2);
                        }
                    });
                } else {
                    loadingRegDialog.dismiss();
                    ErrorDialog errorDialog = new ErrorDialog(requireContext());
                    errorDialog.show();
                    errorDialog.showDialog();
                }
            });
        });

        loginButton.setOnClickListener(view1 -> {
            Navigation.findNavController(view).navigate(R.id.action_global_loginFragment);
        });
    }
}