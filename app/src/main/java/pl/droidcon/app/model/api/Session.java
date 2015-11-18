package pl.droidcon.app.model.api;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;

import org.joda.time.DateTime;

import java.util.ArrayList;
import java.util.List;

public class Session implements Parcelable, Comparable<Session> {

    public static final Creator<Session> CREATOR = new Creator<Session>() {
        @Override
        public Session createFromParcel(Parcel source) {
            return new Session(source);
        }

        @Override
        public Session[] newArray(int size) {
            return new Session[size];
        }
    };

    private static final String ID = "id";
    private static final String DATE_TIME = "date_time";
    private static final String TITLE = "title";
    private static final String DESCRIPTION = "description";
    private static final String SPEAKERS_IDS = "speakers_ids";
    private static final String SPEAKERS_LIST = "speakers_list";
    private static final String ROOM_ID = "room_id";
    private static final String SESSION_DISPLAY_HOUR = "session_display_hour";
    private static final String DAY_ID = "day_id";
    private static final String SINGLE_ITEM = "single_item";

    public int id;
    public DateTime date;
    public String title;
    public String description;
    public int roomId;
    public String sessionDisplayHour;
    public int dayId;
    public ArrayList<Integer> speakersIds = new ArrayList<>();
    public boolean singleItem;

    private ArrayList<Speaker> speakersList = new ArrayList<>();


    public Session() {

    }

    private Session(Parcel source) {
        Bundle bundle = source.readBundle(getClass().getClassLoader());
        id = bundle.getInt(ID);
        date = (DateTime) bundle.getSerializable(DATE_TIME);
        title = bundle.getString(TITLE);
        description = bundle.getString(DESCRIPTION);
        speakersIds = bundle.getIntegerArrayList(SPEAKERS_IDS);
        speakersList = bundle.getParcelableArrayList(SPEAKERS_LIST);
        roomId = bundle.getInt(ROOM_ID);
        sessionDisplayHour = bundle.getString(SESSION_DISPLAY_HOUR);
        dayId = bundle.getInt(DAY_ID);
        singleItem = bundle.getBoolean(SINGLE_ITEM);
    }

    public void setSpeakersList(ArrayList<Speaker> speakersList) {
        this.speakersList = speakersList;
    }

    public List<Speaker> getSpeakersList() {
        return speakersList;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        Bundle bundle = new Bundle(getClass().getClassLoader());
        bundle.putInt(ID, id);
        bundle.putSerializable(DATE_TIME, date);
        bundle.putString(TITLE, title);
        bundle.putString(DESCRIPTION, description);
        bundle.putIntegerArrayList(SPEAKERS_IDS, speakersIds);
        bundle.putParcelableArrayList(SPEAKERS_LIST, speakersList);
        bundle.putInt(ROOM_ID, roomId);
        bundle.putString(SESSION_DISPLAY_HOUR, sessionDisplayHour);
        bundle.putInt(DAY_ID, dayId);
        bundle.putBoolean(SINGLE_ITEM, singleItem);
        dest.writeBundle(bundle);
    }

    @Override
    public int compareTo(@NonNull Session another) {
        return date.compareTo(another.date);
    }
}
