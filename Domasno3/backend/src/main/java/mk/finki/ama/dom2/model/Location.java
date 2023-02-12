package mk.finki.ama.dom2.model;

public record Location(double latitude, double longitude) {

    /**
     * The value 12742 in this code represents the earth's average radius in kilometers.
     * It is used as a factor to convert the result of the calculation into kilometers.
     * The calculation in this code computes the Haversine distance,
     * which is a formula used to find the great-circle distance between two points on a sphere,
     * such as the earth. The Haversine formula is based on the spherical law of cosines
     * and uses the earth's average radius to find the distance between two points on the surface of the earth.
     *
     * @param first first location
     * @param second second location
     * @return the Haversine distance between two locations, first and second, represented in kilometers.
     */
    public static double calculateDistance(Location first, Location second) {
        final double earthRadius = 12742;
        final double radian = Math.PI / 180;

        double latitudeDiff = (second.latitude() - first.latitude()) * radian;
        double longitudeDiff = (second.longitude() - first.longitude()) * radian;

        double latitude1 = first.latitude() * radian;
        double latitude2 = second.latitude() * radian;

        double a = Math.sin(latitudeDiff / 2) * Math.sin(latitudeDiff / 2)
                + Math.cos(latitude1) * Math.cos(latitude2)
                * Math.sin(longitudeDiff / 2) * Math.sin(longitudeDiff / 2);

        double c = 2 * Math.asin(Math.sqrt(a));

        return earthRadius * c;
    }


    @Override
    public String toString() {
        return "lat:" + latitude +
                ", long:" + longitude;
    }
}
