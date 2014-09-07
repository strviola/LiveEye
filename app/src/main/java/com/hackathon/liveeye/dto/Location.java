package com.hackathon.liveeye.dto;

/**
 * Created by yorikane_takayuki on 2014/09/07.
 */
public class Location {

    public double latitude;
    public double longitude;
    public float speed;
    public long time;
    public double altitude;

    @Override
    public String toString() {
        return String.format("Latitude=%.2f, Longitude=%.2f, Speed=%.2f, Time=%.2f, Altitude=%.2f",
                this.latitude, this.longitude, this.speed, this.time, this.altitude);
    }
}
