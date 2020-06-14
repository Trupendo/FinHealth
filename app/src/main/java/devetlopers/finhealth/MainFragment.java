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
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.SetOptions;

import org.w3c.dom.Text;

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

        SingletonUser user = SingletonUser.getInstance();

        FloatingActionButton toAddMoneyScreenButton = view.findViewById(R.id.btnToAddMoneyScreen);
        TextView zostatokValue = view.findViewById(R.id.zostatokValue);
        TextView dashoard = view.findViewById(R.id.dashboard);
        TextView stav1 = view.findViewById(R.id.stav1);
        TextView stav2 = view.findViewById(R.id.stav2);
        TextView stav3 = view.findViewById(R.id.stav3);
        TextView percent1 = view.findViewById(R.id.percent1);
        TextView percent2 = view.findViewById(R.id.percent2);
        TextView percent3 = view.findViewById(R.id.percent3);
        ProgressBar progressBar1 = view.findViewById(R.id.progressBar1);
        ProgressBar progressBar2 = view.findViewById(R.id.progressBar2);
        ProgressBar progressBar3 = view.findViewById(R.id.progressBar3);

        LinearLayout goal1 = view.findViewById(R.id.goal1);
        LinearLayout goal2 = view.findViewById(R.id.goal2);
        LinearLayout goal3 = view.findViewById(R.id.goal3);

        dashoard.append(" - " + user.getLoggedUser().getName() + " " + user.getLoggedUser().getSurname());

        double zostatok = user.getLoggedUser().getZostatok();

        zostatokValue.setText(format(zostatok) + "€");

        stav1.setText(user.getLoggedUser().getRezervaCast() + "/" + user.getLoggedUser().getRezerva() + "€");
        double percentoDouble1 = user.getLoggedUser().getRezervaCast() / user.getLoggedUser().getRezerva() * 100;
        int percentoInt1 = (int) percentoDouble1;
        percent1.setText(percentoInt1 + "%");
        progressBar1.post(() -> {
            progressBar1.setProgress(percentoInt1);
        });

        stav2.setText(user.getLoggedUser().getMajetokCast() + "/" + user.getLoggedUser().getMajetok() + "€");
        double percentoDouble2 = user.getLoggedUser().getMajetokCast() / user.getLoggedUser().getMajetok() * 100;
        int percentoInt2 = (int) percentoDouble2;
        percent2.setText(percentoInt2+"%");
        progressBar2.post(() -> {
            progressBar2.setProgress(percentoInt2);
        });

        goal1.setOnClickListener(view1 -> {
            Bundle dataToPass = new Bundle();
            dataToPass.putString("nazovPlanu", "Železná zásoba");
            dataToPass.putInt("cisloPlanu", 1);
            dataToPass.putString("zostatokPlanu", String.valueOf(stav1.getText()));
            dataToPass.putString("zostatok", format(zostatok));
            Navigation.findNavController(view).navigate(R.id.action_mainFragment_to_sendMoneyFragment, dataToPass);
        });

        goal2.setOnClickListener(view1 -> {
            Bundle dataToPass = new Bundle();
            dataToPass.putString("nazovPlanu", "Budovanie majetku");
            dataToPass.putInt("cisloPlanu", 2);
            dataToPass.putString("zostatokPlanu", String.valueOf(stav2.getText()));
            dataToPass.putString("zostatok", format(zostatok));
            Navigation.findNavController(view).navigate(R.id.action_mainFragment_to_sendMoneyFragment, dataToPass);
        });

        toAddMoneyScreenButton.setOnClickListener(view1 -> {
            Navigation.findNavController(view).navigate(R.id.action_mainFragment_to_addMoneyFragment);
        });
    }

    public String format(double hodnota) {
        return String.format("%.2f", hodnota);
    }
}
