package leetcode.array;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// https://leetcode.com/problems/group-the-people-given-the-group-size-they-belong-to/description
public class GroupGivenSizeBelongTo {
    public List<List<Integer>> groupThePeople(int[] groupSizes) {
        Map<Integer, List<Integer>> tempGroups = new HashMap<>();
        List<List<Integer>> result = new ArrayList<>();

        for (int i = 0; i < groupSizes.length; i++) {
            tempGroups.computeIfAbsent(groupSizes[i], k -> new ArrayList<>()).add(i);
            if (tempGroups.get(groupSizes[i]).size() == groupSizes[i]) {
                result.add(new ArrayList<>(tempGroups.get(groupSizes[i])));
                tempGroups.get(groupSizes[i]).clear();
            }
        }
        return result;
    }

    public static void main(String[] args) {
        GroupGivenSizeBelongTo solution = new GroupGivenSizeBelongTo();
        System.out.println(solution.groupThePeople(new int[]{3,3,3,3,3,1,3}));
        System.out.println(solution.groupThePeople(new int[]{2,1,3,3,3,2}));
    }
}
