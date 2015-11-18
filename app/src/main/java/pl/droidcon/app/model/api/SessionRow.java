package pl.droidcon.app.model.api;


import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.util.Log;

import com.google.gson.annotations.SerializedName;

import org.joda.time.DateTime;

import java.util.ArrayList;
import java.util.List;

public class SessionRow {

    public static final int ID_DELTA = 1000;

    @SerializedName("Id")
    public int id;

    public int dayId;

    @SerializedName("speakerIDs")
    public List<List<Integer>> speakerIds;

    public DateTime sessionDate;

    public String sessionDisplayHour;

    public List<String> sessionTitle;

    public List<String> sessionDescription;

    public List<Integer> roomId;


    public static List<Session> toSessions(@NonNull SessionRow sessionRow) {
        List<Session> sessions = new ArrayList<>();

        Session leftSession = new Session();
        leftSession.date = sessionRow.sessionDate;
        leftSession.id = sessionRow.id;
        leftSession.dayId = sessionRow.dayId;
        leftSession.sessionDisplayHour = sessionRow.sessionDisplayHour;

        leftSession.title = sessionRow.sessionTitle.get(0);
        leftSession.description = sessionRow.sessionDescription.get(0);
        leftSession.speakersIds = (ArrayList<Integer>) sessionRow.speakerIds.get(0);
        leftSession.roomId = sessionRow.roomId.get(0);


        Session rightSession = new Session();
        rightSession.date = sessionRow.sessionDate;
        rightSession.id = sessionRow.id + ID_DELTA;
        rightSession.dayId = sessionRow.dayId;
        rightSession.sessionDisplayHour = sessionRow.sessionDisplayHour;

        rightSession.title = sessionRow.sessionTitle.get(1);
        rightSession.description = sessionRow.sessionDescription.get(1);
        rightSession.speakersIds = (ArrayList<Integer>) sessionRow.speakerIds.get(1);
        rightSession.roomId = sessionRow.roomId.get(1);


        if (!TextUtils.isEmpty(rightSession.title)) {
            sessions.add(rightSession);
        } else {
            leftSession.singleItem = true;
        }

        sessions.add(leftSession);
        return sessions;
    }
}
