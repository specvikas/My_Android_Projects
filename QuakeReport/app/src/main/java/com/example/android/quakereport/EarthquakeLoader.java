package com.example.android.quakereport;

import android.support.v4.content.AsyncTaskLoader;
import android.content.Context;

import com.example.android.quakereport.EarthquakeData;
import com.example.android.quakereport.QueryUtils;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;



public class EarthquakeLoader extends AsyncTaskLoader<ArrayList<EarthquakeData>>
{

    public static final String SAMPLE_WEB_URL = "https://earthquake.usgs.gov/fdsnws/event/1/query?format=geojson&eventtype=earthquake&orderby=time&minmag=6&limit=10";

    public EarthquakeLoader(Context context) {
        super(context);
    }

    @Override
    public ArrayList<EarthquakeData> loadInBackground() {

        URL url = QueryUtils.createUrl(SAMPLE_WEB_URL);

        String jsonResponse = "";

        try {
            jsonResponse = QueryUtils.makeHttpRequest(url);
        } catch (IOException e) {
            e.printStackTrace();
        }

        ArrayList<EarthquakeData> earthquakes = QueryUtils.extractEarthquakes(jsonResponse);

        return earthquakes;
    }
//
//    @Override
//    protected void onPostExecute(ArrayList<EarthquakeData> earthquakeData) {
//
//        displayEarthquakes(earthquakeData);
//    }
}
