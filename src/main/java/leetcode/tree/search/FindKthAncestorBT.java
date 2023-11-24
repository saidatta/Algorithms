package leetcode.tree.search;

// https://leetcode.com/problems/kth-ancestor-of-a-tree-node/
//. A straightforward approach would be to traverse up the tree k times starting from the given node, but this would be
// inefficient for a large number of queries or large values of k. Instead, we can use a technique called
// "Binary Lifting" for efficient ancestor queries.
//
//   - Binary Lifting involves precomputing an ancestor table where ancestor[node][j] stores the 2^j-th ancestor of
//     the node. This allows us to jump up the tree in powers of two, which is efficient for finding the kth ancestor.
//
//   Here's how we can implement it:
//      - Precompute Ancestors: In the constructor, precompute the ancestors for each node for all powers of two up to
//        the maximum possible height of the tree.
//      - Get Kth Ancestor: To get the kth ancestor of a node, we repeatedly jump by powers of two. We start with the
//        highest power of two less than or equal to k and keep reducing k accordingly until it becomes 0.
public class FindKthAncestorBT {
    // binary lift table
    private final int[][] ancestor;

    public FindKthAncestorBT(int n, int[] parent) {
        // Calculate the maximum power for binary lifting based on tree height
        int maxLiftPower = (int) (Math.log(n) / Math.log(2)) + 1;
        ancestor = new int[n][maxLiftPower];

        // Initialize the first lift (2^0-th ancestor) with the immediate parent
        for (int node = 0; node < n; node++) {
            ancestor[node][0] = parent[node];
        }

        // Fill the binary lift table: binaryLiftTable[node][j] stores the 2^j-th ancestor of 'node'
        for (int j = 1; j < maxLiftPower; j++) {
            for (int node = 0; node < n; node++) {
                int intermediateAncestor = ancestor[node][j - 1];
                ancestor[node][j] = (intermediateAncestor == -1) ? -1 : ancestor[intermediateAncestor][j - 1];
            }
        }
    }

    public int getKthAncestor(int node, int k) {
        // Traverse up the tree by jumping powers of two
        for (int j = 0; j < ancestor[0].length && node != -1; j++) {
            // If the j-th bit of k is set, jump to the 2^j-th ancestor
            if ((k & (1 << j)) != 0) {
                node = ancestor[node][j];
            }
        }
        return node;
    }

    public static void main(String[] args) {
        int n = 7; // Number of nodes in the tree
        int[] parent = {-1, 0, 0, 1, 1, 2, 2}; // Parent array representing the tree

        FindKthAncestorBT treeAncestor = new FindKthAncestorBT(n, parent);

        System.out.println(treeAncestor.getKthAncestor(3, 1)); // Expected output: 1
        System.out.println(treeAncestor.getKthAncestor(5, 2)); // Expected output: 0
        System.out.println(treeAncestor.getKthAncestor(6, 3)); // Expected output: -1 (no such ancestor)
    }
}