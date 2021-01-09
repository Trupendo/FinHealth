package devetlopers.finhealth;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.text.Editable;
import android.text.TextWatcher;
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
    private EditText prijem10;
    private EditText prijem20;
    private EditText osobitnePrijmy0;
    private TextView continuousIncomes0;
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

        prijem10 = view.findViewById(R.id.prijem1);
        prijem20 = view.findViewById(R.id.prijem2);
        osobitnePrijmy0 = view.findViewById(R.id.osobitnePrijmy);
        continuousIncomes0 = view.findViewById(R.id.continuousIncomes);
        CheckBox rizPovolanie0 = view.findViewById(R.id.checkBox1);
        CheckBox TPP0 = view.findViewById(R.id.checkBox2);
        CheckBox rodina0 = view.findViewById(R.id.checkBox3);
        Button confirmButton = view.findViewById(R.id.createPlanBtn);

        confirmButton.setOnClickListener(v -> {
            scitajPrijmy();
            boolean rizPovolanie = rizPovolanie0.isChecked();
            boolean TPP = TPP0.isChecked();
            boolean rodina = rodina0.isChecked();
            double rezerva;
            double majetok;
            double mVkladMajetok;

            if (!rizPovolanie && TPP && !rodina) {
                rezerva = 3 * addExpensesFragment.sucetVydavkov;
            } else rezerva = 6 * addExpensesFragment.sucetVydavkov;
//            majetok = 0.1 * pMesPrijemValue;

            HashMap<String, Object> data = new HashMap<>();
            data.put("majetok", 0);
            data.put("rezerva", rezerva);
            data.put("pMesPrijem", pMesPrijem);
            data.put("pMesVydavky", addExpensesFragment.sucetVydavkov);
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

        prijem10.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                scitajPrijmy();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        prijem20.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                scitajPrijmy();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        osobitnePrijmy0.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                scitajPrijmy();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    public void scitajPrijmy() {
        double prijem1 = 0;
        double prijem2 = 0;
        double osobitnePrijmy = 0;

        try {
            prijem1 = Double.parseDouble(String.valueOf(prijem10.getText()));
        } catch (Exception e) {
            prijem1 = 0;
        }
        try {
            prijem2 = Double.parseDouble(String.valueOf(prijem20.getText()));
        } catch (Exception e) {
            prijem2 = 0;
        }
        try {
            osobitnePrijmy = Double.parseDouble(String.valueOf(osobitnePrijmy0.getText()));
        } catch (Exception e) {
            osobitnePrijmy = 0;
        }

        pMesPrijem = prijem1 + prijem2 + osobitnePrijmy;
        continuousIncomes0.setText(Double.toString(pMesPrijem));
    }
}
