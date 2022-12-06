package mk.finki.ama.dom2.pipeAndFilter.filterImpl;

import mk.finki.ama.dom2.model.Location;
import mk.finki.ama.dom2.model.Place;
import mk.finki.ama.dom2.pipeAndFilter.Filter;

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

        Comparator<Place> comparator = new Comparator<Place>() {
            @Override
            public int compare(Place o1, Place o2) {

                //previous
                /*  return Double.compare
                        (Math.abs(Location.calculateDistance(o1.getLocation(), first) + Location.calculateDistance(o1.getLocation(), second))
                        ,Math.abs(Location.calculateDistance(o2.getLocation(), first) + Location.calculateDistance(o2.getLocation(), second)));*/
                return Double.compare
                        (Math.max(Location.calculateDistance(o1.getLocation(), first) , Location.calculateDistance(o1.getLocation(), second))
                        ,Math.max(Location.calculateDistance(o2.getLocation(), first), Location.calculateDistance(o2.getLocation(), second)));


            }
        };

          return input.stream().sorted(comparator).collect(Collectors.toList());

    }
}
