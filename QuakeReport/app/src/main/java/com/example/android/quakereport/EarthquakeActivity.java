/*
 * Copyright (C) 2016 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.android.quakereport;

import android.content.AsyncTaskLoader;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

public class EarthquakeActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<ArrayList<EarthquakeData>> {

    public static final String LOG_TAG = EarthquakeActivity.class.getName();

    QuakeAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.earthquake_activity);

        // Create a fake list of earthquake locations.
//        final ArrayList<EarthquakeData> earthquakes = QueryUtils.extractEarthquakes();

        // Find a reference to the {@link ListView} in the layout

        getSupportLoaderManager().initLoader(1, null,this).forceLoad();

    }


    @Override
    public Loader<ArrayList<EarthquakeData>> onCreateLoader(int id, Bundle args) {
        return new EarthquakeLoader(this);
    }

    @Override
    public void onLoadFinished(Loader<ArrayList<EarthquakeData>> loader, ArrayList<EarthquakeData> data) {
        displayEarthquakes(data);
    }

    @Override
    public void onLoaderReset(Loader<ArrayList<EarthquakeData>> loader) {
            adapter.clear();
    }

    void displayEarthquakes(final ArrayList<EarthquakeData> earthquakes)
    {

        ListView earthquakeListView = (ListView) findViewById(R.id.list);

        earthquakeListView.setDividerHeight(0);

        // Create a new {@link ArrayAdapter} of earthquakes

        adapter = new QuakeAdapter(this, earthquakes);

        // Set the adapter on the {@link ListView}
        // so the list can be populated in the user interface
        earthquakeListView.setAdapter(adapter);

        earthquakeListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                EarthquakeData quakeData = earthquakes.get(position);

                Intent browserIntent = null;

                browserIntent = new Intent(Intent.ACTION_VIEW);

                browserIntent.setData(Uri.parse(quakeData.getUrl()));

                if(browserIntent.resolveActivity(getPackageManager()) != null)
                {
                    startActivity(browserIntent);
                }

            }
        });

    }

}
