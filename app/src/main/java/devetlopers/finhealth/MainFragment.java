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
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.SetOptions;

import java.util.HashMap;


/**
 * A simple {@link Fragment} subclass.
 */
public class MainFragment extends Fragment {

    public MainFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //NERIES DALSI RIADOK
        getArguments().getString("Name");

        FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();
        FirebaseAuth auth = FirebaseAuth.getInstance();
        SingletonUser user = SingletonUser.getInstance();

        TextView docasneMeno = view.findViewById(R.id.docasne);
        TextView docasnyZostatok = view.findViewById(R.id.docasne2);
        Button buttonPlus = view.findViewById(R.id.button);

        docasneMeno.setText(user.getLoggedUser().getName() + " " + user.getLoggedUser().getSurname());
        docasnyZostatok.setText("Zostatok: " + user.getLoggedUser().getZostatok());

        buttonPlus.setOnClickListener(view1 -> {
            HashMap<String, Object> data = new HashMap<>();
            data.put("zostatok", user.getLoggedUser().getZostatok() + 1);
            user.getLoggedUser().setZostatok(user.getLoggedUser().getZostatok() + 1);
            firebaseFirestore.collection("users").document(auth.getCurrentUser().getUid()).set(data, SetOptions.merge()).addOnCompleteListener(runnable1 -> {
                Toast.makeText(getContext(), "Dáta boli uložené", Toast.LENGTH_SHORT).show();
            });
            docasnyZostatok.setText("Zostatok: " + user.getLoggedUser().getZostatok());
        });
    }
}
