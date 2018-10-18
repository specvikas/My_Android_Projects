package com.example.android.quakereport;

import android.util.Log;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import static android.content.ContentValues.TAG;

/**
 * Helper methods related to requesting and receiving earthquake data from USGS.
 */
public final class QueryUtils {

    private QueryUtils() {
    }

    public static URL createUrl(String urlName)
    {
        URL url;
        try
        {
            url = new URL(urlName);
        }catch(MalformedURLException e)
        {
            return null;
        }
        return url;
    }

    public static String makeHttpRequest(URL url) throws IOException {
        String jsonResponse = "";

        if(url == null)
        {
            return jsonResponse;
        }

        HttpURLConnection urlConnection = null;
        InputStream inputStream = null;
        try
        {
            urlConnection = (HttpURLConnection)url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.setReadTimeout(10000);
            urlConnection.setConnectTimeout(15000);
            urlConnection.connect();
            if(urlConnection.getResponseCode() == 200) {
                inputStream = urlConnection.getInputStream();
                jsonResponse = readFromStream(inputStream);
            }
        }catch (IOException e)
        {

        }
        finally {
            if(urlConnection != null)
                urlConnection.disconnect();
            if(inputStream != null)
                inputStream.close();
        }
        return jsonResponse;
    }

    private static String readFromStream(InputStream inputStream) throws IOException {
        StringBuilder jsonResponse = new StringBuilder();
        if(inputStream != null)
        {
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
            BufferedReader reader = new BufferedReader(inputStreamReader);
            String line = reader.readLine();
            while(line != null)
            {
                jsonResponse.append(line);
                line = reader.readLine();
            }
        }
        return jsonResponse.toString();
    }

    /** Sample JSON response for a USGS query */
    /**
     * Create a private constructor because no one should ever create a {@link QueryUtils} object.
     * This class is only meant to hold static variables and methods, which can be accessed
     * directly from the class name QueryUtils (and an object instance of QueryUtils is not needed).
     */

    /**
     * Return a list of {@link EarthquakeData} objects that has been built up from
     * parsing a JSON response.
     */
    public static ArrayList<EarthquakeData> extractEarthquakes( String SAMPLE_JSON_RESPONSE  ) {

        // Create an empty ArrayList that we can start adding earthquakes to
        ArrayList<EarthquakeData> earthquakes = new ArrayList<>();

        JSONObject root;

        JSONObject inFeaturesArray;

        JSONObject properties;

        JSONArray features;
        // Try to parse the SAMPLE_JSON_RESPONSE. If there's a problem with the way the JSON
        // is formatted, a JSONException exception object will be thrown.
        // Catch the exception so the app doesn't crash, and print the error message to the logs.
        try {

            // TODO: Parse the response given by the SAMPLE_JSON_RESPONSE string and
            // build up a list of Earthquake objects with the corresponding data.

            root = new JSONObject(SAMPLE_JSON_RESPONSE);

            features = root.getJSONArray("features");

            for(int i=0; i<features.length(); i++)
            {
                inFeaturesArray = features.getJSONObject(i);

                properties = inFeaturesArray.getJSONObject("properties");

                String city = "";

                String landmark = "";

                String magnitude = String.valueOf(properties.getDouble("mag"));

                String[] place = properties.getString("place").split("\\s");

                if(place.length > 2 && place[2].equals("of"))
                {
                    landmark = place[0].toUpperCase() + " " + place[1] + " " + place[2].toUpperCase() ;

                    for(int j=3; j < place.length ; j++ ) {
                        city += place[j] + " ";
                    }
                }
                else
                {
                    for(int j=0 ; j<place.length; j++)
                    {
                        city += place[j] + " ";
                    }
                }

//                String[] date2 = getDate(properties.getLong("time")).split("\n");

//                Log.i("QueryUtils", date2[0] + " & " + date2[1] );

                String[] date = getDate(properties.getLong("time")).split("\n");

                String url = properties.getString("url");

                earthquakes.add(new EarthquakeData(magnitude,landmark,city,date[0], date[1], url));
            }

        } catch (JSONException e) {
            // If an error is thrown when executing any of the above statements in the "try" block,
            // catch the exception here, so the app doesn't crash. Print a log message
            // with the message from the exception.
            Log.e("QueryUtils", "Problem parsing the earthquake JSON results", e);
        }

        // Return the list of earthquakes
        return earthquakes;
    }

    private static String getDate(Long time)
    {
        DateFormat formatter = new SimpleDateFormat("MMM d, yyyy");

        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(time);
        String date = formatter.format(calendar.getTime());

        formatter = new SimpleDateFormat("HH:mm a");
        date += " \n"+ formatter.format(calendar.getTime());

        return date;
    }

}