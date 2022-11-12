package mk.finki.pipeAndFilter.pipe;

import mk.finki.pipeAndFilter.Filter;

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