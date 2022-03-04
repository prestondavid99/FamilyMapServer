package data;

import com.google.gson.Gson;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;

public class Location {
    private String country;
    private String city;
    private float latitude;
    private float longitude;

    public Location() throws FileNotFoundException {
    }

    class LocationData {
        Location[] data;
    }

    Reader reader = new FileReader("/json/locations.json");
   // LocationData locationData = (LocationData)gson.fromJson(reader, LocationData.class);
}
