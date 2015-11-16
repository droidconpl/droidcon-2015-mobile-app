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
    private static final String WEBSITE_TITLE = "website_title";
    private static final String WEBSITE_LINK = "website_link";
    private static final String FACEBOOK_LINK = "facebook_link";
    private static final String TWITTER_HANDLER = "twitter_handler";
    private static final String GITHUB_LINK = "github_link";
    private static final String LINKED_IN = "linked_in";
    private static final String GOOGLE_PLUS = "google_plus";

    public int id;
    public String firstName;
    public String lastName;
    public String websiteTitle;
    public String bio;
    public String websiteLink;
    public String facebookLink;
    public String twitterHandler;
    public String githubLink;
    public String linkedIn;
    public String googlePlus;
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
        websiteTitle = bundle.getString(WEBSITE_TITLE);
        websiteLink = bundle.getString(WEBSITE_LINK);
        facebookLink = bundle.getString(FACEBOOK_LINK);
        twitterHandler = bundle.getString(TWITTER_HANDLER);
        githubLink = bundle.getString(GITHUB_LINK);
        linkedIn = bundle.getString(LINKED_IN);
        googlePlus = bundle.getString(GOOGLE_PLUS);
    }

    @Override
    public String toString() {
        return "Speaker{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", websiteTitle='" + websiteTitle + '\'' +
                ", bio='" + bio + '\'' +
                ", websiteLink='" + websiteLink + '\'' +
                ", facebookLink='" + facebookLink + '\'' +
                ", twitterHandler='" + twitterHandler + '\'' +
                ", githubLink='" + githubLink + '\'' +
                ", linkedIn='" + linkedIn + '\'' +
                ", googlePlus='" + googlePlus + '\'' +
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
        bundle.putString(WEBSITE_TITLE, websiteTitle);
        bundle.putString(WEBSITE_LINK, websiteLink);
        bundle.putString(FACEBOOK_LINK, facebookLink);
        bundle.putString(TWITTER_HANDLER, twitterHandler);
        bundle.putString(GITHUB_LINK, githubLink);
        bundle.putString(LINKED_IN, linkedIn);
        bundle.putString(GOOGLE_PLUS, googlePlus);
        dest.writeBundle(bundle);
    }
}
