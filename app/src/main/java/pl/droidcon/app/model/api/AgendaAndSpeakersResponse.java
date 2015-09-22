package pl.droidcon.app.model.api;


import java.util.ArrayList;
import java.util.List;

public class AgendaAndSpeakersResponse {

    public List<AgendaAndSpeakers> agendaAndSpeakers = new ArrayList<>();

    public AgendaAndSpeakersResponse(AgendaResponse agendaResponse, SpeakerResponse speakerResponse){
        List<Session> sessions = agendaResponse.sessions;
        List<Speaker> speakers = speakerResponse.speakers;
        for (Session session : sessions) {
            Integer speakerId = session.speakers.get(0);
            Speaker firstSpeaker = findFirstSpeaker(speakerId, speakers);

            agendaAndSpeakers.add(new AgendaAndSpeakers(session.title,
                    firstSpeaker != null ? firstSpeaker.imageUrl : null));
        }
    }

    private Speaker findFirstSpeaker(Integer speakerId, List<Speaker> speakers){
        for (Speaker speaker : speakers) {
            if(speakerId == speaker.id){
                return speaker;
            }
        }
        return null;
    }

    public static class AgendaAndSpeakers {

        public String title;
        public String imageUrl;

        public AgendaAndSpeakers(String title, String imageUrl) {
            this.title = title;
            this.imageUrl = imageUrl;
        }
    }
}
