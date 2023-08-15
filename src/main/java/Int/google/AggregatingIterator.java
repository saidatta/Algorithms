package Int.google;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.NoSuchElementException;

/**
 * Input: {“foo”, “foo”, “foo”, “bar”, "foo"}
 * Output: {(“foo”, 3), (“bar”, 1), ("foo", 1)}
 *
 * class Counter {
 *   public Counter(String value, int count) {
 *     this.value = value;
 *     this.count = count;
 *   }
 *   public String value;
 *   public int count;
 * }
 *
 *
 * Given a Java-style iterator, create another iterator that returns “runs” in the output of the original iterator.
 * The input was a Java iterator (hasNext, next), and I was told to implement a classes "AggregatingIterator" that
 * returned the string as well as its frequency.
 */
public class AggregatingIterator implements Iterator<Counter> {
    private ListIterator<String> iterator;
    private Counter nextElement;

    public AggregatingIterator(ListIterator<String> iterator) {
        this.iterator = iterator;
        loadNextElement();
    }

    @Override
    public boolean hasNext() {
        return iterator.hasNext();
    }

    @Override
    public Counter next() {
        if(!hasNext()) {
            throw new NoSuchElementException();
        }

        Counter current = nextElement;
        loadNextElement();
        return current;
    }

    private void loadNextElement() {
        if (!iterator.hasNext()) {
            throw new NoSuchElementException();
        }

        Counter target = new Counter(iterator.next(), 1);

        while(iterator.hasNext()) {
            String c = iterator.next();
            if(target.value.equals(c)) {
                target.count++;
            } else {

            }
        }

        nextElement = target;
    }

    public static void main(String[] args) {
//        Input: {“foo”, “foo”, “foo”, “bar”, "foo"}
//        Output: {(“foo”, 3), (“bar”, 1), ("foo", 1)}
        List<String> input = Arrays.asList("foo", "foo", "foo", "bar", "foo");
        AggregatingIterator iter = new AggregatingIterator(input.listIterator());

        while (iter.hasNext()) {
            Counter c = iter.next();
            System.out.println(c.value + ": " + c.count);
        }
    }
}
 class Counter {
    public String value;
    public int count;

    public Counter(String value, int count) {
        this.value = value;
        this.count = count;
    }
}
