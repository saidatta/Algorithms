package leetcode.design.datastructure;

import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

/**
 * https://leetcode.com/problems/snapshot-array/
 */
public class SnapshotArray {
    private int timestamp = 0;
    private final ArrayList<SnapshotNode> array = new ArrayList<>();
    private int snapId = 0;

    public SnapshotArray(int length) {
        for (int i = 0; i < length; i++) {
            array.add(new SnapshotNode(new TreeMap<>()));
        }
    }

    public void set(int index, int val) {
        array.get(index).addLog(timestamp, val);
    }

    public int snap() {
        timestamp++;
        return snapId++;
    }

    public int get(int index, int snapId) {
        return array.get(index).getValueAtTimestamp(snapId);
    }

    private record SnapshotNode(TreeMap<Integer, Integer> logs) {
        public int getValueAtTimestamp(int timestamp) {
            Map.Entry<Integer, Integer> entry = logs.floorEntry(timestamp);
            return (entry == null) ? 0 : entry.getValue();
        }
        public void addLog(int timestamp, int val) {
            logs.put(timestamp, val);
        }
    }
}
