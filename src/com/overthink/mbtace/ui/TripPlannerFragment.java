package com.overthink.mbtace.ui;

import android.app.Fragment;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.overthink.mbtace.R;
import com.overthink.mbtace.model.directions.Trip;
import com.overthink.mechmaid.util.Toaster;
import com.overthink.mechmaid.webservices.WebServiceResponse;
import com.overthink.mechmaid.webservices.WebServiceUtils;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * Created by Steve on 3/2/14.
 */
public class TripPlannerFragment extends Fragment {

    private static final String TAG = TripPlannerFragment.class.getName();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View tripPlannerLayout = inflater.inflate(R.layout.fragment_trip_planner, container, false);
        new GetRoutesWebCall("South Station Boston", "1 S Point Drive Dorchester", null ,null).execute();
        return tripPlannerLayout;
    }

    /*
     * Method to convert web response to List of Routes
     *
     * Must take String as param instead of JSONObject due to GSON parameterized restrictions
     */
    private void loadRouteList(Trip trip){



    }

    public class GetRoutesWebCall extends AsyncTask<Void, Void, WebServiceResponse> {

        // Class name (for debug and more)
        public final String TAG = GetRoutesWebCall.class.getName();
        // Web service URL
        public static final String URL = "http://maps.googleapis.com/maps/api/directions/json?";
        public String webServiceUrl;
        public static final String GOOGLE_API_KEY = "AIzaSyC1WsI7T1FFmM0fMhZ-KEuui4me9BbfhTw";
        public static final String UTF8 = "UTF-8";
        // Progress dialog displayed while awaiting web service response
        ProgressDialog progressDialog;

        public GetRoutesWebCall(String origin, String destination, String arrivalTime, String destinationTime) {
            StringBuilder builder = new StringBuilder(URL);
            builder.append("origin=" + origin + "&destination=" + destination + "&sensor=false");
            try {
                webServiceUrl = URLEncoder.encode("http://maps.googleapis.com/maps/api/directions/json?origin=South+Station+Boston&destination=1+S+Point+Drive+Dorchester&sensor=false", UTF8);
            } catch (UnsupportedEncodingException e) {
                Log.e(TAG, "Failed to encode web service URL", e);
            }
        }

        /**
         * Runs on the UI thread before {@link #doInBackground} to show progress dialog while web call
         * is being made.
         */
        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            // Show progress dialog
            progressDialog = new ProgressDialog(TripPlannerFragment.this.getActivity());
            progressDialog.setMessage("testing");
            progressDialog.setIndeterminate(true);
            progressDialog.show();
        }

        /**
         * Makes the web service request, passing the web service response to {@link
         *
         * @return Web service response data
         */
        @Override
        protected WebServiceResponse doInBackground(Void... v) {
            return  WebServiceUtils.makeHttpGetRequestWith("http://maps.googleapis.com/maps/api/directions/json?origin=South+Station+Boston&destination=1+S+Point+Drive+Dorchester&sensor=false");
        }


        /**
         * Calls back to the fragment to store the list of routes.
         *
         * @param result Web service response data
         */
        @Override
        protected void onPostExecute(WebServiceResponse result) {
            super.onPostExecute(result);

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
            TripPlannerFragment.this.loadRouteList(trip);
        }
    }

}