package leetcode.design.product;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

// // https://leetcode.com/problems/encode-and-decode-tinyurl/

public class TinyUrlHuffmanEncoding {

    HashMap<Character, String> huffmanTable;
    Node root;

    //Huffman Tree Node
    static class Node {
        char c;
        int val;
        Node left, right;

        Node(char c, int val) {
            this.c = c;
            this.val = val;
            this.left = null;
            this.right = null;
        }
        Node(char c, int val, Node left, Node right) {
            this.c = c;
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    void createHuffmanTable(Node node, String encoding) {
        if(node.left == null && node.right == null) {
            huffmanTable.put(node.c, encoding);
            // System.out.println(node.c + " ~ " + encoding + " ~ " + f.get(node.c)); // Prints all character's encoding and frequency
            return;
        }

        createHuffmanTable(node.left, encoding + "0");
        createHuffmanTable(node.right, encoding + "1");
    }

    // Encodes a URL to a shortened URL.
    public String encode(String longUrl) {
        int len = longUrl.length();

        huffmanTable = new HashMap<>(); // HashMap containing each unique character and its Huffman encoding ('a' -> '000', 'b' -> '110' etc.)
        HashMap<Character,Integer> f = new HashMap<>(); // HashMap containing each unique character and its frequency ('a' -> 2, 'b' -> 5 etc.)

        // Filling HashMap with each unique character and its frequency in longUrl
        for(int i = 0 ; i < len; i++) {
            char c = longUrl.charAt(i);
            if(!f.containsKey(c)) {
                f.put(c, 0);  // Create an entry for new unique character found in longUrl
            }
            f.put(c, f.get(c) + 1);  // Increase its frequency by 1
        }

        PriorityQueue<Node> pq = new PriorityQueue<>(len, (a, b) -> a.val - b.val); // For building Huffman Tree
        for(Map.Entry<Character, Integer> entry : f.entrySet()) {
            pq.add(new Node(entry.getKey(), entry.getValue())); // Add all unique characters present in url to priority queue (based on their frequency)
        }

        root = null; // Root of HuffmanTree

        // We run this till we have only one node left in priority queue and this remaining last node will be the root of our Huffman tree
        while(pq.size() > 1) {
            Node a = pq.poll(); // 1st Minimum frequency character in Priority Queue
            Node b = pq.poll(); // 2nd Minimum frequency character in Priority Queue
            Node node = new Node('\u0000', a.val + b.val, a, b); // Create a new Node with frequency as 'sum of a's frequency and b's frequency'
            root = node;
            pq.add(node);       // Push newly created node back into priority queue
        }

        createHuffmanTable(root, ""); // Filling huffmanTable to see our encodings

        StringBuilder url = new StringBuilder("http://tinyurl.com/");
        for(int i = 0 ; i < len ; i++) {
            char c = longUrl.charAt(i);
            url.append(huffmanTable.get(c));
        }

        return url.toString();
    }

    // Decodes a shortened URL to its original URL.
    public String decode(String shortUrl) {
        int len = shortUrl.length();
        StringBuilder url = new StringBuilder();

        // {0..18} is "http://tinyurl.com/" so ignore it during decoding
        for(int i = 19 ; i < len ;) {
            Node node = root; // Always start at root of Huffman tree

            // Only leaf nodes have valid characters rest are '\u0000'
            while(node.left != null && node.right != null) {
                // If '0', go left in Huffman tree else go right
                if(shortUrl.charAt(i) == '0') {
                    node = node.left;
                } else {
                    node = node.right;
                }
                i++;
            }

            // Append leaf node's character to the url
            url.append(node.c);
        }
        return url.toString();
    }
}