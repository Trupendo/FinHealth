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

import java.util.concurrent.atomic.AtomicReference;

public class AddExpensesFragment extends Fragment {

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
        Button confirmButton = view.findViewById(R.id.confirmExpenses);
        Button updateExpenses = view.findViewById(R.id.updateExpenses);
        EditText hypoteka0 = view.findViewById(R.id.hypoteka);
        EditText energie0 = view.findViewById(R.id.energie);
        EditText pohonneHmoty0 = view.findViewById(R.id.pohohonneHmoty);
        EditText doprava0 = view.findViewById(R.id.doprava);
        EditText telefon0 = view.findViewById(R.id.telefon);
        EditText televizia0 = view.findViewById(R.id.televizia);
        EditText stravovanie0 = view.findViewById(R.id.stravovanie);
        EditText barber0 = view.findViewById(R.id.barber);
        TextView continuousExpenses0 = view.findViewById(R.id.continuousExpenses);
        AtomicReference<Double> sucetVydavkov = new AtomicReference<>((double) 0);


        updateExpenses.setOnClickListener(view1 -> {
            double hypoteka = 0;
            if (hypoteka0.getText() != null) {
                System.out.println("Dajme tomu");
                hypoteka = Double.parseDouble(String.valueOf(hypoteka0.getText()));
            }
            else hypoteka = 0;
            double energie = Double.parseDouble(String.valueOf(energie0.getText()));
            double pohonneHmoty = Double.parseDouble(String.valueOf(pohonneHmoty0.getText()));
            double doprava = Double.parseDouble(String.valueOf(doprava0.getText()));
            double telefon = Double.parseDouble(String.valueOf(telefon0.getText()));
            double televizia = Double.parseDouble(String.valueOf(televizia0.getText()));
            double stravovanie = Double.parseDouble(String.valueOf(stravovanie0.getText()));
            double barber = Double.parseDouble(String.valueOf(barber0.getText()));
            sucetVydavkov.set(hypoteka + energie + pohonneHmoty + doprava + telefon + televizia + stravovanie + barber);
            continuousExpenses0.setText(sucetVydavkov.toString());
        });

        confirmButton.setOnClickListener(view1 -> {
            Navigation.findNavController(view).navigate(R.id.action_addExpensesFragment2_to_planCreateFragment);
        });
    }
}