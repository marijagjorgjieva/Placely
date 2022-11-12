package mk.finki;

import mk.finki.repository.PlacesAPI;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        String id = PlacesAPI.getCityId("Skopje");
        System.out.println(id);
        System.out.println(PlacesAPI.getPlacesFromCityByID(id));
    }
}