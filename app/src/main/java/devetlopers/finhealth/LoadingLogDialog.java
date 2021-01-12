package devetlopers.finhealth;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;

import androidx.annotation.NonNull;

public class LoadingLogDialog extends Dialog {
    public LoadingLogDialog(@NonNull Context context) {
        super(context);
        setContentView(R.layout.loading_log_dialog);
        getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
    }
}
