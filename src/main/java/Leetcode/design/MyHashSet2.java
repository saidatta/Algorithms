package Leetcode.design;

/**
 * Intuition
 * I think using some hashing function will provide little benefit to this problem, since the keys are already integers,
 * and their distributions are unknown (presumably evenly dirstributed; should ask interviewer).
 *
 * using some hashing function does not help distribute the keys evenly; it may help distribute the continuous keys like
 * 1,2,3,4 evenly, but there will be its own kryptonite sequences that cause collisions. The probability of meeting a
 * kryptonite sequence is the same as using mod, though the speed may be faster .
 * Correct me if I am wrong.
 *
 * Taking advantage of 1000001 keys limit is probably not what the interviewers are asking for, either.
 *
 * Approach
 * Method1: The simplest way is to use M lists to store the keys: key will be stored at the key % M list
 * Method2: Or use a Binary Search Tree to store the keys; if the BST is balanced this is log(N) time; however, implement
 * something like a Red-Black Tree is very hard
 * Method3: then use M BSTs to store the keys are better; hopefully each BST stores only one key, any key collisions
 * will be handled by the BST at log time.
 * Method4: How large should M be? To balance space and time, a loading factor can be used. When the loading factor is
 * reached, we double the size of M. In this way the time complexity is amortized O(1) and space is O(N).
 * The following code is method4. This problem should be a medium problem.
 * If M is 2^m, then k % M = the right m bits of k, which can be used to increase speed.
 *
 * Other Improvements
 * when keys get removed, N needs to shrink down.
 * we need to redistribute the keys when double the Set size, since M changed. Then, using consistent hashing can help
 * reduce the number of the keys to be redistributed; but it will be an overkill for this problem.
 * Complexity
 * Time complexity:
 * Amortized O(1)
 *
 * Space complexity:
 * O(N)
 */
class MyHashSet2 {
//    class TreeNode(var value: Int) {
//        var left: TreeNode? = null
//        var right: TreeNode? = null
//    }
//
//    class BST {
//        private val root: TreeNode = TreeNode(Int.MIN_VALUE)
//        val first: TreeNode?
//        get() = root.right
//
//        fun remove(key: Int): Boolean {
//            var p: TreeNode = root
//            var r: TreeNode? = root.right
//            while (r != null) {
//                if (key < r.value) {
//                    p = r
//                    r = r.left
//                } else if (key > r.value) {
//                    p = r
//                    r = r.right
//                } else {
//                    val left = r.left
//                    val right = r.right
//                    val head = if (left != null && right != null) {
//                        /**
//                         * both left and right subtree have children
//                         * all nodes in the right subtree >= the left subtree
//                         * therefore, the left subtree can be the left of the smallest node of the right subtree
//                         * this is an easy way but not a good way since it disturb the BST a lot, but it is OK for this problem
//                         */
//                        var smallest = right
//                        var s = right.left
//                        while (s?.left != null) { // find the smallest node of the right subtree
//                            smallest = s
//                            s = s.left
//                        }
//                        smallest!!.left = left
//                        right
//                    } else left ?: right
//
//                    if (key < p.value) {
//                        // p's left
//                        p.left = head
//                    } else {
//                        p.right = head
//                    }
//                    return true
//                }
//            }
//            return false
//        }
//
//        fun add(key: Int): Boolean {
//            var r = root
//            while (true) {
//                if (key < r.value) {
//                    if (r.left == null) {
//                        r.left = TreeNode(key)
//                        return true
//                    }
//                    r = r.left!!
//                } else if (key > r.value) {
//                    if (r.right == null) {
//                        r.right = TreeNode(key)
//                        return true
//                    }
//                    r = r.right!!
//                } else {
//                    return false
//                }
//            }
//        }
//
//        fun contains(key: Int): Boolean {
//            var r: TreeNode? = root
//            while (r != null) {
//                r = if (key < r!!.value) {
//                    r!!.left
//                } else if (key > r!!.value) {
//                    r!!.right
//                } else {
//                    return true
//                }
//            }
//            return false
//        }
//    }
//
//
//    private var count = 0
//    private var m = 4
//    private var arr = Array(1.shl(m)) { BST() }
//    private val loadFactor = 0.75
//
//    private fun double() {
//        val old = arr
//        arr = Array(1.shl(++m)) { BST() }
//        fun traverse(root: TreeNode?) {
//            if (root == null) return
//                    val key = root.value
//            arr[index(key)].add(key)
//            traverse(root.left)
//            traverse(root.right)
//        }
//        for (bst in old) {
//            traverse(bst.first)
//        }
//    }
//
//    private fun index(key: Int): Int {
//        return key and (1.shl(m) - 1)
//    }
//
//    fun add(key: Int) {
//        if (arr[index(key)].add(key)) count++
//        if (count.toDouble() / arr.size.toDouble() > loadFactor) double()
//    }
//
//    fun remove(key: Int) {
//        if (arr[index(key)].remove(key)) count--
//    }
//
//    fun contains(key: Int): Boolean {
//        return arr[index(key)].contains(key)
//
//    }


}
