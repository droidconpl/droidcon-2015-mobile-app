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
    private int roomId;
    private String displayHour;
    private int dayId;
    private boolean singleItem;

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

    public RealmList<RealmSpeaker> getSpeakers() {
        return speakers;
    }

    public void setSpeakers(RealmList<RealmSpeaker> speakers) {
        this.speakers = speakers;
    }

    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    public String getDisplayHour() {
        return displayHour;
    }

    public void setDisplayHour(String displayHour) {
        this.displayHour = displayHour;
    }

    public int getDayId() {
        return dayId;
    }

    public void setDayId(int dayId) {
        this.dayId = dayId;
    }

    public boolean isSingleItem() {
        return singleItem;
    }

    public void setSingleItem(boolean singleItem) {
        this.singleItem = singleItem;
    }
}
