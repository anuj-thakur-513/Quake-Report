package com.example.android.quakereport;

import android.app.Activity;
import android.graphics.drawable.GradientDrawable;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class EarthquakeAdapter extends ArrayAdapter<Earthquake> {

//    Location Separator
    private static final String LOCATION_SEPARATOR = " of ";

//    Constructor
    public EarthquakeAdapter(Activity context, ArrayList<Earthquake> earthquakes){
        super(context,0,earthquakes);
    }

//    Helper methods
//    Return the formatted date String from a Date object
    private String formatDate(Date dateObject){
        SimpleDateFormat dateFormat = new SimpleDateFormat("LLL dd, yyyy");
        return dateFormat.format(dateObject);
    }

//    Return the formatted time String from a Date object
    private String formatTime(Date dateObject){
        SimpleDateFormat timeFormat = new SimpleDateFormat("h:mm a");
        return timeFormat.format(dateObject);
    }

//    Returns the formatted magnitude
    private String formatMagnitude(double magnitude){
        DecimalFormat magnitudeFormat = new DecimalFormat("0.0");
        return magnitudeFormat.format(magnitude);
    }

//    Set the magnitude color according to the magnitude
    private int getMagnitudeColor(double magnitude){
        int magnitudeColorResourceId;
        int magnitudeFloor = (int) Math.floor(magnitude);
        switch(magnitudeFloor){
            case 0:
            case 1:
                magnitudeColorResourceId = R.color.magnitude1;
                break;
            case 2:
                magnitudeColorResourceId = R.color.magnitude2;
                break;
            case 3:
                magnitudeColorResourceId = R.color.magnitude3;
                break;
            case 4:
                magnitudeColorResourceId = R.color.magnitude4;
                break;
            case 5:
                magnitudeColorResourceId = R.color.magnitude5;
                break;
            case 6:
                magnitudeColorResourceId = R.color.magnitude6;
                break;
            case 7:
                magnitudeColorResourceId = R.color.magnitude7;
                break;
            case 8:
                magnitudeColorResourceId = R.color.magnitude8;
                break;
            case 9:
                magnitudeColorResourceId = R.color.magnitude9;
                break;
            default:
                magnitudeColorResourceId = R.color.magnitude10plus;
                break;
        }
        return ContextCompat.getColor(getContext(),magnitudeColorResourceId);
    }

//    Overriding getView Method to set the view as our own created view
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItemView = convertView;

        if(listItemView == null){
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.earthquake_list_item,parent,false);
        }

//        Getting the current item from the ArrayList
        Earthquake currentData = getItem(position);

//        Set magnitude of the earthquake
        TextView magnitudeText = listItemView.findViewById(R.id.magnitude);
//        Format the magnitude to 1 place
        String formattedMagnitude = formatMagnitude(currentData.getMagnitude());
        magnitudeText.setText(formattedMagnitude);

//        Get the original location
        String originalLocation = currentData.getLocation();
//        Variables to store the data
        String primaryLocation, locationOffset;

//        Separate the location
        if(originalLocation.contains(LOCATION_SEPARATOR)){
            String[] parts = originalLocation.split(LOCATION_SEPARATOR);
            locationOffset = parts[0] + LOCATION_SEPARATOR;
            primaryLocation = parts[1];
        }else{
            locationOffset = getContext().getString(R.string.near_the);
            primaryLocation = originalLocation;
        }

//        Set the location in the TextViews
        TextView primaryLocationText =(TextView) listItemView.findViewById(R.id.primary_location);
        TextView locationOffsetText =(TextView) listItemView.findViewById(R.id.location_offset);

        primaryLocationText.setText(primaryLocation);
        locationOffsetText.setText(locationOffset);

//        Create a new Date object from the time in milliseconds
        Date date = new Date(currentData.getTimeInMilliseconds());

//        Set the date of the earthquake
        TextView dateText = listItemView.findViewById(R.id.date);
//        Format the date String (i.e. "Mar 3, 1984")
        String formattedDate  = formatDate(date);
//        Display date in the TextView
        dateText.setText(formattedDate);

//        Set the time of the earthquake
        TextView timeText = (TextView) listItemView.findViewById(R.id.time);
//        Format the time string (i.e. "4:40 PM")
        String formattedTime = formatTime(date);
//        Display time in the TextView
        timeText.setText(formattedTime);


//        Set the proper background color on the magnitude circle
//        Fetch the background from the TextView, which is a GradientDrawable.
        GradientDrawable magnitudeCircle = (GradientDrawable) magnitudeText.getBackground();

//        Get the appropriate background color based on the current earthquake
        int magnitudeColor = getMagnitudeColor(currentData.getMagnitude());

//        Set the color on the magnitude circle
        magnitudeCircle.setColor(magnitudeColor);

        return listItemView;
    }
}
