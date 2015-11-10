package pl.droidcon.app.model.api;


import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class SpeakerResponse {

    @SerializedName("people")
    public List<Speaker> speakers = new ArrayList<>();

    @Override
    public String toString() {
        return "SpeakerResponse{" +
                "speakersIds=" + speakers +
                '}';
    }
}
