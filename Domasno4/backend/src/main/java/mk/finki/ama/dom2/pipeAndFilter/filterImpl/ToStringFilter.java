package mk.finki.ama.dom2.pipeAndFilter.filterImpl;

import mk.finki.ama.dom2.model.Place;
import mk.finki.ama.dom2.pipeAndFilter.Filter;

import java.util.List;
import java.util.stream.Collectors;

public class ToStringFilter implements Filter<List<Place>,List<String>> {
    @Override
    public List<String> execute(List<Place> input) {
        return input.stream().map(Place::toString).collect(Collectors.toList());
    }
}
