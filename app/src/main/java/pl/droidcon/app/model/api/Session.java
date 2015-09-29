package pl.droidcon.app.model.api;

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

public class Session {

    public long id;
    public DateTime date;
    public String title;
    public String description;
    public double rating;

    @SerializedName("people")
    public List<Integer> speakers = new ArrayList<>();

    @Override
    public String toString() {
        return "Session{" +
                "id=" + id +
                ", date=" + date +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", rating=" + rating +
                ", speakers=" + speakers +
                '}';
    }
}
