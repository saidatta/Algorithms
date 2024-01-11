package leetcode.array.counting;

import java.util.ArrayList;
import java.util.List;

// https://leetcode.com/contest/weekly-contest-374/problems/find-the-peaks/
public class FindAllPeaks {
    public List<Integer> findPeaks(int[] mountain) {
        List<Integer> peaks = new ArrayList<>();
        for (int i = 1; i < mountain.length - 1; i++) {
            if (mountain[i] > mountain[i - 1] && mountain[i] > mountain[i + 1]) {
                peaks.add(i);
            }
        }
        return peaks;
    }
}
