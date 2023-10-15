package leetcode.design.ds;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// https://leetcode.com/problems/design-memory-allocator/description/
class AllocatorNode {
    int start;  // Starting position of the node
    int size;   // Size of the node
    int mID;    // Memory ID of the node
    AllocatorNode next;
    AllocatorNode prev;

    public AllocatorNode(int startVal, int sizeVal, int mIDVal) {
        this.start = startVal;
        this.size = sizeVal;
        this.mID = mIDVal;
    }

    /**
     * Inserts this node between the given left and right nodes.
     * @param left The node to the left
     * @param right The node to the right
     */
    public void insert(AllocatorNode left, AllocatorNode right) {
        left.next = this;
        this.next = right;
        this.prev = left;
        right.prev = this;
    }

    /**
     * Merges this node with the node to its right.
     * @param right The node to merge with
     */
    public void merge(AllocatorNode right) {
        this.size += right.size;
        this.next = right.next;
        right.next.prev = this;
        right = null;  // Let Java's GC handle the cleanup
    }
}

public class MemoryAllocator {
    private AllocatorNode leftDummy;   // Left boundary dummy node
    private AllocatorNode rightDummy;  // Right boundary dummy node
    private Map<Integer, List<AllocatorNode>> idToNodes = new HashMap<>();

    /**
     * Initializes the Allocator with a memory of size n.
     * @param n Size of the memory
     */
    public MemoryAllocator(int n) {
        leftDummy = new AllocatorNode(0, 0, 0);
        rightDummy = new AllocatorNode(0, 0, 0);
        AllocatorNode freeNode = new AllocatorNode(0, n, -1);  // Initially, all memory is free
        leftDummy.next = freeNode;
        freeNode.prev = leftDummy;
        freeNode.next = rightDummy;
        rightDummy.prev = freeNode;
    }

    /**
     * Allocates a block of memory of the given size and assigns it the given memory ID.
     * @param size Size of the block
     * @param mID Memory ID
     * @return Start index of the allocated block or -1 if allocation is not possible
     */
    public int allocate(int size, int mID) {
        AllocatorNode curr = leftDummy.next;
        while (curr != rightDummy) {
            // Check if the current block is free and big enough
            if (curr.mID == -1 && curr.size >= size) {
                if (curr.size == size) {
                    curr.mID = mID;
                    idToNodes.computeIfAbsent(mID, k -> new ArrayList<>()).add(curr);
                    return curr.start;
                } else {
                    curr.size -= size;
                    AllocatorNode mem = new AllocatorNode(curr.start, size, mID);
                    curr.start += size;
                    mem.insert(curr.prev, curr);
                    idToNodes.computeIfAbsent(mID, k -> new ArrayList<>()).add(mem);
                    return mem.start;
                }
            }
            curr = curr.next;
        }
        return -1;
    }

    /**
     * Frees all blocks associated with the given memory ID.
     * @param mID Memory ID
     * @return Total size of the freed blocks
     */
    public int free(int mID) {
        int freed = 0;
        List<AllocatorNode> nodesList = idToNodes.get(mID);
        if (nodesList == null) return 0;

        for (AllocatorNode node : nodesList) {
            freed += node.size;
            node.mID = -1;

            if (node.next.mID == -1) {
                node.merge(node.next);
            }
            if (node.prev.mID == -1) {
                node.prev.merge(node);
            }
        }
        idToNodes.remove(mID);
        return freed;
    }
}

// Sample usage:
// Allocator allocator = new Allocator(10);
// int param1 = allocator.allocate(5, 1);
// int param2 = allocator.free(1);
