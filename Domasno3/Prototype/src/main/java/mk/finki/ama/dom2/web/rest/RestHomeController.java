package mk.finki.ama.dom2.web.rest;

import mk.finki.ama.dom2.model.Location;
import mk.finki.ama.dom2.model.Place;
import mk.finki.ama.dom2.pipeAndFilter.filterImpl.CategoryFilter;
import mk.finki.ama.dom2.pipeAndFilter.filterImpl.DistanceFilter;
import mk.finki.ama.dom2.pipeAndFilter.pipe.Pipeline;
import mk.finki.ama.dom2.service.PlacesService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
//@CrossOrigin(origins = "http://localhost:3000") zosto vaka
@RequestMapping("/api/home")
public class RestHomeController {

    private final PlacesService placesService;

    public RestHomeController(PlacesService placesService) {
        this.placesService = placesService;
    }

    //TODO dali treba nesto drugo i glava boli od ova malce kako e nastrakano messy mi ide hmmmmm
    @PostMapping("/userPreferences")
    public ResponseEntity<List<Place>> showResults(
            @RequestParam String city,
            @RequestParam List<String> select1,
            @RequestParam String location1,
            @RequestParam List<String> select2,
            @RequestParam String location2)
            throws IOException, InterruptedException {

        String[]locations1 = location1.split(",");
        String[]locations2 = location2.split(",");
        Location l1 = new Location(Double.parseDouble(locations1[0]), Double.parseDouble(locations1[1]));
        Location l2 = new Location(Double.parseDouble(locations2[0]), Double.parseDouble(locations2[1]));

        Set<String> mutualCategories = new HashSet<>(select1);
        Set<String> categories2 = new HashSet<>(select2);
        mutualCategories.retainAll(categories2);

        List<Place> placesToBeReturned = new ArrayList<>();
        for (String category : mutualCategories) {
            Pipeline<List<Place>, List<Place>> pipeline1 =
                    new Pipeline<>(new CategoryFilter(category)).chain(new DistanceFilter(l1, l2));
            placesToBeReturned.addAll(pipeline1.process(placesService.ListAllByName(city)));
        }

        return ResponseEntity.ok().header("Content-Type", "application/json").body(placesToBeReturned);
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/userPreferences")
    public ResponseEntity<List<Place>> showResultsGet(
            @RequestParam String city,
            @RequestParam List<String> select1,
            @RequestParam String location1,
            @RequestParam List<String> select2,
            @RequestParam String location2)
            throws IOException, InterruptedException {

        String[]locations1 = location1.split(",");
        String[]locations2 = location2.split(",");
        Location l1 = new Location(Double.parseDouble(locations1[0]), Double.parseDouble(locations1[1]));
        Location l2 = new Location(Double.parseDouble(locations2[0]), Double.parseDouble(locations2[1]));

        Set<String> mutualCategories = new HashSet<>(select1);
        Set<String> categories2 = new HashSet<>(select2);
        mutualCategories.retainAll(categories2);

        List<Place> placesToBeReturned = new ArrayList<>();
        for (String category : mutualCategories) {
            Pipeline<List<Place>, List<Place>> pipeline1 =
                    new Pipeline<>(new CategoryFilter(category)).chain(new DistanceFilter(l1, l2));
            placesToBeReturned.addAll(pipeline1.process(placesService.ListAllByName(city)));
        }

        return ResponseEntity.ok().header("Content-Type", "application/json").body(placesToBeReturned);
    }
}


