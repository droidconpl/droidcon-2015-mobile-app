package pl.droidcon.app.model.db;

import java.util.Date;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class RealmSession extends RealmObject {

    @PrimaryKey
    private int id;
    private Date date;
    private String title;
    private String description;
    private double rating;

    private RealmList<RealmSpeaker> speakers;


    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public RealmList<RealmSpeaker> getSpeakers() {
        return speakers;
    }

    public void setSpeakers(RealmList<RealmSpeaker> speakers) {
        this.speakers = speakers;
    }
}
