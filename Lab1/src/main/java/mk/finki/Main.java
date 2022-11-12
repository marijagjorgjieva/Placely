package mk.finki;

import mk.finki.repository.PlacesAPI;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        String id = PlacesAPI.getCityId("Prilep");
        System.out.println(PlacesAPI.getPlacesFromCityByID(id));
    }
}