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
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.SetOptions;

import java.util.HashMap;
import java.util.concurrent.atomic.AtomicReference;

public class AddExpensesFragment extends Fragment {
    public double sucetVydavkov;
    private EditText hypoteka0;
    private EditText energie0;
    private EditText pohonneHmoty0;
    private EditText doprava0;
    private EditText telefon0;
    private EditText televizia0;
    private EditText stravovanie0;
    private EditText barber0;

    public AddExpensesFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_expenses, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        FirebaseAuth auth = FirebaseAuth.getInstance();
        FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();
        SingletonUser user = SingletonUser.getInstance();
        
        Button confirmButton = view.findViewById(R.id.confirmExpenses);
        FloatingActionButton updateExpenses = view.findViewById(R.id.updateExpenses);
        hypoteka0 = view.findViewById(R.id.hypoteka);
        energie0 = view.findViewById(R.id.energie);
        pohonneHmoty0 = view.findViewById(R.id.pohohonneHmoty);
        doprava0 = view.findViewById(R.id.doprava);
        telefon0 = view.findViewById(R.id.telefon);
        televizia0 = view.findViewById(R.id.televizia);
        stravovanie0 = view.findViewById(R.id.stravovanie);
        barber0 = view.findViewById(R.id.barber);
        TextView continuousExpenses0 = view.findViewById(R.id.continuousExpenses);


        updateExpenses.setOnClickListener(view1 -> {
            scitajVydavky();
            continuousExpenses0.setText(Double.toString(sucetVydavkov));
        });

        confirmButton.setOnClickListener(view1 -> {
            scitajVydavky();
            System.out.println(sucetVydavkov);
            Navigation.findNavController(view).navigate(R.id.action_addExpensesFragment2_to_planCreateFragment);
        });
    }

    public void scitajVydavky() {
        double hypoteka = 0;
        if (hypoteka0.getText().equals("")) {
            System.out.println("Dajme tomu");
            hypoteka = Double.parseDouble(String.valueOf(hypoteka0.getText()));
        } else hypoteka = 0;
        double energie = Double.parseDouble(String.valueOf(energie0.getText()));
        double pohonneHmoty = Double.parseDouble(String.valueOf(pohonneHmoty0.getText()));
        double doprava = Double.parseDouble(String.valueOf(doprava0.getText()));
        double telefon = Double.parseDouble(String.valueOf(telefon0.getText()));
        double televizia = Double.parseDouble(String.valueOf(televizia0.getText()));
        double stravovanie = Double.parseDouble(String.valueOf(stravovanie0.getText()));
        double barber = Double.parseDouble(String.valueOf(barber0.getText()));
        sucetVydavkov = hypoteka + energie + pohonneHmoty + doprava + telefon + televizia + stravovanie + barber;
    }
}