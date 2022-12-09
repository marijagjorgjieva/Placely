package mk.finki.ama.dom2.web;

import mk.finki.ama.dom2.model.Location;
import mk.finki.ama.dom2.model.Place;
import mk.finki.ama.dom2.pipeAndFilter.filterImpl.CategoryFilter;
import mk.finki.ama.dom2.pipeAndFilter.filterImpl.DistanceFilter;
import mk.finki.ama.dom2.pipeAndFilter.pipe.Pipeline;
import mk.finki.ama.dom2.service.PlacesService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.util.*;

@Controller
@RequestMapping("/home")
public class HomeController {
    private final PlacesService placesService;

    public HomeController(PlacesService placesService) {
        this.placesService = placesService;
    }


    @GetMapping()
    public String getHomePage() {
        return "main-form";
    }


    @PostMapping("/userPreferences")
    public String showResults(
            @RequestParam String city,
            @RequestParam List<String> select1,
            @RequestParam String location1,
            @RequestParam List<String> select2,
            @RequestParam String location2,
            Model model)
     throws IOException, InterruptedException {

        String[]locations1 = location1.split(",");
        String[]locations2 = location2.split(",");
        Location l1 = new Location(Double.parseDouble(locations1[0]), Double.parseDouble(locations1[1]));
        Location l2 = new Location(Double.parseDouble(locations2[0]), Double.parseDouble(locations2[1]));

        Set<String> mutualCategories = new HashSet<String>(select1);
        Set<String> categories2 = new HashSet<String>(select2);
        mutualCategories.retainAll(categories2);

        List<Place> placesToBeReturned = new ArrayList<>();
        for (String category : mutualCategories) {
            Pipeline<List<Place>, List<Place>> pipeline1 =
                    new Pipeline<>(new CategoryFilter(category)).chain(new DistanceFilter(l1, l2));
            placesToBeReturned.addAll(pipeline1.process(placesService.ListAllByName(city)));
        }

        model.addAttribute("results", placesToBeReturned);
        return "results-page";
    }
}




