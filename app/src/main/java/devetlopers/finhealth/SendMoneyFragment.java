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

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.SetOptions;

import java.util.HashMap;


public class SendMoneyFragment extends Fragment {

    double sumaNaPoslanie = 0;

    public SendMoneyFragment() {
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_send_money, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        FirebaseAuth auth = FirebaseAuth.getInstance();
        FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();
        SingletonUser user = SingletonUser.getInstance();

        TextView nazovPlanuText = view.findViewById(R.id.nazovPlanu);
        TextView stavPlanuText = view.findViewById(R.id.stavPlanu);
        TextView zostatokText = view.findViewById(R.id.zostatok);
        TextView sumaNaPoslanieText = view.findViewById(R.id.sumaNaPoslanie);
        Button potvrdit = view.findViewById(R.id.potvrditBtn);

        String nazovPlanu = getArguments().getString("nazovPlanu");
        int cisloPlanu = getArguments().getInt("cisloPlanu");
        String zostatokPlanu = getArguments().getString("zostatokPlanu");
        String zostatok = getArguments().getString("zostatok");

        nazovPlanuText.setText(nazovPlanu);
        stavPlanuText.setText(zostatokPlanu);
        zostatokText.setText(zostatok);

        potvrdit.setOnClickListener(view1 -> {
            try {
                sumaNaPoslanie = Double.parseDouble(String.valueOf(sumaNaPoslanieText.getText()));
            } catch (Exception e) {
                return;
            }
            if (sumaNaPoslanie <= Double.parseDouble(zostatok)) {
                if (cisloPlanu == 1) {
                    HashMap<String, Object> data = new HashMap<>();
                    data.put("zostatok", user.getLoggedUser().getZostatok() - sumaNaPoslanie);
                    data.put("rezervaCast", user.getLoggedUser().getRezervaCast() + sumaNaPoslanie);

                    firebaseFirestore.collection("users").document(auth.getCurrentUser().getUid()).set(data, SetOptions.merge()).addOnCompleteListener(runnable1 -> {
                        if (runnable1.isSuccessful()) {
                            user.getLoggedUser().setZostatok(user.getLoggedUser().getZostatok() - sumaNaPoslanie);
                            user.getLoggedUser().setRezervaCast(user.getLoggedUser().getRezervaCast() + sumaNaPoslanie);
                            Toast.makeText(getContext(), "Dáta boli uložené", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(requireContext(), "Something went wrong", Toast.LENGTH_SHORT).show();
                        }
                    });
                } else if (cisloPlanu == 2) {
                    HashMap<String, Object> data = new HashMap<>();
                    data.put("zostatok", user.getLoggedUser().getZostatok() - sumaNaPoslanie);
                    data.put("majetokCast", user.getLoggedUser().getMajetokCast() + sumaNaPoslanie);

                    firebaseFirestore.collection("users").document(auth.getCurrentUser().getUid()).set(data, SetOptions.merge()).addOnCompleteListener(runnable1 -> {
                        if (runnable1.isSuccessful()) {
                            user.getLoggedUser().setZostatok(user.getLoggedUser().getZostatok() - sumaNaPoslanie);
                            user.getLoggedUser().setMajetokCast(user.getLoggedUser().getMajetokCast() + sumaNaPoslanie);
                            Toast.makeText(getContext(), "Dáta boli uložené", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(requireContext(), "Something went wrong", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            } else {
                Toast.makeText(requireContext(), "Skontrolujte zostatok", Toast.LENGTH_SHORT).show();
            }
        });
    }
}