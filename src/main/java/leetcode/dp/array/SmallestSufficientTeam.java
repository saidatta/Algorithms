package leetcode.dp.array;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

/**
 * https://leetcode.com/problems/smallest-sufficient-team/
 */
public class SmallestSufficientTeam {
    private Integer[] memo;
    private int[] nextPerson;
    private int reqSillsSize;
    private int[] peopleSkillBitMasks;

    public int[] smallestSufficientTeam(String[] reqSkills, List<List<String>> people) {
        this.reqSillsSize = reqSkills.length;
        SkillFactory skillFactory = new SkillFactory(reqSkills);
        this.peopleSkillBitMasks = skillFactory.generatePeopleSkillBitMasks(people);

        findSmallestSufficientTeam();
        return reconstructTeam();
    }

    private void findSmallestSufficientTeam() {
        memo = new Integer[1 << reqSillsSize];
        nextPerson = new int[1 << reqSillsSize];
        dfs(0, 0);
    }

    private int[] reconstructTeam() {
        int curSkillSet = 0;
        List<Integer> res = new ArrayList<>();
        while (curSkillSet != (1 << reqSillsSize) - 1) {
            res.add(nextPerson[curSkillSet]);
            curSkillSet |= peopleSkillBitMasks[nextPerson[curSkillSet]];
        }
        return res.stream().mapToInt(i -> i).toArray();
    }

    private int dfs(int curSkillSet, int startIdx) {
        if (curSkillSet == (1 << reqSillsSize) - 1) {
            return 0;
        }

        if (memo[curSkillSet] == null) {
            int minNumPeople = Integer.MAX_VALUE / 2;
            int nextPersonIdx = -1;
            for (int i = startIdx; i < peopleSkillBitMasks.length; i++) {
                int withNewSkill = peopleSkillBitMasks[i] | curSkillSet;
                if (withNewSkill != curSkillSet) {
                    int numPeople = dfs(withNewSkill, i + 1) + 1;
                    if (minNumPeople > numPeople) {
                        minNumPeople = numPeople;
                        nextPersonIdx = i;
                    }
                }
            }
            memo[curSkillSet] = minNumPeople;
            nextPerson[curSkillSet] = nextPersonIdx;
        }
        return memo[curSkillSet];
    }

    static class SkillFactory {
        private final Map<String, Integer> skillToIdx;

        SkillFactory(String[] reqSkills) {
            this.skillToIdx = new HashMap<>();
            IntStream.range(0, reqSkills.length).forEach(i -> skillToIdx.put(reqSkills[i], i));
        }

        public int[] generatePeopleSkillBitMasks(List<List<String>> people) {
            int[] peopleSkillBitMasks = new int[people.size()];
            for (int i = 0; i < people.size(); i++) {
                peopleSkillBitMasks[i] = generateSkillBitMask(people.get(i));
            }
            return peopleSkillBitMasks;
        }

        private int generateSkillBitMask(List<String> skills) {
            int skillMask = 0;
            for (String skill : skills) {
                skillMask |= (1 << skillToIdx.get(skill));
            }
            return skillMask;
        }
    }
}
