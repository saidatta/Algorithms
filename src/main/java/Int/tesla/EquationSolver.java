package Int.tesla;

import java.util.*;

// solving equations. The input was ['a=b+1', 'b=c+2', 'c=2'], and the expected output was ['a=5', 'b=4', 'c=2'].
// L4 phone.
public class EquationSolver {

    static class Graph {
        Map<String, Node> nodes = new HashMap<>();

        static class Node {
            String var;
            Node dependency = null;
            int value = 0;
            boolean isEvaluated = false;

            Node(String var) {
                this.var = var;
            }
        }

        void addEquation(String equation) {
            String[] parts = equation.split("=");
            String leftVar = parts[0].trim();
            String rightPart = parts[1].trim();

            Node leftNode = nodes.computeIfAbsent(leftVar, Node::new);

            if (Character.isLetter(rightPart.charAt(0))) {
                String[] rightParts = rightPart.split("\\+");
                Node rightNode = nodes.computeIfAbsent(rightParts[0].trim(), Node::new);
                leftNode.dependency = rightNode;
                leftNode.value = Integer.parseInt(rightParts[1].trim());
            } else {
                leftNode.value = Integer.parseInt(rightPart);
                leftNode.isEvaluated = true;
            }
        }

        List<Node> topologicalSort() {
            Map<Node, Integer> inDegree = new HashMap<>();
            for (Node node : nodes.values()) {
                inDegree.put(node, 0);
            }
            for (Node node : nodes.values()) {
                if (node.dependency != null) {
                    inDegree.put(node.dependency, inDegree.get(node.dependency) + 1);
                }
            }

            Queue<Node> queue = new LinkedList<>();
            for (Map.Entry<Node, Integer> entry : inDegree.entrySet()) {
                if (entry.getValue() == 0) {
                    queue.add(entry.getKey());
                }
            }

            List<Node> sortedNodes = new ArrayList<>();
            while (!queue.isEmpty()) {
                Node current = queue.poll();
                sortedNodes.add(current);
                inDegree.put(current.dependency, inDegree.get(current.dependency) - 1);
                if (current.dependency != null && inDegree.get(current.dependency) == 0) {
                    queue.add(current.dependency);
                }
            }

            return sortedNodes;
        }

        void evaluate() {
            List<Node> sortedNodes = topologicalSort();
            for (Node node : sortedNodes) {
                if (!node.isEvaluated && node.dependency != null) {
                    node.value += node.dependency.value;
                    node.isEvaluated = true;
                }
            }
        }

        String[] getResults() {
            evaluate();
            String[] results = new String[nodes.size()];
            int i = 0;
            for (Node node : nodes.values()) {
                results[i++] = node.var + "=" + node.value;
            }
            return results;
        }
    }

    public static String[] solveEquations(String[] equations) {
        Graph graph = new Graph();
        for (String equation : equations) {
            graph.addEquation(equation);
        }
        return graph.getResults();
    }

    public static void main(String[] args) {
        String[] equations = {"a=b+1", "b=c+2", "c=2"};
        String[] results = solveEquations(equations);
        System.out.println(Arrays.toString(results)); // Output: ['a=5', 'b=4', 'c=2']
    }
}

