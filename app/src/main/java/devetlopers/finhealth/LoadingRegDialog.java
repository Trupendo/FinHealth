package devetlopers.finhealth;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;

import androidx.annotation.NonNull;

public class LoadingRegDialog extends Dialog {
    public LoadingRegDialog(@NonNull Context context) {
        super(context);
        setContentView(R.layout.loading_reg_dialog);
        getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
    }
}
