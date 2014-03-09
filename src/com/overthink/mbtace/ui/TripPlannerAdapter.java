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

        View tripStepView = currentView;
        TripPlannerViewHolder holder = null;

        if(currentView == null){
            Log.d(TAG, "No recyclable custom view found. New view created.");
            LayoutInflater inflater = ((Activity) context).getLayoutInflater();
            tripStepView = inflater.inflate(viewResourceId, parent, false);

            holder = new TripPlannerViewHolder();
            holder.stepTxt = (TextView) tripStepView.findViewById(R.id.trip_planner_txt_route);
        }

        else {
            // get the recycled view (stored in tag)
            Log.d(TAG, "View recycled");
            holder = (TripPlannerViewHolder)tripStepView.getTag();
        }

        //construct String for TextView
        Step step = legs.get(position);
        String directions = step.getHtmlInstructions();

        holder.stepTxt.setText(Html.fromHtml(directions));

        Log.d(TAG, "Step data initialized");

        return tripStepView;
    }

    private class TripPlannerViewHolder {
        TextView stepTxt;
    }

}
