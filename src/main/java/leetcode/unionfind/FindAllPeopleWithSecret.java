package leetcode.unionfind;

import java.util.*;

// https://leetcode.com/problems/find-all-people-with-secret/
public class FindAllPeopleWithSecret {
    public List<Integer> findAllPeople(int n, int[][] meetings, int firstPerson) {
        // Map to store indices of meetings by their time
        Map<Integer, List<Integer>> meetingsByTime = new TreeMap<>();
        for (int i = 0; i < meetings.length; i++) {
            meetingsByTime.computeIfAbsent(meetings[i][2], k -> new ArrayList<>()).add(i);
        }

        // Initialize Union-Find structure
        UnionFind uf = new UnionFind(n);

        // Union-first person with person 0
        uf.union(0, firstPerson);

        for (int time : meetingsByTime.keySet()) {
            Set<Integer> currentPool = new HashSet<>();

            // Union all pairs in the current time slot
            for (int meetingIndex : meetingsByTime.get(time)) {
                int[] meeting = meetings[meetingIndex];
                uf.union(meeting[0], meeting[1]);
                currentPool.add(meeting[0]);
                currentPool.add(meeting[1]);
            }

            // Reset the union-find for people not connected to person 0
            for (int person : currentPool) {
                if (!uf.connected(0, person)) {
                    uf.reset(person);
                }
            }
        }

        // Collect all people connected to person 0
        List<Integer> peopleWithSecret = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (uf.connected(i, 0)) {
                peopleWithSecret.add(i);
            }
        }
        return peopleWithSecret;
    }

    private static class UnionFind {
        int[] parent;
        int[] rank;

        public UnionFind(int n) {
            parent = new int[n];
            rank = new int[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
            }
        }

        public void union(int p, int q) {
            int rootP = find(p);
            int rootQ = find(q);

            if (rootP != rootQ) {
                if (rank[rootP] < rank[rootQ]) {
                    parent[rootP] = rootQ;
                } else {
                    parent[rootQ] = rootP;
                    if (rank[rootP] == rank[rootQ]) {
                        rank[rootP]++;
                    }
                }
            }
        }

        public int find(int p) {
            while (parent[p] != p) {
                parent[p] = parent[parent[p]]; // Path compression
                p = parent[p];
            }
            return p;
        }

        public boolean connected(int p, int q) {
            return find(p) == find(q);
        }

        public void reset(int p) {
            parent[p] = p;
            rank[p] = 0;
        }
    }

    public static void main(String[] args) {
        FindAllPeopleWithSecret solution = new FindAllPeopleWithSecret();
        int[][] meetings = {{1,2,5},{2,3,8},{1,5,10}};
        System.out.println(solution.findAllPeople(6, meetings, 1)); // Expected: [0,1,2,3,5]
    }
}

