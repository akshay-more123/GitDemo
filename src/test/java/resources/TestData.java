package resources;

import java.util.ArrayList;
import java.util.List;

import pojo.AddPlace;
import pojo.LocationClass;

public class TestData {

	public  AddPlace getAddPlacePayload(String name, String language, String address)
	{
		AddPlace ap = new AddPlace();
		  ap.setWebsite("https://rahulshettyacademy.com");
		  ap.setAddress(address);
		  ap.setAccuracy(50);
		  ap.setLanguage(language);
		  ap.setName(name);
		  ap.setPhone_number("9172462714");
		  List<String> list = new ArrayList<String>();
		  list.add("shoe park");
		  list.add("shop");
		  ap.setTypes(list);
		  
		LocationClass l = new LocationClass();
		  l.setLat(-38.383494);
		  l.setLng(33.427362);
		  ap.setLocation(l);
		  return ap;
	}
	
	public static String getDeletePlace(String placeId) {
		
		return "{\r\n"
				+ "    \"place_id\":\""+placeId+"\"\r\n"
				+ "}";
	}
}
