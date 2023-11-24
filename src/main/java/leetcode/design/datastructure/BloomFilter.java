package leetcode.design.datastructure;

import java.util.BitSet;
import java.util.Random;

public class BloomFilter<T> {
    private final int size;
    private final int numHashes;
    private final BitSet bitSet;

    public BloomFilter(int size, int numHashes) {
        this.size = size;
        this.numHashes = numHashes;
        this.bitSet = new BitSet(size);
    }

    public void add(T value) {
        for (int i = 0; i < numHashes; i++) {
            int hashCode = getHash(value, i);
            bitSet.set((hashCode & Integer.MAX_VALUE) % size);
        }
    }

    public boolean mightContain(T value) {
        for (int i = 0; i < numHashes; i++) {
            int hashCode = getHash(value, i);
            if (!bitSet.get((hashCode & Integer.MAX_VALUE) % size)) {
                return false;
            }
        }
        return true;
    }

    private int getHash(T value, int i) {
        int h = value.hashCode() + i;
        Random random = new Random(h);
        return random.nextInt();
    }

    public static void main(String[] args) {
        BloomFilter<String> filter = new BloomFilter<>(1000, 3);

        filter.add("test");
        filter.add("example");

        System.out.println(filter.mightContain("test"));      // true
        System.out.println(filter.mightContain("example"));   // true
        System.out.println(filter.mightContain("random"));    // false (usually)
    }
}

