package pl.droidcon.app.ui.view;


import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;

import pl.droidcon.app.helper.UrlHelper;
import pl.droidcon.app.model.api.Session;
import pl.droidcon.app.model.api.Speaker;

public class SessionListItem extends SimpleListItem {


    private SessionList.SessionClickListener sessionClickListener;
    private Session session;

    public SessionListItem(Context context) {
        super(context);
    }

    public SessionListItem(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public SessionListItem(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public SessionListItem(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void init(Context context, AttributeSet attributeSet, int defStyleAttr, int defStyleRes) {
        super.init(context, attributeSet, defStyleAttr, defStyleRes);
        if (isInEditMode()) {
            showStubData();
        }
        setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (sessionClickListener != null) {
                    sessionClickListener.onSessionClicked(session);
                }
            }
        });
    }

    public void setSessionClickListener(SessionList.SessionClickListener sessionClickListener) {
        this.sessionClickListener = sessionClickListener;
    }

    public void setSession(Session session) {
        this.session = session;
        setTitle(session.title);
        setDescription(session.description);
        if (session.getSpeakersList().isEmpty()) {
            return;
        }
        Speaker speaker = session.getSpeakersList().get(0);
        if (!TextUtils.isEmpty(speaker.imageUrl)) {
            setImage(UrlHelper.url(speaker.imageUrl));
        }
    }

    private void showStubData() {
        setDescription("Session description");
        setTitle("Session title");
    }

}
