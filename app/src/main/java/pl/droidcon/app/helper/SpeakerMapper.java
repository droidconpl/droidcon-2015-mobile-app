package pl.droidcon.app.helper;


import java.util.ArrayList;
import java.util.List;

import io.realm.RealmList;
import pl.droidcon.app.model.api.Speaker;
import pl.droidcon.app.model.db.RealmSpeaker;

public class SpeakerMapper implements Mapper<Speaker, RealmSpeaker> {

    @Override
    public RealmSpeaker map(Speaker speaker) {
        RealmSpeaker realmSpeaker = new RealmSpeaker();
        realmSpeaker.setId(speaker.id);
        realmSpeaker.setFirstName(speaker.firstName);
        realmSpeaker.setLastName(speaker.lastName);
        realmSpeaker.setBio(speaker.bio);
        realmSpeaker.setImageUrl(speaker.imageUrl);
        return realmSpeaker;
    }

    @Override
    public RealmList<RealmSpeaker> mapList(List<Speaker> speakers) {
        RealmList<RealmSpeaker> realmSpeakers = new RealmList<>();
        for (Speaker speaker : speakers) {
            realmSpeakers.add(map(speaker));
        }
        return realmSpeakers;
    }

    @Override
    public RealmList<RealmSpeaker> matchFromApi(List<RealmSpeaker> databaseObjects, List<Integer> ids) {
        RealmList<RealmSpeaker> realmSpeakers = new RealmList<>();
        for (Integer id : ids) {
            for (RealmSpeaker orig : databaseObjects) {
                if (id.equals(orig.getId())) {
                    realmSpeakers.add(orig);
                }
            }
        }
        return realmSpeakers;
    }

    @Override
    public List<Speaker> fromDBList(List<RealmSpeaker> realmSpeakers) {
        List<Speaker> speakers = new ArrayList<>();

        for (RealmSpeaker realmSpeaker : realmSpeakers) {
            Speaker speaker = new Speaker();
            speaker.id = realmSpeaker.getId();
            speaker.firstName = realmSpeaker.getFirstName();
            speaker.lastName = realmSpeaker.getFirstName();
            speaker.bio = realmSpeaker.getBio();
            speaker.imageUrl = realmSpeaker.getImageUrl();

            speakers.add(speaker);
        }

        return speakers;
    }
}
