package pl.droidcon.app.model.api;

public class Session {


// "speakerFirstName": "Johnny",
// "speakerLastName": "Smith",
// "speakerBio": "From Bonston MA",
// "speakerPhoto": "https://gravatar.com/avatar/65778?d=retro",
// "sessionTopic": "Johny talks about ....",
// "sessionDate": "2015-09-17 06:58:21",
// "sessionDescription": "talks about his food habbits in the past"

    public String speakerFirstName;
    public String speakerLastName;
    public String speakerBio;
    public String speakerPhoto;
    public String sessionTopic;
    public String sessionDate;
    public String sessionDescription;


    @Override
    public String toString() {
        return "Session{" +
                "speakerFirstName='" + speakerFirstName + '\'' +
                ", speakerLastName='" + speakerLastName + '\'' +
                ", speakerBio='" + speakerBio + '\'' +
                ", speakerPhoto='" + speakerPhoto + '\'' +
                ", sessionTopic='" + sessionTopic + '\'' +
                ", sessionDate=" + sessionDate +
                ", sessionDescription='" + sessionDescription + '\'' +
                '}';
    }
}
