package mk.finki.pipeAndFilter.pipe;

import mk.finki.pipeAndFilter.Filter;

class Pipe<I, O, P> implements Filter<I, P> {

    private final Filter<I, O> current;
    private final Filter<O, P> succ;

    Pipe(Filter<I, O> current, Filter<O, P> next) {
        this.current = current;
        this.succ = next;
    }

    public P execute(I input) {
        return succ.execute(current.execute(input));
    }

}