package io.android.sightsee;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.net.http.AndroidHttpClient;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.Toast;

public class HttpClientListActivity extends ListActivity {
	
	private String searchTerm;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		new HttpGetTask(HttpClientListActivity.this).execute();
		
	}

	private class HttpGetTask extends AsyncTask<Void, Void, PlaceToGo> {

		// Get your own user name at http://www.geonames.org/login
		private static final String BASE_URL = "http://sitecio.herokuapp.com/requests/requests.json?loc=";
		
		private Context mContext;
		
	    public HttpGetTask (Context context){
	         
	    	mContext = context;
	    }

		AndroidHttpClient mClient = AndroidHttpClient.newInstance("");

		@Override
		protected PlaceToGo doInBackground(Void... params) {
			Intent intent = getIntent();
			String cityName = intent.getStringExtra("cityName");
			
			HttpGet request = new HttpGet(BASE_URL+cityName);
			JSONResponseHandler responseHandler = new JSONResponseHandler();
			try {
				return mClient.execute(request, responseHandler);
			} catch (ClientProtocolException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			return null;
		}
		
		protected void onPostExecute(final PlaceToGo result) {
			if (null != mClient)
				mClient.close();
			setListAdapter(new ArrayAdapter<String>(
					HttpClientListActivity.this,
					R.layout.list_item, result.placeNames));
			
			final ListView lv = getListView();
			
			lv.setOnItemClickListener(new OnItemClickListener() {
				@Override
				public void onItemClick(AdapterView<?> parent, View view, int position,
	                    long id) {
					// TODO Auto-generated method stub
					String listItemString = lv.getItemAtPosition(position).toString();
					generateDialog(listItemString, result);
				}
			});
			
	}
		
		private class JSONResponseHandler implements ResponseHandler<PlaceToGo> {

			private static final String BUSINESSES_TAG = "businesses";
			private static final String NAME_TAG = "name";
			private static final String RATING_TAG = "rating";
			private static final String LOCATION_TAG = "location";

			@Override
			public PlaceToGo handleResponse(HttpResponse response)
					throws ClientProtocolException, IOException {
				PlaceToGo result = new PlaceToGo();
				String JSONResponse = new BasicResponseHandler()
						.handleResponse(response);
				try {

					// Get top-level JSON Object - a Mapn
					JSONObject responseObject = (JSONObject) new JSONTokener(JSONResponse).nextValue();

					// Extract value of "earthquakes" key -- a List
					JSONArray businesses = responseObject
							.getJSONArray(BUSINESSES_TAG);

					// Iterate over earthquakes list
					for (int idx = 0; idx < businesses.length(); idx++) {

						// Get single earthquake data - a Map
						JSONObject business = (JSONObject) businesses.get(idx);

						// Summarize business data as a string and add it to
						// result

						String locationData = "";

						for (int i=0; i < business.getJSONArray(LOCATION_TAG).length(); i++) {
							locationData += (" "+business.getJSONArray(LOCATION_TAG).getString(i));
						}
												
						Double rating = (Double) business.get("rating");
						
						result.addPlaceName((String) business.get("name"));
						result.addPlaceDetails((String) business.get("name"), rating.toString()+", "+locationData);
					}
				} catch (JSONException e) {
					e.printStackTrace();
				}
				return result;
			}
		}
	}
	
	protected void generateDialog(String listItemString, PlaceToGo result) {
		
		String bodyString = (String) result.placeDetails.get(listItemString);
		String[] bodyStringArray = bodyString.split(",");
		
		RatingBar rating = new RatingBar(HttpClientListActivity.this, null, android.R.attr.ratingBarStyle);
		rating.setStepSize((float) 0.5);
		rating.setNumStars(5);
		rating.setRating(getRating(bodyStringArray[0]));		
		
        AlertDialog alertDialog = new AlertDialog.Builder(this).create();
        alertDialog.setView(rating, 100, 0, 100, 0);
        alertDialog.setTitle(listItemString);
        alertDialog.setMessage(bodyStringArray[1]+bodyStringArray[2]);        
        alertDialog.show();
	}
	
	protected float getRating(String stars) {
		float fillStars = Float.parseFloat(stars);
		return fillStars;
	}
}