package pl.droidcon.app.ui.adapter;


import android.support.v7.widget.RecyclerView;
import android.view.View;

import pl.droidcon.app.model.api.Session;

public abstract class BaseSessionViewHolder extends RecyclerView.ViewHolder {

    public BaseSessionViewHolder(View itemView) {
        super(itemView);
    }

    abstract public void attachSession(Session session);

    abstract public Session getSession();
}
