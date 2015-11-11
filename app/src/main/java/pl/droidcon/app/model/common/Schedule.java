package pl.droidcon.app.model.common;


import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;

import org.joda.time.DateTime;

import pl.droidcon.app.model.ObservableModel;

public class Schedule implements ObservableModel, Parcelable {

    private static final String ID = "id";
    private static final String DATE = "date";

    public static final Creator<Schedule> CREATOR = new Creator<Schedule>() {
        @Override
        public Schedule createFromParcel(Parcel source) {
            return new Schedule(source);
        }

        @Override
        public Schedule[] newArray(int size) {
            return new Schedule[size];
        }
    };


    private final int sessionId;
    private final DateTime dateTime;

    public Schedule(int sessionId, DateTime dateTime) {
        this.sessionId = sessionId;
        this.dateTime = dateTime;
    }

    private Schedule(Parcel source) {
        Bundle bundle = source.readBundle(getClass().getClassLoader());
        sessionId = bundle.getInt(ID);
        dateTime = (DateTime) bundle.getSerializable(DATE);
    }

    public int getSessionId() {
        return sessionId;
    }

    public DateTime getDateTime() {
        return dateTime;
    }

    @Override
    public String toString() {
        return "Schedule{" +
                "sessionId=" + sessionId +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        Bundle bundle = new Bundle(getClass().getClassLoader());
        bundle.putInt(ID, sessionId);
        bundle.putSerializable(DATE, dateTime);
        dest.writeBundle(bundle);
    }
}
