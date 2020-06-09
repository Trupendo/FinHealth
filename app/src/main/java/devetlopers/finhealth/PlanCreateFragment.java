package devetlopers.finhealth;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.SetOptions;

import java.util.HashMap;


/**
 * A simple {@link Fragment} subclass.
 */
public class PlanCreateFragment extends Fragment {

    public PlanCreateFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_plan_create, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        FirebaseAuth auth = FirebaseAuth.getInstance();
        FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();
        SingletonUser user = SingletonUser.getInstance();

        EditText pMesPrijem0 = view.findViewById(R.id.pMesPrijem);
        EditText pMesVydavky0 = view.findViewById(R.id.pMesVydavky);
        CheckBox rizPovolanie0 = view.findViewById(R.id.checkBox1);
        CheckBox TPP0 = view.findViewById(R.id.checkBox2);
        CheckBox rodina0 = view.findViewById(R.id.checkBox3);
        Button confirmButton = view.findViewById(R.id.createPlanBtn);

        if (user.getLoggedUser().isPlanCreated()) {
            Navigation.findNavController(view).navigate(R.id.action_planCreateFragment_to_mainFragment);
        }

        confirmButton.setOnClickListener(v -> {
            double pMesVydavky = Double.parseDouble(String.valueOf(pMesVydavky0.getText()));
            double pMesPrijem = Double.parseDouble(String.valueOf(pMesPrijem0.getText()));
            boolean rizPovolanie = rizPovolanie0.isChecked();
            boolean TPP = TPP0.isChecked();
            boolean rodina = rodina0.isChecked();
            double rezervaPlan;
            double budMajetkuPlan;
            if (rizPovolanie == false && TPP == true && rodina == false) {
                rezervaPlan = 3*pMesVydavky;
            }
            else rezervaPlan = 6*pMesVydavky;
            budMajetkuPlan = 0.1*pMesPrijem;
            HashMap<String, Object> data = new HashMap<>();
            data.put("rezervaPlan", rezervaPlan);
            data.put("budMajetkuPlan", budMajetkuPlan);
            data.put("planCreated", true);
            firebaseFirestore.collection("users").document(auth.getCurrentUser().getUid()).set(data, SetOptions.merge()).addOnCompleteListener(runnable1 -> {
                Toast.makeText(getContext(), "Dáta boli uložené", Toast.LENGTH_SHORT).show();
                user.getLoggedUser().setPlanCreated(true);
                user.getLoggedUser().setRezervaPlan(rezervaPlan);
                user.getLoggedUser().setRezervaPercent(budMajetkuPlan);
                Navigation.findNavController(view).navigate(R.id.action_planCreateFragment_to_mainFragment);
            });
        });
    }
}
