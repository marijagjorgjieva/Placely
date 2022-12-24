package mk.finki.ama.dom2.model;

public class Location {
    private final double latitude;
    private final double longitude;

    public Location(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public static double calculateDistance(Location first, Location second) {

        double p = Math.PI / 2;    // Math.PI / 180
        double a = 0.5 - Math.cos((second.getLatitude() - first.getLatitude()) * p) / 2 + (Math.cos(first.getLatitude()) * p) * (Math.cos(second.getLatitude()) * p) * (1 - Math.cos((second.getLongitude() - first.getLongitude()) * p)) / 2;

        return 12742 * Math.asin(Math.sqrt(a));

    }

    @Override
    public String toString() {
        return "lat:" + latitude +
                ", long:" + longitude;
    }
}
