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
    public static double sucetVydavkov;
    private EditText hypoteka0;
    private EditText energie0;
    private EditText pohonneHmoty0;
    private EditText doprava0;
    private EditText telefon0;
    private EditText televizia0;
    private EditText stravovanie0;
    private EditText barber0;
    private TextView continuousExpenses0;

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
        hypoteka0 = view.findViewById(R.id.hypoteka);
        energie0 = view.findViewById(R.id.energie);
        pohonneHmoty0 = view.findViewById(R.id.pohohonneHmoty);
        doprava0 = view.findViewById(R.id.doprava);
        telefon0 = view.findViewById(R.id.telefon);
        televizia0 = view.findViewById(R.id.televizia);
        stravovanie0 = view.findViewById(R.id.stravovanie);
        barber0 = view.findViewById(R.id.barber);
        continuousExpenses0 = view.findViewById(R.id.continuousExpenses);

        confirmButton.setOnClickListener(view1 -> {
            scitajVydavky();
            Navigation.findNavController(view).navigate(R.id.action_addExpensesFragment2_to_planCreateFragment);
        });

        hypoteka0.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                scitajVydavky();

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        energie0.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                scitajVydavky();

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        pohonneHmoty0.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                scitajVydavky();

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        doprava0.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                scitajVydavky();

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        telefon0.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                scitajVydavky();

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        televizia0.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                scitajVydavky();

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        stravovanie0.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                scitajVydavky();

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        barber0.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                scitajVydavky();

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

    }

    public void scitajVydavky() {
        double hypoteka = 0;
        double energie = 0;
        double pohonneHmoty = 0;
        double doprava = 0;
        double telefon = 0;
        double televizia = 0;
        double stravovanie = 0;
        double barber = 0;

        try {
            hypoteka = Double.parseDouble(String.valueOf(hypoteka0.getText()));
        } catch (Exception e) {
            hypoteka = 0;
        }
        try {
            energie = Double.parseDouble(String.valueOf(energie0.getText()));
        } catch (Exception e) {
            energie = 0;
        }
        try {
            pohonneHmoty = Double.parseDouble(String.valueOf(pohonneHmoty0.getText()));
        } catch (Exception e) {
            pohonneHmoty = 0;
        }
        try {
            doprava = Double.parseDouble(String.valueOf(doprava0.getText()));
        } catch (Exception e) {
            doprava = 0;
        }
        try {
            telefon = Double.parseDouble(String.valueOf(telefon0.getText()));
        } catch (Exception e) {
            telefon = 0;
        }
        try {
            televizia = Double.parseDouble(String.valueOf(televizia0.getText()));
        } catch (Exception e) {
            televizia = 0;
        }
        try {
            stravovanie = Double.parseDouble(String.valueOf(stravovanie0.getText()));
        } catch (Exception e) {
            stravovanie = 0;
        }
        try {
            barber = Double.parseDouble(String.valueOf(barber0.getText()));
        } catch (Exception e) {
            barber = 0;
        }

        sucetVydavkov = hypoteka + energie + pohonneHmoty + doprava + telefon + televizia + stravovanie + barber;
        continuousExpenses0.setText(Double.toString(sucetVydavkov));
    }
}