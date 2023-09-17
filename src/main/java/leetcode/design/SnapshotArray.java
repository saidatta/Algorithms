package leetcode.design;

import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.TreeMap;

/**
 * https://leetcode.com/problems/snapshot-array/
 */
class SnapshotArray {

//    TreeMap<Integer, Integer>[] indices;
//    int currntSnap = 0;
//
//    public SnapshotArray(int length) {
//
//        indices = new TreeMap[length];
//
//        for (int i = 0; i < length; i++) {
//            indices[i] = new TreeMap<>();
//            indices[i].put(0, 0);
//        }
//    }
//
//    public void set(int index, int val) {
//        indices[index].put(currntSnap, val);
//    }
//
//    public int snap() {
//        currntSnap++;
//        return currntSnap - 1;
//    }
//
//    public int get(int index, int snap_id) {
//        return indices[index].floorEntry(snap_id).getValue();
//    }
    private int snapshotArrayVersion = -1;
    private ArrayList<SnapshotNode> snapshotArray = new ArrayList<>();
    private HashMap<Integer, Integer> snapshotIdVersionMapping = new HashMap<>();
    private int globalSnapshotId = -1;

    public SnapshotArray(int length) {
        for (int i = 0; i < length; i++) {
            snapshotArray.add(new SnapshotNode());
        }
    }

    public void set(int index, int val) {
        snapshotArrayVersion++;
        snapshotArray.get(index).logs.put(snapshotArrayVersion, val);
    }

    public int snap() {
        snapshotArrayVersion++;
        globalSnapshotId++;
        snapshotIdVersionMapping.put(globalSnapshotId, snapshotArrayVersion);
        return globalSnapshotId;
    }

    public int get(int index, int snapshotId) {
        int timestamp = snapshotIdVersionMapping.get(snapshotId);
        TreeMap<Integer, Integer> vals = snapshotArray.get(index).logs;

        // get the key that is less than or equal to timestamp
        Map.Entry<Integer, Integer> entry = vals.floorEntry(timestamp);

        // if the key is null, return 0, otherwise return the associated value
        return (entry == null) ? 0 : entry.getValue();
    }

    class SnapshotNode {
        TreeMap<Integer, Integer> logs = new TreeMap<>();

        SnapshotNode() {
            logs.put(snapshotArrayVersion, 0);
        }
    }

//    private List<List<Integer>> snapshot;
//    private int snapTimes;
//
//    public SnapshotArray(int length) {
//        this.snapshot = new ArrayList<>();
//        for (int i = 0; i < length; i++) {
//            List<Integer> initialValue = new ArrayList<>();
//            initialValue.add(0);
//            this.snapshot.add(initialValue);
//        }
//        this.snapTimes = 0;
//    }
//
//    public void set(int index, int val) {
//        while (this.snapTimes >= this.snapshot.get(index).size()) {
//            int lastUnchangedValue = this.snapshot.get(index).get(this.snapshot.get(index).size() - 1);
//            this.snapshot.get(index).add(lastUnchangedValue);
//        }
//        this.snapshot.get(index).set(this.snapTimes, val);
//    }
//
//    public int snap() {
//        this.snapTimes++;
//        return this.snapTimes - 1;
//    }
//
//    public int get(int index, int snapId) {
//        if (this.snapshot.get(index).size() <= snapId) {
//            return this.snapshot.get(index).get(this.snapshot.get(index).size() - 1);
//        }
//        return this.snapshot.get(index).get(snapId);
//    }
}