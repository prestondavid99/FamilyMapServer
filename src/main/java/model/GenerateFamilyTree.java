package model;

import com.google.gson.Gson;
import data.*;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;
import java.util.Random;
import java.util.UUID;

public class GenerateFamilyTree {

   private String associatedUsername;
   private int year;


    public GenerateFamilyTree(String associatedUsername) {
        Random random = new Random();
        this.associatedUsername = associatedUsername;
        year = random.nextInt(2010);
    }

    // If generations = 0, just the person
    // If generations = 1, the person AND the parents
    // If generations >= 1, add parents to the parents
    public Person generatePerson(String gender, int generations) throws FileNotFoundException {
        Person mother = null;
        Person father = null;
        Random random = new Random();


        if (generations >= 1) {
            mother = generatePerson("f", generations - 1);
            father = generatePerson("m", generations - 1);

            String id = UUID.randomUUID().toString();
            mother.setSpouseID(id);
            father.setSpouseID(id);

            // TODO: Add marriage events to mother and father

        }

        String personID = UUID.randomUUID().toString();
        String firstName = null;

        if (gender.equals("f")) {
            Gson gson = new Gson();
            Reader reader = new FileReader("/json/fnames.json");
            FemaleNames femaleNames = gson.fromJson(reader, FemaleNames.class);
            firstName = femaleNames.getfNames()[random.nextInt(femaleNames.getfNames().length)];
        } // Move parsing part to another function or a constructor
        else if (gender.equals("m")) {
            Gson gson = new Gson();
            Reader reader = new FileReader("/json/mnames.json");
            MaleNames maleNames = gson.fromJson(reader, MaleNames.class);
            firstName = maleNames.getmNames()[random.nextInt(maleNames.getmNames().length)];
        }

        String lastName;
        Gson gson = new Gson();
        Reader reader = new FileReader("/json/snames.json");
        LastNames lastNames = gson.fromJson(reader, LastNames.class);
        lastName = lastNames.getLastNames()[random.nextInt(lastNames.getLastNames().length)];


        Person person = new Person(personID, associatedUsername, firstName, lastName, gender, father.getPersonID(), mother.getPersonID());
        // TODO: Generate Events for Person
        return person;
    }

    public Event generateBirthEvent(String associatedUsername, String personID) throws FileNotFoundException {
        Random random = new Random();
        String eventID = UUID.randomUUID().toString();
        float latitude;
        float longitude;
        String country;
        String city;
        Gson gson = new Gson();
        Reader reader = new FileReader("/json/locations.json");
        LocationData locationObj = gson.fromJson(reader, LocationData.class);
        int index = random.nextInt(locationObj.getData().length);
        latitude = locationObj.getData()[index].getLatitude();
        longitude = locationObj.getData()[index].getLongitude();
        country = locationObj.getData()[index].getCountry();
        city = locationObj.getData()[index].getCity();
        Event event = new Event(eventID, associatedUsername, personID, latitude, longitude, country, city, "Birth", year);
        year = year - 15;
        return event;
    }
}
