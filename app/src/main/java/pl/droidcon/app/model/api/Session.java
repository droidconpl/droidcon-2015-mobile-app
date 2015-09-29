package pl.droidcon.app.model.api;

import com.google.gson.annotations.SerializedName;

import org.joda.time.DateTime;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

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

public class Session extends RealmObject {

    @PrimaryKey
    private long id;
    private Date date;
    private String title;
    private String description;
    private double rating;

    @SerializedName("people")
    private RealmList<Speaker> speakers = new RealmList<>();

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public RealmList<Speaker> getSpeakers() {
        return speakers;
    }

    public void setSpeakers(RealmList<Speaker> speakers) {
        this.speakers = speakers;
    }
}
