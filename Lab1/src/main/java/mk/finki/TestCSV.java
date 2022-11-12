package mk.finki;

import mk.finki.model.Location;
import mk.finki.model.Place;
import mk.finki.pipeAndFilter.filterImpl.ToStringFilter;
import mk.finki.pipeAndFilter.SaveAsCSV;
import mk.finki.pipeAndFilter.filterImpl.CategoryFilter;
import mk.finki.pipeAndFilter.filterImpl.DistanceFilter;
import mk.finki.pipeAndFilter.pipe.Pipeline;
import mk.finki.repository.PlacesAPI;

import java.io.IOException;
import java.util.List;

public class TestCSV {
    public static void main(String[] args) throws IOException, InterruptedException {
        //lokacii za proba
        Location myLocation = new Location(27.2046, 77.4977);
        Location silboLocation = new Location(42.0001, 21.4174);

        //normalen print
        PlacesAPI.getPlacesByCityName("Skopje").forEach(System.out::println);

        Pipeline<List<Place>, List<String>> pipeline1 =
                new Pipeline<>(new CategoryFilter("cafe")).chain(new DistanceFilter(myLocation, silboLocation)).chain(new ToStringFilter());


        SaveAsCSV saveAsCSV = new SaveAsCSV();
        saveAsCSV.toCSV(pipeline1.process( PlacesAPI.getPlacesByCityName("Skopje")));

    }
}
