package io.android.sightsee;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PlaceToGo {
	
	private List placeNames;
	private Map placeDetails;
	
	public PlaceToGo() {
		placeNames = new ArrayList<String>();
		placeDetails = new HashMap();
	}
	
	public void addPlaceName(String place) {
		placeNames.add(place);
	}
	
	public void addPlaceDetails(String place, String details) {
		placeDetails.put(place, details);
	}
	
	public Map getPlaceDetails() {
		return placeDetails;
	}
	
	public List placeNames() {
		return placeNames;
	}


}
