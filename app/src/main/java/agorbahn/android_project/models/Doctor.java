package agorbahn.android_project.models;

/**
 * Created by Adam on 12/2/2016.
 */

import org.parceler.Parcel;
import java.util.ArrayList;

@Parcel
public class Doctor {
    private String mName;
    private String mSpecialty;
    private ArrayList<String> mPhone = new ArrayList<>();
    private double mLatitude;
    private double mLongitude;
    private String mStreet;
    private String mStreet2;
    private String mCity;
    private String mState;
    private String mZip;
    private String mBio;
    private String pushID;

    public Doctor() {}

    public Doctor(String mName, String mSpecialty, ArrayList<String> mPhone, double mLatitude, double mLongitude, String mStreet, String mStreet2, String mCity, String mState, String mZip, String mBio) {
        this.mName = mName;
        this.mSpecialty = mSpecialty;
        this.mPhone = mPhone;
        this.mLatitude = mLatitude;
        this.mLongitude = mLongitude;
        this.mStreet = mStreet;
        this.mStreet2 = mStreet2;
        this.mCity = mCity;
        this.mState = mState;
        this.mZip = mZip;
        this.mBio = mBio;
    }

    public String getName() {
        return mName;
    }

    public String getSpecialty() {
        return mSpecialty;
    }

    public ArrayList<String> getPhone() {
        return mPhone;
    }

    public double getLatitude() {
        return mLatitude;
    }

    public double getLongitude() {
        return mLongitude;
    }

    public String getStreet() {
        return mStreet;
    }

    public String getStreet2() {
        return mStreet2;
    }

    public String getCity() {
        return mCity;
    }

    public String getState() {
        return mState;
    }

    public String getZip() {
        return mZip;
    }

    public String getBio() {
        return mBio;
    }

    public String getPushID() {
        return pushID;
    }

    public void setPushID(String pushID) {
        this.pushID = pushID;
    }
}