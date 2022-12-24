package mk.finki.ama.dom2.service;

import mk.finki.ama.dom2.model.Place;

import java.io.IOException;
import java.util.List;

public interface PlacesService {
    List<Place> ListAllByName(String name) throws IOException, InterruptedException;

    List<Place> ListAllById(String name) throws IOException, InterruptedException;
}
