package com.example.android.quakereport;

public class Earthquake {
//    Magnitude of earthquake
    private double mMagnitude;

//    Location of earthquake
    private String mLocation;

//   Time of earthquake
    private long mTimeInMilliseconds;

//    URL of the website
    private String mUrl;

//    Constructor
    public Earthquake(double magnitude, String location, long date, String url){
        mMagnitude = magnitude;
        mLocation = location;
        mTimeInMilliseconds = date;
        mUrl = url;
    }

//    Getters for the data
    public double getMagnitude(){
        return mMagnitude;
    }

    public String getLocation(){
        return mLocation;
    }

    public long getTimeInMilliseconds(){
        return mTimeInMilliseconds;
    }

    public String getUrl(){ return mUrl; }
}
