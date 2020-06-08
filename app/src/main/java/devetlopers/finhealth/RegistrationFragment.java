package devetlopers.finhealth;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

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


/**
 * A simple {@link Fragment} subclass.
 */
public class RegistrationFragment extends Fragment {

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
        Button registerButton = view.findViewById(R.id.registerButton);
        TextView emailField = view.findViewById(R.id.emailFieldReg);
        TextView passwordField = view.findViewById(R.id.passwordFieldReg);
        TextView nameField = view.findViewById(R.id.nameFieldReg);
        TextView surnameField = view.findViewById(R.id.surnameFieldReg);

        FirebaseAuth auth = FirebaseAuth.getInstance();
        FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();

        registerButton.setOnClickListener(view1 -> {
            String email = String.valueOf(emailField.getText());
            String password = String.valueOf(passwordField.getText());
            String name = String.valueOf(nameField.getText());
            String surname = String.valueOf(surnameField.getText());
            if (email.equals("") || password.equals("") || name.equals("") || surname.equals("")) {
                emailField.setError("Povinne more!");
                passwordField.setError("Povinne more!");
                nameField.setError("Povinne more!");
                surnameField.setError("Povinne more!");
                return;
            }
            auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(runnable -> {
                if (runnable.isSuccessful()) {
                    Toast.makeText(getContext(), "Registrácia prebehla úspešne!", Toast.LENGTH_SHORT).show();
                    HashMap<String, Object> data = new HashMap<>();
                    data.put("name", name);
                    data.put("surname", surname);
                    data.put("zostatok", 0);
                    data.put("planCreated", false);
                    firebaseFirestore.collection("users").document(auth.getCurrentUser().getUid()).set(data, SetOptions.merge()).addOnCompleteListener(runnable1 -> {
                        Toast.makeText(requireContext(), "Dáta boli uložené", Toast.LENGTH_SHORT).show();
                    });
                } else {
                    Toast.makeText(getContext(), "Nepodarilo sa registrovať", Toast.LENGTH_SHORT).show();
                }
            });
        });
    }
}
