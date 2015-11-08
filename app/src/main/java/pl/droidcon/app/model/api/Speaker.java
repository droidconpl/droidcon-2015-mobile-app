package pl.droidcon.app.model.api;


import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

public class Speaker implements Parcelable {

    public static final Creator<Speaker> CREATOR = new Creator<Speaker>() {
        @Override
        public Speaker createFromParcel(Parcel in) {
            return new Speaker(in);
        }

        @Override
        public Speaker[] newArray(int size) {
            return new Speaker[size];
        }
    };

    private static final String ID = "id";
    private static final String FIRST_NAME = "first_name";
    private static final String LAST_NAME = "last_name";
    private static final String BIO = "bio";
    private static final String IMAGE_URL = "image_url";

    public int id;
    public String firstName;
    public String lastName;
    public String bio;
    public String imageUrl;
    public List<Integer> sessions = new ArrayList<>();

    public Speaker() {

    }

    private Speaker(Parcel in) {
        Bundle bundle = in.readBundle(getClass().getClassLoader());
        id = bundle.getInt(ID);
        firstName = bundle.getString(FIRST_NAME);
        lastName = bundle.getString(LAST_NAME);
        bio = bundle.getString(BIO);
        imageUrl = bundle.getString(IMAGE_URL);
    }


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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        Bundle bundle = new Bundle(getClass().getClassLoader());
        bundle.putInt(ID, id);
        bundle.putString(FIRST_NAME, firstName);
        bundle.putString(LAST_NAME, lastName);
        bundle.putString(BIO, bio);
        bundle.putString(IMAGE_URL, imageUrl);
        dest.writeBundle(bundle);
    }
}
