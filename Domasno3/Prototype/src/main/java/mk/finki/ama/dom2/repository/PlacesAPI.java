package mk.finki.ama.dom2.repository;

import mk.finki.ama.dom2.model.Location;
import mk.finki.ama.dom2.model.Place;
import org.json.*;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Repository
public class PlacesAPI {
    private static final String apiKey = "efc0365d3cc14750bb80dbb297d87205";
    private static final int numberOfResults = 500;

    public static String getCityId(String cityName) throws IOException, InterruptedException {

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://api.geoapify.com/v1/geocode/search?text="+cityName+"&lang=en&limit=1&type=city&apiKey="+apiKey))
                .header("Content-Type", "application/json")
                .build();

        HttpResponse<String> response =
                client.send(request, HttpResponse.BodyHandlers.ofString());

        JSONArray jsonArray = new JSONObject(response.body()).getJSONArray("features");
        JSONObject result = jsonArray.getJSONObject(0).getJSONObject("properties");


        return (String) result.get("place_id");

    }


    public  List<Place> getPlacesFromCityByID(String placeId) throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://api.geoapify.com/v2/places?" +
                        "categories=catering.restaurant,catering.cafe,leisure.park,entertainment.water_park,commercial.shopping_mall&" +
                        "filter=place:"+placeId+"&limit=" + numberOfResults + "&" +
                        "apiKey="+apiKey))
                .header("Content-Type", "application/json")
                .build();

        HttpResponse<String> response =
                client.send(request, HttpResponse.BodyHandlers.ofString());

        JSONArray jsonArray = new JSONObject(response.body()).getJSONArray("features");
        List<Place> places = new ArrayList<>();
        jsonArray.forEach(p -> {
            JSONObject properties = ((JSONObject)p).getJSONObject("properties");
            Set<String> keys = properties.keySet();

            Place place;
            if (keys.contains("name"))
                place = new Place(properties.getString("name"));
            else place = new Place("");

            if (keys.contains("street"))
                place.setStreet(properties.getString("street"));
            else place.setStreet("");

            place.setLocation(new Location(properties.getFloat("lat"),properties.getFloat("lon")));
            JSONArray categoriesJson = properties.getJSONArray("categories");
            categoriesJson.forEach(c -> place.getCategories().add(c.toString()));
            places.add(place);
        });

        return places;
    }

    public  List<Place> getPlacesByCityName(String name) throws IOException, InterruptedException {
        String cityID = getCityId(name);
        return getPlacesFromCityByID(cityID);
    }

}
