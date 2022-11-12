package mk.finki.pipeAndFilter.filterImpl;

import mk.finki.model.Place;
import mk.finki.pipeAndFilter.Filter;

import java.util.List;
import java.util.stream.Collectors;

public class CategoryFilter implements Filter<List<Place>, List<Place>> {

    private final String category;

    public CategoryFilter(String category) {
        this.category = category;
    }

    //proveruva dali vo listata od kategorii ima substring sho go barame znaci ako e catering.resaturant ke go dade i restoran
    @Override
    public List<Place> execute(List<Place> input) {
        return input.stream().parallel().filter(x -> x.getCategories().stream().anyMatch(z -> z.contains(category))).collect(Collectors.toList());
    }
}