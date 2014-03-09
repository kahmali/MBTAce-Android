package com.overthink.mbtace.ui;

import android.app.Activity;
import android.content.Context;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import com.overthink.mbtace.R;
import com.overthink.mbtace.model.directions.Step;
import junit.framework.Test;
import org.w3c.dom.Text;

import java.util.List;

/**
 * Created by Steve on 3/8/14.
 */
public class TripPlannerAdapter extends ArrayAdapter<Step> {

    private static final String TAG = TripPlannerAdapter.class.getName();

    private Context context;
    private int viewResourceId;
    private List<Step> legs;

    public TripPlannerAdapter(Context context, int viewResourceId, List<Step> legs){
        super(context, viewResourceId, legs);

        this.context = context;
        this.viewResourceId = viewResourceId;
        this.legs = legs;
    }

    public View getView(int position, View currentView, ViewGroup parent){

        TripPlannerViewHolder holder = null;

        if(currentView == null){
            Log.d(TAG, "No recyclable custom view found. New view created.");
            LayoutInflater inflater = ((Activity) context).getLayoutInflater();
            currentView = inflater.inflate(viewResourceId, parent, false);

            //create holder and references to TextView's
            holder = new TripPlannerViewHolder();
            holder.stepDescription = (TextView) currentView.findViewById(R.id.trip_planner_txt_description);
            holder.stepDistance = (TextView) currentView.findViewById(R.id.trip_planner_txt_distance);
            holder.stepDuration = (TextView) currentView.findViewById(R.id.trip_planner_txt_duration);
            holder.stepStart = (TextView) currentView.findViewById(R.id.trip_planner_txt_start);
            holder.stepEnd = (TextView) currentView.findViewById(R.id.trip_planner_txt_end);

            currentView.setTag(holder);
        }

        else {
            // get the recycled view (stored in tag)
            Log.d(TAG, "View recycled");
            holder = (TripPlannerViewHolder)currentView.getTag();
        }

        //construct Strings for TextView
        Step step = legs.get(position);
        String directions = "Directions: " + step.getHtmlInstructions();
        String duration = "Duration: " + step.getDuration().getText();
        String distance = "Distance: " + step.getDistance().getText();
        String start = "Start: Lat " + String.valueOf(step.getStartLocation().getLat());
        start = start.concat(" Lng " + String.valueOf(step.getStartLocation().getLng()));
        String end = "End: Lat " + String.valueOf(step.getEndLocation().getLat());
        end = end.concat(" Lng " + String.valueOf(step.getEndLocation().getLng()));

        holder.stepDescription.setText(Html.fromHtml(directions));
        holder.stepDuration.setText(duration);
        holder.stepDistance.setText(distance);
        holder.stepStart.setText(start);
        holder.stepEnd.setText(end);

        Log.d(TAG, "Step data initialized");

        return currentView;
    }

    private class TripPlannerViewHolder {
        TextView stepDescription;
        TextView stepDuration;
        TextView stepDistance;
        TextView stepStart;
        TextView stepEnd;
    }

}
