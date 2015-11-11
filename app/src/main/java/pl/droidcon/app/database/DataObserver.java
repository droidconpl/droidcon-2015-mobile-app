package pl.droidcon.app.database;

import pl.droidcon.app.model.ObservableModel;


public abstract class DataObserver<T extends ObservableModel> {

    private final Class<T> type;

    public DataObserver(Class<T> type) {
        this.type = type;
    }

    public abstract void onInsert(T data);

    public abstract void onDelete(T data);

    public Class<T> getType() {
        return type;
    }
}
