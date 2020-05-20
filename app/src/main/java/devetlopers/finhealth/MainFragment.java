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
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
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

        SingletonUser user = SingletonUser.getInstance();

        FloatingActionButton toAddMoneyScreenButton = view.findViewById(R.id.btnToAddMoneyScreen);
        TextView zostatokValue = view.findViewById(R.id.zostatokValue);
        TextView dashoard = view.findViewById(R.id.dashboard);
        LinearLayout goal1 = view.findViewById(R.id.goal1);
        goal1.setOnClickListener(view1 -> {
            Toast.makeText(getContext(), "gg", Toast.LENGTH_SHORT).show();
        });


        dashoard.append(" - "+user.getLoggedUser().getName() + " " + user.getLoggedUser().getSurname());
        zostatokValue.setText(user.getLoggedUser().getZostatok()+"€");

        toAddMoneyScreenButton.setOnClickListener(view1 -> {
            Navigation.findNavController(view).navigate(R.id.action_mainFragment_to_addMoneyFragment);
        });
    }
}
