package devetlopers.finhealth;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.SetOptions;

import java.util.HashMap;

public class RemoveMoneyFragment extends Fragment {

    public RemoveMoneyFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_remove_money, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();
        FirebaseAuth auth = FirebaseAuth.getInstance();
        SingletonUser user = SingletonUser.getInstance();

        EditText sumaNaOdobratieText = view.findViewById(R.id.sumaEditText2);
        Button removeMoneyButton = view.findViewById(R.id.removeMoneyBtn);

        removeMoneyButton.setOnClickListener(view1 -> {
            double suma = Double.parseDouble(String.valueOf(sumaNaOdobratieText.getText()));

            HashMap<String, Object> data = new HashMap<>();
            data.put("zostatok", user.getLoggedUser().getZostatok() - suma);

            firebaseFirestore.collection("users").document(auth.getCurrentUser().getUid()).set(data, SetOptions.merge()).addOnCompleteListener(runnable1 -> {
                user.getLoggedUser().setZostatok(user.getLoggedUser().getZostatok() - suma);
                Toast.makeText(getContext(), "Dáta boli uložené", Toast.LENGTH_SHORT).show();
            });
        });
    }
}