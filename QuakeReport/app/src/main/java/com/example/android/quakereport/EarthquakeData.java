package com.example.android.quakereport;

public class EarthquakeData
{
    private String magnitude;
    private String city;
    private String landmark;
    private String date;
    private String time;
    private String url;

    public EarthquakeData(String magnitude, String landmark,String city, String date, String time, String url)
    {
        this.magnitude = magnitude;
        this.city = city;
        this.landmark = landmark;
        this.date = date;
        this.time = time;
        this.url = url;
    }

    public String getMagnitude()
    {
        return magnitude;
    }

    public String getCity()
    {
        return city;
    }

    public String getLandmark(){ return  landmark; }

    public String getDate()
    {
        return date;
    }

    public String getTime() { return time; }

    public String getUrl() { return url; }

}
