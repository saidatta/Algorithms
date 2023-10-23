package leetcode.design.ds;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// https://leetcode.com/problems/design-memory-allocator/description/
class AllocatorNode {
    // Starting position of the node
    int start;
    int freeSize;   // Size of the node
    int mID;    // Memory ID of the node
    AllocatorNode next;
    AllocatorNode prev;

    public AllocatorNode(int startVal, int sizeVal, int mIDVal) {
        this.start = startVal;
        this.freeSize = sizeVal;
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
        this.freeSize += right.freeSize;
        this.next = right.next;
        right.next.prev = this;
        // Let Java's GC handle the cleanup
        right = null;
    }
}

public class MemoryAllocator {
    private final AllocatorNode leftDummy;   // Left boundary dummy node
    private final AllocatorNode rightDummy;  // Right boundary dummy node
    private final Map<Integer, List<AllocatorNode>> idToNodes = new HashMap<>();

    /**
     * Initializes the Allocator with a memory of size n.
     * @param blockSize Size of the memory
     */
    public MemoryAllocator(int blockSize) {
        leftDummy = new AllocatorNode(0, 0, 0);
        rightDummy = new AllocatorNode(0, 0, 0);
        AllocatorNode freeNode = new AllocatorNode(0, blockSize, -1);  // Initially, all memory is free
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
            if (curr.mID == -1 && curr.freeSize >= size) {
                if (curr.freeSize == size) {
                    // marking the current node tagged with mId
                    curr.mID = mID;
                    // mID -> current node mapping
                    idToNodes.computeIfAbsent(mID, k -> new ArrayList<>()).add(curr);
                    return curr.start;
                } else {
                    curr.freeSize -= size;
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
        if (nodesList == null) {
            return 0;
        }

        for (AllocatorNode node : nodesList) {
            freed += node.freeSize;
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

    public static void main(String[] args) {
        MemoryAllocator allocator = new MemoryAllocator(10);

        // Testing the allocation function
        int param1 = allocator.allocate(5, 1);
        System.out.println("Allocated block starts at index: " + param1);

        // Testing the free function
        int param2 = allocator.free(1);
        System.out.println("Freed blocks size: " + param2);

        // Allocating more memory and testing
        int param3 = allocator.allocate(3, 2);
        System.out.println("Allocated block starts at index: " + param3);

        int param4 = allocator.allocate(2, 3);
        System.out.println("Allocated block starts at index: " + param4);

        int param5 = allocator.free(2);
        System.out.println("Freed blocks size: " + param5);

        int param6 = allocator.free(3);
        System.out.println("Freed blocks size: " + param6);
    }
}

// Sample usage:
// Allocator allocator = new Allocator(10);
// int param1 = allocator.allocate(5, 1);
// int param2 = allocator.free(1);
