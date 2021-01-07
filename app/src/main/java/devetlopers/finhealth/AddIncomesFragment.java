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
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.SetOptions;

import java.util.HashMap;

public class AddIncomesFragment extends Fragment {
    public double pMesPrijem;
    AddExpensesFragment addExpensesFragment = new AddExpensesFragment();

    public AddIncomesFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_incomes, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        FirebaseAuth auth = FirebaseAuth.getInstance();
        FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();
        SingletonUser user = SingletonUser.getInstance();

        EditText prijem10 = view.findViewById(R.id.prijem1);
        EditText prijem20 = view.findViewById(R.id.prijem2);
        EditText osobitnePrijmy0 = view.findViewById(R.id.osobitnePrijmy);
        CheckBox rizPovolanie0 = view.findViewById(R.id.checkBox1);
        CheckBox TPP0 = view.findViewById(R.id.checkBox2);
        CheckBox rodina0 = view.findViewById(R.id.checkBox3);
        TextView continuousIncomes0 = view.findViewById(R.id.continuousIncomes);
        FloatingActionButton updateIncomes = view.findViewById(R.id.updateIncomes);
        Button confirmButton = view.findViewById(R.id.createPlanBtn);

        updateIncomes.setOnClickListener(view1 -> {
            double prijem1 = Double.parseDouble(String.valueOf(prijem10.getText()));
            double prijem2 = Double.parseDouble(String.valueOf(prijem20.getText()));
            double osobitnePrijmy = Double.parseDouble(String.valueOf(osobitnePrijmy0.getText()));

            pMesPrijem = prijem1 + prijem2 + osobitnePrijmy;
            continuousIncomes0.setText(Double.toString(pMesPrijem));
            System.out.println(addExpensesFragment.sucetVydavkov);
        });

        confirmButton.setOnClickListener(v -> {
            double prijem1 = Double.parseDouble(String.valueOf(prijem10.getText()));
            double prijem2 = Double.parseDouble(String.valueOf(prijem20.getText()));
            double osobitnePrijmy = Double.parseDouble(String.valueOf(osobitnePrijmy0.getText()));
            boolean rizPovolanie = rizPovolanie0.isChecked();
            boolean TPP = TPP0.isChecked();
            boolean rodina = rodina0.isChecked();
            double rezerva;
            double majetok;
            double mVkladMajetok;

            pMesPrijem = prijem1 + prijem2 + osobitnePrijmy;

            if (!rizPovolanie && TPP && !rodina) {
                rezerva = 3 * addExpensesFragment.sucetVydavkov;
            } else rezerva = 6 * addExpensesFragment.sucetVydavkov;
//            majetok = 0.1 * pMesPrijemValue;

            HashMap<String, Object> data = new HashMap<>();
            data.put("majetok", 0);
            data.put("rezerva", rezerva);
            data.put("pMesPrijem", pMesPrijem);
            data.put("rezervaCast", 0);
            data.put("majetokCast", 0);
            data.put("prvaVyplata", true);
            data.put("majetokInc", 10);

            firebaseFirestore.collection("users").document(auth.getCurrentUser().getUid()).set(data, SetOptions.merge()).addOnCompleteListener(runnable1 -> {
                Toast.makeText(getContext(), "Dáta boli uložené", Toast.LENGTH_SHORT).show();
                user.getLoggedUser().setRezerva(rezerva);
                user.getLoggedUser().setMajetok(0);
                user.getLoggedUser().setpMesPrijem(pMesPrijem);
                user.getLoggedUser().setpMesVydavky(addExpensesFragment.sucetVydavkov);
                user.getLoggedUser().setRezervaCast(0);
                user.getLoggedUser().setMajetokCast(0);
                user.getLoggedUser().setPrvaVyplata(true);
                user.getLoggedUser().setMajetokInc(10);
                Navigation.findNavController(view).navigate(R.id.action_planCreateFragment_to_sumarizationFragment);
            });
        });
    }
}
