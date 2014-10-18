package io.android.sightsee;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PlaceToGo {
	
	public List<String> placeNames = new ArrayList<String>();
	public Map<String, String> placeDetails = new HashMap<String, String>();
	
	
	public PlaceToGo() {

	}
	
	public void addPlaceName(String place) {
		placeNames.add(place);
	}
	
	public void addPlaceDetails(String place, String details) {
		placeDetails.put(place, details);
	}
	/*
	
	public Map<String, String> getPlaceDetails() {
		return placeDetails;
	}
	
	public List<String> getPlaceNames() {
		return placeNames;
	}*/


}
