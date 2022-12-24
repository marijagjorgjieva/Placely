package mk.finki.ama.dom2.pipeAndFilter.pipe;

import mk.finki.ama.dom2.pipeAndFilter.Filter;

public class Pipeline<I, O> {

    private final Filter<I, O> curr;

    public Pipeline(Filter<I, O> current) {
        this.curr = current;
    }

    public <P> Pipeline<I, P> chain(Filter<O, P> curr) {
        return new Pipeline<>(new Pipe<>(this.curr, curr));
    }

    public O process(I input) {
        return curr.execute(input);
    }
}