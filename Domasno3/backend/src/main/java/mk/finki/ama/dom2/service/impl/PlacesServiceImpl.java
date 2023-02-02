package mk.finki.ama.dom2.service.impl;


import mk.finki.ama.dom2.model.Location;
import mk.finki.ama.dom2.model.Place;
import mk.finki.ama.dom2.pipeAndFilter.filterImpl.CategoryFilter;
import mk.finki.ama.dom2.pipeAndFilter.filterImpl.DistanceFilter;
import mk.finki.ama.dom2.pipeAndFilter.pipe.Pipeline;
import mk.finki.ama.dom2.repository.PlacesAPI;
import mk.finki.ama.dom2.service.PlacesService;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class PlacesServiceImpl implements PlacesService {
    private final PlacesAPI placesAPI;

    public PlacesServiceImpl(PlacesAPI placesAPI) {
        this.placesAPI = placesAPI;
    }

    @Override
    public List<Place> ListAllByName(String name) throws IOException, InterruptedException {
        return placesAPI.getPlacesByCityName(name);
    }

    @Override
    public List<Place> filterByCityLocationsAndSelection(String city, List<String> select1, String location1, List<String> select2, String location2) throws IOException, InterruptedException {

        Location l1 = this.locationSplitter(location1);
        Location l2 = this.locationSplitter(location2);

        Set<String> mutualCategories = this.placesIntersection(select1,select2);

        List<Place> placesToBeReturned = new ArrayList<>();
        for (String category : mutualCategories) {
            Pipeline<List<Place>, List<Place>> pipeline1 =
                    new Pipeline<>(new CategoryFilter(category)).chain(new DistanceFilter(l1, l2));
            placesToBeReturned.addAll(pipeline1.process(this.ListAllByName(city)));
        }
        return placesToBeReturned;
    }

    private Location locationSplitter(String location)
    {
        String[]locations = location.split(",");
        return new Location(Double.parseDouble(locations[0]), Double.parseDouble(locations[1]));
    }
    private Set<String> placesIntersection( List<String> select1,  List<String> select2)
    {
        Set<String> mutualCategories = new HashSet<>(select1);
        Set<String> categories2 = new HashSet<>(select2);
        mutualCategories.retainAll(categories2);
        return mutualCategories;
    }

}

