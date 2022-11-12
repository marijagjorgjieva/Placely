package mk.finki.pipeAndFilter.filterImpl;

import mk.finki.model.Location;
import mk.finki.model.Place;
import mk.finki.pipeAndFilter.Filter;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class DistanceFilter implements Filter<List<Place>, List<Place>> {
    private final Location first;
    private final Location second;

    public DistanceFilter(Location first, Location second) {
        this.first = first;
        this.second = second;
    }

    @Override
    public List<Place> execute(List<Place> input) {

        Comparator<Place> comparator = (o1, o2) -> Double.compare
                (Math.abs(Location.calculateDistance(o1.getLocation(), first) + Location.calculateDistance(o1.getLocation(), second))
                ,Math.abs(Location.calculateDistance(o2.getLocation(), first) + Location.calculateDistance(o2.getLocation(), second)));

          return input.stream().parallel().sorted(comparator).collect(Collectors.toList());

    }
}
