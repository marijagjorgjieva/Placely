package mk.finki.pipeAndFilter;

public interface Filter<I, O> {

    O execute(I input);

}