package Int.cruise;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

class TransactionalMap {

    private final Stack<Transaction> transactions = new Stack<>();
    private final Map<String, Integer> mainStorage = new HashMap<>();

    // Begin a new transaction
    public void begin() {
        transactions.push(new Transaction());
    }

    // Commit the current transaction
    public void commit() {
        if (!transactions.isEmpty()) {
            Transaction current = transactions.pop();
            if (!transactions.isEmpty()) {
                // Merge current transaction changes into the parent transaction
                transactions.peek().changes.putAll(current.changes);
            } else {
                // Merge current transaction changes into the main storage
                mainStorage.putAll(current.changes);
            }
        }
    }

    // End the current transaction
    public void end() {
        if (!transactions.isEmpty()) {
            transactions.pop();
        }
    }

    // Set a value in the current transaction
    public void set(String key, int value) {
        if (!transactions.isEmpty()) {
            transactions.peek().changes.put(key, value);
        } else {
            mainStorage.put(key, value);
        }
    }

    // Get a value, considering the current transaction
    public Integer get(String key) {
        for (int i = transactions.size() - 1; i >= 0; i--) {
            if (transactions.get(i).changes.containsKey(key)) {
                return transactions.get(i).changes.get(key);
            }
        }
        return mainStorage.get(key);
    }

    public static void main(String[] args) {
        TransactionalMap map = new TransactionalMap();

        map.begin();
        map.set("x", 2);

        map.begin();
        map.set("x", 3);
        System.out.println(map.get("x")); // Should print 3

        map.begin();
        map.set("x", 6);
        map.commit();

        System.out.println(map.get("x")); // Should print 6
        map.end();

        System.out.println(map.get("x")); // Should print 2
        map.end();
    }

    private record Transaction(Map<String, Integer> changes) {
        public Transaction() {
            this(new HashMap<>());
        }
    }
}
