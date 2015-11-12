package pl.droidcon.app.ui.dialog;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatDialog;
import android.support.v7.app.AppCompatDialogFragment;
import android.text.Html;
import android.view.View;

import pl.droidcon.app.R;
import pl.droidcon.app.helper.DateTimePrinter;
import pl.droidcon.app.model.api.Session;


public class ScheduleOverlapDialog extends AppCompatDialogFragment {


    private static final String TAG = ScheduleOverlapDialog.class.getSimpleName();

    private static final String COLLISION_SESSION_KEY = "collision";
    private static final String REPLACE_SESSION_KEY = "replace_session";

    public static ScheduleOverlapDialog newInstance(Session collisionSession, Session replaceSession, DialogInterface.OnClickListener positiveClickListener) {
        Bundle args = new Bundle();
        args.putParcelable(COLLISION_SESSION_KEY, collisionSession);
        args.putParcelable(REPLACE_SESSION_KEY, replaceSession);
        ScheduleOverlapDialog fragment = new ScheduleOverlapDialog();
        fragment.setPositiveClickListener(positiveClickListener);
        fragment.setArguments(args);
        return fragment;
    }

    private Session collisionSchedule;
    private Session replaceSession;

    private DialogInterface.OnClickListener positiveClickListener;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(STYLE_NO_TITLE, getTheme());
        collisionSchedule = getArguments().getParcelable(COLLISION_SESSION_KEY);
        replaceSession = getArguments().getParcelable(REPLACE_SESSION_KEY);
    }

    public void setPositiveClickListener(DialogInterface.OnClickListener positiveClickListener) {
        this.positiveClickListener = positiveClickListener;
    }

    @NonNull
    @Override
    public AppCompatDialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        String message = getString(R.string.schedule_overlap_message, collisionSchedule.title, DateTimePrinter.toPrintableString(collisionSchedule.date), replaceSession.title);

        builder.setTitle(R.string.schedule_overlap_title)
                .setMessage(Html.fromHtml(message))
                .setPositiveButton(android.R.string.ok, positiveClickListener)
                .setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        return builder.create();
    }
}
