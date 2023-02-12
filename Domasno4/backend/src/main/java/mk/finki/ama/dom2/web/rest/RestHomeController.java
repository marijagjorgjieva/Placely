package mk.finki.ama.dom2.web.rest;

import mk.finki.ama.dom2.model.Place;
import mk.finki.ama.dom2.service.PlacesService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/home")
public class RestHomeController {

    private final PlacesService placesService;

    public RestHomeController(PlacesService placesService) {
        this.placesService = placesService;
    }

    @CrossOrigin()
    @GetMapping("/userPreferences")
    public ResponseEntity<List<Place>> showResultsGet(
            @RequestParam String city,
            @RequestParam List<String> select1,
            @RequestParam String location1,
            @RequestParam List<String> select2,
            @RequestParam String location2)
            throws IOException, InterruptedException {

        List<Place> placesToBeReturned = placesService.filterByCityLocationsAndSelection(city, select1, location1, select2, location2);

        return ResponseEntity.ok().header("Content-Type", "application/json").body(placesToBeReturned);
    }
}


