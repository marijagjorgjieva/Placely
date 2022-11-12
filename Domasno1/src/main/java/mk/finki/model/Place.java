package mk.finki.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Place {
    private String name;
    private String street;
    private Location location;
    private List<String> categories;

    public Place(String name) {
        this.name = name;
        this.categories = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public List<String> getCategories() {
        return categories;
    }

    public void setCategories(List<String> categories) {
        this.categories = categories;
    }

    @Override
    public String toString() {
        return  name + ','
                + street + ',' +
                 location.getLatitude() +
                "," + location.getLongitude() +
                "," + categories;
    }
}
