package mk.finki.ama.dom2.service.impl;

import mk.finki.ama.dom2.model.Place;
import mk.finki.ama.dom2.repository.PlacesAPI;
import mk.finki.ama.dom2.service.PlacesService;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

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
    public List<Place> ListAllById(String id) throws IOException, InterruptedException {
        return placesAPI.getPlacesFromCityByID(id);
    }
}

