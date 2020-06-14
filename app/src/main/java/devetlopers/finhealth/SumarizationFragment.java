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

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.SetOptions;

import java.util.HashMap;

public class SumarizationFragment extends Fragment {

    public SumarizationFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_sumarization, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        FirebaseAuth auth = FirebaseAuth.getInstance();
        FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();
        SingletonUser user = SingletonUser.getInstance();

        Button confirmButton = view.findViewById(R.id.confirmButton);
        TextView rezervaPlan = view.findViewById(R.id.rezervaPlan);
        TextView budMajetkuPlan = view.findViewById(R.id.budMajetkuPlan);

        double rezerva = user.getLoggedUser().getRezerva();
        double majetok = user.getLoggedUser().getMajetok();

        rezervaPlan.setText(String.valueOf(rezerva));
        budMajetkuPlan.setText(String.valueOf(majetok));

        confirmButton.setOnClickListener(v -> {
            double rezervaNew = Double.parseDouble(String.valueOf(rezervaPlan.getText()));
            double majetokNew = Double.parseDouble(String.valueOf(budMajetkuPlan.getText()));

            HashMap<String, Object> data = new HashMap<>();
            data.put("majetok", majetokNew);
            data.put("rezerva", rezervaNew);

            firebaseFirestore.collection("users").document(auth.getCurrentUser().getUid()).set(data, SetOptions.merge()).addOnCompleteListener(runnable1 -> {
                if (runnable1.isSuccessful()) {
                    user.getLoggedUser().setRezerva(rezervaNew);
                    user.getLoggedUser().setMajetok(majetokNew);
                    Toast.makeText(getContext(), "Dáta boli uložené", Toast.LENGTH_SHORT).show();
                    Navigation.findNavController(view).navigate(R.id.action_sumarizationFragment_to_mainFragment);
                } else {
                    Toast.makeText(requireContext(), "Something went wrong", Toast.LENGTH_SHORT).show();
                }
            });
        });
    }
}