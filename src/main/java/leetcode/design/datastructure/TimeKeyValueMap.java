package leetcode.design.datastructure;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// https://leetcode.com/problems/time-based-key-value-store/description/
public class TimeKeyValueMap<K, V> {
    private record ValueWithTimestamp(String value, int timestamp) {}

    private final Map<String, List<ValueWithTimestamp>> map;

    public TimeKeyValueMap() {
        map = new HashMap<>();
    }

    public void set(String key, String value, int timestamp) {
        map.computeIfAbsent(key, k -> new ArrayList<>()).add(new ValueWithTimestamp(value, timestamp));
    }

    public String get(String key, int timestamp) {
        if (!map.containsKey(key)) {
            return "";
        }
        return binarySearch(map.get(key), timestamp);
    }

    private String binarySearch(List<ValueWithTimestamp> list, int timestamp) {
        int low = 0, high = list.size() - 1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (list.get(mid).timestamp() == timestamp) {
                return list.get(mid).value();
            } else if (list.get(mid).timestamp() < timestamp) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return high >= 0 ? list.get(high).value() : "";
    }
}
