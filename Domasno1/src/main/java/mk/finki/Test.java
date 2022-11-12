package mk.finki;

import mk.finki.model.Location;
import mk.finki.model.Place;
import mk.finki.pipeAndFilter.filterImpl.CategoryFilter;
import mk.finki.pipeAndFilter.filterImpl.DistanceFilter;
import mk.finki.pipeAndFilter.pipe.Pipeline;
import mk.finki.repository.PlacesAPI;

import java.io.IOException;
import java.util.List;

public class Test {
    public static void main(String[] args) throws IOException, InterruptedException {

        //normalen print
        PlacesAPI.getPlacesByCityName("Skopje").forEach(System.out::println);
        System.out.println("_________________________________________________________________________");


        //lokacii za proba
        Location myLocation = new Location(27.2046, 77.4977);
        Location silboLocation = new Location(42.0001, 21.4174);

        //proba na chain na filreri, koristenje na dva za kategorii
        //Pipeline<List<Place>,List<Place>> pipeline1 = new Pipeline<>(new CategoryFilter("catering")).chain(new CategoryFilter("barbecue"));
        Pipeline<List<Place>, List<Place>> pipeline1 = new Pipeline<>(new CategoryFilter("cafe"));
        Pipeline<List<Place>, List<Place>> pipeline2 = new Pipeline<>(new DistanceFilter(myLocation, silboLocation));

        //proba na prvoto pipe1
        pipeline1.process(PlacesAPI.getPlacesByCityName("Skopje")).forEach(System.out::println);
        System.out.println("_________________________________________________________________________");

        //proba na vtoroto pipe2
        pipeline2.process(PlacesAPI.getPlacesByCityName("Skopje")).forEach(System.out::println);
        System.out.println("_______________________both__________________________________________________");

        //dvete naednas
        pipeline1 = pipeline1.chain(new DistanceFilter(myLocation, silboLocation));
        pipeline1.process(PlacesAPI.getPlacesByCityName("Skopje")).forEach(System.out::println);
        System.out.println("_________________________________________________________________________");
    }
}
