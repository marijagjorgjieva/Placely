package mk.finki.ama.dom2.pipeAndFilter;

public interface Filter<I, O> {

    O execute(I input);

}