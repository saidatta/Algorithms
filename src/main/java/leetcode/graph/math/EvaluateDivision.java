package leetcode.graph.math;

import java.util.*;

// https://leetcode.com/problems/evaluate-division/editorial/
public class EvaluateDivision {
    public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
        // a form of adj list, since keys are actually Strings instead of integer.
        Map<String, List<Node>> graph = new HashMap<>();
        buildGraph(graph, equations, values);

        double[] result = new double[queries.size()];
        for (int i = 0; i < queries.size(); i++) {
            result[i] = dfs(graph, queries.get(i).get(0), queries.get(i).get(1), new HashSet<>());
        }
        return result;
    }

    private void buildGraph(Map<String, List<Node>> graph, List<List<String>> equations, double[] values) {
        for (int i = 0; i < equations.size(); i++) {
            String u = equations.get(i).get(0);
            String v = equations.get(i).get(1);
            graph.putIfAbsent(u, new ArrayList<>());
            graph.putIfAbsent(v, new ArrayList<>());
            graph.get(u).add(new Node(v, values[i]));
            graph.get(v).add(new Node(u, 1 / values[i]));
        }
    }

    private double dfs(Map<String, List<Node>> graph, String start, String end, Set<String> visited) {
        if (!graph.containsKey(start)) return -1.0;
        if (start.equals(end)) return 1.0;

        visited.add(start);
        for (Node neighbour : graph.get(start)) {
            if (!visited.contains(neighbour.key)) {
                double productWeight = dfs(graph, neighbour.key, end, visited);
                if (productWeight != -1.0) {
                    return neighbour.value * productWeight;
                }
            }
        }
        return -1.0;
    }

    private record Node(String key, double value) {}

    public static void main(String[] args) {
        EvaluateDivision solution = new EvaluateDivision();
        List<List<String>> equations = Arrays.asList(Arrays.asList("a", "b"), Arrays.asList("b", "c"));
        double[] values = new double[]{2.0, 3.0};
        List<List<String>> queries = Arrays.asList(Arrays.asList("a", "c"), Arrays.asList("b", "a"), Arrays.asList("a", "e"), Arrays.asList("a", "a"), Arrays.asList("x", "x"));
        System.out.println(Arrays.toString(solution.calcEquation(equations, values, queries)));
    }
}
