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
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.SetOptions;

import java.text.DecimalFormat;
import java.util.HashMap;


/**
 * A simple {@link Fragment} subclass.
 */
public class AddMoneyFragment extends Fragment {

    public AddMoneyFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_money, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();
        FirebaseAuth auth = FirebaseAuth.getInstance();
        SingletonUser user = SingletonUser.getInstance();

        Button addMoneyButton = view.findViewById(R.id.addMoneyButton);
        EditText sumaEditText = view.findViewById(R.id.sumaEditText);

        addMoneyButton.setOnClickListener(view1 -> {
            double suma = Double.parseDouble(String.valueOf(sumaEditText.getText()));
            HashMap<String, Object> data = new HashMap<>();
            data.put("zostatok", user.getLoggedUser().getZostatok() + suma);
            user.getLoggedUser().setZostatok(user.getLoggedUser().getZostatok() + suma);
            firebaseFirestore.collection("users").document(auth.getCurrentUser().getUid()).set(data, SetOptions.merge()).addOnCompleteListener(runnable1 -> {
                Toast.makeText(getContext(), "Dáta boli uložené", Toast.LENGTH_SHORT).show();
            });
        });
    }
}
