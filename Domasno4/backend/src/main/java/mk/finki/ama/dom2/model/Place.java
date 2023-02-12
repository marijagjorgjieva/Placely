package mk.finki.ama.dom2.model;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Place {
    private String name;
    private String street;
    private Location location;
    private List<String> categories;

    public Place(String name) {
        this.name = name;
        this.categories = new ArrayList<>();
    }
    @Override
    public String toString() {
        return  name + ','
                + street + ',' +
                 location.latitude() +
                "," + location.longitude() +
                "," + categories;
    }
}
