package io.android.sightsee;

import java.io.IOException;
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
import android.net.http.AndroidHttpClient;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class HttpClientListActivity extends ListActivity {
	
	private String searchTerm;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		new HttpGetTask(HttpClientListActivity.this).execute();
		
	}

	private class HttpGetTask extends AsyncTask<Void, Void, List<String>> {

		// Get your own user name at http://www.geonames.org/login
		private static final String USER_NAME = "aporter";

		private static final String URL = "http://api.geonames.org/earthquakesJSON?north=44.1&south=-9.9&east=-22.4&west=55.2&username="
				+ USER_NAME;
		
		private Context mContext;
		
	    public HttpGetTask (Context context){
	         
	    	mContext = context;
	    }

		AndroidHttpClient mClient = AndroidHttpClient.newInstance("");

		@Override
		protected List<String> doInBackground(Void... params) {
			HttpGet request = new HttpGet(URL);
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

		@Override
		protected void onPostExecute(List<String> result) {
			if (null != mClient)
				mClient.close();
			setListAdapter(new ArrayAdapter<String>(
					HttpClientListActivity.this,
					R.layout.list_item, result));
			
			final ListView lv = getListView();
			setContentView(lv);
			
			lv.setOnItemClickListener(new OnItemClickListener() {
				@Override
				public void onItemClick(AdapterView<?> parent, View view, int position,
	                    long id) {
					// TODO Auto-generated method stub
					String listItemString = lv.getItemAtPosition(position).toString();
					generateDialog(listItemString);
				}
			});
			
			lv.setOnTouchListener(new OnSwipeTouchListener(mContext) {
			    public void onSwipeTop() {
			        Toast.makeText(mContext, "top", Toast.LENGTH_SHORT).show();
			    }
			    public void onSwipeRight() {
			        Toast.makeText(mContext, "right", Toast.LENGTH_SHORT).show();
			    }
			    public void onSwipeLeft() {
			        Toast.makeText(mContext, "left", Toast.LENGTH_SHORT).show();
			    }
			    public void onSwipeBottom() {
			        Toast.makeText(mContext, "bottom", Toast.LENGTH_SHORT).show();
			    }
			    public boolean onTouch(View v, MotionEvent event) {
			    	return gestureDetector.onTouchEvent(event);
			    }
			});
			
	}

	private class JSONResponseHandler implements ResponseHandler<List<String>> {

		private static final String LONGITUDE_TAG = "lng";
		private static final String LATITUDE_TAG = "lat";
		private static final String MAGNITUDE_TAG = "magnitude";
		private static final String EARTHQUAKE_TAG = "earthquakes";

		@Override
		public List<String> handleResponse(HttpResponse response)
				throws ClientProtocolException, IOException {
			List<String> result = new ArrayList<String>();
			String JSONResponse = new BasicResponseHandler()
					.handleResponse(response);
			try {

				// Get top-level JSON Object - a Map
				JSONObject responseObject = (JSONObject) new JSONTokener(
						JSONResponse).nextValue();

				// Extract value of "earthquakes" key -- a List
				JSONArray earthquakes = responseObject
						.getJSONArray(EARTHQUAKE_TAG);

				// Iterate over earthquakes list
				for (int idx = 0; idx < earthquakes.length(); idx++) {

					// Get single earthquake data - a Map
					JSONObject earthquake = (JSONObject) earthquakes.get(idx);

					// Summarize earthquake data as a string and add it to
					// result
					searchTerm = getIntent().getStringExtra("cityName");
					Log.i("dfghjk", searchTerm);
					
					if(earthquake.get(MAGNITUDE_TAG).toString().contains(searchTerm)) {
						
						result.add(MAGNITUDE_TAG + ":"
								+ earthquake.get(MAGNITUDE_TAG) + ","
								+ LATITUDE_TAG + ":"
								+ earthquake.getString(LATITUDE_TAG) + ","
								+ LONGITUDE_TAG + ":"
								+ earthquake.get(LONGITUDE_TAG));
					}
				}
			} catch (JSONException e) {
				e.printStackTrace();
			}
			return result;
		}
	}
	}
	
	protected void generateDialog(String listItemString) {
		
		String[] array = listItemString.split(",");
		
		String bodyString = null;
		
		for(String str : array) {
			bodyString = bodyString + (str + "\n");
		}
		
        AlertDialog alertDialog = new AlertDialog.Builder(this).create();
        alertDialog.setTitle(array[0]);
        alertDialog.setMessage(bodyString);
        alertDialog.show();
	}
}