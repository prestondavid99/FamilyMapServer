package service;

import com.google.gson.Gson;
import data.*;
import dataaccess.DataAccessException;
import dataaccess.EventDAO;
import model.Event;
import model.Person;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Random;
import java.util.UUID;

public class GenerateFamilyTree {

   private final String associatedUsername;
   private final int year;
   private final Connection connection;
   private ArrayList<Person> arrayList;
   private ArrayList<Event> eventList;
   private String personID;



    public ArrayList<Person> getArrayList() {
        return arrayList;
    }

    public void setArrayList(ArrayList<Person> arrayList) {
        this.arrayList = arrayList;
    }

    public GenerateFamilyTree(Connection connection, String associatedUsername) {
        this.associatedUsername = associatedUsername;
        year = 2020;
        this.connection = connection;
        arrayList = new ArrayList<>();
        eventList = new ArrayList<>();

    }

    public String getPersonID() {
        return personID;
    }

    // If generations = 0, just the person
    // If generations = 1, the person AND the parents
    // If generations >= 1, add parents to the parents
    public Person generatePerson(String gender, int generations, int year, boolean canDie) throws FileNotFoundException, DataAccessException {
        Person mother = null;
        Person father = null;
        String fatherID = null;
        String motherID = null;
        Random random = new Random();


        if (generations >= 1) {
            mother = generatePerson("f", generations - 1, year - 30, true);
            motherID = mother.getPersonID();
            father = generatePerson("m", generations - 1, year - 30, true);
            fatherID = father.getPersonID();

            for (Person p : arrayList) {
                if (p.getPersonID().equals(mother.getPersonID())) {
                    p.setSpouseID(fatherID);
                }
                if (p.getPersonID().equals(father.getPersonID())) {
                    p.setSpouseID(motherID);
                }
            }
            int marriageYear = year + 15;
            generateMarriageEvent(associatedUsername, mother.getPersonID(), father.getPersonID(), marriageYear);
        }

        String personID = UUID.randomUUID().toString();
        String firstName = null;

        if (gender.equals("f")) {
            try {
                Gson gson = new Gson();
                Reader reader = new FileReader("json/fnames.json");
                FemaleNames femaleNames = gson.fromJson(reader, FemaleNames.class);
                firstName = femaleNames.getData()[random.nextInt(femaleNames.getData().length)];
            }
            catch (Exception e) {
                e.printStackTrace();
            }

        } // Move parsing part to another function or a constructor
        else if (gender.equals("m")) {
            try {
                Gson gson = new Gson();
                Reader reader = new FileReader("json/mnames.json");
                MaleNames maleNames = gson.fromJson(reader, MaleNames.class);
                firstName = maleNames.getData()[random.nextInt(maleNames.getData().length)];
            }
            catch (Exception e) {
                e.printStackTrace();
            }

        }

        String lastName = null;
        try {
            Gson gson = new Gson();
            Reader reader = new FileReader("json/snames.json");
            LastNames lastNames = gson.fromJson(reader, LastNames.class);
            lastName = lastNames.getData()[random.nextInt(lastNames.getData().length)];
        }
        catch (Exception e) {
            e.printStackTrace();
        }



        try {
            Person person = new Person(personID, associatedUsername, firstName, lastName, gender, fatherID, motherID);

            generateBirthEvent(associatedUsername, personID, year);
            if (canDie) {
                generateDeathEvent(associatedUsername, personID, year + 7);
            }
            else {
                this.personID = person.getPersonID();
            }
            arrayList.add(person);
            return person;
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void generateBirthEvent(String associatedUsername, String personID, int year) throws FileNotFoundException, DataAccessException {
        EventDAO eventDAO = new EventDAO(connection);
        Random random = new Random();
        String eventID = UUID.randomUUID().toString();
        float latitude;
        float longitude;
        String country;
        String city;
        Gson gson = new Gson();
        Reader reader = new FileReader("json/locations.json");
        LocationData locationObj = gson.fromJson(reader, LocationData.class);
        int index = random.nextInt(locationObj.getData().length);
        latitude = locationObj.getData()[index].getLatitude();
        longitude = locationObj.getData()[index].getLongitude();
        country = locationObj.getData()[index].getCountry();
        city = locationObj.getData()[index].getCity();
        Event event = new Event(eventID, associatedUsername, personID, latitude, longitude, country, city, "Birth", year);
        eventDAO.insert(event);
        eventList.add(event);
    }

    public void generateMarriageEvent(String associatedUsername, String personID1, String personID2, int year) throws FileNotFoundException, DataAccessException {
        EventDAO eventDAO = new EventDAO(connection);
        Random random = new Random();
        String eventID = UUID.randomUUID().toString();
        String eventID2 = UUID.randomUUID().toString();
        float latitude;
        float longitude;
        String country;
        String city;
        Gson gson = new Gson();
        Reader reader = new FileReader("json/locations.json");
        LocationData locationObj = gson.fromJson(reader, LocationData.class);
        int index = random.nextInt(locationObj.getData().length);
        latitude = locationObj.getData()[index].getLatitude();
        longitude = locationObj.getData()[index].getLongitude();
        country = locationObj.getData()[index].getCountry();
        city = locationObj.getData()[index].getCity();
        Event event1 = new Event(eventID, associatedUsername, personID1, latitude, longitude, country, city, "Marriage", year);
        Event event2 = new Event(eventID2, associatedUsername, personID2, latitude, longitude, country, city, "Marriage", year);
        eventDAO.insert(event1);
        eventDAO.insert(event2);
        eventList.add(event1);
        eventList.add(event2);
    }

    public ArrayList<Event> getEventList() {
        return eventList;
    }

    public void setEventList(ArrayList<Event> eventList) {
        this.eventList = eventList;
    }

    public void generateDeathEvent(String associatedUsername, String personID, int year) throws FileNotFoundException, DataAccessException {
        EventDAO eventDAO = new EventDAO(connection);
        Random random = new Random();
        String eventID = UUID.randomUUID().toString();
        float latitude;
        float longitude;
        String country;
        String city;
        Gson gson = new Gson();
        Reader reader = new FileReader("json/locations.json");
        LocationData locationObj = gson.fromJson(reader, LocationData.class);
        int index = random.nextInt(locationObj.getData().length);
        latitude = locationObj.getData()[index].getLatitude();
        longitude = locationObj.getData()[index].getLongitude();
        country = locationObj.getData()[index].getCountry();
        city = locationObj.getData()[index].getCity();
        Event event = new Event(eventID, associatedUsername, personID, latitude, longitude, country, city, "Death", year);
        eventDAO.insert(event);
        eventList.add(event);
    }
}
