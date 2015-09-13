package pl.droidcon.app.wrapper;


import android.support.annotation.NonNull;
import android.support.annotation.StringRes;
import android.support.design.widget.Snackbar;
import android.view.View;

public class SnackbarWrapper {


    public void showSnackbar(@NonNull View view, String message) {
        Snackbar.make(view, message, Snackbar.LENGTH_LONG).show();
    }

    public void showSnackbar(@NonNull View view, CharSequence message) {
        showSnackbar(view, message.toString());
    }

    public void showSnackbar(@NonNull View view, @StringRes int message) {
        showSnackbar(view, view.getContext().getString(message));
    }
}
