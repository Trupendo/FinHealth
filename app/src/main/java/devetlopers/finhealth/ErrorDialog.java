package devetlopers.finhealth;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.widget.Button;

import androidx.annotation.NonNull;

public class ErrorDialog extends Dialog {
    public ErrorDialog(@NonNull Context context) {
        super(context);
        setContentView(R.layout.error_dialog);
        getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
    }

    public void showDialog() {
        Button ok = findViewById(R.id.dismiss);
        ok.setOnClickListener(view -> {
            dismiss();
        });
    }
}
