package pl.droidcon.app.model.api;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import org.joda.time.DateTime;

import java.util.ArrayList;
import java.util.List;

/*
{
    "id": 1,
    "date": "1962-10-5 18:00:00",
    "title": "Dr. No",
    "description": "John Strangways, the British Intelligence (SIS) Station Chief in Jamaica, is killed. In response, British agent James Bond—also known as 007—is sent to Jamaica to investigate the circumstances. During his investigation Bond meets Quarrel, a Cayman fisherman, who had been working with Strangways around the nearby islands to collect mineral samples. One of the islands was Crab Key, home to the reclusive Dr. No. Bond visits the island, where he meets a local shell diver, Honey Ryder. The three are attacked by No's men, who kill Quarrel using a flame-throwing armoured tractor; Bond and Honey are taken prisoner. Dr. No informs them he is a member of SPECTRE, the SPecial Executive for Counter-intelligence, Terrorism, Revenge, and Extortion, and he plans to disrupt the Project Mercury space launch from Cape Canaveral with his atomic-powered radio beam. Bond and Honey escape from the island, killing No and blowing up his lair in the process.",
    "rating": 7.4,
    "people": [
        1,
        2
    ]
}
 */

public class Session implements Parcelable {

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
    private static final String RATING = "rating";
    private static final String SPEAKERS_IDS = "speakers_ids";
    private static final String SPEAKERS_LIST = "speakers_list";

    public int id;
    public DateTime date;
    public String title;
    public String description;
    public double rating;

    @SerializedName("people")
    public ArrayList<Integer> speakersIds = new ArrayList<>();

    private ArrayList<Speaker> speakersList = new ArrayList<>();


    public Session() {

    }

    private Session(Parcel source) {
        Bundle bundle = source.readBundle(getClass().getClassLoader());
        id = bundle.getInt(ID);
        date = (DateTime) bundle.getSerializable(DATE_TIME);
        title = bundle.getString(TITLE);
        description = bundle.getString(DESCRIPTION);
        rating = bundle.getDouble(RATING);
        speakersIds = bundle.getIntegerArrayList(SPEAKERS_IDS);
        speakersList = bundle.getParcelableArrayList(SPEAKERS_LIST);
    }

    public void setSpeakersList(ArrayList<Speaker> speakersList) {
        this.speakersList = speakersList;
    }

    public List<Speaker> getSpeakersList() {
        return speakersList;
    }

    @Override
    public String toString() {
        return "Session{" +
                "id=" + id +
                ", date=" + date +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", rating=" + rating +
                ", speakersIds=" + speakersIds +
                ", speakersList=" + speakersList +
                '}';
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
        bundle.putDouble(RATING, rating);
        bundle.putIntegerArrayList(SPEAKERS_IDS, speakersIds);
        bundle.putParcelableArrayList(SPEAKERS_LIST, speakersList);
        dest.writeBundle(bundle);
    }
}
