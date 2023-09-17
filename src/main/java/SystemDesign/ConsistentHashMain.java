package SystemDesign;

import java.util.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

class Node {
    private String name;
    private int replicas;

    public Node() {
        this.name = "";
        this.replicas = 0;
    }

    public Node(String name, int replicas) {
        this.name = name;
        this.replicas = replicas;
    }

    public String getName() {
        return name;
    }

    public int getReplicas() {
        return replicas;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Node node = (Node) o;
        return replicas == node.replicas && Objects.equals(name, node.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, replicas);
    }
}

class ConsistentHash {
    private TreeMap<Long, Node> hashRing = new TreeMap<>();

    public void addNode(Node node) {
        for (int i = 0; i < node.getReplicas(); i++) {
            String virtualNodeName = node.getName() + i;
            long hashValue = hash(virtualNodeName);
            hashRing.put(hashValue, node);
        }
    }

    public void removeNode(Node node) {
        for (int i = 0; i < node.getReplicas(); i++) {
            String virtualNodeName = node.getName() + i;
            long hashValue = hash(virtualNodeName);
            hashRing.remove(hashValue);
        }
    }

    public Node getNode(String key) {
        if (hashRing.isEmpty()) {
            return new Node();
        }
        long hashValue = hash(key);
        if (!hashRing.containsKey(hashValue)) {
            SortedMap<Long, Node> tailMap = hashRing.tailMap(hashValue);
            hashValue = tailMap.isEmpty() ? hashRing.firstKey() : tailMap.firstKey();
        }
        return hashRing.get(hashValue);
    }

    public void reset() {
        hashRing.clear();
    }

    private long hash(String key) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] bytes = md.digest(key.getBytes());
            return (bytes[0] & 0xFFL) |
                    ((bytes[1] & 0xFFL) << 8) |
                    ((bytes[2] & 0xFFL) << 16) |
                    ((bytes[3] & 0xFFL) << 24) |
                    ((bytes[4] & 0xFFL) << 32) |
                    ((bytes[5] & 0xFFL) << 40) |
                    ((bytes[6] & 0xFFL) << 48) |
                    ((bytes[7] & 0xFFL) << 56);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
}

class TestSuite {
    private final ConsistentHash consistentHash = new ConsistentHash();
    private final Map<Node, Integer> countMap = new HashMap<>();

    public void generateNodes(int count, int replicas) {
        for (int i = 0; i < count; i++) {
            String name = "Node" + i;
            Node node = new Node(name, replicas);
            consistentHash.addNode(node);
        }
    }

    public void test(int keyNums) {
        Random rand = new Random();
        for (int i = 0; i < keyNums; i++) {
            int len = rand.nextInt(10) + 10;
            String s = generateRandomString(len);
            Node node = consistentHash.getNode(s);
            countMap.put(node, countMap.getOrDefault(node, 0) + 1);
        }
    }

    public void printResult() {
        System.out.println("----------------------Test Result------------------------------");
        for (Map.Entry<Node, Integer> entry : countMap.entrySet()) {
            System.out.println(entry.getValue());
        }
    }

    public void reset() {
        consistentHash.reset();
        countMap.clear();
    }

    private String generateRandomString(int len) {
        String alphanum = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        StringBuilder sb = new StringBuilder(len);
        Random rand = new Random();
        for (int i = 0; i < len; i++) {
            sb.append(alphanum.charAt(rand.nextInt(alphanum.length())));
        }
        return sb.toString();
    }
}

public class ConsistentHashMain {
    public static void main(String[] args) {
        testVirtualNodes();
    }

    public static void testVirtualNodes() {
        int[] replicas = {1, 10, 50, 100, 200, 400, 800};
        for (int replica : replicas) {
            TestSuite testSuite = new TestSuite();
            testSuite.generateNodes(10, replica);
            testSuite.test(10000);
            testSuite.printResult();
        }
    }
}
