package com.overthink.mbtace.ui;

import android.app.Fragment;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.overthink.mbtace.R;
import com.overthink.mbtace.model.directions.Leg;
import com.overthink.mbtace.model.directions.Step;
import com.overthink.mbtace.model.directions.Trip;
import com.overthink.mechmaid.util.Toaster;
import com.overthink.mechmaid.webservices.WebServiceResponse;
import com.overthink.mechmaid.webservices.WebServiceUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Steve on 3/2/14.
 */
public class TripPlannerFragment extends Fragment {

    private static final String TAG = TripPlannerFragment.class.getName();
    private List<Step> steps = new ArrayList<Step>();
    private ListView listViewTripPlanner;
    private String startAddress = "Los Angeles, California";
    private String endAddress = "1 South Point Drive, Boston";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //Inflate layout from XML
        View tripPlannerLayout = inflater.inflate(R.layout.fragment_trip_planner, container, false);

        //create references to View objects
        listViewTripPlanner = (ListView) tripPlannerLayout.findViewById(R.id.trip_planner_list_view);
        final EditText editTextStart = (EditText) tripPlannerLayout.findViewById(R.id.trip_planner_edit_text_start);
        final EditText editTextEnd = (EditText) tripPlannerLayout.findViewById(R.id.trip_planner_edit_text_end);

        // Setup click event for Search button
        Button btnSearch = (Button) tripPlannerLayout.findViewById(R.id.trip_planner_btn);
        btnSearch.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                //make web call with info from two text boxes
                Log.d(TAG, "Search Button Pressed");
                new GetRoutesWebCall(editTextStart.getText().toString(),editTextEnd.getText().toString(), null ,null).execute();
            }
        });

        //new GetRoutesWebCall(removeSpaces(startAddress), removeSpaces(endAddress), null ,null).execute();

        return tripPlannerLayout;
    }

    /**
     * Method to convert Trip from web response to List of Legs
     *
     * @param trip to be broken down into a List of Legs
     */
    private void loadStepList(Trip trip){

        //get all Steps from all Legs of route 0 and create a List of Steps
        List<Leg> legs = trip.getRoutes().get(0).getLegs();
        for(int i=0; i<legs.size(); i++)
            steps.addAll(legs.get(i).getSteps());

        TripPlannerAdapter adapter = new TripPlannerAdapter(getActivity(), R.layout.item_list_trip_planner, steps);

        listViewTripPlanner.setAdapter(adapter);
    }

    /**
     * Method to remove spaces (' ') and replaces with plus signs ('+')
     *
     * @param string with ' ' to be converted
     * @return string with ' ' converted to '+'
     */
    private String removeSpaces(String string){
        return string.replace(' ', '+');
    }

    public class GetRoutesWebCall extends AsyncTask<Void, Void, WebServiceResponse> {

        // Class name (for debug and more)
        public final String TAG = GetRoutesWebCall.class.getName();
        // Web service URL
        public static final String URL = "http://maps.googleapis.com/maps/api/directions/json?";
        //String to store final URL after building
        public String webServiceUrl;
        public static final String GOOGLE_API_KEY = "AIzaSyC1WsI7T1FFmM0fMhZ-KEuui4me9BbfhTw";
        public static final String UTF8 = "UTF-8";
        // Progress dialog displayed while awaiting web service response
        ProgressDialog progressDialog;

        public GetRoutesWebCall(String origin, String destination, String arrivalTime, String departureTime) {

            //ensure text fields were entered and build up URL from params
            if(origin.isEmpty() != true && destination.isEmpty() != true){
                StringBuilder builder = new StringBuilder(URL);
                builder.append("origin=" + removeSpaces(origin));
                builder.append("&destination=" + removeSpaces(destination));
                if(arrivalTime != null)
                    builder.append("&arrival_time=" + arrivalTime);
                if(departureTime != null)
                    builder.append("&departure_time=" + departureTime);
                builder.append("&sensor=false");

                //TODO: Figure out why web call fails when API key is present
                //builder.append("&key=" + GOOGLE_API_KEY);

                webServiceUrl = builder.toString();
            }
        }

        /**
         * Runs on the UI thread before {@link #doInBackground} to show progress dialog while web call
         * is being made.
         */
        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            // Show progress dialog only if URL is built correctly
            if(webServiceUrl != null) {
                progressDialog = new ProgressDialog(TripPlannerFragment.this.getActivity());
                progressDialog.setMessage("testing");
                progressDialog.setIndeterminate(true);
                progressDialog.show();
            }
        }

        /**
         * Makes the web service request, passing the web service response to {@link
         *
         * @return Web service response data
         */
        @Override
        protected WebServiceResponse doInBackground(Void... v) {
            //if URL is not built, return null
            if(webServiceUrl == null)
                return null;

            Log.d(TAG, "Web call made with: " + webServiceUrl);
            return  WebServiceUtils.makeHttpGetRequestWith(webServiceUrl);
        }


        /**
         * Calls back to the fragment to store the list of routes.
         *
         * @param result Web service response data
         */
        @Override
        protected void onPostExecute(WebServiceResponse result) {
            super.onPostExecute(result);

            if(result == null) {
                Toaster.showToastFromString(TripPlannerFragment.this.getActivity(), "Please enter text in fields");
                return;
            }

            // Close the progress dialog
            if (progressDialog.isShowing()) {
                progressDialog.dismiss();
            }

            // If web service fails
            if (result.getException() != null) {
                // Notify user and prevent callback
                Toaster.showToastFromString(TripPlannerFragment.this.getActivity(), "Web Call Failed");
                Log.e(TAG, "Exception(s) occurred in web service call:", result.getException());
                return;
            }

            //create gson object with correct field naming policy
            Gson gson = new GsonBuilder()
                    .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                    .create();

            Trip trip = gson.fromJson(result.getRawResponseBody(), Trip.class);
            Log.d(TAG, trip.toString());

            //pass Trip to callback method
            TripPlannerFragment.this.loadStepList(trip);
        }
    }

}