package com.example.android.quakereport;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class QuakeAdapter extends ArrayAdapter<EarthquakeData>
{
    private Context mContext;

    private ArrayList<EarthquakeData> quakeData;

    public QuakeAdapter(Context context, ArrayList<EarthquakeData> earthquakeData)
    {
        super(context, 0,earthquakeData);
        mContext = context;
        quakeData = earthquakeData;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View listItemView = convertView;

        if(listItemView == null)
            listItemView = LayoutInflater.from(mContext).inflate(R.layout.list_items,parent,false);

        EarthquakeData data = getItem(position);

        TextView quakeMagnitude = (TextView)listItemView.findViewById(R.id.magnitude);
        if(Float.parseFloat(data.getMagnitude())<7)
            quakeMagnitude.setBackgroundResource(R.drawable.circle);
        else
            quakeMagnitude.setBackgroundResource(R.drawable.circle_dark);

        quakeMagnitude.setText(data.getMagnitude());

        TextView quakeLandmark = (TextView)listItemView.findViewById(R.id.landmark);
        quakeLandmark.setText(data.getLandmark());

        TextView quakeCity = (TextView)listItemView.findViewById(R.id.city);
        quakeCity.setText(data.getCity());

        TextView quakeDate = (TextView)listItemView.findViewById(R.id.date);
        quakeDate.setText(data.getDate());

        TextView quakeTime =(TextView)listItemView.findViewById(R.id.waqt);
        quakeTime.setText(data.getTime());


        return listItemView;
    }
}
