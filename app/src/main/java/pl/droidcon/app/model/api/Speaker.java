package pl.droidcon.app.model.api;


import java.util.ArrayList;
import java.util.List;

public class Speaker {

    public long id;
    public String firstName;
    public String lastName;
    public String bio;
    public String imageUrl;
    public List<Integer> sessions = new ArrayList<>();

    @Override
    public String toString() {
        return "Speaker{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", bio='" + bio + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", sessions=" + sessions +
                '}';
    }
}
