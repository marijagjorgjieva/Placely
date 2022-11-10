package mk.finki.repository;

import mk.finki.model.Place;
import org.json.*;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

public class PlacesAPI {
    private static final String apiKey = "efc0365d3cc14750bb80dbb297d87205";

    public static String getCity(String cityName) throws IOException, InterruptedException {

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

}
